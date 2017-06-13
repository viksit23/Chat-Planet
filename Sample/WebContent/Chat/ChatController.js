app.controller('ChatController',['$scope','ChatService','$location','$window','$routeParams',
                                 
    function($scope,$ChatService,$location,$window,$routeParams){
		console.log("ChatController.......");
		
		if($routeParams.secondUser != undefined)
		{
				$window.sessionStorage.setItem("secondUser",$routeParams.secondUser);
		}

		$scope.currentUser = $window.sessionStorage.getItem("currentUser");
		
		
		
		$scope.messages = [];
	    $scope.message = "";
	    $scope.max = 140;
	    $scope.userid = "";
	    $scope.friendid="";
	    
	   
	    
	    $scope.addMessage = function() {
	      $ChatService.send($scope.message,$scope.secondUser);
	      $scope.message = "";
	    };
	    
	    $ChatService.receive().then(null, null, function(message) {
	      $scope.messages.push(message);
	      //alert('Message');
	    });
		//
	}]);