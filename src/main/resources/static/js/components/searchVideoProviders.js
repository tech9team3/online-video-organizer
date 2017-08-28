(function () {
    angular.module("app")
        .component("searchVideoProviders", {
            templateUrl: 'js/components/searchVideoProviders.html',
            bindings: {
                onVideoSelect: '&'
            },
            controller: function ($http, $cookieStore, $window) {
                var ctrl = this;

                ctrl.searchYouTube = searchYouTube;
                ctrl.searchVimeo = searchVimeo;
                ctrl.searchDailymotion = searchDailymotion;

                ctrl.providerVideoList = [];
                ctrl.progressBar = false;

                ctrl.scrollbarsConfig = {
                    axis: 'y',
                    autoHideScrollbar: true,
                    theme: 'rounded-dots-dark',
                    advanced: {
                        updateOnContentResize: true
                    },
                    callbacks: {
                        onTotalScrollOffset: 3000,
                        onTotalScroll: function () {
                            switch (ctrl.providerName) {
                                case 'youtube':
                                    searchYouTube(youtubeNextPageToken);
                                    break;
                                case 'vimeo':
                                    searchVimeo(vimeoPage + 1);
                                    break;
                                case 'dailymotion':
                                    searchDailymotion(dailymotionPage +1);
                                    break;
                            }
                        }
                    },
                    setHeight: $window.innerHeight - 210,
                    scrollInertia: 500,
                }


                //YouTube
                const YOUTUBE_SERARCH_API_URL = "https://www.googleapis.com/youtube/v3/search";
                const YOUTUBE_API_KEY = "AIzaSyAODuK_7-4SIKgPBScK0y6pj7CX6s7E8X8";
                const YOUTUBE_RETURN_FIELDS = "items(id,snippet(title,description,thumbnails/medium/url)),nextPageToken";
                var youtubeNextPageToken;

                //Vimeo
                const VIMEO_SERARCH_API_URL = "https://api.vimeo.com/videos";
                const VIMEO_CLIENT_IDENTIFIER = "ef063f042ae1bca6baa3114e36a9218e4290bde5";
                const VIMEO_CLIENT_SECRET = "h/8ZgbQMhXbCqHEuPoXQv/2sAIHcL73zoo+4Hw4b5T964jZcJ7Dz0dx27gb2qeyMPn3i8W/w8mLQBqDuM7PMVjswoi/JzEGgPL5w1MQ06ggBvNm7EVDtNGskDiu4BBIZ";
                const VIMEO_AUTHORIZE_URL = "https://api.vimeo.com/oauth/authorize/client";
                const VIMEO_ACCESS_TOKEN_URL = "https://api.vimeo.com/oauth/access_token";
                const VIMEO_RETURN_FIELDS = "uri,name,link,description,tags.name,pictures.sizes.link";
                const VIMEO_TOKEN = "24649bd87ad57cf0331a1a5754464bf1";
                var vimeoPage;

                //Dailymotion
                const DAILYMOTION_SERARCH_API_URL = "https://api.dailymotion.com/videos";
                const DAILYMOTION_RETURN_FIELDS = "description,id,tags,thumbnail_360_url,title";
                var dailymotionPage;

                //var vimeoToken = generateVimeoToken();
                var vimeoToken = VIMEO_TOKEN;

                function generateVimeoToken() {
                    var base64Credential = btoa(VIMEO_CLIENT_IDENTIFIER + ':' + VIMEO_CLIENT_SECRET);
                    var req = {
                        method: 'POST',
                        headers: {
                            'X-Requested-With': undefined,
                            'Authorization': 'Basic ' + base64Credential
                        },
                        url: VIMEO_AUTHORIZE_URL,
                        data: {
                            'grant_type': 'client_credentials',
                        }
                    };

                    return $http(req)
                        .then(function (response) {
                            //$cookieStore.put('vimeoToken', response.data.access_token);
                            console.log(response.data.access_token);
                            return response.data.access_token;
                        }, function () {
                            // Error handler
                        }).finally(function () {
                            // Always execute this on both error and success
                        });
                }


                //Search YouTube
                function searchYouTube(nextPageToken) {
                    delete ctrl.error;
                    if (!nextPageToken){
                        ctrl.providerVideoList = [];
                    }                        
                    ctrl.providerName = "youtube";
                    ctrl.progressBar = true;

                    var req = {
                        method: 'GET',
                        headers: {
                            'X-Requested-With': undefined,
                            'Authorization': undefined
                        },
                        url: YOUTUBE_SERARCH_API_URL,
                        params: {
                            'maxResults': '25',
                            'part': 'snippet',
                            'fields': YOUTUBE_RETURN_FIELDS,
                            'pageToken': nextPageToken,
                            'q': ctrl.searchVideoTerm,
                            'type': 'video',
                            'key': YOUTUBE_API_KEY
                        }
                    };

                    $http(req)
                        .then(function (response) {
                            youtubeNextPageToken = response.data.nextPageToken;
                            angular.forEach(response.data.items, function (item) {
                                var video = {
                                    videoUrl: "https://www.youtube.com/watch?v=" + item.id.videoId,
                                    videoPlayerUrl: "https://www.youtube.com/embed/" + item.id.videoId,
                                    providerName: "youtube",
                                    videoUrlId: item.id.videoId,
                                    title: item.snippet.title,
                                    description: item.snippet.description,
                                    videoImageUrl: item.snippet.thumbnails.medium.url
                                }
                                ctrl.providerVideoList.push(video);
                            });
                        }, function (error) {
                            // Error handler
                            ctrl.error = error.data;
                        }).finally(function () {
                            // Always execute this on both error and success
                            ctrl.progressBar = false;
                        });
                }



                //Search Vimeo
                function searchVimeo(page) {
                    delete ctrl.error;
                    ctrl.providerName = "vimeo";
                    if(page === 1){
                        vimeoPage = page;
                        ctrl.providerVideoList = [];
                    }                    
                    ctrl.progressBar = true;

                    //vimeoToken.then(function (vimeoToken) {
                    var req = {
                        method: 'GET',
                        headers: {
                            'X-Requested-With': undefined,
                            'Authorization': undefined
                        },
                        url: VIMEO_SERARCH_API_URL,
                        params: {
                            'fields': VIMEO_RETURN_FIELDS,
                            'per_page': '25',
                            'page': page,
                            'query': ctrl.searchVideoTerm,
                            'access_token': vimeoToken // $cookieStore.get('vimeoToken');
                        }
                    };

                    $http(req)
                        .then(function (response) {
                            angular.forEach(response.data.data, function (item) {
                                var video = {
                                    videoUrl: "https://vimeo.com/" + item.uri.split('/videos/')[1],
                                    videoPlayerUrl: "https://player.vimeo.com/video/" + item.uri.split('/videos/')[1],
                                    providerName: "vimeo",
                                    videoUrlId: item.uri.split('/videos/')[1],
                                    title: item.name,
                                    description: item.description,
                                    videoImageUrl: item.pictures.sizes[3].link,
                                    videoTag: item.tags
                                }
                                ctrl.providerVideoList.push(video);
                            });
                        }, function (error) {
                            // Error handler
                            ctrl.error = error.data.error;
                        }).finally(function () {
                            // Always execute this on both error and success
                            ctrl.progressBar = false;
                        });
                    //});
                }



                //Search Dailymotion
                function searchDailymotion(page) {
                    delete ctrl.error;
                    ctrl.providerName = "dailymotion";
                    if(page === 1){
                        dailymotionPage = page;
                        ctrl.providerVideoList = [];
                    }
                    ctrl.progressBar = true;

                    var req = {
                        method: 'GET',
                        headers: {
                            'X-Requested-With': undefined,
                            'Authorization': undefined
                        },
                        url: DAILYMOTION_SERARCH_API_URL,
                        params: {
                            'limit': '25',
                            'page': page,
                            'fields': DAILYMOTION_RETURN_FIELDS,
                            'search': ctrl.searchVideoTerm,
                        }
                    };

                    $http(req)
                        .then(function (response) {
                            angular.forEach(response.data.list, function (item) {
                                var video = {
                                    videoUrl: "http://www.dailymotion.com/video/" + item.id,
                                    videoPlayerUrl: "http://www.dailymotion.com/embed/video/" + item.id,
                                    providerName: "dailymotion",
                                    videoUrlId: item.id,
                                    title: item.title,
                                    description: item.description,
                                    videoImageUrl: item.thumbnail_360_url,
                                    videoTag: item.tags.map(function (item) {
                                        return {
                                            name: item
                                        };
                                    })
                                }
                                ctrl.providerVideoList.push(video);
                            });
                        }, function (error) {
                            // Error handler
                            ctrl.error = error.data;
                        }).finally(function () {
                            // Always execute this on both error and success
                            ctrl.progressBar = false;
                        });
                }
            }
        });
})();
