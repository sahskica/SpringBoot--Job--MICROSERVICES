package com.jobapp.jobms.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	public JobService service;
	
	
	public JobController(JobService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		service.creteJob(job);
		//Company c = job.getCompany();
		return new ResponseEntity<>("Job added successfuly", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		
		Job job = service.getJobById(id);
		if (job != null) 
		return new ResponseEntity<>(job, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		boolean deleted = service.deleteJob(id);
		if (deleted)
			return new ResponseEntity<>("Job deleted successfuly", HttpStatus.OK);
		 return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob ){
		boolean updated = service.updateJob(id, updatedJob);
		if (updated)
			return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
