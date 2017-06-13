package com.samplebackend.Interested;

import java.util.List;

import com.samplebackend.Blog.Blog;

public interface InterestedDAO {
	public void addInterested(Interested interested);
	public void deleteInterested(Interested interested);
	public List<Interested> listInterested();
	
}
