package com.footballreview.api.controllers;

import com.footballreview.api.dtos.PlayerRequestDto;
import com.footballreview.api.dtos.PlayerResponseDto;
import com.footballreview.api.dtos.PlayerResponseWithPaginationDto;
import com.footballreview.api.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // GET all players request --> without pagination
    // @GetMapping("/players")
    // public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
    //     return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    // }


    // GET all players request  --> with pagination
    @GetMapping("/players")
    public ResponseEntity<PlayerResponseWithPaginationDto> getAllPlayers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(playerService.getAllPlayers(pageNumber, pageSize), HttpStatus.OK);
    }

    // GET player by id request
    @GetMapping("/players/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayerById (@PathVariable Integer id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    // POST -> Create new player
    @PostMapping("/players")
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody PlayerRequestDto playerRequestDto) {
       return new ResponseEntity<>(playerService.createPlayer(playerRequestDto), HttpStatus.CREATED);
    }

    // PUT -> Update an existing player request
    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@RequestBody PlayerRequestDto playerRequestDto, @PathVariable("id") Integer playerId) {
        PlayerResponseDto response = playerService.updatePlayer(playerRequestDto, playerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // PATCH -> Patch the field of an existing player request
    @PatchMapping("/players/{id}")
    public ResponseEntity<PlayerResponseDto> patchPlayer(@RequestBody PlayerRequestDto playerRequestDto, @PathVariable("id") Integer playerId) {
        PlayerResponseDto response = playerService.patchPlayer(playerRequestDto, playerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // DELETE a player request
    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Integer playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.noContent().build();
    }
}
