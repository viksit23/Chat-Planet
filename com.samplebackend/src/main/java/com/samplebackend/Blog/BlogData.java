package com.samplebackend.Blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class BlogData {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long BlogDataId;
	
	@ManyToOne
	@JoinColumn(name="blogId")
	private Blog BlogId;
	
	@Type(type="text")
	private String blogData;

	public long getBlogDataId() {
		return BlogDataId;
	}

	public void setBlogDataId(long blogDataId) {
		BlogDataId = blogDataId;
	}

	public Blog getBlogId() {
		return BlogId;
	}

	public void setBlogId(Blog blogId) {
		BlogId = blogId;
	}

	public String getBlogData() {
		return blogData;
	}

	public void setBlogData(String blogData) {
		this.blogData = blogData;
	}
	
}
