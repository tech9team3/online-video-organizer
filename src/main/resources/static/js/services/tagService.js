(function () {
    angular.module("app")
            .service('TagService', TagService);

    TagService.$inject = ['$http', '$q'];

    function TagService($http, $q) {            
        
    };
}());