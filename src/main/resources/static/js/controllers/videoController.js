(function () {
    angular.module('app')
            .controller('VideoController', VideoController);

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce) {
        var videoCtrl = this;

        init();

         function init() {
            videoCtrl.embeddedVideoUrl = $sce.trustAsResourceUrl("//www.youtube.com/embed/" + $routeParams.videoId);
        }      
    }

})();
