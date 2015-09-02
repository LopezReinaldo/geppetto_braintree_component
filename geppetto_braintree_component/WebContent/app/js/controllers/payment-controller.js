app.controller("CheckoutController", [
		'$scope',
		'$log',
		'$location',
		'$http',
		'$routeParams',
		'$modal',
		'RestURL',
		'ClientToken',
		function($scope, $log, $location, $http, $routeParams,
				$modal, RestURL, ClientToken) {
						
			$scope.clientToken = "";						
			
			$scope.initializeForm = function() {
				$scope.paymentDetail = {
						number: "",
						cardholderName: "",
						expirationDate: "",
						cvv: "",
						billingAddress: {
							postalCode: ""
						}									
				}
				$scope.paymentForm.$setPristine(true);
			}
			
			$scope.openAlertDialog = function(size, msg) {
				var modalInstance = $modal.open({
					animation : true,
					size : size,
					templateUrl : 'app/views/en-US/templates/modals/alertmessage.html',
					controller : 'ModalController',
					resolve : {
						data : function() {
							return angular.copy(msg);
						},
					}
				});
				modalInstance.result.then(function(dataFromModal) {});
			};			
						
			$scope.subscribe = function() {				
				var client = new braintree.api.Client({clientToken: $scope.clientToken.token});				
				client.tokenizeCard({					
				  number: $scope.paymentDetail.number,
				  cardholderName: $scope.paymentDetail.cardholderName,				  
				  expirationDate: $scope.paymentDetail.expirationDate, 
				  cvv: $scope.paymentDetail.cvv,				  
				  billingAddress: {
				    postalCode: $scope.paymentDetail.billingAddress.postalCode
				  }				
				}, function (err, nonce) {														 
					
					$scope.processPayment(nonce);																			
					
				});
			};
			
			
			$scope.processPayment = function(nonce){
				
				var url = RestURL.baseURL + '/checkout/processPayment';
				$http({
					method : "POST",
					url : url,
					data : {
						nonce : nonce							
					},
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {						
					if (response != null) {	
						if (response.id != null) {								
							var msg = "Congratulations! You have been subscribed successfully!"
						} else {
							var msg = "Subscription failed! "+response.error;
						}																	
						$scope.openAlertDialog('sm', msg);
						$scope.initializeForm();
					}
				}).error(function(response) {						
					$log.debug("Request Error: ", response);
				});
			}
			
					
			$scope.getToken = function() {				
				ClientToken.getClientToken().then(function(data) {
				    $scope.clientToken = data;				    
				  }, function() {
					 $scope.error = 'unable to get token';					    
				  });
			};
			
			switch ($routeParams['action']) {
			case undefined:
				$scope.getToken();				
				break;
			case 'subscribe':
				break;
				
			default:
				$location.url('/en-US/checkout');
			};
			
		} ]);