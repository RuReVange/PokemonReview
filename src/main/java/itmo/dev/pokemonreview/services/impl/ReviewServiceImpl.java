package itmo.dev.pokemonreview.services.impl;

import itmo.dev.pokemonreview.dto.ReviewDto;
import itmo.dev.pokemonreview.exceptions.PokemonNotFoundException;
import itmo.dev.pokemonreview.exceptions.ReviewNotFoundException;
import itmo.dev.pokemonreview.models.Pokemon;
import itmo.dev.pokemonreview.models.Review;
import itmo.dev.pokemonreview.repositories.PokemonRepository;
import itmo.dev.pokemonreview.repositories.ReviewRepository;
import itmo.dev.pokemonreview.services.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {

        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, int pokemonId) {

        Review review = reviewToModel(reviewDto);
        Pokemon tmpPokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        review.setPokemon(tmpPokemon);

        return reviewToDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getAllReviews() {

        List<Review> reviewsList = reviewRepository.findAll();

        return reviewsList.stream()
                .map(review -> reviewToDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {

            throw new ReviewNotFoundException("This review doesnt belong to the pokemon");
        }

        return reviewToDto(review);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int pokemonId) {

        List<Review> reviewList = reviewRepository.findByPokemonId(pokemonId);

        return reviewList.stream()
                .map(review -> reviewToDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(int pokemonId,  int reviewId, ReviewDto reviewDto) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {

            throw new ReviewNotFoundException("This review doesnt belong to the pokemon");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return reviewToDto(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {

            throw new ReviewNotFoundException("This review doesnt belong to the pokemon");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto reviewToDto(Review review) {

        return ReviewDto.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .stars(review.getStars())
                .build();
    }

    private Review reviewToModel(ReviewDto reviewDto) {

        return Review.builder()
                .title(reviewDto.getTitle())
                .content(reviewDto.getContent())
                .stars(reviewDto.getStars())
                .build();
    }
}
