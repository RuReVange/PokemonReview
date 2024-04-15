package itmo.dev.pokemonreview.controllers;

import itmo.dev.pokemonreview.dto.PokemonDto;
import itmo.dev.pokemonreview.dto.PokemonResponsePages;
import itmo.dev.pokemonreview.services.interfaces.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon (@RequestBody PokemonDto pokemonDto) {

        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @GetMapping("/pokemon")
    public ResponseEntity<PokemonResponsePages> getPokemons (
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(pokemonService.getAllPokemons(pageNumber, pageSize));
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonById (@PathVariable Integer id) {

        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PutMapping("/pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") Integer pokemonId) {

        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonDto, pokemonId));
    }

    @DeleteMapping("/pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Integer pokemonId) {

        pokemonService.deletePokemonById(pokemonId);

        return ResponseEntity.ok("Pokemon deleted successfully");
    }
}
