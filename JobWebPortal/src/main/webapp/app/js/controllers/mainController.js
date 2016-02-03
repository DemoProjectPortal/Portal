'use strict';

angular.module('job-app.controllers', ['job-app.services'])
.controller('mainController', [ "$scope", "userService", "$location", function($scope, userService, $location) {

	$scope.next = function(path) {
        $location.url(path);        
        if (path == "/login") {
        	$scope.$on( "$routeChangeSuccess", function(event, next, current) {
        		$('#login').hide(); 
            	$('#sign-up').show();
            });        	
		}
    };
    
    $scope.getData = function(){
    	console.log( $scope.formData);
    	userService.addUser($scope.formData, function(response) {
    		$location.url("/sign-up-success");
    	}, function(response) {
    		$location.url("/sign-up-error");
    	});
    }
    
    // we will store all of our form data in this object
    $scope.formData = {};
}]);