app.controller('FriendController',[ '$scope' , 'FriendService' ,'$window' , function($scope , $FriendService,$window) 
{
	
	$scope.allusers = [];
	
	console.log("Friend Controller");
	
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}
	
	$scope.currentUser = '';
	
	if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
	{
		$scope.currentUserRole = $window.sessionStorage.getItem("currentUserRole");
		console.log($scope.currentUserRole);
		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
		
		$scope.LoginStatus = true;

		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
		
		//alert( $location.path() );
		//alert( $location.path().split('/')[2] );
		
		/*if( $location.path() == '/' )
			$location.path('/myprofile');*/
	}
	
	function getAllUsers(  ) {
		console.log("in the getallusers");
		
	}
	
	 $scope.sendFriendRequset = function(FriendId) {
			console.log(FriendId);
			
			$FriendService.sendFriendRequset({"FriendId":FriendId,"Email": $scope.currentUser}).then(function(response) {
				$scope.status = response.status;
				getFriends({"Email": $scope.currentUser});
				//countFriendRequests({"Email":$scope.currentUser});
				
			}, function(errResponse) {
				console.log('Error fetching Users');
			});
	
	
}
	 
	  getFriends({"Email":$scope.currentUser});
	     
		 
		 $scope.viewFriendRequset = function() {
			 
			 console.log('hello');
			 
			 $FriendService.viewFriendRequset( {"Email":$scope.currentUser} )
						.then(function(response) {
								$scope.friendrequest = response;
								
								console.log( "FriendRequests:" );
								console.log( $scope.friendrequest );
								
								},
								function(errResponse) {
									console.log('Error fetching Users');
								});
			}
	 $FriendService.getAllUsers( $scope.currentUser ).then(function(response) {
			$scope.allusers = response;
			
			console.log("All Users");
			console.log($scope.allusers  );
			
			
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	 
	 $scope.acceptFriendRequset= function (friendId) {
		 $FriendService.acceptFriendRequset(friendId,$scope.currentUser)
					.then(function(response) {
						$scope.friendrequest = response;
						 getFriends({"Email":$scope.currentUser});
						 countFriendRequests({"Email":$scope.currentUser});
							},
							function(errResponse) {
								console.log('Error fetching Users');
							});
		
		 
		}
	 
	 $scope.removeFriendRequset = function(friendId) {
		 $FriendService.removeFriendRequset(friendId,$scope.currentUser)
					.then(function(response) {
						$scope.friendrequest = response;
						 getFriends({"Email": $scope.currentUser});
						 //countFriendRequests({"Email":$scope.currentUser});
							},
							function(errResponse) {
								console.log('Error fetching Users');
							});
		}
	 
	 function rejectFriendRequset(friendId) {
		 $FriendService.rejectFriendRequset({"FriendId": friendId ,"Email":$scope.currentUser})
					.then(function(response) {
						$scope.friendrequest = response;
							},
							function(errResponse) {
								console.log('Error fetching Users');
							});
		}
	 
	 function getFriends(item) {
		 window.setTimeout(function(){
		 $FriendService.getFriends(item).then(function(response) {
						$scope.friends = response;
						
						console.log("Friends:");
						
						console.log( $scope.friends );
						/*
						for( var i = 0 ; i < $scope.allusers.length ; i++ )
						{
							$scope.allusers[i].isFriend = false;
							
							for( var j = 0 ; j < $scope.friends.length ; j++ )
							{
							
								if( $scope.allusers[i].email == $scope.friends[j].friendId.email )
								{
									$scope.allusers[i].isFriend = $scope.friends[j].status;
									console.log($scope.friends[j].status);
								}
								
							}
						}*/
						
							},
							function(errResponse) {
								console.log('Error fetching Users');
							});
		 },200);
		}
	 
	 }]);