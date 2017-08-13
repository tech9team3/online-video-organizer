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
            .when('/home', {
                templateUrl: '/views/home.html',
                controller: 'HomeController',
                controllerAs: 'homeCtrl'
            })
            .when('/login', {
                templateUrl: '/views/loginRegister.html',
                controller: 'MainController',
                controllerAs: 'mainCtrl'
            })
            .otherwise('/');
            }
}());
