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
        videoCtrl.deleteComment = deleteComment;
        var videoId = parseInt($routeParams.videoId);
        videoCtrl.spin = false;
        
        videoCtrl.loggedInUser = UserService.getLoggedInUser();

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
        	if(!videoCtrl.loggedInUser){
        		$('#login-register-modal').modal('show');
        	}
        	else{
        		videoCtrl.spin = true;
        		videoCtrl.comment.user = videoCtrl.loggedInUser;
            	delete videoCtrl.comment.user.roles;
            	videoCtrl.comment.video = videoCtrl.video;
         	    CommentService.saveComment(videoCtrl.comment).then(function (response) {
         	    	 getCommentsForVideo(videoId); 
         	       }).finally(function () {
                            // Always execute this on both error and success
                            videoCtrl.spin = false;
                            delete videoCtrl.comment;
                        });	    	   
        	}
        }
        
        function deleteComment(commentId){
            CommentService.deleteComment(commentId).then(function(response){
            	 getCommentsForVideo(videoId);
                });
            //videoCtrl.comment= {};
        }
        
        function getTagsByVideoId(videoId) {
            TagService.getTagsByVideoId(videoId).then(function(response){
                videoCtrl.video.videoTags = response.data;                
            })
        }
  
    }

})();
