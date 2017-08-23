(function () {
    angular.module('app')
            .controller('VideoController', VideoController);

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce', 'CommentService', 'UserService'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce, CommentService, UserService) {
        var videoCtrl = this;

        videoCtrl.getCommentsForVideo = getCommentsForVideo;
        videoCtrl.postComment = postComment;
        var videoId = parseInt($routeParams.videoId);

        init();

        function init() {
            getVideoByVideoId(videoId);
            getCommentsForVideo(videoId);
        }

        function getVideoByVideoId(videoId) {
            VideoService.getVideoByVideoId(videoId).then(function(response){
                videoCtrl.video = response.data;
            })
        }

        function getCommentsForVideo(videoId) {
            CommentService.getCommentsForVideo(videoId).then(function (response) {
                videoCtrl.comments = response.data;
            })
        }

        function postComment(comment) {
     	   videoCtrl.comment = {content:comment.content, user:{id: UserService.getLoggedInUserId()}, video:{id:videoId}};
     	    CommentService.saveComment(videoCtrl.comment).then(function (response) {
     	    	 console.log(response);
     	    	 getCommentsForVideo(videoId); 
          });
        }
  
    }

})();
