var app = angular.module("BraintreeActivity", [ 'ngRoute', 'ngCookies',
		'ui.bootstrap', 'ui.grid', 'ui.grid.pagination',
		'ui.grid.resizeColumns' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when("/", {
		redirectTo : "/en-US/products"
	}).when("/en-US/products", {
		templateUrl : "app/views/en-US/user/products.html",
		controller : "ProductController"		
	}).when("/en-US/products/:action", {
		templateUrl : "app/views/en-US/user/products.html",
		controller : "ProductController"
	}).when("/en-US/checkout", {
		templateUrl : "app/views/en-US/user/checkout.html",
		controller : "CheckoutController"
	}).when("/en-US/checkout/:action", {
		templateUrl : "app/views/en-US/user/checkout.html",
		controller : "CheckoutController"
	}).otherwise({
		redirectTo : "/en-US/products"
	});
} ]);

app.run([ '$log', function($log) {
	$log.debug("Started loading the application.!");
} ]);