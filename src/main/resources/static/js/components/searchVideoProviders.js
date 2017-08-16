(function () {
    angular.module("app")
        .component("searchVideoProviders", {
            templateUrl: 'js/components/searchVideoProviders.html',
            controller: function () {
                this.searchYouTube = function () {
                    this.text = "YouTube videos";
                }
                
                this.searchVimeo = function () {
                    this.text = "Vimeo videos";
                }
                
                this.searchDailymotion = function () {
                    this.text = "Dailymotion videos";
                }                
            },
            bindings: {

            }
        })
})();
