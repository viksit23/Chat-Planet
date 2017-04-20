app.factory('signUpService', ['$http', '$q', function($http, $q){
	 
	var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
    return {
         		InsertUser: function(item){
         			
         			console.log( 'Insert User Service:' );
         			console.log(item);
         			
                    return $http({
                    	  method: 'POST',
                    	  url: BASE_URL + '/adduser',
                    	  data:item,
                    	  headers:{'Content-Type': 'application/json'}
                    	})
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
    		,
//    		UserLogin: function(item){
//                return $http.post(BASE_URL + '/loginuser', item)
//                        .then(
//                                function(response){
//                                    return response.data;
//                                }, 
//                                function(errResponse){
//                                    console.error('Error while updating User');
//                                    return $q.reject(errResponse);
//                                }
//                        );
//        		
//		}
//    		
    };
                       
        		
		
    		
    
 
}])