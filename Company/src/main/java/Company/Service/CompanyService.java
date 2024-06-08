package Company.Service;

import java.util.List;

import Company.Model.Company;

public interface CompanyService {
	
	List<Company> getAllCompany();
	boolean updateCompany(Company company, Long id);
	void createCompany(Company company);
	boolean deleteById(Long id);
	Company getCompanyById(Long id);
	
}

