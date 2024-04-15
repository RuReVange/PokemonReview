package itmo.dev.pokemonreview.services.interfaces;

import itmo.dev.pokemonreview.dto.PokemonDto;
import itmo.dev.pokemonreview.dto.PokemonResponsePages;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponsePages getAllPokemons(int pageNumber, int pageSize);
    PokemonDto getPokemonById(Integer id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, Integer id);
    void deletePokemonById(Integer id);
}
