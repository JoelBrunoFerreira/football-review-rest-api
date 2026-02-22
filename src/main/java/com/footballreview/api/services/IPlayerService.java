package com.footballreview.api.services;

import com.footballreview.api.dtos.PlayerRequestDto;
import com.footballreview.api.dtos.PlayerResponseDto;
import com.footballreview.api.dtos.PlayerResponseWithPaginationDto;

public interface IPlayerService {
    // GET all - without pagination
    // List<PlayerResponseDto> getAllPlayers();

    // GET all - with pagination
    PlayerResponseWithPaginationDto getAllPlayers(int pageNumber, int pageSize);

    // GET by id
    PlayerResponseDto getPlayerById(Integer id);

    // POST
    PlayerResponseDto createPlayer(PlayerRequestDto playerDto);

    // PUT
    PlayerResponseDto updatePlayer(PlayerRequestDto playerDto, Integer id);

    // PATCH
    PlayerResponseDto patchPlayer(PlayerRequestDto playerDto, Integer id);

    // DELETE
    void deletePlayer(Integer id);
}