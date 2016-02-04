angular.module('job-app.services', []).factory('userService', function($http) {

	var serviceObj = {};

	serviceObj.addUser = function(formData, successCallBack, errorCallBack) {
		var res = $http({
			method : 'POST',
			url : 'user/addUser',
			data : formData
		}).then(function(response) {
			// this callback will be called asynchronously
			// when the response is available
			console.log("success");
			console.log(response);

			if (response) {
				if (response.data.responseCode == 200) {
					if (successCallBack) {
						successCallBack(response);
					}
				} else {
					if (errorCallBack) {
						errorCallBack(response);
					}
				}
			}
		}, function(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.

			console.log("error");
			console.log(response);
			if (errorCallback) {
				errorCallback(response);
			}
		});
		return res;
	};

	// This method calls getQualificationList api and if the response is a
	// success then runs a successCallBack method
	serviceObj.getQualifications = function(successCallBack, errorCallBack) {
		var res = $http({
			method : 'GET',
			url : 'user/getQualificationList',
		}).then(function(response) {
			// this callback will be called asynchronously
			// when the response is available
			console.log("success");
			console.log(response);

			if (response) {
				if (response.data.responseCode == 200) {
					if (successCallBack) {
						successCallBack(response);
					}
				} else {
					if (errorCallBack) {
						errorCallBack(response);
					}
				}
			}
		}, function(response) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.

			console.log("error");
			console.log(response);
			if (errorCallback) {
				errorCallback(response);
			}
		});
		return res;
	};

	return serviceObj;
});

/*angular.module('job-app.services', []).factory('userService',
 function($resource) {
 return $resource('/api/user/:id',{id:'@_id'},{
 update: {
 method: 'PUT'
 }
 });
 });*/
