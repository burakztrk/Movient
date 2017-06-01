
package com.ozturkburak.movient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cast  implements  Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("character_name")
    @Expose
    private String characterName;
    @SerializedName("url_small_image")
    @Expose
    private String urlSmallImage;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;


    public static List<Cast> demoCast()
    {
        List<Cast> cast = new ArrayList<>();
        Cast actor = new Cast("Bruce Willis" , "Det. Agent. Jack Mosley" , "https://yts.ag/assets/images/actors/thumb/nm0000246.jpg" , "0000246");
        cast.add(actor);
        actor = new Cast("David Morse" , "Det. Doc. Frank Nugent" , "https://yts.ag/assets/images/actors/thumb/nm0001556.jpg" , "0001556");
        cast.add(actor);
        actor = new Cast("Casey Sander" , "Capt. Professor Dan Gruber" , null , "0761389");
        cast.add(actor);
        actor  =  new Cast("Tom Wlaschiha" , "Bus Passenger" , "https://yts.ag/assets/images/actors/thumb/nm0937239.jpg" , "0937239");
        cast.add(actor);

        return cast;
    }

    public  Cast(){};

    public Cast(String name, String characterName, String urlSmallImage, String imdbCode)
    {
        this.name = name;
        this.characterName = characterName;
        this.urlSmallImage = urlSmallImage;
        this.imdbCode = imdbCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getUrlSmallImage() {

        return urlSmallImage;
    }

    public void setUrlSmallImage(String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

}
