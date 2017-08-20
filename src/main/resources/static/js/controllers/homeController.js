(function () {
    angular.module('app')
            .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', '$http', '$route', 'VideoService'];

    function HomeController($location, $http, $route, VideoService) {
        var homeCtrl = this;
        homeCtrl.orderBy = orderBy;
        homeCtrl.order = 'id';
        homeCtrl.reverseOrder = false;
        homeCtrl.ratesAndCommentsComparatro = ratesAndCommentsComparatro;

        VideoService.getPublicVideos().then(function(response){
            homeCtrl.publicVideos = response.data;
        })

        function orderBy(order) {
            homeCtrl.reverseOrder = (homeCtrl.order === order) ? !homeCtrl.reverseOrder : false;
            homeCtrl.order = order;
        }

        function ratesAndCommentsComparatro(v1, v2){
            
        }
    }
})();
