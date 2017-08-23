(function () {
    angular.module("app")
            .service('CommentService', CommentService);

    CommentService.$inject = ['$http', '$q'];

    function CommentService($http, $q) {
    	
    	  var commentList = [];
    	  
    	  
	     this.getComment = function (id) {
	          var def = $q.defer();
	          var req = {
	              method: 'GET',
	              url: "comments/" + id
	          }
	          $http(req).success(function (data) {
	              def.resolve(data);
	          }).error(function () {
	              def.reject("Failed");
	          });
	          return def.promise;
	    }
    	  
        this.deleteComment = function (id) {
            var def = $q.defer();
            var req = {
                method: 'DELETE',
                url: "comments/" + id
            }
            $http(req).success(function (data) {
                def.resolve(data);
            }).error(function () {
                def.reject("Failed");
            });
            return def.promise;
        }
        
        this.getComments = function () {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "comments"
            }
            return $http(req).success(function (response) {
                return commentList = response.data;
            }).error(function () {
                return def.reject("Failed to get comment");
            });
        } 
        
        this.getCommentsForVideo = function (videoId) {
            var def = $q.defer();
            var req = {
                method: 'GET',
                url: "comments/search/comments/forVideo/" + videoId,
            }
            return $http(req).success(function (response) {
                return commentList = response.data;
            }).error(function () {
                return def.reject("Failed to get comment");
            });
        } 
        
        this.saveComment = function (comment) {
            var def = $q.defer();
            var req = {
                method: comment.id ? 'PUT': 'POST',
                url: "comments",
                data: comment
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