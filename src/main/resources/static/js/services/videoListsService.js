(function () {
    angular.module("app")
        .service('VideoListsService', VideoListsService);

    VideoListsService.$inject = ['$http', '$q'];

    function VideoListsService($http, $q) {

        this.getVideoLists = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videoLists",

            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get video lists");
            });
        }
        
        this.getVideoListsByUserId = function (userId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videoLists/search/userId/" + userId,

            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get video lists");
            });
        }

        this.saveVideoList = function (videoList) {
            var def = $q.defer();
            var req = {
                method: videoList.id ? 'PUT' : 'POST',
                url: "videoLists",
                data: videoList

            }
            return $http(req).success(function (response) {
                console.log(response);
                return response;
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

        this.deleteVideoList = function (id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "videoLists/" + id
            }
            $http(req).success(function (data) {
                def.resolve(data);
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }
    };
}());
