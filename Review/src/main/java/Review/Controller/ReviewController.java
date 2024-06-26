package Review.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Review.Model.Review;
import Review.Service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllCompany(@RequestParam Long companyId){
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<String> addReview(@RequestBody Review review, @RequestParam Long companyId) {
		boolean isReviewSaved= reviewService.addReview(companyId, review);
		if(isReviewSaved)
		return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Review Not Added", HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long reviewId	){ 
		return new ResponseEntity<>( reviewService.getReview(reviewId),HttpStatus.OK);
		
	}
	
	@PutMapping("/{reviewId}")
			public ResponseEntity<String> updateReview(@PathVariable Long reviewId , @RequestBody Review review){
		boolean isReviewUpdated=reviewService.updateReview(reviewId, review);
		if(isReviewUpdated)
		return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Review Not Updated ", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
		boolean isReviewDeleted = reviewService.deleteReview(reviewId);
		if(isReviewDeleted)
			return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("Review is not Deleted", HttpStatus.NOT_FOUND);
		
	}
	
}

