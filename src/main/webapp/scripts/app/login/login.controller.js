/*!
 * @author Jonathan Leijendekker
 * Date: 01/23/2016
 * Time: 11:23 AM
 */

 angular.module('bdo_oauth')
    .controller('LoginController', ['$scope', '$state', '$stateParams', function($scope, $state, $stateParams) {
        $scope.auth = $stateParams;

        $scope.login = function() {
            console.log("LOGGING IN")
        }
    }]);