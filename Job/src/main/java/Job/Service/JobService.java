package Job.Service;


import Job.Model.Job;
import Job.dto.JobDTO;

import java.util.List;

public interface JobService {

	List<JobDTO> findAll();
	void createJob(Job job);
	JobDTO getJobById(Long id);
	boolean deleteById(Long id);
	boolean updateJobById(Long id, Job updatedJob);



}