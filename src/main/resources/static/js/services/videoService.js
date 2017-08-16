(function () {
    angular.module("app")
            .service('VideoService', VideoService);

    VideoService.$inject = ['$http', '$q'];

    function VideoService($http, $q) {
        this.getVideos = function () {
//            var base64Credential = btoa('pera' + ':' + 'para@1234');
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videos"
//                	,
//                headers: {
//                    'Authorization': 'Basic ' + base64Credential
//                }
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
    };
}());