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
        videoListsCtrl.operation;
        videoListsCtrl.getVideosByVideoList = getVideosByVideoList;
        videoListsCtrl.addVideo = addVideo;

        videoListsCtrl.videoLists = {};
        videoListsCtrl.videos;
        getVideoLists();

        
        //delete for production
        $http.defaults.headers.common['Authorization'] = 'Basic ' + btoa('pera:para@1234');


        function addVideoList() {
            videoListsCtrl.operation = "Add";
            videoListsCtrl.videoList = {};
            videoListsCtrl.videoList.visible = true;
            videoListsCtrl.addVideoListsForm.$setPristine();
        }

        function editVideoList(videoList) {
            console.log(videoList);
            videoListsCtrl.operation = "Edit";
            videoListsCtrl.videoList = angular.copy(videoList);
        }

        function saveVideoList(videoList) {
            videoList.user = {};
            videoList.user.id = UserService.getLoggedInUserId();
            VideoListsService.saveVideoList(videoList).then(function (response) {
                getVideoLists();
                $('#add-lists-modal').modal('hide');
            }, function (error) {

            })
        }

        function selectVideoList(videoList) {
            videoListsCtrl.videoList = videoList;
        }

        function deleteVideoList() {
            VideoListsService.deleteVideoList(videoListsCtrl.videoList.id).then(function (response) {
                getVideoLists();
            }, function (error) {

            });
            videoListsCtrl.videoList = {};
        }


        function getVideoLists() {
            VideoListsService.getVideoLists().then(handleSuccessVideoLists);
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
