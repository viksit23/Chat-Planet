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
	
	getAllBlogs : function() {
		return $http.get(BASE_URL + '/allblogs').then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	
	getBlogs : function() {
		return $http.get(BASE_URL + '/blogs').then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	
	
	publishBlog : function(item) {
		return $http.post(BASE_URL + '/publishblog', item).then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	
	
	
	unpublishBlog : function(item) {
		console.log("inside unpublish service");
		return $http.post(BASE_URL + '/unpublishblog', item)
	
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	
	
	addBlogData : function(item) {
		return $http.post(BASE_URL + '/addblogdata', item).then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	getBlogData : function(item) {
		return $http.get(BASE_URL + '/getblogdata', item).then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	deleteBlogData : function(item) {
		return $http.get(BASE_URL + '/deleteblogdata/' + item)
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	updateBlogData : function(item) {
		return $http.post(BASE_URL + '/updateblogdata', item)
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},

	postComment : function(item) {
		console.log("inside the post comment service")
		console.log(item);
		return $http.post(BASE_URL + '/blogpostcomment', item)
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	//list Comments

	listComments : function() {
		return $http.get(BASE_URL + "/listpostcomments").then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	
	
	postLike : function(item) {
		console.log("inside the post like service")
		console.log(item);
		return $http.post(BASE_URL + '/blogpostlike', item)
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	
	getBlogLike : function(item) {
		return $http.get(BASE_URL + '/getbloglike', item).then(
				function(response) {
					return response.like;
				}, function(errResponse) {
					console.error('Error while sending data');
					return $q.reject(errResponse);
				});
	},
	
	
	
	
	}
	
	
	}

])