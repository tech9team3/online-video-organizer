(function () {
    angular.module('app')
            .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/views/home.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/books', {
                templateUrl: '/views/books.html',
                controller: 'BookController',
                controllerAs: 'vm'
            })
            .when('/categories', {
                templateUrl: '/views/categories.html',
                controller: 'CategoryController',
                controllerAs: 'vm'
            })
=======
                controllerAs: 'homeCtrl'
            })
            .when('/loginRegister', {
                templateUrl: '/views/loginRegister.html',
                controller: 'LoginRegisterController',
                controllerAs: 'loginRegisterCtrl'
>>>>>>> 8db7e348976a38ac46ffa582b16dd5f76138d832
            .otherwise('/');
    }
}());
