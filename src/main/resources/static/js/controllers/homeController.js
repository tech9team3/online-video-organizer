(function () {
    angular.module('app')
            .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', '$http', '$route', 'VideoService'];

    function HomeController($location, $http, $route, VideoService) {
        var homeCtrl = this;

        VideoService.getVideos().then(function(response){
            homeCtrl.publicVideos = response.data;
        })
    }

})();
