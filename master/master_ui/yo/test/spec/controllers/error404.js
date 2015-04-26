'use strict';

describe('Controller: Error404Ctrl', function () {

  // load the controller's module
  beforeEach(module('masterUiApp'));

  var Error404Ctrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    Error404Ctrl = $controller('Error404Ctrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
