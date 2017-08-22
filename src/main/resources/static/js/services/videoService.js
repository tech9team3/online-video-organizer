(function () {
    angular.module("app")
            .service('VideoService', VideoService);

    VideoService.$inject = ['$http', '$q'];

    function VideoService($http, $q) {
        this.getVideos = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos"
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get videos");
            });
        }
        
        this.getPublicVideos = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos/search/visible"
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get videos");
            });
        }
        
        this.getVideosByVideoListId = function (videoListId) {
          var def = $q.defer();
          var req = {
              method: 'GET',
              url: "videos/search/videos/byVideoList/" + videoListId,
       
          }
          return $http(req).success(function (response) {
              return response;
          }).error(function () {
              return def.reject("Failed to get videos");
          });
      }
        
        this.getPublicVideosByVideoListId = function (videoListId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos/search/public/videoListId/" + videoListId,
         
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get videos");
            });
        }
    };
}());