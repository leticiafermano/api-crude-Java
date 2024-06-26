package com.LSAFerman.apicrude.controller;

import com.LSAFerman.apicrude.dto.GamersDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Object.*;

@RestController
@RequestMapping("/jogosArcaide")
public class apiCrudeController {
    //private List<GamersDto> gamesList = new ArrayList<>();
    private List<GamersDto> gamesList= insertGames();

    private List<GamersDto> insertGames() {
        var listOfGames = new ArrayList<GamersDto>();
        listOfGames.add(new GamersDto(1, "Mario", 1988));
        listOfGames.add(new GamersDto(2, "Luigi", 1983));
        listOfGames.add(new GamersDto(3, "Bombarman", 1980));
        return listOfGames;
    }

    /*
    * Método que cria um novo dado na lista
    */
    @PostMapping
    public ResponseEntity<GamersDto> save(@RequestBody final GamersDto gamersDto) {
        gamesList.add(gamersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gamersDto);
    }

    /*
    * Método que faz a leitura de todos os dados da lista
    */
    //@GetMapping("/allGames")
    public ResponseEntity<List<GamersDto>> getAll() {
        return ResponseEntity.ok(gamesList);
    }

    /*
    * Método que faz a leitura por um id específico
    */
    @GetMapping("/{id}")
    public ResponseEntity<GamersDto> findById(@PathVariable("id") final long id) {
        for (var games: gamesList){
            if (games.getId() == id){
                return ResponseEntity.ok(games);
            }
        }
        return ResponseEntity.notFound().build();
    }

    /*
    * Método que Filtra dados por meio de um prefixo
    */
    @GetMapping
    public ResponseEntity<List<GamersDto>> getAll(
            @RequestParam(name = "prefix", required = false) final String prefix) {
        if (Objects.isNull(prefix)) {
            return ResponseEntity.ok(gamesList);
        } else {
            var listOfGames =
                    gamesList.stream()
                            .filter(gamersDto -> gamersDto.getName().startsWith(prefix))
                            .collect(Collectors.toList());
            return ResponseEntity.ok(listOfGames);
        }
    }


    //UPDATE

    //DELETE

}
