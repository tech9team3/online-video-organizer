(function () {
    angular.module("app")
            .service('NotificationService', NotificationService);

    NotificationService.$inject = ['$http', '$q'];

    function NotificationService($http, $q) {    	  
    	  
	     this.getNotification = function (id) {
	          var def = $q.defer();
	          var req = {
	              method: 'GET',
	              url: "notification/" + id
	          }
	          $http(req).success(function (data) {
	              def.resolve(data);
	          }).error(function () {
	              def.reject("Failed");
	          });
	          return def.promise;
	    }
    	  
        this.deleteNotification = function (id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "notification/" + id
            }
            $http(req).success(function (data) {
                def.resolve(data);
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }
        
        this.getNotifications = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "notification"
            }
            return $http(req).success(function (response) {
                return  response.data;
            }).error(function () {
                return def.reject("Failed");
            });
        } 
        
        this.getNotificationsByUserId = function (userId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "notification/forUser/" + userId,
            }
            return $http(req).success(function (response) {
                return response.data;
            }).error(function () {
                return def.reject("Failed");
            });
        } 
        
        this.getNewNotificationsByUserId = function (userId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "notification/getNewNotifications/" + userId,
            }
            return $http(req).success(function (response) {
                return response.data;
            }).error(function () {
                return def.reject("Failed");
            });
        } 
        
        this.saveNotification = function (notification) {
            var def = $q.defer();
            var req = {
                method: notification.id ? 'PUT': 'POST',
                url: "notification",
                data: notification
            }
            return $http(req).success(function (response) {
            	return response;
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }

       


    };
}());