(function () {
    angular.module('app')
            .controller('VideoController', VideoController);

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce', 'CommentService', 'UserService'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce, CommentService, UserService) {
        var videoCtrl = this;
        videoCtrl.getCommentsForVideo=getCommentsForVideo;
        videoCtrl.postComment = postComment;
        var videoId = parseInt($routeParams.videoId);
        
        videoCtrl.loggedInUser = {
                id: UserService.getLoggedInUserId()
            };
        
     

        init();

         function init() {
            videoCtrl.embeddedVideoUrl = $sce.trustAsResourceUrl("//www.youtube.com/embed/" + videoId);
            getCommentsForVideo(videoId);
        }     
         
         function getCommentsForVideo(videoId) {
             CommentService.getCommentsForVideo(videoId).then(function (response) {
                 videoCtrl.comments = response.data;
             })
         }
         
         function postComment(comment) {
        	   comment.user = videoCtrl.loggedInUser;
        	   videoCtrl.comment = {content:comment.content, user:comment.user, video:videoId};
        	    CommentService.saveComment(videoCtrl.comment).then(function (response) {
        	    	 console.log(response);
        	    	 getCommentsForVideo(videoId); 
             }, function (error) {

             })
            // delete videoCtrl.comment;
         }
  
    }

})();
