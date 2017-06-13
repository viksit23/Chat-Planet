package com.samplebackend.Blog;

import java.util.List;

public interface BlogDataDAO {

	public void addBlogData(BlogData blogData);
	
	public BlogData getBlogDataById(int blogdataid);
	
	public List<BlogData> listBlogData();
	
	public void deleteBlogData(BlogData blogdata);
}
