app.factory('loginService', ['$http', '$q', function($http, $q){
	 
	var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
    return {
         		
    		UserLogin: function(item){
                return $http.post(BASE_URL + '/loginuser', item)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while updating User');
                                    return $q.reject(errResponse);
                                }
                        );
        		
		}
    		
    };
 
}]);