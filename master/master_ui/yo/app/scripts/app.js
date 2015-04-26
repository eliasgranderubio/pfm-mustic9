'use strict';

/**
 * @ngdoc overview
 * @name masterUiApp
 * @description
 * # masterUiApp
 *
 * Main module of the application.
 */
angular
  .module('masterUiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/error404', {
        templateUrl: 'views/error404.html',
        controller: 'Error404Ctrl'
      })
      .when('/john', {
        templateUrl: 'views/john.html',
        controller: 'JohnCtrl'
      })
      .when('/hydra', {
        templateUrl: 'views/hydra.html',
        controller: 'HydraCtrl'
      })
      .when('/feedback', {
        templateUrl: 'views/feedback.html',
        controller: 'FeedbackCtrl'
      })
      .otherwise({
        redirectTo: '/error404'
      });
  });
