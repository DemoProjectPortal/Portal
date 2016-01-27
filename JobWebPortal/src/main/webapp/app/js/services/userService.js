angular.module('job-app.services', []).factory('userService', function($http) {

	var serviceObj = {};

	serviceObj.addUser = function(formData) {
		var res = $http({
			method : 'POST',
			url : '/api/user/',
			data: formData
		}).then(function successCallback(response) {
		    // this callback will be called asynchronously
		    // when the response is available
			console.log("success");
		  }, function errorCallback(response) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
			  
			  console.log("error");
			  console.log(response);
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
