app.controller('signUpController',[ '$scope' , 'signUpService' , function($scope , $signUpService) 
{   
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}
	$scope.password='';
	$scope.confirm_password='';
	$scope.address='';
	$scope.username='';
	$scope.passwordmismatch=false;
	
    $scope.user = 'enter user name';
    $scope.email = 'example@yahoo.com';
    $scope.myFunc = function()
    {
    	
    }
    $scope.overAllError = true;
	$scope.checkOverallError = function()
	{  
		console.log($scope.checkOverallError);
		$scope.passwordmismatch = !($scope.password==$scope.ConfirmPassword) ;
		
		$scope.overAllError = ( $scope.emailError || !$scope.emailTouched || $scope.phoneError || !$scope.phoneTouched || $scope.passwordError || !$scope.passwordTouched);
	    
	}
	
	$scope.email = '';
	$scope.emailError = false;
	$scope.emailTouched = false;
	$scope.CheckEmail = function()
	{
		$scope.emailTouched = true;
		var patt = /\S+@\S+\.\S+/;
		$scope.emailError = !patt.test($scope.email);
		$scope.checkOverallError();
	}
	
	$scope.phone = '';
	$scope.phoneError = false;
	$scope.phoneTouched = false;
	$scope.CheckPhone = function()
	{
		$scope.phoneTouched = true;
		var patt = /^[7-9][0-9]{9}$/;
		$scope.phoneError = !patt.test($scope.phone);
		$scope.checkOverallError();
		
	}
	$scope.password='';
	$scope.passwordError = false;
	$scope.passwordTouched = false;
	$scope.CheckPassword = function()
	
	{
		$scope.passwordTouched = true;
		var patt=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		$scope.passwordError = !patt.test($scope.password);
		$scope.checkOverallError();
		
		 }
	$scope.ConfirmPassword = '';
	$scope.ConfirmPasswordError = false;
	$scope.ConfirmPasswordTouched = false;
	$scope.ValidateConfirmPassword = function()
	{
		$scope.ConfirmPasswordTouched = true;
		var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		$scope.ConfirmPasswordError = !reg.test( $scope.ConfirmPassword );
		$scope.checkOverallError();
	}
    
	$scope.CreateUser = function()
	{
		console.log("pass"+$scope.password);
		console.log($scope.ConfirmPassword);
		
		console.log("Password : "+$scope.passwordmismatch);
		
		
		{
			$scope.UsernameError = false;
			$scope.EmailError = false;
			$scope.PasswordError = false;
			$scope.ConfirmPasswordError = false;
			
		
			$scope.PhoneError = false;
			$scope.passwordmismatch = false;
			
			var json = 	{
					"Username" : $scope.username,
					"Email" : $scope.email ,
					"Password" : $scope.password,
					"ConfirmPassword" : $scope.ConfirmPassword,
					"Address" : $scope.address,
					"Phone" : $scope.phone
				
				};
	
			console.log(json);
			
			$signUpService.InsertUser(json).then(function(response){
				console.log(response);
				
				$scope.ServerResponse = response.msg;
				
				window.setTimeout(function(){
					$scope.$apply( $scope.ServerResponse = '' );					
				}, 5000);
			});
		}		
	}
}]);
