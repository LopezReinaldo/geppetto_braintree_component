var app = angular.module("BraintreeActivity", [ 'ngRoute', 'ngCookies',
		'ui.bootstrap', 'ui.grid', 'ui.grid.pagination',
		'ui.grid.resizeColumns' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when("/", {
		redirectTo : "/en-US/checkout"
	}).when("/en-US/checkout", {
		templateUrl : "app/views/en-US/user/checkout.html",
		controller : "CheckoutController"
	}).when("/en-US/checkout/:action", {
		templateUrl : "app/views/en-US/user/checkout.html",
		controller : "CheckoutController"
	}).otherwise({
		redirectTo : "/en-US/checkout"
	});
} ]);

app.run([ '$log', function($log) {
	$log.debug("Started loading the application.!");
} ]);