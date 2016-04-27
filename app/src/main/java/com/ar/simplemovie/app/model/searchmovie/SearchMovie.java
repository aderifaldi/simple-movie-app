package com.ar.simplemovie.app.model.searchmovie;

import java.io.Serializable;

/**
 * Created by aderifaldi on 27-Apr-16.
 */
public class SearchMovie implements Serializable{

    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }
}
