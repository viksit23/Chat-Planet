app.controller('BlogController',[ '$scope' , 'BlogService' ,'$window' , function($scope , $BlogService,$window) 
{
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
	
	$scope.user = {};
	
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
	
	$scope.getAllBlogs = function() {
		$BlogService.getAllBlogs().then(function(response) {
			$scope.allblogs = response;
			console.log('All Blogs');
			console.log( $scope.allblogs );
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	$scope.getAllBlogs();
	
	$BlogService.getBlogs().then(function(response) {
		$scope.blogs = response;
		console.log("Blogs:");
		console.log( $scope.blogs  );
		/*$scope.getBlogsData();*/
	}, function(errResponse) {
		console.log('Error fetching Users');
	});
	
	$scope.getBlogsData = function() {
		console.log("controller blog data");
		$BlogService.getBlogData().then(function(response) {
			$scope.blogsdata = response;
			console.log("Blogs2:");
			console.log($scope.blogsdata);
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	
	$scope.publishBlog = function(blogId) {
		console.log(blogId);
		$BlogService.publishBlog(blogId).then(function(response) {
			$scope.status = response.status;
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	
	$scope.unpublishBlog = function(blogId) {
		console.log(blogId);
		$BlogService.unpublishBlog(blogId).then(function(response) {
		
			$scope.status = response.status;
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	

	//get blogs data
	
	$scope.getBlogsData = function() {
		$BlogService.getBlogData().then(function(response) {
			$scope.blogsdata = response;
			console.log("Blogs2:");
			console.log($scope.blogsdata);
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	//add blog data
	$scope.addBlogData = function() {
		console.log("in the post blog");
		$scope.UserBlogData = {
			//id of the selected blog
			BlogID : $scope.selectedBlog,
			BlogData : $scope.user.blogData
		};
		console.log($scope.UserBlogData);
		$BlogService.addBlogData($scope.UserBlogData).then(
				function(response) {
					$scope.status = response.status;
					$scope.getBlogsData();
				}, function(errResponse) {
					console.log('Error fetching Users');
				});
	}
	//delete blog data
	$scope.deleteBlogData = function(blogDataId) {
		console.log(blogDataId);
		$BlogService.deleteBlogData(blogDataId).then(
				function(response) {
					$scope.status = response.status;
					$scope.getBlogsData();
				}, function(errResponse) {
					console.log('Error fetching Users');
				});
	}
	
	$scope.postComment = function(post) {
		console.log("in the post comment");
		this.BlogInfo = {
			User : $scope.currentUser,	
			BlogID : $scope.selectedBlog,
			Comment : post.comment
		};
		console.log(this.BlogInfo);

		$BlogService.postComment(this.BlogInfo).then(
				function(response) {
					try {
						console.log("response:");
						console.log(response);
						$scope.comments = response;
						} catch (e) {
						$scope.data = [];
					}

				}, function(errResponse) {
					console.error('Error while Sending Data.');
				});

	}
	
	/*like*/
	$scope.postLike = function(post) {
		console.log("in the post like");
		this.BlogInfo = {
			User : $scope.currentUser,	
			BlogID : $scope.selectedBlog,
			like : post.like
		};
		$BlogService.postLike(this.BlogInfo).then(
				function(response) {
					try {
						console.log("response:");
						console.log(response);
						$scope.comments = response;
						} catch (e) {
						$scope.data = [];
					}

				}, function(errResponse) {
					console.error('Error while Sending Data.');
				});
		
		
	}
	
	$scope.getBlogsLike = function() {
		$BlogService.getBlogLike().then(function(response) {
			$scope.blogslike = response;
			console.log("Blogs2:");
			console.log($scope.blogslike);
		}, function(errResponse) {
			console.log('Error fetching Users');
		});
	}
	
	
	
	//update blog data
	
	$scope.updateBlogData = function(blogId) {
		$scope.istrue = false;
		console.log("in the edit blog data");
		$scope.UpdateUserBlogData = {
			//id of the selected blog data
			BlogID : $scope.editedItem.blogDataId,
			UpdatedBlogData : $scope.editedItem.blogData,
		};
		console.log($scope.UpdateUserBlogData);
		$BlogService.updateBlogData($scope.UpdateUserBlogData)
				.then(function(response) {
					$scope.status = response.status;
					$scope.getBlogsData();
				}, function(errResponse) {
					console.log('Error fetching data');
				});
	}
	//set the id to current blog used to add data to the blog
	$scope.setBlogId = function(blogId) {
		$scope.selectedBlog = blogId;
	}
	
}
]);