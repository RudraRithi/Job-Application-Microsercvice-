package Review.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Review.Model.Review;


public interface ReviewRepo extends JpaRepository<Review, Long> {

	List<Review> findByCompanyId(Long companyId);

}

