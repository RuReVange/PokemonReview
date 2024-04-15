package itmo.dev.pokemonreview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private Date timestamp;
}
