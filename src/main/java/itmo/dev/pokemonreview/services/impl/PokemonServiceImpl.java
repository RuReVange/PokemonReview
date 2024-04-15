package itmo.dev.pokemonreview.services.impl;

import itmo.dev.pokemonreview.dto.PokemonDto;
import itmo.dev.pokemonreview.dto.PokemonResponsePages;
import itmo.dev.pokemonreview.exceptions.PokemonNotFoundException;
import itmo.dev.pokemonreview.models.Pokemon;
import itmo.dev.pokemonreview.repositories.PokemonRepository;
import itmo.dev.pokemonreview.services.interfaces.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Pokemon pokemon = pokemonToModel(pokemonDto);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        PokemonDto responsePokemonDto = pokemonToDto(savedPokemon);

        return responsePokemonDto;
    }

    @Override
    public PokemonResponsePages getAllPokemons(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemons = pokemons.getContent();

        List<PokemonDto> pokemonDtoList = listOfPokemons.stream()
                .map(pokemon -> pokemonToDto(pokemon))
                .collect(Collectors.toList());

        PokemonResponsePages pokemonResponsePages = PokemonResponsePages.builder()
                .content(pokemonDtoList)
                .pageNumber(pokemons.getNumber())
                .pageSize(pokemons.getSize())
                .totalElements(pokemons.getTotalElements())
                .totalPages(pokemons.getTotalPages())
                .isLast(pokemons.isLast())
                .build();

        return pokemonResponsePages;
    }

    @Override
    public PokemonDto getPokemonById(Integer id) {

        Pokemon tmpPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));

        return pokemonToDto(tmpPokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, Integer id) {

        Pokemon tmpPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found and update"));
        tmpPokemon.setName(pokemonDto.getName());
        tmpPokemon.setType(pokemonDto.getType());

        return pokemonToDto(pokemonRepository.save(tmpPokemon));
    }

    @Override
    public void deletePokemonById(Integer id) {

        Pokemon tmpPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be delete"));

        pokemonRepository.delete(tmpPokemon);
    }

    private PokemonDto pokemonToDto(Pokemon pokemon) {

        return PokemonDto.builder()
                .name(pokemon.getName())
                .type(pokemon.getType())
                .reviews(pokemon.getReviews())
                .build();
    }

    private Pokemon pokemonToModel(PokemonDto pokemonDto) {

        return Pokemon.builder()
                .name(pokemonDto.getName())
                .type(pokemonDto.getType())
                .build();
    }
}
