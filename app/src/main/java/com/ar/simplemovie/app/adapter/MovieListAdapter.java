package com.ar.simplemovie.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar.simplemovie.R;
import com.ar.simplemovie.app.model.searchmovie.SearchMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private ArrayList<SearchMovie> data;
    private LayoutInflater inflater;
    private Context contextz;
    private AdapterView.OnItemClickListener onItemClickListener;

    public MovieListAdapter(Context context) {
        this.contextz = context;
        inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayList<SearchMovie> getListMovie() {
        return data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_search_movie, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SearchMovie movie = data.get(position);
        holder.position = position;

        final long identity = System.currentTimeMillis();
        holder.identity = identity;

        Picasso.with(contextz).
                load(movie.getPoster()).
                into(holder.img_movie);

        holder.txt_movie_title.setText(movie.getTitle());
        holder.txt_movie_year.setText(movie.getYear());
        holder.txt_movie_type.setText(movie.getType());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        int position;
        TextView txt_movie_title, txt_movie_year, txt_movie_type;
        ImageView img_movie;
        long identity;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            img_movie = (ImageView) itemView.findViewById(R.id.img_movie);

            txt_movie_title = (TextView) itemView.findViewById(R.id.txt_movie_title);
            txt_movie_year = (TextView) itemView.findViewById(R.id.txt_movie_year);
            txt_movie_type = (TextView) itemView.findViewById(R.id.txt_movie_type);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(null, v, position, 0);
            }
        }
    }
}
