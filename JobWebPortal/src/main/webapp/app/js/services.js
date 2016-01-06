angular.module('job-app.services', []).factory('userService', function($http) {

	var serviceObj = {};

	serviceObj.getUser = function() {
		var res = $http({
			method : '',
			url : ''
		});
		return res;
	};

	return serviceObj;
});