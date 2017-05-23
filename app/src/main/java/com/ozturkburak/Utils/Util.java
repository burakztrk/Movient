package com.ozturkburak.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ozturkburak.movient.R;
import com.ozturkburak.movient.model.Cast;
import com.squareup.picasso.Picasso;
/**
 * Created by burak on 4/16/17.
 */

public class Util
{
    public enum PAGES
    {
        LIST(0),
        MOVIEDETAIL(1),
        SEARCH(2);

        private final int m_value;
        PAGES(int val) { m_value = val; }
        public int getValue(){ return m_value; }

    }

    private static boolean firstRunning = true;
    private static float ms_displayMetric = 0;
    private static LinearLayout.LayoutParams ms_layoutParams = null;


    static
    {

    }

    public static boolean isAppFirstOpening()
    {
        if (firstRunning)
        {
            firstRunning = false;
            return true;
        }
        return firstRunning;
    }

    public static int getDpi(Context context, int val)
    {
        if (ms_displayMetric == 0)
            ms_displayMetric = context.getResources().getDisplayMetrics().density;

        return (int) (val * ms_displayMetric);
    }

    public static Button getGenreStyleButton(Context context, String text, int color)
    {
        if (ms_layoutParams == null)
        {
            ms_layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, Util.getDpi(context, 35), 1.0f);
            int marginVal = Util.getDpi(context, 10);
            ms_layoutParams.setMargins(marginVal, 0, marginVal, 0);

        }

        Button bt = new Button(context);
        bt.setBackgroundColor(color);
        bt.setTextAppearance(context, R.style.button_genre_fontstyle);
        bt.setText(text);
        bt.setAllCaps(false);
        bt.setGravity(Gravity.CENTER_HORIZONTAL);
        bt.setTextColor(Color.WHITE);
        bt.setLayoutParams(ms_layoutParams);

        return bt;
    }

    //Cast object gecilecek
    public static LinearLayout getCastLayout(Context context, Cast cast)
    {
        LinearLayout ln = new LinearLayout(context);
        ln.setOrientation(LinearLayout.HORIZONTAL);
        ln.setPadding(0,0,10,10);

        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(Util.getDpi(context, 60), Util.getDpi(context, 60));
        layoutparams.gravity = Gravity.CENTER_VERTICAL;

        LinearLayout.LayoutParams textlayoutparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textlayoutparams.gravity = Gravity.CENTER_VERTICAL;


        ImageView img = new ImageView(context);
        img.setLayoutParams(layoutparams);
        ln.addView(img);


        Picasso.with(context)
                .load(cast.getUrlSmallImage())
                .error(R.drawable.cast_error)
                .transform(new CircleTransform())
                .placeholder(R.drawable.cast_error)
                .into(img);



        TextView tx = new TextView(context);
        tx.setText(String.format("%s %n(%s)" , cast.getName() , cast.getCharacterName()));
        tx.setTextSize(15.0f);
        tx.setGravity(Gravity.CENTER_VERTICAL);
        tx.setLayoutParams(textlayoutparams);
        tx.setPadding(20, 0, 10, 0);
        tx.setMaxLines(2);
        ln.addView(tx);

        final Context c = context;
        ln.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO fragment1 e yonlendirilecek aktrisle beraber
                Toast.makeText(c, "Intent", Toast.LENGTH_SHORT).show();
            }
        });

        return ln;
    }
}
