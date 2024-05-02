package com.LSAFerman.apicrude.dto;

public class GamersDto {
    private Integer id;
    private String name;
    private Integer ano;

    public GamersDto() {

    }

    public GamersDto(Integer id, String name, Integer ano) {
        this.id = id;
        this.name = name;
        this.ano = ano;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
