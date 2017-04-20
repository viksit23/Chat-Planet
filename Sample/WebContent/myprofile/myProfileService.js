
app.factory('myProfileService', ['$http', '$q', function($http, $q){
	 
	var BASE_URL = 'http://localhost:7777/com.samplebackend/';




return {
		userData : function(item) {
			
			console.log( item );
			
			return $http.post(BASE_URL + 'userdata' , item ).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
			
		},	

		updatePassword : function(item) {
			return $http.post(BASE_URL + 'updatepassword', item)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while sending data');
						return $q.reject(errResponse);
					})
		},


updateUser : function(item) {
	return $http.post(BASE_URL + 'updateuser', item).then(
			function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while sending data');
				return $q.reject(errResponse);
			});
}

}
}])
		
		