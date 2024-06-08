package Job.ServiceImpl;


import Job.External.Company;
import Job.External.Review;
import Job.Model.Job;
import Job.Repo.JobRepo;
import Job.Service.JobService;
import Job.clients.CompanyClient;
import Job.clients.ReviewClient;
import Job.dto.JobDTO;
import mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpMethod;
import java.util.List;


import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl  implements JobService {

	private JobRepo jobRepo;

	@Autowired
	private RestTemplate restTemplate;
	private CompanyClient companyClient;

	private ReviewClient reviewClient;

	public JobServiceImpl(JobRepo jobRepo , CompanyClient companyClient , ReviewClient reviewClient) {
		super();
		this.jobRepo = jobRepo;
		this.companyClient =  companyClient;
		this.reviewClient = reviewClient;
	}

	@Override
	public List<JobDTO> findAll() {
		List<Job> jobs = jobRepo.findAll();
		List<JobDTO> jobDTOS = new ArrayList<>();

		return jobs.stream().map(this :: convertToDto).collect(Collectors.toList());
	}
	private JobDTO convertToDto(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());

		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());



		JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
       // jobDTO.setCompany(company);
        return jobDTO;
    }

	@Override
	public void createJob(Job job) {
		jobRepo.save(job);

	}

	@Override
	public JobDTO getJobById(Long id) {
		Job job = jobRepo.findById(id).orElse(null);
		return convertToDto(job);
	}

	@Override
	public boolean deleteById(Long id) {
		try {
			jobRepo.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateJobById(Long id, Job updatedJob) {
		Optional<Job> jobOptional = jobRepo.findById(id);
		if(jobOptional.isPresent()) {
			Job job= jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepo.save(job);
			return true;
		}
		return false;

	}

}