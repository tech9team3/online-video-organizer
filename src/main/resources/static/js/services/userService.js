(function () {
    angular.module("app")
            .service('UserService', UserService);

    UserService.$inject = ['$http', '$q'];

    function UserService($http, $q) {

        var usersList = [];
        var loggedInUser;

        this.loggedInUser = function() {
            return loggedInUser;
        }

        this.sendCaptcha = function (data) {
            var def = $q.defer();
            var req = {
                method: 'POST',
                url: "users/captcha",
                data: data
            }
            return $http(req).success(function (response) {
            	return response;
            	console.log(response);
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

        this.getUsers = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "users"
            }
            return $http(req).success(function (response) {
                return usersList = response.data;
            }).error(function () {
                return def.reject("Failed to get users");
            });
        }
        
        this.getUserByUsername = function (username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "users/searchByName/" + username
            }
            return $http(req).success(function (response) {
                return response.data;
            }).error(function () {
                return def.reject("Failed to get user");
            });
        }

        this.getLoggedInUserByUsername = function (username) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "users/searchByName/" + username
            }
            return $http(req).success(function (response) {
                loggedInUser = response;
            }).error(function () {
                return def.reject("Failed to get user");
            });
        }

        this.saveUser = function (user) {
            var def = $q.defer();
            var req = {
                method: user.id ? 'PUT': 'POST',
                url: "users",
                data: user
            }
            return $http(req).success(function (response) {
            	return response;
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

        this.deleteUser = function (id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "users/" + id
            }
            $http(req).success(function (data) {
                def.resolve(data);
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }
    };
}());