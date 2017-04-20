app.controller('homeController',[ '$scope' ,'$window' , function($scope ,$window) 
{
	console.log('homeController');
	
	if( document.URL.endsWith('/#/home') ){$('#body').css({'background': "url('resources/images/bg2.jpg')"});}else{$('#body').css({'background': "none"});}
}]);