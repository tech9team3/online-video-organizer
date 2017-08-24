(function () {
    angular.module("app")
            .service('TagService', TagService);

    TagService.$inject = ['$http', '$q'];

    function TagService($http, $q) {            
        this.getTagsByVideoId = function (videoId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "videoTags/tagsForVideo/" + videoId
            }
            return $http(req).success(function (response) {
                return response;
            }).error(function () {
                return def.reject("Failed to get tags");
            });
        }
    };
}());