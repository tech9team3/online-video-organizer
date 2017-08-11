(function () {
    angular.module('app')
            .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/views/home.html',
                controller: 'HomeController',
                controllerAs: 'homeCtrl'
            })
            .when('/loginRegister', {
                templateUrl: '/views/loginRegister.html',
                controller: 'LoginRegisterController',
                controllerAs: 'loginRegisterCtrl'
            .otherwise('/');
    }
}());
