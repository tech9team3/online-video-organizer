(function () {
    angular.module('app')
            .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
     
            .when('/home', {
                templateUrl: '/views/home.html',
               //controller: 'LoginRegisterController',
              // controllerAs: 'loginRegisterCtrl'
            })
            .otherwise('/');
            }
}());
