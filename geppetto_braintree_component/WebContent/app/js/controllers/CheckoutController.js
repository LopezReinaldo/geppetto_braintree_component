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
					
			$scope.totalAmount = 0;
						
			
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
						
			$scope.pay = function() {				
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
						nonce : nonce,
						total: $scope.getTotal()
					},
					headers : {
						"content-type" : "application/json",
						"Accept" : "application/json"
					},
				}).success(function(response) {						
					if (response != null) {	
						if (response.id != null) {								
							var msg = "Congratulations! Your payment has been processed successfully!"
						} else {
							var msg = "Payment failed! "+response.error;
						}																	
						$scope.openAlertDialog('sm', msg);
						$scope.initializeForm();
						$scope.deleteCookies();	    	    				    				    			
		    			$location.url('/en-US/products');
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
									
			
			$scope.getCart = function() {
			  	var url = RestURL.baseURL + '/checkout/getCart';		  	    		  	
			  	
			    $http.get(url).success(function (data) {
			    	$scope.cart = data;					    	
			    	
	            }).error(function (data) {   	            	
	            	$scope.error = 'unable to get cart';                
	            });

			};
						
			
			$scope.getTotal = function(){
			    var total = 0;
			    for(var i = 0; i < $scope.cart.length; i++){			    	
			        var product = $scope.cart[i].product;
			        var quantity = $scope.cart[i].quantity;
			        total += product.price*quantity;
			    }
			    return total;
			};
			
			$scope.incrementQuantity = function(id){
				for(var i = 0; i < $scope.cart.length; i++){
					if($scope.cart[i].product.id == id){
						var q = $scope.cart[i].quantity++;
						q++;
						$scope.updateQuantity(id, q);
					}			        
			    }								
			};
			
			$scope.decreaseQuantity = function(id){
				for(var i = 0; i < $scope.cart.length; i++){
					if($scope.cart[i].product.id == id && $scope.cart[i].quantity > 1){
						var q = $scope.cart[i].quantity--;
						q--;
						$scope.updateQuantity(id, q);
					}			        
			    }								
			};
			
			$scope.updateQuantity = function(p_id, q){
				
				var url = RestURL.baseURL + '/checkout/updateQuantity';																	
				
				$http({
    				method : "POST",
    				url : url,
    				data : {					
    					product_id: p_id,
    					quantity: q
    				},
    				headers : {
    					"content-type" : "application/json",
    					"Accept" : "application/json"
    				}
				}).success(function(data){						
					$scope.deleteCookies();	    	    				    				    			
					$location.url('/en-US/checkout');
    			    			
				}).error(function(data){
					alert("Error updating quantity of product");
					$log.log("Error updating quantity of product "+data);
				});
			}

			
			$scope.removeProduct = function(product_id){
				
				var url = RestURL.baseURL + '/checkout/removeProduct';
			  					
				$http({
    				method : "POST",
    				url : url,
    				data : {					
    					id: product_id    					
    				},
    				headers : {
    					"content-type" : "application/json",
    					"Accept" : "application/json"
    				}
				}).success(function(data){
					$scope.getCart();					
					$scope.deleteCookies();	    	    				    				    			
					$location.url('/en-US/checkout');
    			    			
				}).error(function(data){
					alert("Error removing product from cart");
					$log.log("Error removing product from cart "+data);
				});	
				
			}
			
			
			switch ($routeParams['action']) {
			case undefined:
				$scope.getToken();
				$scope.getCart();
				
				break;
				
			default:
				$location.url('/en-US/checkout');
			};
			
		} ]);