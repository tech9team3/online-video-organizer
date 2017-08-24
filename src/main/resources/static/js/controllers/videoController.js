(function () {
    angular.module('app')
        .controller('VideoController', VideoController)
        .config(function($sceProvider){
        $sceProvider.enabled(false);
    });

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce', 'CommentService', 'UserService', 'TagService'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce, CommentService, UserService, TagService) {
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
                getTagsByVideoId(videoId);
                console.log(videoCtrl.video);
            })
        }

        function getCommentsForVideo(videoId) {
            CommentService.getCommentsForVideo(videoId).then(function (response) {
                videoCtrl.comments = response.data;
            })
        }

        function postComment() {
        	videoCtrl.comment.user = UserService.getLoggedInUser();
        	delete videoCtrl.comment.user.roles;
        	videoCtrl.comment.video = videoCtrl.video;
     	    CommentService.saveComment(videoCtrl.comment).then(function (response) {
     	    	 getCommentsForVideo(videoId); 
          });
     	   delete videoCtrl.comment;
        }
        
        function getTagsByVideoId(videoId) {
            TagService.getTagsByVideoId(videoId).then(function(response){
                videoCtrl.video.videoTags = response.data;                
            })
        }
  
    }

})();
