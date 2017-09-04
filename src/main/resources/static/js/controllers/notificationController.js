(function () {
    angular.module('app')
        .controller('NotificationController', NotificationController);

    NotificationController.$inject = ['$location', '$http', '$route', 'UserService'];

    function NotificationController($location, $http, $route, UserService) {
        var notificationCtrl = this;

        

    }
})();
