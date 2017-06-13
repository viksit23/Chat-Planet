app.controller('jobController',[ '$scope' , 'jobService' ,'$window' , function($scope , $jobService,$window)
{
	if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
	{
		$scope.currentUserRole = $window.sessionStorage.getItem("currentUserRole");
		console.log($scope.currentUserRole);
		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
		
		$scope.LoginStatus = true;

		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
		
		
	}
	
	$scope.user = {};
	
	console.log("job controller");
	
	$scope.editedItem = {};

	$scope.editrow = function($index) {
		$scope.istrue = true;
		$scope.$index = $index;
		angular.copy($scope.jobs[$index], $scope.editedItem);
		console.log($scope.editedItem);
	}
	
	
	$scope.postJob = function() {

		console.log("in the post job");
		$scope.JobInfo = {
			JobTitle : $scope.user.jobTitle,
			JobQual : $scope.user.jobQual,
			JobDesc : $scope.user.jobDesc
		};

		$jobService.postJob($scope.JobInfo).then(
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
	/*apply for job*/
	
	$jobService.getJobs().then(function(response) {
		console.log('in get jobs');

		$scope.jobs = response;
	}, function(errResponse) {
		console.log('Error fetching Users');
	});$scope.applyJob = function(jobId) {

		console.log("in the apply job");
		
		this.AppliedJob= {
				User : $scope.currentUser,
				JobID : jobId,
			};
		$jobService.applyJob(this.AppliedJob).then(function(response) {
			try {
				$scope.status = response.status;
			} catch (e) {
				$scope.data = [];
			}

		}, function(errResponse) {
			console.error('Error while Sending Data.');
		});

	}
	
	
	
	/*delete job*/

	$scope.deleteJob = function(jobId) {

		console.log("in the delete blog");

		$jobService.deleteJob(jobId).then(function(response) {
			try {
				$scope.jobs = response;
			} catch (e) {
				$scope.data = [];
			}

		}, function(errResponse) {
			console.error('Error while Sending Data.');
		});

	}

	
	
	/*edit job*/
	
	$scope.editJob = function(jobId) {
		
		window.setTimeout(function(){
			
		
		$scope.istrue = false;

		console.log("in the edit blog data");
		this.editJobData = {
			
			JobID : jobId,
			JobTitle : $scope.editedItem.title,
			JobQual : $scope.editedItem.qualification,
			JobDesc : $scope.editedItem.description,
			
		};

		console.log(this.editJobData);

	 	$jobService.editJob(this.editJobData)
				.then(function(response) {
					$scope.jobs = response;
					console.log($scope.jobs );
					
				}, function(errResponse) {
					console.log('Error fetching data');
				}); 
		},500);
		
	}
	
	
	

	
	
	
}

]);