package com.samplebackend;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.friend.Friend;
import com.samplebackend.friend.FriendDAO;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
public class RESTFriendController {

	@Autowired
	UserDAO userdao;
	
	@Autowired
	FriendDAO frienddao;
	
	@RequestMapping(value="/getusers", method=RequestMethod.POST)
	public ResponseEntity<List<User>> getAllUsers(@RequestBody JSONObject data){
		
		List<User> list = userdao.getAllUserExceptMe( data.get("Email").toString() );
		
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
	@RequestMapping(value="/friendrequest", method=RequestMethod.POST)
	public ResponseEntity<String> friendRequest(@RequestBody JSONObject data){
		
		String friendId = data.get("FriendId").toString();
		
		System.out.println("json data"+data);
		
		System.out.println("friend Id : "+friendId);
		
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		User friend = userdao.getUserById(Integer.parseInt(friendId));
		
		Friend friendobj = new Friend();
		
		friendobj.setUserId(user);
		friendobj.setFriendId(friend);
		friendobj.setStatus("SENT");
		
		
		frienddao.addFriend(friendobj);
		
		friendobj.setUserId(friend);
		friendobj.setFriendId(user);
		
		
		frienddao.addFriend(friendobj);
		
		JSONObject json = new JSONObject();

		json.put("status", "FRIEND REQUEST SEND");
		System.out.println(json.toString());
		
		return new ResponseEntity<String>(json.toString(),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/friendrequests", method=RequestMethod.POST)
	public ResponseEntity<List<Friend>> friendRequest1( @RequestBody JSONObject data){
		
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		long id = user.getUserId();
		System.out.println(id);
		List<Friend> list  = frienddao.getFriendRequsts(id);
		
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptfriendrequest/{friendId}", method=RequestMethod.POST)
	public ResponseEntity<List<Friend>> acceptFriendRequest(@PathVariable("friendId") long friendId, @RequestBody JSONObject data){
		
		System.out.println(friendId);
		System.out.println(data.get("Email").toString());
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		
		long loggedInUserId = user.getUserId();
		
		System.out.println( loggedInUserId );
		
		//friend x-->y
		Friend friend1 = frienddao.getFriend(loggedInUserId, friendId);
		friend1.setStatus("ACCEPTED");
		frienddao.updateFriend(friend1);
		
		//friend y-->x
		Friend friend2 = frienddao.getFriend(friendId, loggedInUserId);
		friend2.setStatus("ACCEPTED");
		frienddao.updateFriend(friend2);
		
		List<Friend> list = frienddao.getFriendRequsts(loggedInUserId);
	
		return new ResponseEntity<List<Friend>>(list,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/rejectfriendrequest", method=RequestMethod.POST)
	public ResponseEntity<List<Friend>> rejectFriendRequest( @RequestBody JSONObject data){
		
		long friendId = Long.parseLong(data.get("FriendId").toString());
		
		System.out.println(friendId);
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		
		long loggedInUserId = user.getUserId();
		
		//friend x-->y
		frienddao.delFriend(loggedInUserId, friendId);
				
		//friend y-->x
		frienddao.delFriend( friendId , loggedInUserId);
				
		
		List<Friend> list = frienddao.getFriendRequsts(loggedInUserId);
	
		return new ResponseEntity<List<Friend>>(list,HttpStatus.CREATED);
	}
	@RequestMapping(value="/removefriendrequest/{friendId}", method=RequestMethod.POST)
	public ResponseEntity<List<Friend>> removeFriendRequest(@PathVariable("friendId") long friendId,  @RequestBody JSONObject data){
		
		System.out.println(friendId);
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		
		long loggedInUserId = user.getUserId();
		
		//friend x-->y
		frienddao.delFriend(loggedInUserId, friendId);
		
		//friend y-->x
		frienddao.delFriend( friendId , loggedInUserId);
		
		List<Friend> list = frienddao.getFriendRequsts(loggedInUserId);
	
		return new ResponseEntity<List<Friend>>(list,HttpStatus.CREATED);
	}
	@RequestMapping(value="getfriends", method=RequestMethod.POST)
	public ResponseEntity<List<Friend>> getFriends(@RequestBody JSONObject data){
		
		String email = data.get("Email").toString();
		
		System.out.println(email);
		
		User user = userdao.getUserByEmail( email );
		long userId = user.getUserId();
		
		List<Friend> list = frienddao.listFriends(userId);
		
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
	}
}
