'use strict';

/**
 * @ngdoc function
 * @name masterUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the masterUiApp
 */
angular.module('masterUiApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
