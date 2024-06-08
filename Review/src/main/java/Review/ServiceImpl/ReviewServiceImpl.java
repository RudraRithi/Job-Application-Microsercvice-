package Review.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import Review.Model.Review;
import Review.Repo.ReviewRepo;
import Review.Service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepo reviewRepo;
	
	public ReviewServiceImpl(ReviewRepo reviewRepo) {
		super();
		this.reviewRepo = reviewRepo;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews= reviewRepo.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		if(companyId != null && review != null) {
			review.setCompanyId(companyId);
			 reviewRepo.save(review);
			 return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Review getReview(Long reviewId) {
		return reviewRepo.findById(reviewId).orElse(null);
		
		
	}

	@Override
	public boolean updateReview( Long reviewId, Review updatedReview) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
	    if(review != null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setCompanyId(updatedReview.getCompanyId());
			reviewRepo.save(review);
			return true;
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteReview(Long reviewId) {
		Review review = reviewRepo.findById(reviewId).orElse(null);
		if(review != null) {
			reviewRepo.deleteById(reviewId);
			return true;
		}else
		return false;
	}

	
}
