/*!
 * @author Jonathan Leijendekker
 * Date: 01/20/2016
 * Time: 9:42 PM
 */

angular.module('bdo_oauth')
    .controller('AuthController', ['$scope', '$http', '$state', '$stateParams', function($scope, $http, $state, $stateParams) {

           $scope.auth = {
                status: "Authenticating...",
                clientKey: $stateParams.clientkey,
                redirectUri: $stateParams.redirect_uri
           };

           $http.post('api/v1/authenticate', $scope.auth)
                .then(function(data) {
                    $state.transitionTo('login', {
                        clientKey: data.data.clientKey,
                        redirectUri: data.data.redirectUri
                    }, {
                        location: false
                    });
                }, function(data) {
                    console.log(data)
                    if(data.status == 500)
                        $scope.auth.status = data.data.error;
                    else
                        $scope.auth.status = data.data.errors[0].defaultMessage;
                });
    }]);