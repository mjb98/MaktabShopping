package com.example.mjb.maktabshopping.model;

public class Category {

    private String id;
    private String name;
    private String parent;
    private Image image;

    public Category(String id, String name, String parent, Image image) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
