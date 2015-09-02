app.factory('ClientToken',[ 
	'$http',
	'$q',
	'RestURL',                              
	function($http, $q, RestURL) {	
	  var getClientToken = function() {
		  	var url = RestURL.baseURL + '/checkout/getClientToken';		  	    		  	
		    var defer = $q.defer();

		    $http.get(url).success(function (data, status, headers, config) {
		    	defer.resolve(data);                
            }).error(function (data, status, headers, config) {
            	alert(data);
            	defer.reject(data);                
            });

		    return defer.promise;
	  };

	  return {
		  getClientToken: getClientToken
	  };
	}]);
