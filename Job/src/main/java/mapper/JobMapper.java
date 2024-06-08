package mapper;

import Job.External.Company;
import Job.External.Review;
import Job.Model.Job;
import Job.dto.JobDTO;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job , Company company , List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;

    }
}
