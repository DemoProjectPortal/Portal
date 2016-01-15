// Declare app level module which depends on filters, and services
angular.module('job-app', [ 'ngRoute', 'ngResource', 'job-app.controllers' ]).

config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/login', {
		templateUrl : 'app/partials/login.html',
		controller : 'loginController'
	}).when('/user-profile', {
		templateUrl : 'app/partials/profile.html',
		controller : 'profileController'
	}).when('/profile-wizard', {
		templateUrl : 'app/partials/personal_details_wizard.html',
		controller : 'profileController'
	}).when('/education-wizard', {
		templateUrl : 'app/partials/education_wizard.html',
		controller : 'profileController'
	}).otherwise({
		redirectTo : '/login'
	});
} ]);
