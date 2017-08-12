(function () {
    angular.module('app')
            .controller('MainController', MainController);

    MainController.$inject = ['$location', '$http', '$route', 'UserService', 'vcRecaptchaService'];

    function MainController($location, $http, $route, UserService, vcRecaptchaService) {

        var self = this;
        self.isActive = isActive;
        self.register = register;
        self.login = login;
        self.logout = logout;
        self.toggleLoginRegister = toggleLoginRegister;
        self.closeRegistrationConfirmation = closeRegistrationConfirmation;
        self.loginOrRegister = "login";
        self.user;
        self.loginError;
        self.registrationError;
        self.registrationMessage;
        self.loginUserForm;
        self.registerUserForm;
        
        //reCaptcha
        self.publicKey = "6LdBOCsUAAAAAApZH8xQDF78JM5e-4bFMdY1LYaK";     

        
        
        
        
        init();

        function init() {
            if (self.user) {
                $route.reload();
                self.loginUserForm.$setPristine(); 
            }
        }        

        //nav-bar
        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }

        function register(user) {
            if(vcRecaptchaService.getResponse() === ""){ //if string is empty
                self.registrationMessage = "Please resolve the captcha and submit!"
                $('#registrationModal').modal('show');
            } else {
                var data = {
                    'g-recaptcha-response': vcRecaptchaService.getResponse()  //send g-captcah-reponse to our server        
                }
                UserService.sendCaptcha(data).then(function(response){
                    if(response.data.success){
                        saveUser(user);
                    }
                    else{
                        self.registrationMessage = "You are a robot!";
                        $('#registrationModal').modal('show');
                    }
                }, function(error) {
                    self.registrationMessage = "User registration failed!";
                    $('#registrationModal').modal('show');
                })
            }
        }

        function saveUser(user){
            user.status = true;
        	user.roles = [{"id":1,"type":"ROLE_USER"}];
        	UserService.saveUser(user).then(function(response){
        		//self.loginOrRegister = "login";
                self.registrationMessage = user.username + "  is registered!";
                $('#registrationModal').modal('show');
            }, function(error){
            	self.registrationError = {};
                angular.forEach(error.data.exceptions, function(e){
                    errorHandler(e);
                });
            })
            //remove input value after submit
            self.registerUserForm.$setPristine();
        }

        function login() {
            // creating base64 encoded String from username and password
            var base64Credential = btoa(self.credentials.username + ':' + self.credentials.password);

            // calling GET request for getting the user details
            $http.get('users/user', {
                headers: {
                    // setting the Authorization Header
                    'Authorization': 'Basic ' + base64Credential
                }
            }).success(function (res) {
                self.credentials.password = null;
                self.message = '';
                // setting the same header value for all request calling from this app
                $http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
                self.user = res;          
                UserService.getLoggedInUserByUsername(self.user.username).then(function(response){
                	if(!response.data.status)
                		logout();
                	else{
                      init();
                       delete self.loginError;
                    }
                });
            }).error(function (error) {
                self.loginError = 'Bad credentials!';
            });
        }

        function toggleLoginRegister(showForm) {
            if(showForm == "register") {
                self.loginUserForm.$setPristine();
                delete self.credentials;
                delete self.loginError;
            }
            else {
                self.registerUserForm.$setPristine();
                delete self.registerInput;
                delete self.registrationError;
            }
            self.loginOrRegister = showForm;
           // grecaptcha.reset();
        }

        function closeRegistrationConfirmation() {
            if(self.registrationMessage.includes("is registered!")) {
            	grecaptcha.reset();
            	self.registerUserForm.$setPristine();
                delete self.registrationError;
            	self.loginOrRegister = "login";
            }
        }

        // method for logout
        function logout() {
            // clearing the authorization header
            $http.defaults.headers.common['Authorization'] = null;
            // clearing all data
            delete self.user;
            delete self.error;
            delete self.registrationError;
            delete self.loginError;
        }
        
        function errorHandler(error){
            switch(error.field){
                case 'username':
                    self.registrationError.username = error.message;
                    grecaptcha.reset();
                    break;
                case 'email':
                    self.registrationError.email = error.message;
                    grecaptcha.reset();                    
                    break;
            }
        }
        
    }

})();
