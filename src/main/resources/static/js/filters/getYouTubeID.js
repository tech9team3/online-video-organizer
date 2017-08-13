(function () {
    angular.module("app")
        .filter("GetYouTubeID", function ($sce) {
            return function (text) {
                var video_id = text.split('v=')[1].split('&')[0];
                return video_id;
            }
        })
}());