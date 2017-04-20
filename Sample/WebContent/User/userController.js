app.controller('userController',[ '$scope' , 'userService' , function($scope , $userService) 
{
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}
	$scope.allusers = [];
	
	console.log("User Controller");
	
	function getAllUsers() {
		console.log("in the getallusers");
		
	}
	
	$userService.getAllUsers().then(function(response) {
		$scope.allusers = response;
		
		console.log("All Users");
		console.log($scope.allusers  );
		
		
	}, function(errResponse) {
		console.log('Error fetching Users');
	});
}]);