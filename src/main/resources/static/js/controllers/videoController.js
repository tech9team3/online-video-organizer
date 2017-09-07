(function () {
    angular.module('app')
        .controller('VideoController', VideoController)
        .config(function ($sceProvider) {
            $sceProvider.enabled(false);
        });

    VideoController.$inject = ['$location', '$http', '$route', '$window', 'VideoService', '$routeParams', '$sce', 'CommentService', 'UserService', 'TagService', 'RateService', 'ReportService'];

    function VideoController($location, $http, $route, $window, VideoService, $routeParams, $sce, CommentService, UserService, TagService, RateService, ReportService) {
        var videoCtrl = this;

        videoCtrl.getCommentsForVideo = getCommentsForVideo;
        videoCtrl.postComment = postComment;
        videoCtrl.deleteComment = deleteComment;
        videoCtrl.saveRate = saveRate;
        videoCtrl.reportCommentToAdmin = reportCommentToAdmin;
        videoCtrl.selectComment = selectComment;
        var videoId = parseInt($routeParams.videoId);
        videoCtrl.highlitedCommentId = $location.hash();
        videoCtrl.spin = false;

        videoCtrl.loggedInUser = UserService.getLoggedInUser();

        videoCtrl.scrollbarsConfig = {
            axis: 'y',
            autoHideScrollbar: true,
            theme: 'rounded-dots-dark',
            advanced: {
                updateOnContentResize: true
            },
            setHeight: $window.innerHeight - 210,
            scrollInertia: 500,
        }

        init();

        function init() {
            getVideoByVideoId(videoId);
            getCommentsForVideo(videoId);
            if (UserService.getLoggedInUser()) {
                getRateByVideoAndUser();
                getAverageRateForVideo();
            }
        }

        function getVideoByVideoId(videoId) {
            VideoService.getVideoByVideoId(videoId).then(function (response) {
                videoCtrl.video = response.data;
                getVideosByVideoList(videoCtrl.video.videoList.id);
            })
        }

        function getVideosByVideoList(videoListId) {
            if (videoCtrl.video.user.id === UserService.getLoggedInUserId()) {
                VideoService.getVideosByVideoListId(videoListId).then(function (response) {
                    videoCtrl.videos = response.data;
                })
            } else {
                VideoService.getPublicVideosByVideoListId(videoListId).then(function (response) {
                    videoCtrl.videos = response.data;
                })
            }
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
                videoCtrl.rateModel = response.data;
            });
        }

        function reportCommentToAdmin() {
            videoCtrl.report.reportAuthor = UserService.getLoggedInUser();
            delete videoCtrl.report.reportAuthor.roles;
            videoCtrl.report.reportedComment = videoCtrl.commentReport;
            ReportService.reportCommentToAdmin(videoCtrl.report).then(function (response) {
                $('#notifyAdminModal').modal('hide');

            });
            delete videoCtrl.report;
        }

        function selectComment(comment) {
            videoCtrl.commentReport = comment;
        }



    }




})();
