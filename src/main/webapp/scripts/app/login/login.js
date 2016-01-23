/*!
 * @author Jonathan Leijendekker
 * Date: 01/23/2016
 * Time: 9:52 AM
 */

 angular.module('bdo_oauth')
     .config(['$stateProvider', function($stateProvider) {
        $stateProvider
        .state('login', {
            url: '/login',
            params: {
                clientKey: null,
                redirectUri: null
            },
            views: {
                "content" : {
                    templateUrl: "scripts/app/login/login.html"
                }
            }
        });
     }]);
