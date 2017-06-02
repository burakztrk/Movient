package com.ozturkburak.movient;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.picassopalette.PicassoPalette;
import com.ozturkburak.Utils.IntentUtils;
import com.ozturkburak.Utils.Util;
import com.ozturkburak.movient.model.Cast;
import com.ozturkburak.movient.model.Movie;
import com.ozturkburak.movient.model.Torrent;
import com.ozturkburak.movient.search.SearchOptions;
import com.robertlevonyan.views.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity
{

    private CollapsingToolbarLayout m_collapsingToolbarLayout = null;
    private Chip m_chipDownload, m_chipYts, m_chipImdb;
    private LinearLayout m_suggestionsLayout, m_genresLayout, m_castLayout;
    private PicassoPalette m_palette = null;
    private ImageView m_imageViewBackground, m_imageViewCover;
    private TextView m_textViewYear , m_textViewImdb , m_textViewRuntime , m_textViewLanguage, m_textViewMpa , m_textViewDesc;

    private ProgressDialog m_ProgressDialog;
    private Movie m_movieDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        m_ProgressDialog = new ProgressDialog(this);
        m_ProgressDialog.setMessage("Loading........");
        m_ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_ProgressDialog.setCancelable(false);
        m_ProgressDialog.show();
        TabPageAdapter.setCurrentTab(Util.APP_PAGES.SEARCH);


        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(Util.MOVIEINFO);
        m_movieDetail = movie == null ? Movie.DemoMovie() : movie;

        gatherViews();
        collapsingToolbarInit();
        setAllViews(m_movieDetail);
    }


    private void collapsingToolbarInit()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.ACTIVITY_TOOLBAR);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        m_collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR);
        m_collapsingToolbarLayout.setTitle(m_movieDetail.getTitle());

        m_collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        m_collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);

    }

    private void gatherViews()
    {

        //COLLAPSING TOOLBAR INCLUDE EDILECEK
        m_collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR);
        m_imageViewBackground = (ImageView) findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR_IMAGEVIEW_COVER);
        m_chipDownload = (Chip) findViewById(R.id.MOVIEDETAIL_CHIP_DOWNLOAD);
        m_chipImdb = (Chip) findViewById(R.id.MOVIEDETAIL_CHIP_IMDB);
        m_chipYts = (Chip) findViewById(R.id.MOVIEDETAIL_CHIP_YTS);
        m_suggestionsLayout = (LinearLayout) findViewById(R.id.MOVIEDETAIL_SUG_LINEARLAYOUT_SUGMOVIES);
        m_genresLayout = (LinearLayout) findViewById(R.id.MOVIEDETAIL_GENRE_LINEARLAYOUT_BUTTONS);
        m_castLayout = (LinearLayout) findViewById(R.id.MOVIEDETAIL_CAST_LINEARLAYOUT);
        m_textViewLanguage = (TextView) findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_LANGUAGE);
        m_textViewImdb = (TextView) findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_IMDB);
        m_textViewMpa = (TextView) findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_MPAARAITING);
        m_textViewRuntime = (TextView) findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_RUNTIME);
        m_textViewYear = (TextView)findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_YEAR);
        m_textViewDesc = (TextView)findViewById(R.id.MOVIEDETAIL_DESC_DESCTEXT);
        m_imageViewCover = (ImageView) findViewById(R.id.MOVIEDETAIL_INFO_IMAGEVIEW_POSTER);

    }




    private void setAllViews(Movie movie)
    {
        initChips(movie);
        inflateGenreLayout(movie);
        inflateSuggestionLayout(movie);
        inflateCastLayout(movie);
        setImagesandColors(movie);

        m_textViewLanguage.setText(movie.getLanguage());
        m_textViewImdb.setText(String.valueOf(movie.getRating()));
        m_textViewMpa.setText(movie.getMpaRating());
        m_textViewRuntime.setText(String.valueOf(movie.getRuntime()));
        m_textViewYear.setText(String.valueOf(movie.getYear()));
        m_textViewDesc.setText(movie.getDescriptionFull());
    }




    private void initChips(final Movie movie)
    {
        View.OnClickListener downloadChipListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO Custom SnackBar eklenebilir
                List<Torrent> torrents = movie.getTorrents();
                if (torrents == null || torrents.size() == 0)
                {
                    Toast.makeText(DetailActivity.this, "Torrent info not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                Util.showTrntDialog(DetailActivity.this, torrents);
            }
        };


        View.OnClickListener ytsChipListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentUtils.openChromeTab(DetailActivity.this , movie.getUrl());
                Log.d("DEBUG-LINK" , movie.getUrl());
            }
        };


        View.OnClickListener imdbChipListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentUtils.openChromeTab(DetailActivity.this , movie.getImdbCode());
                Log.d("DEBUG-LINK" , movie.getImdbCode());
            }
        };

        m_chipDownload.setOnClickListener(downloadChipListener);
        m_chipYts.setOnClickListener(ytsChipListener);
        m_chipImdb.setOnClickListener(imdbChipListener);
    }






    private void setImagesandColors(final Movie movie)
    {

        Picasso.with(DetailActivity.this)
                .load(movie.getBackgroundImage())
                .error(R.drawable.cover_error)
                .into(m_imageViewBackground);

        m_imageViewBackground.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                IntentUtils.watchYoutubeVideo(DetailActivity.this , movie.getYtTrailerCode());
                Log.d("DEBUG-LINK" , movie.getYtTrailerLink());
            }
        });

        Picasso.with(DetailActivity.this)
                .load(movie.getMediumCoverImage())
                .error(R.drawable.cover_error)
                .into(m_imageViewCover, PicassoPalette.with(movie.getImdbCode(), m_imageViewCover)
                        .use(PicassoPalette.Profile.VIBRANT)
                        .intoCallBack(new PicassoPalette.CallBack()
                        {
                            @Override
                            public void onPaletteLoaded(Palette palette)
                            {

                                int[] colors = {
                                        palette.getLightVibrantColor(getResources().getColor(R.color.colorPrimaryDark)), //2
                                        palette.getVibrantColor(getResources().getColor(R.color.colorPrimaryDark)),      //1
                                        palette.getLightMutedColor(getResources().getColor(R.color.colorPrimaryDark)), //4
                                        palette.getDarkVibrantColor(getResources().getColor(R.color.colorPrimaryDark)), //3
                                        palette.getDarkMutedColor(getResources().getColor(R.color.colorPrimaryDark)),    //0
                                }; //3

                                m_collapsingToolbarLayout.setContentScrimColor(colors[0]);
                                m_collapsingToolbarLayout.setStatusBarScrimColor(colors[0]);
                                m_chipDownload.setBackgroundColor( colors[0] );
                                m_chipYts.setBackgroundColor( colors[1] );
                                m_chipImdb.setBackgroundColor( colors[2] );

                                //Kategori butonlarinin renkleri
                                for (int i = 0; i < m_genresLayout.getChildCount() -1 ; i++)
                                    m_genresLayout.getChildAt(i).setBackgroundColor(colors[i]);
                            }
                        })
                );
        m_ProgressDialog.hide();

    }



    private void inflateGenreLayout(Movie movie)
    {
        m_genresLayout.removeAllViews();
        final List<String> genres = movie.getGenres();

        int genreMax = genres.size() < 3 ? genres.size() : 3;
        for (int i = 0; i < genreMax ; i++)
        {
            final int index = i;
            final String genreStr = genres.get(i);

            Button bt = Util.getGenreStyleButton(this, genreStr, getResources().getColor(R.color.colorPrimaryDark));
            bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //TODO Search Options Interface ile startActivityForResult ile cagirilacak main activityden interface cagrilacak
                    //TODO : Genre sorgusu yapilacak list fragment da gosterilecek.
                    Toast.makeText(DetailActivity.this, genreStr, Toast.LENGTH_SHORT).show();

                    SearchOptions options = new SearchOptions();
                    options.setGenre(genreStr);
                    options.set_rating(SearchOptions.Raiting.R7);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(IntentUtils.BUNDLE_SEARCHOPTIONDATA, options);
                    DetailActivity.this.setResult(IntentUtils.RESULTCODE_NEWSEARCHOPTIONS, resultIntent);
                    DetailActivity.this.finish();

                }
            });
            m_genresLayout.addView(bt);
        }
    }




    private void inflateSuggestionLayout(Movie movie)
    {
        m_suggestionsLayout.removeAllViewsInLayout();
        //TODO restapiye yeni sorgu eklenecek
        // https://yts.ag/api/v2/movie_suggestions.json?movie_id=10

        Context context = this;
        int[] covers = {R.drawable.cover1 , R.drawable.cover2 , R.drawable.cover3 , R.drawable.cover4 , R.drawable.cover5
                , R.drawable.cover6 , R.drawable.cover7 , R.drawable.cover8 , R.drawable.cover9 , R.drawable.cover10};

        LinearLayout ln = null;
        ImageView img = null;
        TextView tx = null;
        LinearLayout.LayoutParams  layoutparams = new LinearLayout.LayoutParams( Util.getDpi(context, 150), Util.getDpi(context , 200));


        for (int i = 0; i <  covers.length ; i++)
        {
            ln = new LinearLayout(context);
            ln.setOrientation(LinearLayout.VERTICAL);

            img = new ImageView(context);
            img.setImageResource(covers[i]);
            img.setPadding(0 , 0 , 10 ,0);
            img.setLayoutParams(layoutparams);

            tx = new TextView(context);
            tx.setText("HANGOVER" );
            tx.setTextSize(18.0f);

            tx.setGravity(Gravity.CENTER);
            tx.setPadding(10 , 0 , 10 ,0);


            final int index = i;
            img.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(DetailActivity.this , "Intent" + index, Toast.LENGTH_SHORT).show();
                }
            });

            ln.addView(img ,0);
            ln.addView(tx , 1);

            m_suggestionsLayout.addView(ln , i);
        }
    }



    private void inflateCastLayout(Movie movie)
    {
        m_castLayout.removeAllViewsInLayout();
        List<Cast> casts = movie.getCast();

        if (casts == null)
            return;

        for (int i = 0; i <  casts.size() ; i++)
        {
            LinearLayout ln = Util.getCastLayout(this, casts.get(i) );
            m_castLayout.addView(ln , i);
        }
    }

}
