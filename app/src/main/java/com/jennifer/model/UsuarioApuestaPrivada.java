package com.jennifer.model;

/**
 * Created by Alex on 06/02/2016.
 */
public class UsuarioApuestaPrivada {

    private int id;
    private String name;
    private String description;
    private int finalResult;
    private String dateTime;
    private int creator;
    private String typeBet;
    private Double award;

    public UsuarioApuestaPrivada() {

    }

    public UsuarioApuestaPrivada(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UsuarioApuestaPrivada(int id, String name, String description, int finalResult, String dateTime, int creator, String typeBet, Double award) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finalResult = finalResult;
        this.dateTime = dateTime;
        this.creator = creator;
        this.typeBet = typeBet;
        this.award = award;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(int finalResult) {
        this.finalResult = finalResult;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public Double getAward() {
        return award;
    }

    public void setAward(Double award) {
        this.award = award;
    }
}
