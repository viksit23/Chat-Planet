var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    
.when("/signup", {
          templateUrl : "signup/signup.html",
          controller: "signUpController"
    })
    .when("/user", {
          templateUrl : "User/user.html",
          controller: "userController"
    })
    .when("/myprofile", {
          templateUrl : "myprofile/myprofile.html",
          controller: "myProfileController"
    })
    .when("/friend", {
          templateUrl : "friends/friend.html",
          controller: "FriendController"
    })
	.when("/login", {
	        templateUrl : "login/login.html",
	        controller: "loginController"	
	    })
    .when("/home", {
        templateUrl : "home/home.html",
        controller: "homeController"	
    })
    .when("/aboutus", {
        templateUrl : "aboutus/aboutus.html",
        controller: "aboutusController"	
    })
    .when("/contactus", {
        templateUrl : "contactus/contactus.html",
        controller: "contactusController"	
    })
    .when("/blog", {
        templateUrl : "blogs/blog.html",
        controller: "BlogController"	
    })
    .when("/Event", {
        templateUrl : "Event/Event.html",
        controller: "EventController"	
    });
});