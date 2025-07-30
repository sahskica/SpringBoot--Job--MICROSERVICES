package com.jobapp.jobms.job;
import java.util.List;

import com.jobapp.jobms.dto.JobWithCompanyDTO;

public interface JobService {
	
	List<JobWithCompanyDTO> findAll();
	void creteJob(Job job);
	Job getJobById(Long id);
	boolean deleteJob(Long id);
	boolean updateJob(Long id, Job updatedJob);

}
