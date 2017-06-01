
package com.ozturkburak.movient.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Serializable
{
    private static final long serialVersionUID = 1L;

    @SerializedName("id") @Expose private String id;
    @SerializedName("url") @Expose private String url;
    @SerializedName("imdb_code") @Expose private String imdbCode;
    @SerializedName("title") @Expose private String title;
    @SerializedName("title_long") @Expose private String titleLong;
    @SerializedName("slug") @Expose private String slug;
    @SerializedName("year") @Expose private Integer year;
    @SerializedName("rating") @Expose private Float rating;
    @SerializedName("runtime") @Expose private Integer runtime;
    @SerializedName("genres") @Expose private List<String> genres = null;
    @SerializedName("description_full") @Expose private String descriptionFull;
    @SerializedName("yt_trailer_code") @Expose private String ytTrailerCode;
    @SerializedName("language") @Expose private String language;
    @SerializedName("mpa_rating") @Expose private String mpaRating;
    @SerializedName("background_image") @Expose private String backgroundImage;
    @SerializedName("small_cover_image") @Expose private String smallCoverImage;
    @SerializedName("medium_cover_image") @Expose private String mediumCoverImage;
    @SerializedName("large_cover_image") @Expose private String largeCoverImage;
    @SerializedName("cast") @Expose private List<Cast> cast = null;
    @SerializedName("torrents") @Expose private List<Torrent> torrents = null;

    public Movie(){}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getImdbCode() { return String.format("http://www.imdb.com/title/%s/",imdbCode) ; }
    public void setImdbCode(String imdbCode) { this.imdbCode = imdbCode; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTitleLong() { return titleLong;}
    public void setTitleLong(String titleLong) { this.titleLong = titleLong; }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {this.descriptionFull = descriptionFull; }

    public String getYtTrailerCode() { return  ytTrailerCode;}

    public String getYtTrailerLink() { return String.format("https://www.youtube.com/watch?v=%s" , ytTrailerCode);}

    public void setYtTrailerCode(String ytTrailerCode) {
        this.ytTrailerCode = ytTrailerCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }

    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    public void setSmallCoverImage(String smallCoverImage) {
        this.smallCoverImage = smallCoverImage;
    }

    public String getMediumCoverImage() {
        return mediumCoverImage;
    }

    public void setMediumCoverImage(String mediumCoverImage) {
        this.mediumCoverImage = mediumCoverImage;
    }

    public String getLargeCoverImage() {
        return largeCoverImage;
    }

    public void setLargeCoverImage(String largeCoverImage) {
        this.largeCoverImage= largeCoverImage;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }



    public static Movie DemoMovie()
    {
        Movie movieDetail = new Movie();

        movieDetail.setId("15");
        movieDetail.setUrl("https://yts.ag/movie/16-blocks-2006");
        movieDetail.setImdbCode("tt0450232");
        movieDetail.setTitle("16 Blocks");
        movieDetail.setTitleLong("16 Blocks (2006)");
        movieDetail.setSlug("16-blocks-2006");
        movieDetail.setYear(2006);
        movieDetail.setRating(6.6f);
        movieDetail.setRuntime(102);


        List<String> genres = new ArrayList<>();
        genres.add("Action");
        genres.add("Crime");
        genres.add("Drama");
        genres.add("Thriller");
        movieDetail.setGenres(genres);

        movieDetail.setDescriptionFull("Jack Mosley, a burnt-out detective, is assigned the unenviable task of transporting a fast-talking convict from jail to a courthouse 16 blocks away. However, along the way he learns that the man is supposed to testify against Mosley's colleagues, and the entire NYPD wants him dead. Mosley must choose between loyalty to his colleagues and protecting the witness, and never has such a short distance seemed so long...");
        movieDetail.setYtTrailerCode("7LaqTTRNvpg");
        movieDetail.setLanguage("English");
        movieDetail.setMpaRating("PG-13");
        movieDetail.setBackgroundImage("https://yts.ag/assets/images/movies/16_Blocks_2006/background.jpg");
        movieDetail.setSmallCoverImage("https://yts.ag/assets/images/movies/16_Blocks_2006/small-cover.jpg");
        movieDetail.setMediumCoverImage("https://yts.ag/assets/images/movies/16_Blocks_2006/medium-cover.jpg");
        movieDetail.setCast(Cast.demoCast());
        movieDetail.setTorrents(Torrent.demoTorrent());

        return movieDetail;

    }
}