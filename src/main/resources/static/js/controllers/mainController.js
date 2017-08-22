(function () {
    angular.module('app')
            .controller('MainController', MainController);

    MainController.$inject = ['$location', '$http', '$route', 'UserService', '$scope'];

    function MainController($location, $http, $route, UserService, $scope) {

        var mainCtrl = this;
        mainCtrl.logout = logout;
        
        mainCtrl.user = UserService.loggedInUser;   
        
        function logout() {
            // clearing the authorization header
            $http.defaults.headers.common['Authorization'] = null;
            // clearing all data
            UserService.setLoggedInUser(null);
            delete mainCtrl.user; 

        }
    }

})();