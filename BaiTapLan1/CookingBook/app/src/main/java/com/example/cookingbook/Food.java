package com.example.cookingbook;

public class Food {
    String name;
    String image;
    String url;
    public Food(String name,String image,String url)
    {
        this.name=name;
        this.image=image;
        this.url=url;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
