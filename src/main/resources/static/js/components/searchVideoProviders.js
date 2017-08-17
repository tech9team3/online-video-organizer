(function () {
    angular.module("app")
        .component("searchVideoProviders", {
            templateUrl: 'js/components/searchVideoProviders.html',
            bindings: {

            },
            controller: function ($http) {
                const YOUTUBE_SERARCH_API_URL = "https://www.googleapis.com/youtube/v3/search";
                const VIMEO_SERARCH_API_URL = "";
                const DAILYMOTION_SERARCH_API_URL = "";
                
                var ctrl = this;

                this.searchYouTube = function (searchVideoTerm) {
    
                    var req = {
                        method: 'GET',
                        url: YOUTUBE_SERARCH_API_URL,
                        params: {
                            'maxResults': '25',
                            'part': 'snippet',
                            'q': searchVideoTerm,
                            'type': 'video',
                            'key': 'AIzaSyAODuK_7-4SIKgPBScK0y6pj7CX6s7E8X8'
                        }                        
                    };
                    
                    $http(req).success(function(response) {
                        ctrl.text = response;
                    }).error(function () {

                    });
                }

                this.searchVimeo = function () {
                    this.text = "Vimeo " + this.searchVideoTerm;
                }

                this.searchDailymotion = function () {
                    this.text = "Dailymotion " + this.searchVideoTerm;
                }
            }
        });
})();
