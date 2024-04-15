package itmo.dev.pokemonreview.models;

import itmo.dev.pokemonreview.dto.PokemonDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;

    public static Pokemon toModel(PokemonDto pokemonDto) {

        return Pokemon.builder()
                .name(pokemonDto.getName())
                .type(pokemonDto.getType())
                .build();
    }
}
