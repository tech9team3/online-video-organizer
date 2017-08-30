(function () {
    angular.module("app")
            .service('RateService', RateService);

    RateService.$inject = ['$http', '$q'];

    function RateService($http, $q) {
    
        
         this.saveRate = function (rate) {
            var def = $q.defer();
            var req = {
                method: rate.id ? 'PUT': 'POST',
                url: "rate",
                data: rate
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