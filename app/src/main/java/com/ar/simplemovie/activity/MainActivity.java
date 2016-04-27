package com.ar.simplemovie.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.ar.simplemovie.R;
import com.ar.simplemovie.app.adapter.MovieListAdapter;
import com.ar.simplemovie.app.api.APISearchMovie;
import com.ar.simplemovie.app.model.searchmovie.SearchMovie;
import com.radyalabs.irfan.util.AppUtility;

public class MainActivity extends AppCompatActivity {

    private static final int LIMIT = 10;

    private EditText edt_keyword;
    private RecyclerView list_movie;
    private LinearLayoutManager linearLayoutManager;
    private MovieListAdapter adapter;
    private ProgressDialog progressDialog;
    private FloatingActionButton fab_search;

    private boolean loading;
    private int page, pastVisiblesItems, visibleItemCount, totalItemCount;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_keyword = (EditText) findViewById(R.id.edt_keyword);
        list_movie = (RecyclerView) findViewById(R.id.list_movie);
        fab_search = (FloatingActionButton) findViewById(R.id.fab_search);

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MovieListAdapter(this);

        list_movie.setLayoutManager(linearLayoutManager);
        list_movie.setAdapter(adapter);

        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie(false);
            }
        });

//        list_movie.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                visibleItemCount = linearLayoutManager.getChildCount();
//                totalItemCount = linearLayoutManager.getItemCount();
//                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                if (loading) {
//                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && totalItemCount >= LIMIT) {
//                        searchMovie(true);
//                    }
//                }
//
//            }
//        });

    }

    public void searchMovie(final boolean isLoadMore){

        if (!isLoadMore){
            progressDialog = AppUtility.showLoading(progressDialog, this);
            adapter.getListMovie().clear();
        }

        keyword = edt_keyword.getText().toString();

        APISearchMovie apiSearchMovie = new APISearchMovie(getApplicationContext(), keyword, page + 1 ) {
            @Override
            public void onFinishRequest(boolean success, String returnItem) {
                progressDialog.dismiss();

                if (success) {
                    if (data != null){

                        page = page + 1;
                        for (SearchMovie searchMovie : data.getSearch()){
                            adapter.getListMovie().add(searchMovie);
                            adapter.notifyDataSetChanged();
                        }

                        //Toast.makeText(MainActivity.this, "" + adapter.getItemCount(), Toast.LENGTH_SHORT).show();
                    }
                }
                loading = true;

            }
        };

        loading = false;
        apiSearchMovie.executeAjax();
    }

}
