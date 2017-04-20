app.controller('aboutusController',[ '$scope' ,'$window' , function($scope ,$window) 
{
	console.log('aboutusController');
	
	if( document.URL.endsWith('/#/aboutus') ){$('#body').css({'background': "url('resources/images/bg6.jpg')"});}else{$('#body').css({'background': "none"});}
}]);