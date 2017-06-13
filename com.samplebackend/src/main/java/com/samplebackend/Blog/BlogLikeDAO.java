package com.samplebackend.Blog;

import java.util.List;

public interface BlogLikeDAO {

	public void addBlogLike(BlogLike blogLike);
	
	public List<BlogLike> listBlogLike();
}
