(function () {
    angular.module('app')
        .controller('MainController', MainController);

    MainController.$inject = ['$location', '$http', '$route', 'UserService', 'NotificationService', 'vcRecaptchaService', '$scope', '$stomp', '$log', 'growl', 'ReportService'];

    function MainController($location, $http, $route, UserService, NotificationService, vcRecaptchaService, $scope, $stomp, $log, growl, ReportService) {

        var mainCtrl = this;
        mainCtrl.isActive = isActive;
        mainCtrl.register = register;
        mainCtrl.login = login;
        mainCtrl.logout = logout;
        mainCtrl.toggleLoginRegister = toggleLoginRegister;
        mainCtrl.closeRegistrationConfirmation = closeRegistrationConfirmation;
        mainCtrl.loginOrRegister = "login";
        mainCtrl.user;
        mainCtrl.loginError;
        mainCtrl.registrationError;
        mainCtrl.registrationMessage;
        mainCtrl.loginUserForm;
        mainCtrl.registerUserForm;
        mainCtrl.payload;
        mainCtrl.getNewNotifications = getNewNotifications;
        mainCtrl.showNotification = showNotification;
        mainCtrl.getNewReports = getNewReports;
        mainCtrl.showReport = showReport;
        mainCtrl.setRecaptchaId = setRecaptchaId;
        mainCtrl.clearForm = clearForm;

        //reCaptcha
        mainCtrl.publicKey = "6LceCy0UAAAAALAVMh0eYQnnlXsyvWkksQYayaCN";


        init();

        function init() {
            if (localStorage.getItem("base64Credential")) {
                login(localStorage.getItem("base64Credential"));
                mainCtrl.credentials = {
                    autologin: false
                };
            }
        }

        function enableNotifications() {
            connectToWebSocketNotification();
            getNewNotifications();
            getNewReports();
            getNotifications();
            getReports();
        }

        function connectToWebSocketNotification() {
            var socket = new SockJS('/stompwebsocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                stompClient.subscribe('/queue/private.messages/' + UserService.getLoggedInUser().username, function (retVal) {
                    getNewNotifications();
                    getNewReports();
                    getNotifications();
                    getReports();
                    growl.info(retVal.body);
                });
            });
        }

        function getNewNotifications() {
            NotificationService.getNewNotificationsByUserId(UserService.getLoggedInUserId()).then(function (response) {
                mainCtrl.newNotifications = response.data;
                mainCtrl.notificationCount = angular.copy(response.data.length);
            });
        }

        function getNotifications() {
            NotificationService.getNotificationsByUserId(UserService.getLoggedInUserId()).then(function (response) {
                mainCtrl.notifications = response.data;
            });
        }

        function showNotification(notification) {
            NotificationService.saveNotification(notification).then(function () {
                getNewNotifications();
            });
        }

        function getNewReports() {
            NotificationService.getNewReports().then(function (response) {
                mainCtrl.newReports = response.data;
                mainCtrl.reportCount = angular.copy(response.data.length);
            });
        }

        function getReports() {
            NotificationService.getReports().then(function (response) {
                mainCtrl.reports = response.data;
            });
        }

        function showReport(report) {
            NotificationService.saveNotification(report).then(function () {
                getNewReports();
            });
        }

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }

        function register(user) {
            var data = {
                'g-recaptcha-response': vcRecaptchaService.getResponse(mainCtrl.recaptchaId)
            }
            UserService.sendCaptcha(data).then(function (response) {
                if (response.data.success) {
                    saveUser(user);
                } else {
                    mainCtrl.registrationMessage = "You are a robot!";
                    $('#registrationModal').modal('show');
                }
            }, function (error) {
                mainCtrl.registrationMessage = "User registration failed!";
                $('#registrationModal').modal('show');
            })
        }

        function saveUser(user) {
            user.status = true;
            user.roles = [{
                "id": 1,
                "type": "ROLE_USER"
            }];
            UserService.saveUser(user).then(function (response) {
                //mainCtrl.loginOrRegister = "login";
                mainCtrl.registrationMessage = "Please check your email to activate your account!";
                delete mainCtrl.registerInput;
                delete mainCtrl.registrationError;
                mainCtrl.registerUserForm.$setPristine();
                grecaptcha.reset();
                $('#registrationModal').modal('show');
            }, function (error) {
                mainCtrl.registrationError = {};
                angular.forEach(error.data.exceptions, function (e) {
                    errorHandler(e);
                });
            })
        }

        function login(base64Credential) {
            if (!base64Credential) {
                var base64Credential = btoa(mainCtrl.credentials.username + ':' + mainCtrl.credentials.password);
            }
            $http.get('users/user', {
                headers: {
                    // setting the Authorization Header
                    'Authorization': 'Basic ' + base64Credential
                }
            }).success(function (res) {
                mainCtrl.message = '';
                $http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
                if (mainCtrl.credentials.autologin) {
                    localStorage.setItem("base64Credential", base64Credential);
                }
                mainCtrl.user = angular.copy(res);
                UserService.setLoggedInUser(angular.copy(res));
                enableNotifications();
                angular.element('#login-register-modal').modal('hide');
                //$location.path('playlists');
            }).error(function (error) {
                mainCtrl.loginError = 'Bad credentials!';
            });
        }

        function toggleLoginRegister(showForm) {
            if (showForm == "register") {
                delete mainCtrl.credentials;
                delete mainCtrl.loginError;
                mainCtrl.loginUserForm.$setPristine();
            } else {
                delete mainCtrl.registerInput;
                delete mainCtrl.registrationError;
                mainCtrl.registerUserForm.$setPristine();
            }
            mainCtrl.loginOrRegister = showForm;
            // grecaptcha.reset();
        }

        function closeRegistrationConfirmation() {
            if (mainCtrl.registrationMessage === "Please check your email to activate your account!") {
                angular.element('#login-register-modal').modal('hide');
                grecaptcha.reset();
                mainCtrl.registerUserForm.$setPristine();
                delete mainCtrl.registrationError;
            }
        }

        function setRecaptchaId(widgetId) {
            mainCtrl.recaptchaId = widgetId;
        }

        function clearForm(form) {
            delete mainCtrl.credentials;
            delete mainCtrl.loginError;
            delete mainCtrl.registerInput;
            delete mainCtrl.registrationError;
            form.$setPristine();
            form.$setUntouched();

        }

        function logout() {
            $http.defaults.headers.common['Authorization'] = null;
            UserService.setLoggedInUser(null);
            localStorage.removeItem("base64Credential");
            delete mainCtrl.user;
            delete mainCtrl.error;
            delete mainCtrl.registrationError;
            delete mainCtrl.loginError;
            $location.path('home');
        }

        function errorHandler(error) {
            switch (error.field) {
                case 'username':
                    mainCtrl.registrationError.username = error.message;
                    grecaptcha.reset();
                    break;
                case 'email':
                    mainCtrl.registrationError.email = error.message;
                    grecaptcha.reset();
                    break;
            }
        }

    }

})();
