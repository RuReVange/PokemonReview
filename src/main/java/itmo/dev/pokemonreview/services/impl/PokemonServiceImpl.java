package itmo.dev.pokemonreview.services.impl;

import itmo.dev.pokemonreview.dto.PokemonDto;
import itmo.dev.pokemonreview.exceptions.PokemonNotFoundException;
import itmo.dev.pokemonreview.models.Pokemon;
import itmo.dev.pokemonreview.repositories.PokemonRepository;
import itmo.dev.pokemonreview.services.interfaces.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {

        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {

        Pokemon pokemon = Pokemon.toModel(pokemonDto);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        PokemonDto responsePokemonDto = PokemonDto.toDto(savedPokemon);

        return responsePokemonDto;
    }

    @Override
    public List<PokemonDto> getAllPokemons() {

        List<Pokemon> pokemonList = pokemonRepository.findAll();
        List<PokemonDto> pokemonDtoList = pokemonList.stream().map(pokemon -> PokemonDto.toDto(pokemon))
                .collect(Collectors.toList());

        return pokemonDtoList;
    }

    @Override
    public PokemonDto getPokemonById(Integer id) {

        Pokemon tmpPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));

        return PokemonDto.toDto(tmpPokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, Integer id) {

        Pokemon tmpPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));

        tmpPokemon.setName(pokemonDto.getName());
        tmpPokemon.setType(pokemonDto.getType());

        return PokemonDto.toDto(pokemonRepository.save(tmpPokemon));
    }
}
