(function () {
    angular.module('app')
        .controller('VideoController', VideoController)
        .config(function ($sceProvider) {
            $sceProvider.enabled(false);
        });

    VideoController.$inject = ['$location', '$http', '$route', 'VideoService', '$routeParams', '$sce', 'CommentService', 'UserService', 'TagService', 'RateService'];

    function VideoController($location, $http, $route, VideoService, $routeParams, $sce, CommentService, UserService, TagService, RateService) {
        var videoCtrl = this;

        videoCtrl.getCommentsForVideo = getCommentsForVideo;
        videoCtrl.postComment = postComment;
        videoCtrl.deleteComment = deleteComment;
        videoCtrl.saveRate = saveRate;
        var videoId = parseInt($routeParams.videoId);
        videoCtrl.spin = false;

        videoCtrl.loggedInUser = UserService.getLoggedInUser();

        init();

        function init() {
            getVideoByVideoId(videoId);
            getCommentsForVideo(videoId);
            if (UserService.getLoggedInUser()){
            getRateByVideoAndUser();
            getAverageRateForVideo();
            }
        }

        function getVideoByVideoId(videoId) {
            VideoService.getVideoByVideoId(videoId).then(function (response) {
                videoCtrl.video = response.data;
            })
        }

        function getCommentsForVideo(videoId) {
            CommentService.getCommentsForVideo(videoId).then(function (response) {
                videoCtrl.comments = response.data;
            })
        }

        function postComment() {
            if (!UserService.getLoggedInUser()) {
                $('#login-register-modal').modal('show');
            } else {
                videoCtrl.spin = true;
                videoCtrl.comment.user = UserService.getLoggedInUser();
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

        function deleteComment(commentId) {
            CommentService.deleteComment(commentId).then(function (response) {
                getCommentsForVideo(videoId);
            });
            //videoCtrl.comment= {};
        }
        
        function saveRate() {
        	 if (!UserService.getLoggedInUser()) {
                 $('#login-register-modal').modal('show');
        	 } else {
	            videoCtrl.rate.user = UserService.getLoggedInUser();
	            delete videoCtrl.rate.user.roles;
	            videoCtrl.rate.video = videoCtrl.video;
	            RateService.saveRate(videoCtrl.rate).then(function (response) {
	            	 getAverageRateForVideo();
	            });           
        	}
        }
        
        function getRateByVideoAndUser() {
            RateService.getRateByVideoAndUser(videoId, UserService.getLoggedInUser().username).then(function (response) {
            	videoCtrl.rate = response.data;
            });        	
        }
        
        function getAverageRateForVideo() {
            RateService.getAverageRateForVideo(videoId).then(function (response) {
               videoCtrl.rateModel=response.data;
            });        	
        }
        
        }
        
          
    

})();
