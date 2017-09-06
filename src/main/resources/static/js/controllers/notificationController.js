(function () {
    angular.module('app')
        .controller('NotificationController', NotificationController);

    NotificationController.$inject = ['$location', '$http', '$route', 'UserService', 'NotificationService'];

    function NotificationController($location, $http, $route, UserService, NotificationService) {
        var notificationCtrl = this;
        notificationCtrl.showNotification = showNotification;
        
        NotificationService.getNotificationsByUserId(UserService.getLoggedInUserId()).then(function(response) {
            notificationCtrl.notifications = response.data;
        });
        
        function showNotification(notification,  getNewNotifications) {
            NotificationService.saveNotification(notification).then(function() {
                getNewNotifications();
            });
        }
    }
})();
