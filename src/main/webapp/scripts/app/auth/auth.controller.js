/*!
 * @author Jonathan Leijendekker
 * Date: 01/20/2016
 * Time: 9:42 PM
 */

angular.module('bdo_oauth')
    .controller('AuthController', ['$scope', '$http', '$state', '$location', function($scope, $http, $state, $location) {
           $scope.auth = {
                status: "Authenticating...",
                clientKey: $location.$$search.clientkey,
                redirectUri: $location.$$search.redirect_uri
           };

           console.log($scope.auth);

           $http.post('api/v1/authenticate', $scope.auth)
                .success(function(data) {
                    console.log(data);
                })
                .error(function(data) {
                    console.log(data);
                });
    }]);