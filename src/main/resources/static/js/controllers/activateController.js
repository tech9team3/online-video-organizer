(function () {
    angular.module('app')
        .controller('ActivateController', ActivateController);

    ActivateController.$inject = ['$location', '$http', '$route', '$routeParams', 'UserService'];

    function ActivateController($location, $http, $route, $routeParams, UserService) {
        var activateCtrl = this;
        var userId = $routeParams.userId;

        var req = {
            method: 'GET',
            url: "users/activateUser/" + userId,
        };

        $http(req)
            .then(function (response) {
                activateCtrl.user = response.data;
            });

    }
})();
