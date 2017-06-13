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
		
		getEvents : function() {
			console.log("inside function");
			return $http.get(BASE_URL + '/viewevents').then(
					function(response) {
						
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
		},
		
		deleteEvent : function(eventId) {
			console.log(eventId);
			return $http.get(BASE_URL + '/deleteevent/'+eventId).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					});
		},
		updateEvent : function(item) { 
			console.log("inside update");
			return $http.post(BASE_URL + '/updateevent', item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while sending data');
						return $q.reject(errResponse);
					});
		},

	
		applyEvent : function(item) {
			return $http.post(BASE_URL + '/applyevent', item).then(
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