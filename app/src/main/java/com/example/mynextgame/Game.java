package com.example.mynextgame;

public class Game {
    private int _id;
    private String _name;
    private float _rating;
    private  int _playtime;
    private String _released;
    private String _image;
    private String _description;
    private String _developer;
    private String _website;


    public Game() {
    }

    public Game( String name, float rating, int playtime, String released, String image, String description, String developer, String website) {
        this._name = name;
        this._rating = rating;
        this._playtime = playtime;
        this._released = released;
        this._image = image;
        this._description = description;
        this._developer = developer;
        this._website = website;

    }

    public Game(int id, String name, float rating, int playtime, String released, String image, String description, String developer, String website) {
        this._id = id;
        this._name = name;
        this._rating = rating;
        this._playtime = playtime;
        this._released = released;
        this._image = image;
        this._description = description;
        this._developer = developer;
        this._website = website;

    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setRating(float rating) {
        this._rating = rating;
    }

    public float getRating() {
        return this._rating;
    }

    public void setPlaytime(int playtime) {
        this._playtime = playtime;
    }

    public int getPlaytime() {
        return this._playtime;
    }


    public void setReleased(String released) {
        this._released = released;
    }

    public String getReleased() {
        return this._released;
    }

    public void setImage(String image) {
        this._image = image;
    }

    public String getImage() {
        return this._image;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getDeveloper() {
        return this._developer;
    }

    public void setDeveloper(String developer) {
        this._developer = developer;
    }

    public String getWebsite() {
        return this._website;
    }

    public void setWebsite(String website) {
        this._website = website;
    }
}
