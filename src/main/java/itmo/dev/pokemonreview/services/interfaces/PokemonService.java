package itmo.dev.pokemonreview.services.interfaces;

import itmo.dev.pokemonreview.dto.PokemonDto;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemons();
    PokemonDto getPokemonById(Integer id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, Integer id);
}
