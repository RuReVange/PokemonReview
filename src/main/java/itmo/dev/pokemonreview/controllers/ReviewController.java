package itmo.dev.pokemonreview.controllers;

import itmo.dev.pokemonreview.dto.ReviewDto;
import itmo.dev.pokemonreview.services.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/pokemon/{pokemonId}/reviev")
    public ResponseEntity<ReviewDto> postReview(@RequestBody ReviewDto reviewDto, @PathVariable Integer pokemonId) {

        return new ResponseEntity<>(reviewService.createReview(reviewDto, pokemonId), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable Integer pokemonId) {

        return ResponseEntity.ok(reviewService.getReviewsByPokemonId(pokemonId));
    }

    @GetMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable(value = "pokemonId") Integer pokemonId,
                                               @PathVariable(value = "reviewId") Integer reviewId) {

        return ResponseEntity.ok(reviewService.getReviewById(pokemonId, reviewId));
    }

    @PutMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> putReview(@PathVariable(value = "pokemonId") Integer pokemonId,
                                               @PathVariable(value = "reviewId") Integer reviewId,
                                               @RequestBody ReviewDto reviewDto) {

        return ResponseEntity.ok(reviewService.updateReview(pokemonId, reviewId, reviewDto));
    }

    @DeleteMapping("/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") Integer pokemonId,
                                                  @PathVariable(value = "reviewId") Integer reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);

        return ResponseEntity.ok("Review was deleted");
    }
}
