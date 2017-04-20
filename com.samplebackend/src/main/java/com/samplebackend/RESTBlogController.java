package com.samplebackend;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.Blog.Blog;
import com.samplebackend.Blog.BlogDAO;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
@RequestMapping("/")
public class RESTBlogController {

	@Autowired
	UserDAO userdao;

	@Autowired
	BlogDAO blogdao;
	
	@RequestMapping(value = "/postblog" , method = RequestMethod.POST)
	public ResponseEntity<String> postBlog1(@RequestBody JSONObject data) {
		System.out.println(data);

		User user = userdao.getUserByEmail( data.get("Email").toString() );
		
		long userid = user.getUserId();

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 */
//		Date date = new Date();
//		System.out.println(date);

		Blog blog = new Blog();

		blog.setTitle(data.get("BlogTitle").toString());
		blog.setDescription(data.get("BlogDesc").toString());
		//blog.setBlogdate(date);

		blog.setUserId(user);

		blogdao.addBlog(blog);

		JSONObject json = new JSONObject();

		json.put("status", "BLOG SEND FOR APPROVAL");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
}
