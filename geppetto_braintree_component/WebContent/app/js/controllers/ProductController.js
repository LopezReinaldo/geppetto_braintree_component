app.controller("ProductController", [
		'$scope',
		'$log',
		'$location',
		'$http',
		'$routeParams',
		'$modal',
		'$window',
		'RestURL',		
		function($scope, $log, $location, $http, $routeParams,
				$modal, $window, RestURL) {					
			
			
			$scope.getProducts = function() {				
		        $http.get(RestURL.baseURL+'/product/getProducts')		        
		            .success(function(data) {		            	
		                $scope.json = data;
		            }).error(function(data) {		            	
		            	$log.log("products data"+data);
		            });
		    };	
		    
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
		    
		    
		    $scope.addToCart = function(p_id, p_quantity){		    	
		    	var url = RestURL.baseURL + '/product/addToCart';
	    		$http({
	    				method : "POST",
	    				url : url,
	    				data : {					
	    					product_id: p_id,
	    					quantity: p_quantity
	    				},
	    				headers : {
	    					"content-type" : "application/json",
	    					"Accept" : "application/json"
	    				}
	    		}).success(function(data){
	    			$scope.openAlertDialog('sm', 'Product added to the cart');
	    			$scope.deleteCookies();	    	    				    				    			
	    			$location.url('/en-US/checkout');
	    			
	    			
	    		}).error(function(data){
	    			alert("error");
	    			$log.log("Error adding product to cart "+data);
	    		});		    			   		    			    	
		    }		
		    
			
			
			switch ($routeParams['action']) {
			case undefined:				
				$scope.getProducts();				
				break;
			case 'add':
				 $scope.addToCart();
				break;
				
			default:
				$location.url('/en-US/products');
			};
			
		} ]);