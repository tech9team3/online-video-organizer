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
         
       
    };
}());