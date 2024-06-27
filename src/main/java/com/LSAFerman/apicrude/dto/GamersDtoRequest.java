package com.LSAFerman.apicrude.dto;

public class GamersDtoRequest {
    private String name;
    private Integer ano;

    public GamersDtoRequest() {

    }

    public GamersDtoRequest(Integer id, String name, Integer ano) {
        this.name = name;
        this.ano = ano;
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
