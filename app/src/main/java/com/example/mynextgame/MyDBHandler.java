package com.example.mynextgame;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    // Specifying DB statics
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "gameDB.db";
    public static final String TABLE_WISHLIST = "wishlist";
    public static final String TABLE_LIBRARY = "library";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "gamename";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_PLAYTIME = "playtime";
    public static final String COLUMN_RELEASED = "released";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DEVELOPER = "developer";
    public static final String COLUMN_WEBSITE = "website";

    //Constructor
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating wishlist table
        String CREATE_WISHLIST_TABLE = "CREATE TABLE " +
                TABLE_WISHLIST + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_RATING + " REAL," +
                COLUMN_PLAYTIME + " INTEGER," +
                COLUMN_RELEASED + " TEXT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_DEVELOPER + " TEXT," +
                COLUMN_WEBSITE + " TEXT" + ")";

        // creating library table
        String CREATE_LIBRARY_TABLE = "CREATE TABLE " +
                TABLE_LIBRARY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_RATING + " REAL," +
                COLUMN_PLAYTIME + " INTEGER," +
                COLUMN_RELEASED + " TEXT," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_DEVELOPER + " TEXT," +
                COLUMN_WEBSITE + " TEXT" + ")";

        db.execSQL(CREATE_WISHLIST_TABLE);
        db.execSQL(CREATE_LIBRARY_TABLE);
    }

    // Upgrading DB: delete it to create it anew
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRARY);
        onCreate(db);
    }

    //method to add games to Wish TABLE
    public void addGameToWishlist(Game game) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, game.getID());
        values.put(COLUMN_NAME, game.getName());
        values.put(COLUMN_RATING, game.getRating());
        values.put(COLUMN_PLAYTIME, game.getPlaytime());
        values.put(COLUMN_RELEASED,game.getReleased());
        values.put(COLUMN_IMAGE,game.getImage());
        values.put(COLUMN_DESCRIPTION, game.getDescription());
        values.put(COLUMN_DEVELOPER,game.getDeveloper());
        values.put(COLUMN_WEBSITE,game.getWebsite());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_WISHLIST, null, values);
        db.close();
    }

    //method to add games to Lib TABLE
    public void addGameToLibrary(Game game) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, game.getID());
        values.put(COLUMN_NAME, game.getName());
        values.put(COLUMN_RATING, game.getRating());
        values.put(COLUMN_PLAYTIME, game.getPlaytime());
        values.put(COLUMN_RELEASED,game.getReleased());
        values.put(COLUMN_IMAGE,game.getImage());
        values.put(COLUMN_DESCRIPTION, game.getDescription());
        values.put(COLUMN_DEVELOPER,game.getDeveloper());
        values.put(COLUMN_WEBSITE,game.getWebsite());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_LIBRARY, null, values);
        db.close();
    }

    // Method to find game from DB -> returns the object or null
    public Game findInWishlist(String gameName) {
        String query = "SELECT * FROM " + TABLE_WISHLIST + " WHERE " +
                COLUMN_NAME + " = '" + gameName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            game.setID(Integer.parseInt(cursor.getString(0)));
            game.setName(cursor.getString(1));
            game.setRating(Float.parseFloat(cursor.getString(2)));
            game.setPlaytime(Integer.parseInt(cursor.getString(3)));
            game.setReleased((cursor.getString(4)));
            game.setImage((cursor.getString(5)));
            game.setDescription((cursor.getString(6)));
            game.setDeveloper((cursor.getString(7)));
            game.setWebsite((cursor.getString(8)));
            cursor.close();
        } else {
            game = null;
        }
        db.close();
        return game;
    }

    // Method to find game from DB -> returns the object or null
    public Game findInLibrary(String gameName) {
        String query = "SELECT * FROM " + TABLE_LIBRARY + " WHERE " +
                COLUMN_NAME + " = '" + gameName + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            game.setID(Integer.parseInt(cursor.getString(0)));
            game.setName(cursor.getString(1));
            game.setRating(Float.parseFloat(cursor.getString(2)));
            game.setPlaytime(Integer.parseInt(cursor.getString(3)));
            game.setReleased((cursor.getString(4)));
            game.setImage((cursor.getString(5)));
            game.setDescription((cursor.getString(6)));
            game.setDeveloper((cursor.getString(7)));
            game.setWebsite((cursor.getString(8)));
            cursor.close();
        } else {
            game = null;
        }
        db.close();
        return game;
    }

    Cursor readAllDataWishlist(){
        String query = "SELECT * FROM " + TABLE_WISHLIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db !=null){
            cursor= db.rawQuery(query, null);

        }
        return cursor;
    }

    Cursor readAllDataLibrary(){
        String query = "SELECT * FROM " + TABLE_LIBRARY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db !=null){
            cursor= db.rawQuery(query, null);

        }
        return cursor;
    }
//
//    //Μέθοδος για διαγραφή προϊόντος βάσει ονομασίας του
//    public boolean deleteGame(String gamename) {
//        boolean result = false;
//        Game game = findGame(gamename);
//        if (game != null){
//            SQLiteDatabase db = this.getWritableDatabase();
//            db.delete(TABLE_WISHLIST, COLUMN_ID + " = ?",
//                    new String[] { String.valueOf(game.getID()) });
//            result = true;
//            db.close();
//        }
//        return result;
//    }
}