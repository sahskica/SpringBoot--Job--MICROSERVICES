package com.jobapp.jobms.dto;

import com.jobapp.jobms.external.company.Company;
import com.jobapp.jobms.job.Job;

public class JobWithCompanyDTO {
	private Job job;
	private Company company;
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
