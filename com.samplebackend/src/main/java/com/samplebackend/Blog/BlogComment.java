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
public class BlogComment {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long blogCommentId;
	
	private String blogComment;
	
	@ManyToOne
	@JoinColumn(name="blogId")
	private Blog blogId;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private User userId;
	
	private Date commentDate;

	public long getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(long blogCommentId) {
		this.blogCommentId = blogCommentId;
	}

	public String getBlogComment() {
		return blogComment;
	}

	public void setBlogComment(String blogComment) {
		this.blogComment = blogComment;
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

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	
}
