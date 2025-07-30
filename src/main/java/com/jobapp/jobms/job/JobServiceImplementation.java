package com.jobapp.jobms.job;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class JobServiceImplementation implements JobService{
	
	//public List<Job> jobs = new ArrayList<>();
	
	JobRepository jobRepository;

	


	public JobServiceImplementation(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}

	@Override
	public void creteJob(Job job) {
	
		jobRepository.save(job);
		
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
		
	}

	@Override
	public boolean deleteJob(Long id) {
		
		try {
		jobRepository.deleteById(id);
		return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJob(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		
			if (jobOptional.isPresent()) {
				Job job = jobOptional.get();
		
				job.setTitle(updatedJob.getTitle());
				job.setDescription(updatedJob.getDescription());
				job.setMaxSalary(updatedJob.getMaxSalary());
				job.setMinSalary(updatedJob.getMinSalary());
				job.setLocation(updatedJob.getLocation());
				jobRepository.save(job);
				return true;
				}
		return false;
		}

}
