package com.samplebackend;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samplebackend.job.Job;
import com.samplebackend.job.JobDAO;
import com.samplebackend.job.JobUser;
import com.samplebackend.job.JobUserDAO;
import com.samplebackend.user.User;
import com.samplebackend.user.UserDAO;

@RestController
public class RESTJobController {

	@Autowired
	JobDAO jobdao;
	@Autowired
	UserDAO userdao;
	@Autowired
	JobUserDAO jobuserdao;
	
	@RequestMapping(value="/postjob", method=RequestMethod.POST)
	public ResponseEntity<String> postBlog(@RequestBody JSONObject data) {
		System.out.println(data);

		Date date = new Date();
		System.out.println(date);

		Job job = new Job();

		job.setTitle(data.get("JobTitle").toString());
		job.setDescription(data.get("JobDesc").toString());
		job.setQualification(data.get("JobQual").toString());
		job.setPostdate(date);

		jobdao.addJob(job);

		JSONObject json = new JSONObject();

		json.put("status", "Job is Posted");
		System.out.println(json.toString());

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping("/viewjobs")
	public ResponseEntity<List<Job>> jobs() {

		List<Job> list = jobdao.listJobs();

		return new ResponseEntity<List<Job>>(list, HttpStatus.OK);

	}
	
	@RequestMapping(value="/applyjob", method=RequestMethod.POST)
	public ResponseEntity<String> applyjob(@RequestBody JSONObject data){
		

		System.out.println(data);
		
		Job job = jobdao.getJobById(Integer.parseInt((data.get("JobID").toString())));
		User user2 = userdao.getUserByEmail(data.get("User").toString());
		
		JobUser jobUser = new JobUser();
		
		jobUser.setJobId(job);
		jobUser.setUserId(user2);
		
		
		jobuserdao.addJobApplied(jobUser);
		
		JSONObject json = new JSONObject();

		json.put("status", "Job is Applied");
		System.out.println(json.toString());
		
		

		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping("/deletejob/{jobId}")
	public ResponseEntity<List<Job>> deleteEvent(@PathVariable("jobId") int jobId) {
		
		Job job = jobdao.getJobById(jobId);
		
		job.setPosted(false);
		
		jobdao.addJob(job);
		
		List<Job> list = jobdao.listJobs();
		
		return new ResponseEntity<List<Job>>(list, HttpStatus.OK);	
		
	}
	
	@RequestMapping(value="/editjob", method=RequestMethod.POST)
	public ResponseEntity<List<Job>> editJob(@RequestBody JSONObject data){
		
		System.out.println(data);
		
	
		Job job = jobdao.getJobById(Integer.parseInt(data.get("JobID").toString()));
		
		job.setJobId(Integer.parseInt(data.get("JobID").toString()));
		job.setTitle(data.get("JobTitle").toString());
		job.setDescription(data.get("JobDesc").toString());
		job.setQualification(data.get("JobQual").toString());
		
		System.out.println(data.get("JobID").toString());
		
		jobdao.addJob(job);
		
		List<Job> list = jobdao.listJobs();
		
		return new ResponseEntity<List<Job>>(list, HttpStatus.OK);	
		
	}
	
}
