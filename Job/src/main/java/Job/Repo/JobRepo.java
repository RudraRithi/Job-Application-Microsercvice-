package Job.Repo;


import Job.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo  extends JpaRepository<Job, Long> {

}

