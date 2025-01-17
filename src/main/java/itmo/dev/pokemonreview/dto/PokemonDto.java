package itmo.dev.pokemonreview.dto;

import itmo.dev.pokemonreview.models.Pokemon;
import itmo.dev.pokemonreview.models.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonDto {

    private String name;
    private String type;
    private List<Review> reviews;
}
