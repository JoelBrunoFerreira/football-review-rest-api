package com.footballreview.api.mappers;

import com.footballreview.api.dtos.PlayerRequestDto;
import com.footballreview.api.dtos.PlayerResponseDto;
import com.footballreview.api.entities.Player;

public class PlayerMappers {

    // Map DB Entity --> to Domain Object
    public static PlayerResponseDto mapPlayerToPlayerResponseDto(Player player) {
        PlayerResponseDto playerDto = new PlayerResponseDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setPosition(player.getPosition());
        playerDto.setClub(player.getClub());
        return playerDto;
    }

    // Map Domain Object --> to DB Entity
    public static Player mapPlayerRequestDtoToPlayer(PlayerRequestDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setPosition(playerDto.getPosition());
        player.setClub(playerDto.getClub());
        return player;
    }
}
