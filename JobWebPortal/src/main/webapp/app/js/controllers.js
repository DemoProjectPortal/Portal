'use strict';

angular.module('job-app.controllers', [])

.controller('loginController', [ "$scope", function($scope) {
	$scope.message = "This message is from angular controller.";
} ])
.controller('profileController', [ "$scope", function($scope) {
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