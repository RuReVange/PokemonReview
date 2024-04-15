package itmo.dev.pokemonreview.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
