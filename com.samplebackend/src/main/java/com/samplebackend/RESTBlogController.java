package com.samplebackend;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.Blog.Blog;
import com.samplebackend.Blog.BlogComment;
import com.samplebackend.Blog.BlogCommentDAO;
import com.samplebackend.Blog.BlogDAO;
import com.samplebackend.Blog.BlogData;
import com.samplebackend.Blog.BlogDataDAO;
import com.samplebackend.Blog.BlogLike;
import com.samplebackend.Blog.BlogLikeDAO;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
@RequestMapping("/")
public class RESTBlogController {

	@Autowired
	UserDAO userdao;

	@Autowired
	BlogDAO blogdao;
	
	@Autowired
	BlogDataDAO blogdatadao;
	
	@Autowired
	BlogCommentDAO blogcommentmdao;
	
	@Autowired
	BlogLikeDAO bloglikemdao;
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

		Date date = new Date();
		
		blog.setTitle(data.get("BlogTitle").toString());
		blog.setDescription(data.get("BlogDesc").toString());
		blog.setBlogdate(date);

		blog.setUserId(user);

		blogdao.addBlog(blog);

		JSONObject json = new JSONObject();

		json.put("status", "BLOG SEND FOR APPROVAL");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/allblogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> allBlogs() {

		List<Blog> list = blogdao.listAllBlogs();
		System.out.println(list);

		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> blogs() {

		List<Blog> list = blogdao.listBlogs();
		System.out.println(list);

		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getblogdata" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getBlogData(){
		System.out.println("inside blog data");
		List<Blog> list = blogdao.listBlogs();
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/publishblog" , method = RequestMethod.POST)
	public ResponseEntity<String> publishBlog(@RequestBody String inputdata) {
		System.out.println(inputdata);

		int blogid = Integer.parseInt(inputdata);

		System.out.println(blogid);

		Blog blog = blogdao.getBlogById(blogid);

		blog.setPosted(true);

		// update blog
		blogdao.addBlog(blog);

		JSONObject json = new JSONObject();

		json.put("status", "BLOG PUBLISHED");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/unpublishblog" , method = RequestMethod.POST)
	public ResponseEntity<String> unPublishBlog(@RequestBody String inputdata) {
		System.out.println(inputdata);

		int blogid = Integer.parseInt(inputdata);

		System.out.println(blogid);

		Blog blog = blogdao.getBlogById(blogid);

		blog.setPosted(false);

		// update blog
		blogdao.addBlog(blog);

		JSONObject json = new JSONObject();

		json.put("status", "BLOG UNPUBLISHED");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/addblogdata" , method = RequestMethod.POST)
	public void addBlogData(@RequestBody JSONObject data){
		
		System.out.println(data);
		BlogData blogData = new BlogData();
		blogData.setBlogData(data.get("BlogData").toString());
		Blog b = new Blog();
		String selectedBlogId = data.get("BlogID").toString();
		b.setBlogId(Integer.parseInt(selectedBlogId));
		blogData.setBlogId(b);
		
		blogdatadao.addBlogData(blogData);
	}
	

	@RequestMapping(value="/blogpostcomment", method=RequestMethod.POST)
	public ResponseEntity<List<BlogComment>> blogPostComment(@RequestBody JSONObject data){
		
		System.out.println(data);
		
		User user = userdao.getUserByEmail(data.get("User").toString());
		Blog blog = blogdao.getBlogById(Integer.parseInt(data.get("BlogID").toString()));
		Date date = new Date();
		
		System.out.println(data.get("BlogID").toString());
		System.out.println(data.get("Comment").toString());
		
		BlogComment blogComment = new BlogComment();
		blogComment.setBlogComment(data.get("Comment").toString());
		blogComment.setUserId(user);
		blogComment.setBlogId(blog);
		blogComment.setCommentDate(date);
		
		blogcommentmdao.addBlogComment(blogComment);
		
		List<BlogComment> list = blogcommentmdao.listBlogComment();
		
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	}
	
	@RequestMapping("/listpostcomments")
	public ResponseEntity<List<BlogComment>> listForumComments(){
		List<BlogComment> list = blogcommentmdao.listBlogComment();
		
		return new ResponseEntity<List<BlogComment>>(list, HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/blogpostlike", method=RequestMethod.POST)
	public ResponseEntity<List<BlogLike>> blogPostLike(@RequestBody JSONObject data){
		
		System.out.println(data);
		
		User user = userdao.getUserByEmail(data.get("User").toString());
		Blog blog = blogdao.getBlogById(Integer.parseInt(data.get("BlogID").toString()));
		Date date = new Date();
		
		System.out.println(data.get("BlogID").toString());
		//System.out.println(data.get("Comment").toString());
		
		BlogLike blogLike = new BlogLike();
		blogLike.setUserId(user);
		blogLike.setBlogId(blog);
		blogLike.setLikeDate(date);
		blogLike.setBlogLike("1");
		
		bloglikemdao.addBlogLike(blogLike);
		
	List<BlogLike> list = bloglikemdao.listBlogLike();
		
		return new ResponseEntity<List<BlogLike>>(list, HttpStatus.OK);
	
		
		
		}
	@RequestMapping(value = "/getbloglike" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogLike>> getBlogLike(){
		System.out.println("inside blog data");
		List<BlogLike> list = bloglikemdao.listBlogLike();
		return new ResponseEntity<List<BlogLike>>(list,HttpStatus.OK);
	}
	
}
