package com.ozturkburak.Utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import com.ozturkburak.movient.DetailActivity;
import com.ozturkburak.movient.R;
import com.ozturkburak.movient.model.Movie;

/**
 * Created by burak on 5/31/17.
 */

public class IntentUtils
{

    public static String BUNDLE_SEARCHOPTIONDATA = "SEARCHOPTIONS";
    public static int RESULTCODE_NEWSEARCHOPTIONS = 1;
    public static int REQUESTCODE_SHOWMOVIEDETAIL = 2;

    public static Intent movieDetailIntent(Activity activity, Movie movie)
    {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(Util.MOVIEINFO , movie);
        return intent;
    }

    public static Intent openWebBrowser(String url)
    {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        return new Intent(Intent.ACTION_VIEW , Uri.parse(url));
    }

    public static void openChromeTab(Activity activity , String url)
    {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        CustomTabsIntent customTabsIntent = builder
                .setToolbarColor(activity.getResources().getColor(R.color.colorPrimary))
                .setShowTitle(false)
                .setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left)
                .build();

        customTabsIntent.launchUrl(activity , Uri.parse(url));

    }


    public static void watchYoutubeVideo(Activity activity , String id)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));

        try
        {
            activity.startActivity(intent);
        }
        catch (ActivityNotFoundException ex)
        {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
            activity.startActivity(intent);
        }
    }
}
