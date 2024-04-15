package itmo.dev.pokemonreview.dto;

import itmo.dev.pokemonreview.models.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonDto {

    private String name;
    private String type;

    public static PokemonDto toDto(Pokemon pokemon) {

        return PokemonDto.builder()
                .name(pokemon.getName())
                .type(pokemon.getType())
                .build();
    }
}
