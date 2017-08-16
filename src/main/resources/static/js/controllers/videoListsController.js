(function () {
    angular.module('app')
            .controller('VideoListsController', VideoListsController);

    VideoListsController.$inject = ['$location', '$http', '$route', 'VideoListsService', 'VideoService'];

    function VideoListsController($location, $http, $route, VideoListsService, VideoService) {
    	
        var videoListsCtrl = this;
        videoListsCtrl.addVideoLists = addVideoLists;
      //  vm.deleteCategory = deleteCategory;
      //  vm.editCategory = editCategory;
        videoListsCtrl.saveVideoLists = saveVideoLists;
      //  vm.selectCategory = selectCategory;
      //  vm.operation;
        videoListsCtrl.getVideosByVideoList = getVideosByVideoList;
       
        videoListsCtrl.videoLists={};
        videoListsCtrl.videos;
        getVideoLists();
        
        function addVideoLists() {
        	 videoListsCtrl.addVideoListsForm;
        	 videoListsCtrl.videoLists = {};
        }
        
        function saveVideoLists(videoList){
        	
        	VideoListsService.saveVideoList(videoList).then(function(response){
                getVideoLists();
                $('#add-videoLists-modal').modal('hide');
            }, function(error){

            })
            videoListsCtrl.addVideoListsForm.$setPristine();
        }
        
        function getVideoLists(){
        	VideoListsService.getVideoLists().then(handleSuccessVideoLists);
        }
        
       
        function handleSuccessVideoLists(response, status){
        	videoListsCtrl.videoLists = response.data;
        }
        
        function getVideosByVideoList(videoListId){
        	VideoService.getVideosByVideoListId(videoListId).then(function(response){
        		videoListsCtrl.videos = response.data;
        	})
        }
    }
    
})();