(function () {
    angular.module('app')
            .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', '$http', '$route', 'VideoService', 'VideoListsService'];

    function HomeController($location, $http, $route, VideoService, VideoListsService) {
        var homeCtrl = this;
        homeCtrl.orderBy = orderBy;
        homeCtrl.order = 'id';
        homeCtrl.reverseOrder = false;
        homeCtrl.ratesAndCommentsComparatro = ratesAndCommentsComparatro;
        homeCtrl.getVideoListsByVisible = getVideoListsByVisible();
        homeCtrl.getPublicVideosByVideoListId  = getPublicVideosByVideoListId ;
        homeCtrl.getPublicVideos = getPublicVideos;
        
        getPublicVideos();

        function getPublicVideos() {
	        VideoService.getPublicVideos().then(function(response){
	            homeCtrl.publicVideos = response.data;
	        })
        }

        function orderBy(order) {
            homeCtrl.reverseOrder = (homeCtrl.order === order) ? !homeCtrl.reverseOrder : false;
            homeCtrl.order = order;
        }

        function ratesAndCommentsComparatro(v1, v2){
            
        }
        
        function getVideoListsByVisible() {
            VideoListsService.getVideoListsByVisible().then(function (response) {
            	homeCtrl.publicVideoLists = response.data;
            })
        }
        
        function getPublicVideosByVideoListId (videoListId) {
            VideoService.getPublicVideosByVideoListId(videoListId).then(function (response) {
                homeCtrl.publicVideos = response.data;
            })
        }
    }
})();
