(function () {
    angular.module('app')
        .controller('ReportController', ReportController);

    ReportController.$inject = ['$location', '$http', '$route', 'UserService', 'NotificationService'];

    function ReportController($location, $http, $route, UserService, NotificationService) {
        var reportCtrl = this;
        reportCtrl.showReport = showReport;

        NotificationService.getReports().then(function (response) {
            reportCtrl.reports = response.data;
        });

        function showReport(report, getNewReports) {
            NotificationService.saveNotification(report).then(function() {
                getNewReports();
            });

        }
    }
})();
