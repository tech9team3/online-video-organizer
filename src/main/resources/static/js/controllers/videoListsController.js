(function () {
    angular.module('app')
        .controller('VideoListsController', VideoListsController);

    VideoListsController.$inject = ['$location', '$http', '$route', 'VideoListsService', 'VideoService', 'UserService'];

    function VideoListsController($location, $http, $route, VideoListsService, VideoService, UserService) {

        var videoListsCtrl = this;
        videoListsCtrl.addVideoList = addVideoList;
        videoListsCtrl.deleteVideoList = deleteVideoList;
        videoListsCtrl.editVideoList = editVideoList;
        videoListsCtrl.saveVideoList = saveVideoList;
        videoListsCtrl.selectVideoList = selectVideoList;
        videoListsCtrl.selectedVideoList = selectedVideoList;
        videoListsCtrl.operation;
        videoListsCtrl.getVideosByVideoList = getVideosByVideoList;
        videoListsCtrl.addVideo = addVideo;


        //delete for production
        $http.defaults.headers.common['Authorization'] = 'Basic ' + btoa('pera:para@1234');
        UserService.setLoggedInUser({
        	id: 1,
        	username: 'pera'
        });


        videoListsCtrl.videoLists = {};
        videoListsCtrl.videos;
        videoListsCtrl.loggedInUser = {
            id: UserService.getLoggedInUserId()
        };
        
        
        getVideoListsByUserId(videoListsCtrl.loggedInUser);


        function addVideoList() {
            videoListsCtrl.operation = "Add";
            videoListsCtrl.videoList = {visible: true};
            videoListsCtrl.addVideoListsForm.$setPristine();
        }

        function editVideoList(videoList) {
            videoListsCtrl.operation = "Edit";
            videoListsCtrl.videoList = angular.copy(videoList);
        }

        function saveVideoList(videoList) {
            videoList.user = videoListsCtrl.loggedInUser;
            VideoListsService.saveVideoList(videoList).then(function (response) {
                getVideoListsByUserId(videoListsCtrl.loggedInUser);
                $('#add-lists-modal').modal('hide');
            }, function (error) {

            })
        }

        function selectVideoList(videoList) {
            videoListsCtrl.videoList = videoList;
        }
        
        function selectedVideoList() {
            return videoListsCtrl.videoList;
        }

        function deleteVideoList() {
            VideoListsService.deleteVideoList(videoListsCtrl.videoList.id).then(function (response) {
                getVideoListsByUserId(videoListsCtrl.loggedInUser);
            }, function (error) {

            });
            videoListsCtrl.videoList = {};
        }


        function getVideoListsByUserId(loggedInUser) {
            VideoListsService.getVideoListsByUserId(loggedInUser.id).then(handleSuccessVideoLists);
        }


        function handleSuccessVideoLists(response, status) {
            videoListsCtrl.videoLists = response.data;
        }

        function getVideosByVideoList(videoListId) {
            VideoService.getVideosByVideoListId(videoListId).then(function (response) {
                videoListsCtrl.videos = response.data;
            })
        }

        function addVideo(video) {
            console.log(video);
        }
    }

})();
