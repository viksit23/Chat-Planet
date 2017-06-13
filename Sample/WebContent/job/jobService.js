app.factory('jobService', ['$http', '$q', function($http, $q,$rootScope){

	console.log("job serviceeee..")
var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
	
	return {
	

		postJob : function(item) {
			return $http.post(BASE_URL + '/postjob', item)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while sending data');
						return $q.reject(errResponse);
					});
		},
		
		
		getJobs : function() {
			console.log('get jobs in service')
			return $http.get(BASE_URL + '/viewjobs').then(
					function(response) {
						console.log(response.data);
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
		},
		
		
		applyJob : function(item) {
			return $http.post(BASE_URL + '/applyjob', item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while sending data');
						return $q.reject(errResponse);
					});
		},

		deleteJob : function(deleteId) {
			console.log(deleteId);
			return $http.get(BASE_URL + '/deletejob/' + deleteId)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
		},
		
		editJob : function(jobData) {
			console.log("inside the job edit service");
			return $http.post(BASE_URL + '/editjob', jobData)
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
		}
		
		
		
		
	}
}
])