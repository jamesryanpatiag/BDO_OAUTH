/*!
 * @author Jonathan Leijendekker
 * Date: 01/20/2016
 * Time: 7:18 PM
 */

/**
* The BDO Authenticator main module
*/
angular.module('bdo_oauth', ['ui.router'])
     .config(['$locationProvider', function($locationProvider) {
        $locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('!');
     }]).run(['$rootScope', '$state', function($rootScope, $state) {
        $rootScope.$state = $state;
     }]);