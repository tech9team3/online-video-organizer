(function () {
    angular.module('app')
        .controller('AdminController', AdminController);

    AdminController.$inject = ['$location', '$http', '$route', 'VideoService', 'VideoListsService', 'UserService', 'CommentService'];

    function AdminController($location, $http, $route, VideoService, VideoListsService, UserService, CommentService) {
        var adminCtrl = this;
        adminCtrl.getVideoListsByUser = getVideoListsByUser;
        adminCtrl.getVideosByVideoList = getVideosByVideoList;
        adminCtrl.getCommentsForVideo = getCommentsForVideo;
        adminCtrl.deleteUser = deleteUser;
        adminCtrl.selectUser = selectUser;
        adminCtrl.editUser = editUser;
        adminCtrl.saveUser = saveUser;
        adminCtrl.operation;
        
        getUsers();       
        
        
        function getUsers(){
            UserService.getUsers().then(function(response){
                adminCtrl.users = response.data;
            });
        }
        
        function getVideoListsByUser(user) {
            VideoListsService.getVideoListsByUserId(user.id).then(function(response){
                user.videoLists = response.data;
            });
        }     
        
        function getVideosByVideoList(videoList) {
            VideoService.getVideosByVideoListId(videoList.id).then(function(response){
                videoList.videos = response.data;
            });
        }  
        
        function getCommentsForVideo(video) {
            CommentService.getCommentsForVideo(video.id).then(function (response) {
                video.comments = response.data;
            });
        }
        
        function deleteUser(userId){
            UserService.deleteUser(adminCtrl.user.id).then(function(response){
            	 getUsers();
            }, function(error){

            });
          //  vm.user= {};
        }
        
        function selectUser(user){
            adminCtrl.user = user;
        }
        
        function editUser(user){
        	adminCtrl.user = user;
        	delete adminCtrl.error;
            adminCtrl.operation = "Edit";
            adminCtrl.user = angular.copy(user);
        }
        
        function saveUser(user) {
        	user.roles = [{"id":1,"type":"ROLE_USER"}];
        	UserService.saveUser(user).then(function(response){
        		getUsers();
        		$('#add-user-modal').modal('hide');
            }, function(error){
            	adminCtrl.error = {};
                angular.forEach(error.data.exceptions, function(e){
                    errorHandler(e);
                });
            })
           //remove input value after submit
            adminCtrl.adminUserForm.$setPristine();
        	delete adminCtrl.error;
        }        
        
        function capitalize(error){
            return '* ' + error[0].toUpperCase() + error.slice(1); 
        }
        
        function errorHandler(error){
            switch(error.field){
                case 'username':
                    adminCtrl.error.username = error.message;
                    break;
                case 'email':
                    adminCtrl.error.email = error.message;
                    break;
                    }
        
        }
    }
})();