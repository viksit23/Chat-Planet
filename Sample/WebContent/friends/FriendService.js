app.factory('FriendService', ['$http', '$q', function($http, $q,$rootScope){
	 
	console.log('Friend Service');
	
	var BASE_URL = 'http://localhost:7777/com.samplebackend';
	
	
	return {
		getAllUsers: function(user){
 			
 			
            return $http({
            	  method: 'POST',
            	  url: BASE_URL + '/getuser',
            	  data: user,
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
	},
	
	sendFriendRequset: function(id) {
		console.log("inside the sendRequst service");
		return $http.post(BASE_URL + '/friendrequest', id).then(
				function(response) {
					return response.data;
				}, function(errResponse) {
					return $q.reject(errResponse);
				});
	},
	
	 viewFriendRequset:function (item){
		 console.log("inside the friendrequest service");
		 return $http.post(BASE_URL + '/friendrequests' , item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					}); 
	 },
	 acceptFriendRequset:function (friendId,email){
		 console.log(friendId);
		 console.log("inside the accept friend request service");
		 
		 var item = {"Email":email};
		 
		 return $http.post(BASE_URL + '/acceptfriendrequest/'+friendId , item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					}); 
	 },
	 removeFriendRequset:function (friendId,email){
		 console.log(friendId);
		 console.log("inside the accept friend remove service");
		 
		 var item = {"Email":email};
		 
		 return $http.post(BASE_URL + '/removefriendrequest/'+friendId , item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					}); 
	 },

	  rejectFriendRequset: function (item){
		 //console.log(friendId);
		 console.log("inside the reject friend request service");
		 return $http.post(BASE_URL + '/rejectfriendrequest' , item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					}); 
	 },
	
	  getFriends: function (item){
		 console.log("inside the get friend request service");
		 console.log( item );
		 return $http.post(BASE_URL + '/getfriends' , item).then(
					function(response) {
						return response.data;
					}, function(errResponse) {
						return $q.reject(errResponse);
					}); 
	 }

	};

}])