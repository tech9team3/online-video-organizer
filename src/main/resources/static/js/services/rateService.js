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
         
         this.getRateByVideoAndUser = function (videoId, username) {
             var def = $q.defer();
             var req = {
                 method: 'GET',
                 url: "rate/search/mark/video/" + videoId + "/user/" + username
             }
             return $http(req).success(function (response) {
                 return response.data;
             }).error(function () {
                 return def.reject("Failed");
             });
         }
         
       
    };
}());