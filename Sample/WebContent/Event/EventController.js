app.controller('EventController',[ '$scope' , 'EventService' ,'$window' , function($scope , $EventService,$window)
{
	if( $window.sessionStorage.getItem("currentUser") != null && $window.sessionStorage.getItem("currentUser") != undefined )
	{
		$scope.currentUserRole = $window.sessionStorage.getItem("currentUserRole");
		console.log($scope.currentUserRole);
		$scope.LoginEmail = $window.sessionStorage.getItem("currentUser");
		
		$scope.LoginStatus = true;

		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
		
		
	}
	
	
	console.log('Event Controller');
	
	$scope.postEvent = function() {

		console.log("in the post event");
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
	
	
	
	$EventService.getEvents().then(function(response) {
		console.log("get events");
		$scope.events = response;
		console.log($scope.events);
	}, function(errResponse) {
		console.log('Error fetching Events');
	});
	
	

	
	$scope.deleteEvent = function(eventId) {

		console.log("in the delete event");
		
		$EventService.deleteEvent(eventId).then(
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
	
	$scope.editedItem = {};

	$scope.editrow = function($index) {
		$scope.istrue = true;
		$scope.$index = $index;
		angular.copy($scope.events[$index], $scope.editedItem);
		console.log($scope.editedItem);
	}
	
	
	$scope.updateEvent = function(eventId) {
		window.setTimeout(function(){
		console.log("in the edit event");
		this.eventInfo = {
			EventId : $scope.editedItem.eventId,
			EventTitle : $scope.editedItem.title,
			EventDesc : $scope.editedItem.description,
			EventDateFrom : $scope.editedItem.eventFrom,
			EventDateTo : $scope.editedItem.eventTo,
		};
		
		console.log(this.eventInfo);
		$EventService.updateEvent(this.eventInfo).then(
				function(response) {						
				$scope.events = response;
				$('#editeventclose').click();
				
				}, function(errResponse) {
					console.error('Error while Sending Data.');
				});
		},500);
	}
	
/*join event*/
	
	$EventService.getEvents().then(function(response) {
		console.log('in get events');

		$scope.Event = response;
	}, function(errResponse) {
		console.log('Error fetching Users');
	});
	
	
	$scope.applyEvent = function(eventId) {

		console.log("in the apply job");
		
		this.AppliedEvent= {
				User : $scope.currentUser,
				EventID : eventId,
			};
		$EventService.applyEvent(this.AppliedEvent).then(function(response) {
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
])