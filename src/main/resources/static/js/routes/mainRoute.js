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
            .when('/admin', {
                templateUrl: '/views/admin.html',
                controller: 'AdminController',
                controllerAs: 'adminCtrl'
            })
            .when('/activate/:userId', {
                templateUrl: '/views/activate.html',
                controller: 'ActivateController',
                controllerAs: 'activateCtrl'
            })
            .when('/notifications', {
                templateUrl: '/views/notifications.html',
                controller: 'NotificationController',
                controllerAs: 'notificationCtrl'
            })
            .when('/reports', {
                templateUrl: '/views/reports.html',
                controller: 'ReportController',
                controllerAs: 'reportCtrl'
            })
            .otherwise('/');
    }
}());
