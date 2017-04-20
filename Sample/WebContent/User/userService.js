app.factory('userService', ['$http', '$q', function($http, $q){
	 
	console.log('User Service');
	
	var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
	return {
		getAllUsers: function(){
 			
 			
            return $http({
            	  method: 'POST',
            	  url: BASE_URL + '/getuser',
            	  data:$scope.currentUser,
            	  headers:{'Content-Type': 'application/json'}
            	})
                    .then(
                            function(response){
                            	console.log(response.data);
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while updating User');
                                return $q.reject(errResponse);
                            }
                    );
    		
	}};

}])