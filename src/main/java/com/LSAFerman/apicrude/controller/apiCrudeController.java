package com.LSAFerman.apicrude.controller;

import com.LSAFerman.apicrude.dto.GamersDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Object.*;

@RestController
@RequestMapping("/jogosArcaide")
public class apiCrudeController {
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

    /*
    * Método de atualização completa da lista
    */
    @PutMapping("/{id}")
    public ResponseEntity<GamersDto> update(@PathVariable("id") final int id,
                                            @RequestBody final GamersDto request) {
        GamersDto gamesDto = null;
        for(var games: gamesList) {
            if(games.getId() == id){
                gamesDto = games;
            }
        }

        if(Objects.nonNull(gamesDto)) {
            gamesDto.setId(request.getId());
            gamesDto.setAno(request.getAno());
            gamesDto.setName(request.getName());
            return ResponseEntity.ok(gamesDto);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    /*
     * Método para deletar um ítem da lista
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final int id) {
        int index = -1;
        for (int i = 1; i< gamesList.size(); i++) {
            if(gamesList.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if(index >= 0) {
            gamesList.remove(index);
        }
        return ResponseEntity.noContent().build();
    }
}
