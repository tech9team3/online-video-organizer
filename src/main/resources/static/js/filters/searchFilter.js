(function () {
    angular.module("app")
        .filter("searchFilter", function ($filter) {
            return function (collection, query) {
                var filteredCollection = collection;
                var queryTokens = query ? query.split(' ') : [query];

                // user entered more than word
                if (queryTokens.length > 1) {
                    for (var i = 0; i < queryTokens.length; i++) {
                        filteredCollection = $filter('filter')(filteredCollection, queryTokens[i]);
                    }
                } else {
                    filteredCollection = $filter('filter')(collection, queryTokens[0]);
                }

                return filteredCollection;
            };
        })
}());
