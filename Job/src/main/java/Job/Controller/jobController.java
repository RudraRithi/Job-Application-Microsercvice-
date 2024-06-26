package Job.Controller;


import java.util.List;

import Job.Model.Job;
import Job.Service.JobService;
import Job.dto.JobDTO;
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
public class jobController {


	private JobService jobService;


	public jobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<JobDTO>> findAll(){
		return ResponseEntity.ok(jobService.findAll());
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
		return new ResponseEntity<>("job added Successfully" , HttpStatus.OK);

	}
	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
		JobDTO jobDTO = jobService.getJobById(id);
		if(jobDTO != null) {
			return new ResponseEntity<>(jobDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		boolean deleted = jobService.deleteById(id);
		if(deleted)
			return new ResponseEntity<>("Job Deleted", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob)
	{
		boolean updated = jobService.updateJobById(id, updatedJob);

		if(updated)
			return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);





	}
}
