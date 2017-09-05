(function () {
    var app = angular.module('app', ['ngRoute', 'ngAnimate', 'ngResource', 'ui.bootstrap', 'vcRecaptcha', 'ngCookies', 'ngTagsInput', 'ui.tree', 'ngScrollbars', 'ngStomp', 'angular-growl', 'angular-notification-icons', '720kb.socialshare']);

    // If we implement the basic security in spring boot then the response will
    // contains the header 'WWW-Authenticate: Basic'. So the browser will popup a
    // alert to get the user credentials. To avoid that we have to set a header in
    // every request we are making using AngularJs.
    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]);
    app.config(['growlProvider', function (growlProvider) {
        growlProvider.globalTimeToLive(3000);
        growlProvider.globalReversedOrder(true);
        growlProvider.globalPosition('top-center');
        //growlProvider.globalDisableIcons(true);
}]);
})();
