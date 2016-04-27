package com.ar.simplemovie.app.api;

import android.content.Context;

import com.ar.simplemovie.app.model.searchmovie.ModelSearchMovie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.radyalabs.async.AsyncHttpResponseHandler;
import com.radyalabs.irfan.util.AppUtility;

import org.apache.http.Header;


abstract public class APISearchMovie extends BaseApi{
    protected ModelSearchMovie data;
    private JsonObject object;
    private JsonObject json;
    private String rawContent;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    public APISearchMovie(Context context, String keyword, int page) {
        super(context);

        ajaxType = AjaxType.GET;
        endpointApi = "";
        params.put("s", keyword);
        params.put("page", "" + page);

        responseHandler = new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int codeReturn, Header[] headers, byte[] content) {
                try {
                    rawContent = new String(content);
                    json = new JsonParser().parse(rawContent).getAsJsonObject();
                    object = json.getAsJsonObject();
                    gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                    data = gson.fromJson(object, ModelSearchMovie.class);

                    AppUtility.logD("APISearchMovie", "Success-Result : " + rawContent);

                    onFinishRequest(true, rawContent);
                } catch(Exception e) {
                    e.printStackTrace();
                    AppUtility.logD("APISearchMovie", "Exception-Result : " + rawContent);
                    onFinishRequest(false, rawContent);
                }
            }

            @Override
            public void onFailure(int codeReturn, Header[] headers, byte[] content, Throwable error) {
                String textContent = null;
                if (content != null){
                    textContent = new String(content);
                    AppUtility.logD("APISearchMovie", "Failed-Result : " + textContent);
                }
                onFinishRequest(false, textContent);
            }
        };
    }
}
