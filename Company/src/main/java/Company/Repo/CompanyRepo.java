package Company.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import Company.Model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}

