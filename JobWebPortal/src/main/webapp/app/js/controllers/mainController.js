'use strict';

angular.module('job-app.controllers', ['job-app.services'])
.controller('mainController', [ "$scope", "userService", "$location", function($scope, userService, $location) {
    // we will store all of our form data in this object

	$scope.next = function(path) {
        $location.url(path);
        //this.getData();
    };
    
    $scope.getData = function(){
    	console.log( $scope.formData);
    	userService.addUser($scope.formData, function(response) {
    		$location.url("/sign-up-success");
    	}, function(response) {
    		$location.url("/sign-up-error");
    	});
    }
    $scope.formData = {};
}]);