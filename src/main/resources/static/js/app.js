(function () {
    var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap', 'vcRecaptcha','ngCookies','ngTagsInput']);

    // If we implement the basic security in spring boot then the response will
    // contains the header 'WWW-Authenticate: Basic'. So the browser will popup a
    // alert to get the user credentials. To avoid that we have to set a header in
    // every request we are making using AngularJs.
    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]);
})();

