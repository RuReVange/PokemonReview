package itmo.dev.pokemonreview.dto;

import itmo.dev.pokemonreview.models.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private String title;
    private String content;
    private int stars;
}
