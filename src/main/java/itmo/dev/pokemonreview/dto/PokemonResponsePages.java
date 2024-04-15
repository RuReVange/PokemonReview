package itmo.dev.pokemonreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonResponsePages {

    private List<PokemonDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}
