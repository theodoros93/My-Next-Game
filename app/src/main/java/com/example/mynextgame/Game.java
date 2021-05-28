package com.example.mynextgame;

public class Game {
    private int _id;
    private String _name;
    private float _rating;
    private  int _playtime;
    private String _genre;
    private String _tags;
    private String _released;
    private String _image;


    public Game() {
    }

    public Game(int id, String name, float rating, int playtime, String genre, String tags, String released, String image) {
        this.setID(id);
        this._name = name;
        this._rating = rating;
        this._playtime = playtime;
        this._genre = genre;
        this._tags = tags;
        this._released = released;
        this._image = image;
    }

    public Game(String name, float rating, int playtime, String genre, String tags, String released, String image) {
        this._name = name;
        this._rating = rating;
        this._playtime = playtime;
        this._genre = genre;
        this._tags = tags;
        this._released = released;
        this._image = image;
    }

    public int getID() {
        return _id;
    }

    public void setID(int _id) {
        this._id = _id;
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

    public void setGenre(String genre) {
        this._genre = genre;
    }

    public String getGenre() {
        return this._genre;
    }

    public void setTags(String tags) {
        this._tags = tags;
    }

    public String getTags() {
        return this._tags;
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
}
