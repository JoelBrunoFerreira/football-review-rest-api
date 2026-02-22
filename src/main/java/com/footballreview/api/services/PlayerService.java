package com.footballreview.api.services;

import com.footballreview.api.dtos.PlayerRequestDto;
import com.footballreview.api.dtos.PlayerResponseDto;
import com.footballreview.api.dtos.PlayerResponseWithPaginationDto;
import com.footballreview.api.entities.Player;
import com.footballreview.api.exceptions.PlayerNotFoundException;
import com.footballreview.api.mappers.PlayerMappers;
import com.footballreview.api.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements IPlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // ---------------------------------------------------------
    // GET all --> without pagination
    // @Override
    // public List<PlayerResponseDto> getAllPlayers() {
    //     List<Player> players = playerRepository.findAll();
    //     return players.stream().map(PlayerMappers::mapPlayerToPlayerResponseDto)
    //             .collect(Collectors.toList());

    // }

    // GET all --> with pagination
    @Override
    public PlayerResponseWithPaginationDto getAllPlayers(int pageNumber, int pageSize) {
        if (pageSize > 100) pageSize = 100; // limite máximo
        if (pageSize < 1) pageSize = 10;    // limite mínimo

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Player> players = playerRepository.findAll(pageable);
        List<Player>  playerList = players.getContent();
        List<PlayerResponseDto> content = playerList.stream().map(PlayerMappers::mapPlayerToPlayerResponseDto)
                .collect(Collectors.toList());

        PlayerResponseWithPaginationDto playerResponseWithPaginationDto = new PlayerResponseWithPaginationDto();
        playerResponseWithPaginationDto.setContent(content);
        playerResponseWithPaginationDto.setPageNumber(pageNumber);
        playerResponseWithPaginationDto.setPageSize(pageSize);
        playerResponseWithPaginationDto.setTotalElements(players.getTotalElements());
        playerResponseWithPaginationDto.setTotalPages(players.getTotalPages());
        playerResponseWithPaginationDto.setLast(players.isLast());

        return playerResponseWithPaginationDto;
    }

    // GET by id
    @Override
    public PlayerResponseDto getPlayerById(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found!"));
        return PlayerMappers.mapPlayerToPlayerResponseDto(player);
    }

    // POST -> Create e new player
    @Override
    public PlayerResponseDto createPlayer(PlayerRequestDto playerDto) {
        Player player = PlayerMappers.mapPlayerRequestDtoToPlayer(playerDto);
        Player newPlayer = playerRepository.save(player);
        return PlayerMappers.mapPlayerToPlayerResponseDto(newPlayer);
    }

    // PUT -> Update player
    @Override
    public PlayerResponseDto updatePlayer(PlayerRequestDto playerDto, Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player couldn't be updated!!"));
        player.setName(playerDto.getName());
        player.setPosition(playerDto.getPosition());
        player.setClub(playerDto.getClub());

        Player updatedPlayer = playerRepository.save(player);
        return PlayerMappers.mapPlayerToPlayerResponseDto(updatedPlayer);
    }

    // PATCH -> Patch a player field
    @Override
    public PlayerResponseDto patchPlayer(PlayerRequestDto playerDto, Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found!"));

        if (playerDto.getName() != null) player.setName(playerDto.getName());
        if (playerDto.getPosition() != null) player.setPosition(playerDto.getPosition());
        if (playerDto.getClub() != null) player.setClub(playerDto.getClub());

        Player updatedPlayer = playerRepository.save(player);
        return PlayerMappers.mapPlayerToPlayerResponseDto(updatedPlayer);
    }

    // DELETE
    @Override
    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player couldn't be deleted!!"));
        playerRepository.delete(player);
    }
}
