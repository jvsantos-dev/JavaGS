package com.gs.CareerBooster.model;

public class TrackModel {
    private Integer id;
    private String title;
    private String description;
    private String area; // Ex: Desenvolvimento, Data Science, IA

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}
