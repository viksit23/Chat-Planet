app.controller('EventController',[ '$scope' , 'EventService' ,'$window' , function($scope , $EventService,$window)
{
	console.log('Event Controller');
	
	$scope.postEvent = function() {

		console.log("in the post blog");
		$scope.eventInfo = {
			EventTitle : $scope.user.eventTitle,
			EventDesc : $scope.user.eventDesc,
			EventDateFrom : $scope.user.eventDateFrom,
			EventDateTo : $scope.user.eventDateTo,
		};
		
		$EventService.postEvent($scope.eventInfo).then(
				function(response) {
					try {
						$scope.events = response;
					} catch (e) {
						$scope.data = [];
					}

				}, function(errResponse) {
					console.error('Error while Sending Data.');
				});

	}
	
}
])