app.factory('EventService', ['$http', '$q', function($http, $q,$rootScope){
	 
	console.log('Event Service');
	
	var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
	
	return {
		
		postEvent : function(item) {
			return $http.post(BASE_URL + '/postevent', item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while sending data');
						return $q.reject(errResponse);
					});
		},
	}
}


])