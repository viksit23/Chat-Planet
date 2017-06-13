package com.samplebackend;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.samplebackend.Blog.Blog;
import com.samplebackend.Blog.BlogDAO;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
public class RESTController {

	@Autowired
	UserDAO userdao;
	
	@Autowired
	BlogDAO blogdao;
	
	@Autowired
	ServletContext context;
	

	@RequestMapping(value = "/adduser" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addUser(@RequestBody String data) {
		System.out.println(data);

		JSONParser jpar=new JSONParser();
		
		JSONObject jobj =new JSONObject(); 
		try{
		jobj=(JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		
		List<User> listuser = userdao.listUser();

		boolean usermatch = false;

		for (User u : listuser) {
			if (u.getEmail().equals(jobj.get("Email").toString() )) {
				usermatch = true;
				break;
			}
		}
		if (usermatch == false) {
			User u = new User();
			
			
			u.setCpassword(jobj.get("ConfirmPassword").toString());
			
			u.setEmail(jobj.get("Email").toString());
			
			
			u.setPassword(jobj.get("Password").toString());
			u.setPhone(jobj.get("Phone").toString());
			
			u.setUsername(jobj.get("Username").toString());
			u.setAddress(jobj.get("Address").toString());
			userdao.addUser(u);
			
			json.put("msg", "User Added Successfully");
			
		} else {
			json.put("msg", "User Already Exists");
		}
		
		
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="getuser", method=RequestMethod.POST)
	public ResponseEntity<String> getFriends (@RequestBody String data){
		
		System.out.println(data);
		
		/*System.out.println(data.get("Email"));
		
		User user = userdao.getUserByEmail( data.get("Email").toString() );
		long userId = user.getUserId();
		
		List<User> list = userdao.listUser();
		
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);*/
		
		//System.out.println(new Gson().toJson(userdao.getAllUserExceptMe(data)));
		
		return new ResponseEntity<String>( new Gson().toJson(userdao.getAllUserExceptMe(data)) , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userdata" , method = RequestMethod.POST)
	public ResponseEntity<String> UserProfile(@RequestBody String data1) {

		System.out.println(data1);
		
		JSONObject data = new JSONObject();
		try {
			data = (JSONObject)new JSONParser().parse(data1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String username = data.get("Email").toString();
		
		User user = userdao.getUserByEmail(username);
		
		System.out.println(user);
		
		return new ResponseEntity<String>(new Gson().toJson(user), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateuser" , method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody String data3) {
		
		JSONObject data2 = new JSONObject();
		try {
			data2 = (JSONObject)new JSONParser().parse(data3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data2);

		int userid = Integer.parseInt(data2.get("UserId").toString());

		User user = userdao.getUserById(userid);

		user.setUserId(userid);
		user.setUsername(data2.get("Username").toString());
		user.setPhone(data2.get("Phone").toString());
		user.setAddress(data2.get("Address").toString());
		
		userdao.updateUser(user);

		JSONObject json = new JSONObject();

		json.put("status", "Details Updated");
		System.out.println(json.toString());

		return new ResponseEntity<String>( json.toJSONString() , HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value = "/updateProfilePicture/" , method = RequestMethod.POST)
	public ResponseEntity<String> updateProfilePicture(MultipartHttpServletRequest request,
			HttpServletResponse response, UriComponentsBuilder ucBuilder) {

		System.out.println(request.getHeader("user"));

		System.out.println(request.getFile("file").getName());
		System.out.println(request.getFile("file").getSize());
		System.out.println(request.getFile("file").getContentType());
		System.out.println(request.getFile("file").getOriginalFilename());

		JSONObject json = new JSONObject();

		BufferedOutputStream stream = null;

		try {
			String path = context.getRealPath("/");

			System.out.println(path);

			File directory = null;

			System.out.println(request.getFile("file"));

			if (request.getFile("file").getContentType().contains("image")) {
				directory = new File(path + "\\resources\\images");

				System.out.println(directory);

				byte[] bytes = null;
				File file = null;
				bytes = request.getFile("file").getBytes();

				if (!directory.exists())
					directory.mkdirs();

				User u = userdao.getUserByEmail(request.getHeader("user"));
				
				file = new File(directory.getAbsolutePath() + System.getProperty("file.separator")
						+ "user_" + u.getUserId() + ".jpg");

				System.out.println(file.getAbsolutePath());

				stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(bytes);
				
				json.put("status", "Image Uploaded");

				stream.close();

				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/loginuser" , method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody JSONObject data, Principal p) {
		System.out.println(data);

		JSONObject json = new JSONObject();
		
		List<User> listuser = userdao.listUser();

		boolean usermatch = false;

		for (User u : listuser) {
			if (u.getEmail().equals(data.get("Email").toString() ) && u.getPassword().equals(data.get("Password").toString()) ) {
				usermatch = true;
				break;
			}
		}
		if (usermatch == false) {
			json.put("msg", "Invalid Login");
			
		} else {
			User u = userdao.getUserByEmail(data.get("Email").toString());
			
			json.put("msg", "Login Successful");
			json.put("role", u.getRole());
			
		}
		
		System.out.println(json.toString());
		
		
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value= "/updatepassword" , method = RequestMethod.POST)
	public ResponseEntity<String> UserPasswordUpdate(@RequestBody JSONObject data) {

		System.out.println(data.get("Email"));
		System.out.println(data.get("NewPassword"));

		User user = userdao.getUserByEmail(data.get("Email").toString());
		user.setPassword(data.get("NewPassword").toString());
		userdao.updateUser(user);

		JSONObject json = new JSONObject();

		json.put("status", "Password Updated");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.ACCEPTED);
	}
	
	
}
