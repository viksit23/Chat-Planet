app.controller('BlogController',[ '$scope' , 'BlogService' ,'$window' , function($scope , $BlogService,$window) 
{
	if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
	{
		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
		
		$scope.LoginStatus = false;

		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
		
		//alert( $location.path() );
		//alert( $location.path().split('/')[2] );
		
		/*if( $location.path() == '/' )
			$location.path('/myprofile');*/
	}
	
	console.log('blog controller');
	$scope.postBlog = function() {
		console.log("in the post blog");
		$scope.UserBlog = {
			BlogTitle : $scope.user.blogTitle,
			BlogDesc : $scope.user.blogDesc,
			Email: $scope.currentUser,
			
			
		};
		$BlogService.postBlog($scope.UserBlog).then(
				function(response) {
					try {
						$scope.status = response.status;
					} catch (e) {
						$scope.data = [];
					}
				}, function(errResponse) {
					console.error('Error while Sending Data.');
				});
	}
	
}




]);