app.factory('BlogService', ['$http', '$q', function($http, $q,$rootScope)
{
	console.log('blog service');
var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
	
	return {
		
		postBlog : function(item) {
			return $http.post(BASE_URL + '/postblog', item).then(
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