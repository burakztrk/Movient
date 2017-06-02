package com.ozturkburak.movient.resultlist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ozturkburak.Utils.IntentUtils;
import com.ozturkburak.movient.R;
import com.ozturkburak.movient.controller.RetrofitClient;
import com.ozturkburak.movient.model.Movie;
import com.ozturkburak.movient.model.MovieDetailModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: poster adapter da rating saga alinacak sol alta yil

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.MyViewHolder> {

    private Activity m_activity;
    private Context m_context;
    private List<Movie> movieList;
    private final int MAX_LEN_TITLE = 15;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView m_textviewTitle, m_textviewCount, m_textviewYear;
        private ImageView thumbnail ;

        public MyViewHolder(View view) {
            super(view);
            m_textviewTitle = (TextView) view.findViewById(R.id.POSTERCARD_TEXTVIEW_TITLE);
            m_textviewCount = (TextView) view.findViewById(R.id.POSTERCARD_TEXTVIEW_COUNT);
            m_textviewYear = (TextView) view.findViewById(R.id.POSTERCARD_TEXTVIEW_YEAR);

            thumbnail = (ImageView) view.findViewById(R.id.POSTERCARD_IMAGEVIEW_THUMBNAIL);
        }
    }


    public PosterAdapter(Activity activity , List<Movie> movies) {
        this.m_activity = activity;
        this.m_context = activity.getBaseContext();
        this.movieList = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poster_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.m_textviewCount.setText(movie.getRating() + "/10");
        holder.m_textviewYear.setText(String.valueOf(movie.getYear()));



        //Film adi 10 karakterden uzunsa bosluga kadar kisalt
        String albumStr = movie.getTitle();
        if (albumStr.length() >= MAX_LEN_TITLE)
        {
            int spaceIndex = albumStr.indexOf(' ' , MAX_LEN_TITLE);
            spaceIndex = spaceIndex ==-1 ? albumStr.length() : spaceIndex ;

            albumStr = albumStr.substring(0 , spaceIndex);
        }
            holder.m_textviewTitle.setText( albumStr);



        Picasso.with(m_context)
                .load(movie.getLargeCoverImage())
                .into(holder.thumbnail);


        holder.thumbnail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final ProgressDialog m_ProgressDialog = new ProgressDialog(m_activity);
                m_ProgressDialog.setMessage("Loading........");
                m_ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                m_ProgressDialog.setCancelable(false);
                m_ProgressDialog.show();

                Call<MovieDetailModel> call = RetrofitClient.getMovieDetailCallBack(movie.getId());
                Log.d("DEBUG-LINK", call.request().toString());

                call.enqueue(new Callback<MovieDetailModel>()
                {
                    @Override
                    public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response)
                    {
                        if (response.body() == null)
                            return;

                        if (!(response.body().getStatus().toLowerCase().contains("ok")))
                            return;

                        Movie responseMovie = response.body().getData().getMovie();
                        Toast.makeText(m_context, responseMovie.getTitleLong() , Toast.LENGTH_SHORT).show();

                        Intent intent = IntentUtils.movieDetailIntent(m_activity, responseMovie);
                        m_activity.startActivityForResult(intent, IntentUtils.REQUESTCODE_SHOWMOVIEDETAIL);


                        m_ProgressDialog.hide();
                    }

                    @Override
                    public void onFailure(Call<MovieDetailModel> call, Throwable t)
                    {
                        Toast.makeText(m_context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
