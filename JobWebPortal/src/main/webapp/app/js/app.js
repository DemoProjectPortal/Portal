// Declare app level module which depends on filters, and services
angular.module('job-app', [ 'ngRoute', 'ngResource', 'job-app.controllers', 'UserValidation' ]).

config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/login', {
		templateUrl : 'app/partials/login.html'
	}).when('/user-profile', {
		templateUrl : 'app/partials/profile.html'
	}).when('/profile-wizard', {
		templateUrl : 'app/partials/personal_details_wizard.html'
	}).when('/education-wizard', {
		templateUrl : 'app/partials/education_wizard.html'
	}).when('/employment_wizard', {
		templateUrl : 'app/partials/employment_wizard.html'
	}).otherwise({
		redirectTo : '/login'
	});
} ]);
angular.module('UserValidation', []).directive('validPasswordConfirmation', function () {
    return {
        require: 'ngModel',
        link:{
        	post: function (scope, elm, attrs, ctrl) {
	            ctrl.$parsers.unshift(function (viewValue, $scope) {
	                var noMatch = viewValue != scope.registerForm.password.$viewValue
	                ctrl.$setValidity('noMatch', !noMatch)
	            })
        	}
        }
    }
});
