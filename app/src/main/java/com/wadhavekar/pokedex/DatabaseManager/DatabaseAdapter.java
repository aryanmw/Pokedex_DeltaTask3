package com.wadhavekar.pokedex.DatabaseManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favouritePokemons.db";
    public static final String FAVOURITES_TABLE = "Favourites";
    public static final String COL1 = "ID";
    public static final String COL2 = "PokemonNames";
    public static final String COL3 = "ImageURL";
    //public static final String COL3 = "DATE";

public DatabaseAdapter(Context context){
    super(context,DATABASE_NAME,null,1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ FAVOURITES_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "PokemonNames TEXT, ImageURL TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FAVOURITES_TABLE);
    }

    public boolean addNameAndURL(String pokemonName,String url){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,pokemonName);
        contentValues.put(COL3,url);
        long result = db.insert(FAVOURITES_TABLE,null,contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getNameAndURL(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data =db.rawQuery("SELECT * FROM "+ FAVOURITES_TABLE, null);
        return data;
    }
}
