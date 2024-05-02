package com.LSAFerman.apicrude.controller;

import com.LSAFerman.apicrude.dto.GamersDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jogosArcaide")
public class apiCrudeController {
    private List<GamersDto> gamesList = new ArrayList<>();

    /*
    * Método que cria um novo dado na lista
    */
    @PostMapping
    public GamersDto save(@RequestBody final GamersDto gamersDto) {
        gamesList.add(gamersDto);
        return gamersDto;
    }

    /*
    * Método que faz a leitura de todos os dados da lista
    */
    @GetMapping("/allGames")
    public List<GamersDto> getAll() {
        return gamesList;
    }

    //Ler especifico

    //Ler Um

    //UPDATE

    //DELETE

}
