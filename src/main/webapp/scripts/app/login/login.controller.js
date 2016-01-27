/*!
 * @author Jonathan Leijendekker
 * Date: 01/23/2016
 * Time: 11:23 AM
 */

 angular.module('bdo_oauth')
    .controller('LoginController', ['$scope', '$http', '$stateParams', '$window', function($scope, $http, $stateParams, $window) {
        $scope.user = {};

        $scope.login = function() {

            $scope.error = "";

            $scope.loginForm = {
                authResponseDTO: {
                    clientKey: $stateParams.clientKey,
                    redirectUri: $stateParams.redirectUri
                },
                loginDTO: {
                    username: $scope.user.username,
                    password: $scope.user.password
                }
            }

            $http.post('api/v1/login', $scope.loginForm)
                 .then(function(data) {
                    $window.location = data.data;
                 }, function(data) {
                    if(data.data.errors)
                        $scope.error = data.data.errors[0].defaultMessage;
                    else if(data.data.error)
                        $scope.error = data.data.error;
                    else
                        $scope.error = data.statusText;
                 });
        }
    }]);