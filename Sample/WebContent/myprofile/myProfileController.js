
app.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, paramuser, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);
		
		return $http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined,
				user : paramuser
			}
		}).then(function(response) {
			return response.data;
		}, function(errResponse) {
			console.error('Error while updating User');
			return "error";
		});
	}
}]);
app.controller('myProfileController',['$scope','$filter','myProfileService','$location','$window','$routeParams','fileUpload',
                                      function($scope,$filter,$myProfileService,$location,$window,$routeParams,$fileUpload) 
                                      {      
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}                        
                                      $scope.user;
                                      $scope.userId;
                                      $scope.currentUser = '';
                                      if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
                                  	{
                                  		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
                                  		
                                  		$scope.LoginStatus = true;

                                  		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
                                  		
                                  		//alert( $location.path() );
                                  		//alert( $location.path().split('/')[2] );
                                  		
                                  		/*if( $location.path() == '/' )
                                  			$location.path('/myprofile');*/
                                  	}
$myProfileService

.userData({"Email" : $scope.currentUser})
.then(
		function(response) {
			//console.log(response);
			$scope.userdetails = response;
			
			console.log( response )
			
			$scope.user = response;
			
			console.log( 'User:' );
			console.log($scope.user);
			
			$scope.userId = $scope.user.userId;
		},
		function(errResponse) {
			console.log('Error fetching User Details');
		});
                                      
                                      $scope.updateUser = function() {
	$scope.userData = {
		UserId : $scope.user.userId,
		Username : $scope.user.username,
		Phone : $scope.user.phone,
		Address: $scope.user.address,
	}
	};
	console.log($scope.userData);
	console.log("in the update user");
	$myProfileService
			.updateUser($scope.userData)
			.then(
					function(response) {
						try {
							$scope.status = response.status;
							
							console.log( $scope.status );
						} catch (e) {
							$scope.data = [];
						}
					},
					function(errResponse) {
						console
								.error('Error while Sending Data.');
					}),                                  
                                      
                         
					$scope.updateUser = function() {
						$scope.userData = {
							UserId : $scope.user.userId,
							Username : $scope.user.username,
							Phone : $scope.user.phone,
							Address: $scope.user.address,
							
						};
						console.log($scope.userData);
						console.log("in the update user");
						$myProfileService
								.updateUser($scope.userData)
								.then(
										function(response) {
											try {
												$scope.status = response.status;
												
												console.log( $scope.status );
											} catch (e) {
												$scope.data = [];
											}
										},
										function(errResponse) {
											console
													.error('Error while Sending Data.');
										});
					},
					
                                      
                                      
                function($scope,$filter,$myProfileService,$location,$window,$routeParams,$fileUpload){
                	console.log("ProfileController.......");
                                		
                	
                	
                	$scope.userImage = '';
                	
                	$scope.currentUserRole = $window.sessionStorage.getItem("currentUserRole");
					}
                	// open File Explorer for seleting file/image
            		$scope.openFileChooser = function() {
            			
            			
            			$('#myfile').trigger('click');
            		};
                    //
                    $scope.picUpdated = false;
                    $scope.picUpdatedWithError = false;
                    $scope.invalidPicType = false;


                    $scope.updatePassword = function() {
            			console.log("in the update password update");
            			$myProfileService
            					.updatePassword(
            							{"Email":$scope.currentUser,"NewPassword":$scope.userdetails.newpassword})
            					.then(
            							function(response) {
            								try {
            									$scope.status = response.status;
            								} catch (e) {
            									$scope.data = [];
            								}
            							},
            							function(errResponse) {
            								console
            										.error('Error while Sending Data.');
            							});
            		}
                    
                    
                  //Upload image 
                    $scope.setFile = function(element) {
                    	
                    	console.log('setFile');
                    	
                    	var user = {'email':$scope.currentUser,'Image':null};
                    	
                    	$scope.currentFile = element.files[0];
                    	var reader = new FileReader();
                    	reader.onload = function(event) {
                    		user.Image = event.target.result
                    		$scope.$apply()
                    	};
                    	// when the file is read it triggers the onload event above.
                    	reader.readAsDataURL($scope.currentFile);
                    	var file = $scope.currentFile;
                    	console.log('file is :');
                    	console.dir(file);
                    	var uploadUrl = "http://localhost:7777/com.samplebackend/updateProfilePicture/";
                    	// calling uploadFileToUrl function of $fileUpload
                    	
                    	$scope.userImage = '';
                    	
                    	var res = $fileUpload
                    			.uploadFileToUrl(file,
                    					user.email,
                    					uploadUrl)
                    			.then(
                    					function(response) {
                    						$scope.status = response.status;
                    						$scope.imagesrc = response.imagesrc;
                    						$scope.picDeleted = false;
                    						//console.log( $scope.response );
                    						//console.log( $scope.imagesrc );
                    						$scope.currentImage = '/'
                    								+ $scope.imagesrc;
                    						$scope.stateDisabled = false;
                    						
                    						window.setTimeout(function(){
                    							$scope.$apply($scope.userImage = 'user_'+$scope.userId+'.jpg' + '?' + new Date().getTime());
                    						},500);
                    						
                    					},
                    					function(errResponse) {
                    						console
                    								.error('Error while Updating User.');
                    					});
                   
                    };
                                      }]);



