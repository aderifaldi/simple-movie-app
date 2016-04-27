package com.ar.simplemovie.app.model.searchmovie;

import java.io.Serializable;

/**
 * Created by aderifaldi on 27-Apr-16.
 */
public class ModelSearchMovie implements Serializable{

    private SearchMovie[] Search;
    private String totalResults;
    private String Response;

    public SearchMovie[] getSearch() {
        return Search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return Response;
    }
}
