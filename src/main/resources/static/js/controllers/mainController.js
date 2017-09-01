(function () {
    angular.module('app')
        .controller('MainController', MainController);

    MainController.$inject = ['$location', '$http', '$route', 'UserService', 'NotificationService', 'vcRecaptchaService', '$scope', '$stomp', '$log', 'growl'];

    function MainController($location, $http, $route, UserService, NotificationService, vcRecaptchaService, $scope, $stomp, $log, growl) {

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
        mainCtrl.getNotifications = getNotifications;
        mainCtrl.showNotification = showNotification;

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
            if (mainCtrl.user) {
                $route.reload();
                mainCtrl.loginUserForm.$setPristine();
            }
        }

        function enableNotifications() {
            connectToWebSocketNotification();
            NotificationService.getNotificationsByUserId(UserService.getLoggedInUserId()).then(function (response) {
                mainCtrl.notificationCount = angular.copy(response.data.length);
            });

        }

        function connectToWebSocketNotification() {
            var socket = new SockJS('/stompwebsocket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                stompClient.subscribe('/queue/private.messages/' + UserService.getLoggedInUser().username, function (retVal) {
                    NotificationService.getNotificationsByUserId(UserService.getLoggedInUserId()).then(function (response) {
                        mainCtrl.notificationCount += 1;
                        growl.success(retVal.body);
                    });

                });
            });
        }

        function getNotifications() {
            NotificationService.getNotificationsByUserId(UserService.getLoggedInUserId()).then(function (response) {
                mainCtrl.notifications = response.data;
            });
        }
        
        function showNotification(notification) {
            console.log(notification);
        }


        //nav-bar
        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }

        function register(user) {
            var data = {
                'g-recaptcha-response': vcRecaptchaService.getResponse() //send g-captcah-reponse to our server        
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
                $('#registrationModal').modal('show');
            }, function (error) {
                mainCtrl.registrationError = {};
                angular.forEach(error.data.exceptions, function (e) {
                    errorHandler(e);
                });
            })
            //remove input value after submit
            mainCtrl.registerUserForm.$setPristine();
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
                mainCtrl.loginUserForm.$setPristine();
                delete mainCtrl.credentials;
                delete mainCtrl.loginError;
            } else {
                mainCtrl.registerUserForm.$setPristine();
                delete mainCtrl.registerInput;
                delete mainCtrl.registrationError;
            }
            mainCtrl.loginOrRegister = showForm;
            // grecaptcha.reset();
        }

        function closeRegistrationConfirmation() {
            if (mainCtrl.registrationMessage.includes("is registered!")) {
                grecaptcha.reset();
                mainCtrl.registerUserForm.$setPristine();
                delete mainCtrl.registrationError;
                mainCtrl.loginOrRegister = "login";
            }
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
