(function () {
    angular.module('app')
            .controller('VideoController', VideoController);

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce', 'CommentService'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce, CommentService) {
        var videoCtrl = this;

        init();

         function init() {
            videoCtrl.embeddedVideoUrl = $sce.trustAsResourceUrl("//www.youtube.com/embed/" + $routeParams.videoId);
        }     
         
         function getCommentsForVideo (videoId){
             CommentService.getCommentsForVideo(videoId).then(handleSuccessComments);
         }
         
         function handleSuccessComments(data, status){
             videoCtrl.comment = data.data;
         }
         
       
    }

})();
