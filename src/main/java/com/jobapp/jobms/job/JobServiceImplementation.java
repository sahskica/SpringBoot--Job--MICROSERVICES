package com.jobapp.jobms.job;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobapp.jobms.dto.JobWithCompanyDTO;
import com.jobapp.jobms.external.company.Company;

@Service
public class JobServiceImplementation implements JobService{
	
	//public List<Job> jobs = new ArrayList<>();
	
	JobRepository jobRepository;

	


	public JobServiceImplementation(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobWithCompanyDTO> findAll() {
		List<Job> jobs = jobRepository.findAll();
					
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private JobWithCompanyDTO convertToDto(Job job) {
		RestTemplate restTemplate = new RestTemplate();
		JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
		jobWithCompanyDTO.setJob(job);
		
		Company company = restTemplate.getForObject(
				"http://localhost:8081/companies/" + job.getCompanyId(), 
				Company.class);
		
		jobWithCompanyDTO.setCompany(company);
		
		return jobWithCompanyDTO;
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
