package com.ozturkburak.movient.search;


import java.io.Serializable;

/**
 * Created by burak on 4/3/17.
 * TODO: rating textViewinda + olayini yaz
 * TODO: orderby kismi hatali
 *
 *
 limit=10&
 page=1&
 m_quality=1080p&
 minimum_rating=7&
 query_term=star&
 m_genre=action&
 sort_by=rating&
 with_rt_ratings=true
 */

public class SearchOptions implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String  LIMIT_DEFAULT = "25";
    public static final String  PAGE_DEFAULT = "1";

    public static SearchOptions ms_searchOptions;

    private String m_limit ;
    private String  m_page;
    private boolean m_withRtRating;
    private String m_searchText;

    private String m_genre;
    private Raiting m_rating;
    private SortBy m_sortBy;
    private Quality m_quality;

    //return latest movie query
    public SearchOptions()
    {
        m_limit = LIMIT_DEFAULT;
        m_page = PAGE_DEFAULT;

        m_genre = Genre.ALL.name();
        m_rating = Raiting.ALL;
        m_sortBy = SortBy.LATEST;
        m_quality = Quality.ALL;
        m_searchText = null;
        m_withRtRating = false;
    }

    public SearchOptions(String limit , String page , String searchText , Quality quality , Genre genre , Raiting raiting , SortBy sortBy , boolean withRtRating) {

        if (searchText != null)
            m_searchText = searchText.toLowerCase();

        m_limit = limit.contains(LIMIT_DEFAULT) ? null : limit;
        m_page = page;
        m_withRtRating = withRtRating;

        m_quality = quality;
        m_genre = genre.name();
        m_rating = raiting;
        m_sortBy = sortBy;

    }


    public SearchOptions(String limit , String page , String searchText , int qualityId , int genreId , int raitingId , int sortById , boolean withRtRating) {
        this(limit , page , searchText , Quality.values()[qualityId] , Genre.values()[genreId] , Raiting.values()[raitingId], SortBy.values()[sortById] , withRtRating );
    }


    public SearchOptions(String limit , String page , String searchText , String qualityStr , String genreStr , String raitingStr , String sortByStr , boolean withRtRating) {
        this(limit ,page ,searchText , Quality.valueOf(qualityStr) , Genre.valueOf(genreStr), Raiting.valueOf(raitingStr) , SortBy.valueOf(sortByStr) , withRtRating);
    }

    public void setPage(int page){ this.m_page = String.valueOf(page); }

    public void setGenre(Genre genre)
    {
        m_genre = genre.name();
    }

    public void setGenre(String genreText)
    {
        m_genre = genreText;
    }

    public void set_rating(Raiting rating)
    {
        m_rating = rating;
    }

    public void set_rating(String ratingText)
    {
        m_rating = Raiting.valueOf(ratingText);
    }


    public String getLimit(){ return m_limit;}
    public String getPage(){ return m_page;}
    public String getSearchText(){ return m_searchText;}

    public String getGenre()
    {
        return m_genre;
    }
    public Raiting getRating(){ return m_rating; }
    public SortBy getSortBy(){ return m_sortBy; }
    public Quality getQuality(){ return m_quality; }

    public boolean isWithRtRating(){ return m_withRtRating; }

    public String getGenreVal()
    {
        return m_genre.compareTo(Genre.ALL.name()) == 0 ? null : m_genre;
    }
    public String getRatingVal(){ return  m_rating.compareTo(Raiting.ALL)== 0 ? null : m_rating.text; }
    public String getSortByVal(){ return  m_sortBy.compareTo(SortBy.LATEST)== 0 ? null : m_sortBy.text; }
    public String getQualityVal(){ return  m_quality.compareTo(Quality.ALL)== 0 ? null : m_quality.text; }


    public String toString(){ return String.format("%s | %s | %s | %s | %s", m_searchText , m_quality  , m_genre , m_rating , m_sortBy);}



    public enum Quality
    {
        ALL("all"),
        Q720P("720p"),
        Q1080P("1080p"),
        Q3D("3D");

        private String text;
        Quality(String s ) { text = s; }
        public static Quality byId(int Id) { return Quality.values()[Id]; }

        public static String getNamebyId(int Id) { return byId(Id).text; }

        @Override public String toString() { return this.text;}
    }


    public enum Genre
    {
        ALL("all"),
        ACTION("action"),
        ADVENTURE("adventure"),
        ANIMATION("animation"),
        BIOGRAPHY("biography"),
        COMEDY("comedy"),
        CRIME("crime"),
        DOCUMENTARY("documentary"),
        DRAMA("drama"),
        FAMILY("family"),
        FANTASY("fantasy"),
        HISTORY("history"),
        HORROR("horror"),
        MUSIC("music"),
        MYSTERY("mystery"),
        ROMANCE("romance"),
        SCI_FI("sci-fi"),
        SPORT("sport"),
        THRILLER("thriller"),
        WAR("war"),
        WESTERN("western");

        private String text;
        Genre(String s){ text = s; }
        public static Genre byId(int Id) { return Genre.values()[Id]; }

        public static String getNamebyId(int Id) { return byId(Id).text; }

        @Override public String toString() { return this.text;}
    }


    public enum Raiting
    {
        ALL("all"),
        R9("9"),
        R8("8"),
        R7("7"),
        R6("6"),
        R5("5"),;

        private String text;
        Raiting(String s){ text = s; }
        public static Raiting byId(int Id) { return Raiting.values()[Id]; }

        public static String getNamebyId(int Id) { return byId(Id).text; }

        @Override public String toString() { return this.text;}
    }


    public enum SortBy
    {
        RATING("rating"),
        LATEST("date_added"),
        ALPHABETICAL("title"),
        YEAR("year"),
        DOWNLOAD("download_count");

        private String text;
        SortBy(String s){ text = s; }

        public static SortBy byId(int Id) { return SortBy.values()[Id]; }

        public static String getNamebyId(int Id) { return byId(Id).text; }
    }
}
