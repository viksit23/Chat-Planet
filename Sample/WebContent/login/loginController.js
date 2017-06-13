app.controller("loginController",['loginService','$scope','$location','$window',function( $loginService ,  $scope , $location,$window)
   {
	console.log('loaded');
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}
	$scope.LoginEmail = '';
	$scope.LoginPassword = '';
	
	$scope.InvalidLogin = false;
	$scope.LoginStatus = true;
	
	if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
	{
		$scope.currentUserRole = $window.sessionStorage.getItem("currentUserRole");
		console.log($scope.currentUserRole);
		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
		
		$scope.LoginStatus = true;

		//alert( $location.path() );
		//alert( $location.path().split('/')[2] );
		
		if( $location.path() == '/' )
			$location.path('/myprofile');
	}
	
	$scope.logout = function()
	{
		//$window.sessionStorage.empty();
		
		sessionStorage.clear();
		
		//$window.sessionStorage.setItem("currentUser",null);
		$scope.LoginStatus = false;
		
		$location.path('/home');
	}
	
	$scope.login = function()
	{
		console.log("inside login");
		
		var json = 	{
				"Email" : $scope.LoginEmail ,
				"Password" : $scope.LoginPassword
			};

		console.log(json);
		
		$loginService.UserLogin(json).then(function(response){
			console.log(response);
			
			if( response.msg == 'Invalid Login' )
			{
				$scope.InvalidLogin = true;
				window.setTimeout(function(){
					$scope.$apply( $scope.InvalidLogin = false );					
				}, 5000);
				
				$scope.LoginStatus = false;
				console.log( $scope.LoginStatus );
				
			}	
			else
			{
				$window.sessionStorage.setItem("currentUser", $scope.LoginEmail);
				$window.sessionStorage.setItem("currentUserRole", response.role);
			
				$location.path('/myprofile');
				$scope.LoginStatus = true;
				console.log( $scope.LoginStatus );
			}
			/*
			$scope.logout = function()
			{
				$window.sessionStorage.clear();
				$scope.LoginStatus = true;
				
				$location.path('/home');
			}*/
			
		});
	
	}
	}]);
