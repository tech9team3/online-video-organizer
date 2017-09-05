(function () {
    angular.module('app')
        .controller('ReportController', ReportController);

    ReportController.$inject = ['$location', '$http', '$route', 'UserService', 'ReportService'];

    function ReportController($location, $http, $route, UserService, ReportService) {
        var reportCtrl = this;
        reportCtrl.showReport = showReport;

        ReportService.getReports().then(function (response) {
            reportCtrl.reports = response.data;
        });

        function showReport(report) {
            ReportService.saveReport(report).then(function () {

            });
        }
    }
})();
