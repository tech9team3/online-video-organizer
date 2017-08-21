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
            .when('/login', {
                templateUrl: '/views/loginRegister.html',
                controller: 'LoginRegisterController',
                controllerAs: 'loginRegisterCtrl'
            })
            .when('/home', {
                templateUrl: '/views/home.html',
                controller: 'HomeController',
                controllerAs: 'homeCtrl'
            })
            .when('/video/:videoId', {
                templateUrl: '/views/video.html',
                controller: 'VideoController',
                controllerAs: 'videoCtrl'
            })
            .when('/playlists', {
                templateUrl: '/views/playlists.html',
                controller: 'VideoListsController',
                controllerAs: 'videoListsCtrl'
            })
            .otherwise('/');
            }
}());
