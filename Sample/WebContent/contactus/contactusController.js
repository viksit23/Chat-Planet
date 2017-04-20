app.controller('contactusController',[ '$scope' ,'$window' , function($scope ,$window) 
{
	console.log('contactController');
	
	if( document.URL.endsWith('/#/contactus') ){$('#body').css({'background': "url('resources/images/bg6.jpg')"});}else{$('#body').css({'background': "none"});}
}]);