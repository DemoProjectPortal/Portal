'use strict';

angular.module('job-app.controllers', ['job-app.services'])
.controller('mainController', [ "$scope", "userService", "$location", function($scope, userService, $location) {
    // we will store all of our form data in this object

	$scope.next = function(path) {        
       $location.url(path);
    };
    
    $scope.getData = function(){
    	console.log( $scope.formData);
    	userService.addUser($scope.formData);
    }
    $scope.formData = {};
    
}]).controller('profileController', [ "$scope", function($scope) {
		$scope.userProfile = {
						userName: "test_user",
						firstName: "Ankit",
					lastName: "Tyagi",
						qualifications: [{
							name: "SSC",
							percentage: "65"
						},{
						name: "HSC",
							percentage: "65"				
						}]
				};
} ]);