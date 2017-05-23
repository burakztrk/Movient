package layout;

/*
 * TODO: glide yerine picassoya cevir
 * TODO: Fontlar kucultulecek
 * TODO: SUGGESTIONS SCROLL BAR KALDIR
 * TODO: FILM ADI YAZILMALI MI KALDIRMALI MI
*/

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.picassopalette.PicassoPalette;
import com.ozturkburak.Utils.Util;
import com.ozturkburak.movient.R;
import com.ozturkburak.movient.model.Cast;
import com.ozturkburak.movient.model.Movie;
import com.ozturkburak.movient.model.Torrent;
import com.robertlevonyan.views.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Fragment2_Details extends Fragment
{
    private CollapsingToolbarLayout m_collapsingToolbarLayout = null;
    private Chip m_chipDownload, m_chipYts, m_chipImdb;
    private LinearLayout m_suggestionsLayout, m_genresLayout, m_castLayout;
    private PicassoPalette m_palette = null;
    private ImageView m_imageViewBackground, m_imageViewCover;
    private TextView m_textViewYear , m_textViewImdb , m_textViewRuntime , m_textViewLanguage, m_textViewMpa , m_textViewDesc;

    private Movie m_movieDetail;



    public Fragment2_Details() { }



    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment2_detail, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        gatherViews();

        if (m_movieDetail == null){ m_movieDetail = Movie.DemoMovie(); }

        setAllViews(m_movieDetail);
        Toast.makeText(getContext(), "onCreated", Toast.LENGTH_SHORT).show();
    }




    private void gatherViews()
    {
        Activity activity = getActivity();

        m_collapsingToolbarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR);
        m_imageViewBackground = (ImageView) activity.findViewById(R.id.ACTIVITY_COLLAPSINGTOOLBAR_IMAGEVIEW_COVER);
        m_chipDownload = (Chip) activity.findViewById(R.id.MOVIEDETAIL_CHIP_DOWNLOAD);
        m_chipImdb = (Chip) activity.findViewById(R.id.MOVIEDETAIL_CHIP_IMDB);
        m_chipYts = (Chip) activity.findViewById(R.id.MOVIEDETAIL_CHIP_YTS);
        m_suggestionsLayout = (LinearLayout) activity.findViewById(R.id.MOVIEDETAIL_SUG_LINEARLAYOUT_SUGMOVIES);
        m_genresLayout = (LinearLayout) activity.findViewById(R.id.MOVIEDETAIL_GENRE_LINEARLAYOUT_BUTTONS);
        m_castLayout = (LinearLayout) activity.findViewById(R.id.MOVIEDETAIL_CAST_LINEARLAYOUT);
        m_textViewLanguage = (TextView) activity.findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_LANGUAGE);
        m_textViewImdb = (TextView) activity.findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_IMDB);
        m_textViewMpa = (TextView) activity.findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_MPAARAITING);
        m_textViewRuntime = (TextView) activity.findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_RUNTIME);
        m_textViewYear = (TextView)activity.findViewById(R.id.MOVIEDETAIL_INFO_TEXTVIEW_YEAR);
        m_textViewDesc = (TextView)activity.findViewById(R.id.MOVIEDETAIL_DESC_DESCTEXT);
        m_imageViewCover = (ImageView) activity.findViewById(R.id.MOVIEDETAIL_INFO_IMAGEVIEW_POSTER);

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
                //TODO Dialog ekrani cikacak
                String str = "";
                List<Torrent> torrents = movie.getTorrents();

                for (int i =0 ; i  < torrents.size() ; i++)
                    str += torrents.get(i).getSize() + " | ";

                Toast.makeText(getContext() , str , Toast.LENGTH_SHORT).show();
            }
        };


        View.OnClickListener ytsChipListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO open chrome url
                Toast.makeText(getContext(), movie.getUrl() , Toast.LENGTH_LONG).show();
                Log.d("DEBUG-LINK" , movie.getUrl());
            }
        };


        View.OnClickListener imdbChipListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO open chrome url
                Toast.makeText(getContext(), movie.getImdbCode() , Toast.LENGTH_LONG).show();
                Log.d("DEBUG-LINK" , movie.getImdbCode());
            }
        };


        m_chipDownload.setOnClickListener(downloadChipListener);
        m_chipYts.setOnClickListener(ytsChipListener);
        m_chipImdb.setOnClickListener(imdbChipListener);
    }






    private void setImagesandColors(Movie movie)
    {
        final Movie finalMovie = movie;

        Picasso.with(getContext())
                .load(movie.getBackgroundImage())
                .error(R.drawable.cover_error)
                .into(m_imageViewBackground);

        m_imageViewBackground.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO youtube selector acilacak
                Toast.makeText(getContext(), finalMovie.getYtTrailerLink(), Toast.LENGTH_SHORT).show();
                Log.d("DEBUG-LINK" , finalMovie.getYtTrailerLink());
            }
        });

        Picasso.with(getContext())
                .load(finalMovie.getMediumCoverImage())
                .error(R.drawable.cover_error)
                .resize(100 , 100)
                .centerInside()
                .into(m_imageViewCover, PicassoPalette.with(finalMovie.getImdbCode(), m_imageViewCover)
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
    }



    private void inflateGenreLayout(Movie movie)
    {
        m_genresLayout.removeAllViews();
        final List<String> genres = movie.getGenres();

        int genreMax = genres.size() < 3 ? genres.size() : 3;
        for (int i = 0; i < genreMax ; i++)
        {
            final int index = i;
            Button bt = Util.getGenreStyleButton(getContext(), genres.get(i) , getResources().getColor(R.color.colorPrimaryDark));
            bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //TODO : Genre sorgusu yapilacak list fragment da gosterilecek.
                    Toast.makeText(getContext() , genres.get(index) , Toast.LENGTH_SHORT).show();
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

        Context context = getContext();
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
                    Toast.makeText(getActivity() , "Intent" + index, Toast.LENGTH_SHORT).show();
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


        for (int i = 0; i <  casts.size() ; i++)
        {
            LinearLayout ln = Util.getCastLayout(getContext(), casts.get(i) );
            m_castLayout.addView(ln , i);
        }
    }




    public void setMovieData(Movie movie)
    {
        //##################################################################
        //chip hatasi ve fragment not attached
        m_movieDetail =movie;

        Log.d("DEBUG" , isAdded()+"");
//        setAllViews(m_movieDetail);

    }



    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState)
    {
        Log.d("X" , "onInflate");
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment)
    {
        Log.d("X" , "onAttachFragment");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context)
    {
        Log.d("X" , "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d("X" , "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        Log.d("X" , "onStart");
        super.onStart();
    }

    @Override
    public void onResume()
    {
        Log.d("X" , "onResume");
        super.onResume();
    }

    @Override
    public void onPause()
    {
        Log.d("X" , "onPause");
        super.onPause();
    }

    @Override
    public void onStop()
    {
        Log.d("X" , "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        Log.d("X" , "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        Log.d("X" , "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach()
    {
        Log.d("X" , "onDetach");
        super.onDetach();
    }
}