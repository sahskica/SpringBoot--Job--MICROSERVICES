package com.jobapp.jobms.job;
import java.util.List;

public interface JobService {
	
	List<Job> findAll();
	void creteJob(Job job);
	Job getJobById(Long id);
	boolean deleteJob(Long id);
	boolean updateJob(Long id, Job updatedJob);

}
