/*!
 * @author Jonathan Leijendekker
 * Date: 01/20/2016
 * Time: 9:36 PM
 */

 angular.module('bdo_oauth')
     .config(['$stateProvider', function ($stateProvider) {
            $stateProvider
            .state('authorize', {
                url: '/authorize',
                views: {
                    "content": {
                        templateUrl: 'scripts/app/auth/auth.html',
                        controller: 'AuthController'
                    }
                },
                data: {
                    pageTitle: 'Authentication'
                }
            })
 }]);