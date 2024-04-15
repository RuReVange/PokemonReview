package itmo.dev.pokemonreview.controllers;

import itmo.dev.pokemonreview.dto.PokemonDto;
import itmo.dev.pokemonreview.models.Pokemon;
import itmo.dev.pokemonreview.services.interfaces.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon")
    public ResponseEntity<List<PokemonDto>> getPokemons () {

        return ResponseEntity.ok(pokemonService.getAllPokemons());
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonById (@PathVariable Integer id) {

        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("/pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon (@RequestBody PokemonDto pokemonDto) {

        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("/pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable Integer id) {

        Pokemon updatedPokemon = Pokemon.builder().id(id).build();
        updatedPokemon.setName(pokemon.getName());
        updatedPokemon.setType(pokemon.getType());

        return ResponseEntity.ok(updatedPokemon);
    }

    @DeleteMapping("/pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable Integer id) {

        Pokemon deletedPokemon = Pokemon.builder().id(id).build();

        return ResponseEntity.ok("Pokemon deleted successfully");
    }

}
