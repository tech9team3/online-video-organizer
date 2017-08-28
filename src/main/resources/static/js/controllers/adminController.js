(function () {
    angular.module('app')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$location', '$http', '$route', 'VideoService', 'VideoListsService', 'UserService', 'CommentService'];

    function AdminController($location, $http, $route, VideoService, VideoListsService, UserService, CommentService) {
        var adminCtrl = this;
        adminCtrl.getVideoListsByUser = getVideoListsByUser;
        adminCtrl.getVideosByVideoList = getVideosByVideoList;
        adminCtrl.getCommentsForVideo = getCommentsForVideo;
        adminCtrl.deleteUser = deleteUser;
        adminCtrl.selectUser = selectUser;
        adminCtrl.editUser = editUser;
        adminCtrl.saveUser = saveUser;
        adminCtrl.operation;
        adminCtrl.clearAll = clearAll;
        adminCtrl.deleteComment = deleteComment;
        adminCtrl.selectVideoList = selectVideoList;
        adminCtrl.deleteVideoList = deleteVideoList;
        adminCtrl.selectVideo = selectVideo;
        adminCtrl.deleteVideo = deleteVideo;
        adminCtrl.editVideoList = editVideoList;
        adminCtrl.saveVideoList = saveVideoList;
        adminCtrl.editVideo = editVideo;
        adminCtrl.saveVideo = saveVideo;


        getUsers();


        function getUsers() {
            UserService.getUsers().then(function (response) {
                adminCtrl.users = response.data;
            });
        }

        function getVideoListsByUser(user) {
            VideoListsService.getVideoListsByUserId(user.id).then(function (response) {
                user.videoLists = response.data;
            });
        }

        function getVideosByVideoList(videoList) {
            VideoService.getVideosByVideoListId(videoList.id).then(function (response) {
                videoList.videos = response.data;
            });
        }

        function getCommentsForVideo(video) {
            CommentService.getCommentsForVideo(video.id).then(function (response) {
                video.comments = response.data;
            });
        }

        function deleteUser(userId) {
            UserService.deleteUser(adminCtrl.user.id).then(function (response) {
                getUsers();
            }, function (error) {

            });
            //  vm.user= {};
        }

        function selectUser(user) {
            adminCtrl.user = user;
        }

        function deleteVideoList(videoListId, user) {
            VideoListsService.deleteVideoList(videoListId).then(function (response) {
                getVideoListsByUser(user);
            }, function (error) {

            });
            // adminCtrl.videoList = {};
        }

        function selectVideoList(videoList, user) {
            adminCtrl.videoList = videoList;
            adminCtrl.user = user;
        }

        function deleteVideo(videoId, videoList) {
            VideoService.deleteVideo(videoId).then(function (response) {
                getVideosByVideoList(videoList)
            }, function (error) {

            });

        }

        function selectVideo(video, videoList) {
            adminCtrl.video = video;
            adminCtrl.videoList = videoList;
        }

        function editUser(user) {
            adminCtrl.user = user;
            delete adminCtrl.error;
            adminCtrl.operation = "Edit";
            adminCtrl.user = angular.copy(user);
        }

        function saveUser(user) {
            user.roles = [{
                "id": 1,
                "type": "ROLE_USER"
            }];
            UserService.saveUser(user).then(function (response) {
                getUsers();
                $('#add-user-modal').modal('hide');
            }, function (error) {
                adminCtrl.error = {};
                angular.forEach(error.data.exceptions, function (e) {
                    errorHandler(e);
                });
            })
            //remove input value after submit
            adminCtrl.adminUserForm.$setPristine();
            delete adminCtrl.error;
        }
        
        function editVideoList(videoList, user) {
            adminCtrl.videoList = angular.copy(videoList);
            adminCtrl.user = user;
        }

        function saveVideoList(videoList, user) {
            adminCtrl.user = user;
            VideoListsService.saveVideoList(videoList).then(function (response) {
            	  getVideoListsByUser(adminCtrl.user);
                $('#edit-lists-modal').modal('hide');
            }, function (error) {

            })
        }
          
        function editVideo(video, videoList, user) {
            adminCtrl.video = angular.copy(video);
            adminCtrl.videoList = videoList;
            adminCtrl.user = user;
          
        }
        
        function saveVideo(video, videoList, user) {
            if (!video.videoTag) {
                video.videoTag = [];
            }
            video.videoList = videoList;
            video.user = user;   
            console.log(video);
            VideoService.saveVideo(video).then(function (response) {
                console.log(response.data);
                getVideosByVideoList(videoList)
            });
            $('#edit-video-modal').modal('hide');
        }

        function capitalize(error) {
            return '* ' + error[0].toUpperCase() + error.slice(1);
        }

        function errorHandler(error) {
            switch (error.field) {
                case 'username':
                    adminCtrl.error.username = error.message;
                    break;
                case 'email':
                    adminCtrl.error.email = error.message;
                    break;
            }

        }

        function clearAll() {
            adminCtrl.searchByUsername = "";
            adminCtrl.searchByVideolistTitle = "";
            adminCtrl.searchByVideoTitle = "";

        }

        function deleteComment(commentId, video) {
            CommentService.deleteComment(commentId).then(function (response) {
                getCommentsForVideo(video);
            });
            //videoCtrl.comment= {};
        }
    }
})();
