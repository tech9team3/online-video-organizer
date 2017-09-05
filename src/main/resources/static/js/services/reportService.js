(function () {
    angular.module("app")
        .service('ReportService', ReportService);

    ReportService.$inject = ['$http', '$q'];

    function ReportService($http, $q) {


        this.reportCommentToAdmin = function (report) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "reports/commentReport",
                data: report
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

        this.getReports = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "reports"
            }
            return $http(req).success(function (response) {
                return response.data;
            }).error(function () {
                return def.reject("Failed");
            });
        }

        this.getNewReports = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "reports/getNewReports"
            }
            return $http(req).success(function (response) {
                return response.data;
            }).error(function () {
                return def.reject("Failed");
            });
        }

        this.saveReport = function (report) {
            var def = $q.defer();
            var req = {
                method: report.id ? 'PUT' : 'POST',
                url: "reports",
                data: report
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

    };
}());
