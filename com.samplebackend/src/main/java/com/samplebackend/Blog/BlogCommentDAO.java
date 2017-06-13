package com.samplebackend.Blog;

import java.util.List;

public interface BlogCommentDAO {


	public void addBlogComment(BlogComment blogComment);
	
	public List<BlogComment> listBlogComment();
	
}
