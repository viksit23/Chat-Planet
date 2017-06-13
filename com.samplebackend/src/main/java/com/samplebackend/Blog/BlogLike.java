package com.samplebackend.Blog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.samplebackend.user.User;
@Entity
public class BlogLike {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long blogLikeId;
	
	private String blogLike;
	
	@ManyToOne
	@JoinColumn(name="blogId")
	private Blog blogId;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private User userId;
	
	private Date likeDate;

	public long getBlogLikeId() {
		return blogLikeId;
	}

	public void setBlogLikeId(long blogLikeId) {
		this.blogLikeId = blogLikeId;
	}

	public String getBlogLike() {
		return blogLike;
	}

	public void setBlogLike(String blogLike) {
		this.blogLike = blogLike;
	}

	public Blog getBlogId() {
		return blogId;
	}

	public void setBlogId(Blog blogId) {
		this.blogId = blogId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	
	
}
