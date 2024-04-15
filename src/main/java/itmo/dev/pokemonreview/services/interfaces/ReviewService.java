package itmo.dev.pokemonreview.services.interfaces;

import itmo.dev.pokemonreview.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto, int pokemonId);
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(int pokemonId, int reviewId);
    List<ReviewDto> getReviewsByPokemonId(int pokemonId);
    ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);
    void deleteReview(int pokemonId, int reviewId);
}
