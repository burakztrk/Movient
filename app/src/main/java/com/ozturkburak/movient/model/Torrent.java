
package com.ozturkburak.movient.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Torrent {

    @SerializedName("hash") @Expose private String hash;
    @SerializedName("quality") @Expose private String quality;
    @SerializedName("seeds") @Expose private Integer seeds;
    @SerializedName("peers") @Expose private Integer peers;
    @SerializedName("size") @Expose private String size;


    public static List<Torrent> demoTorrent()
    {
        List<Torrent> torrents = new ArrayList<>();
        Torrent torrent = new Torrent("8619B57A3F39F1B49A1A698EA5400A883928C0A2" , "720p" , 9 , 2 ,"702.04 MB");
        torrents.add(torrent);
        torrent = new Torrent("2A4B9A41C92A20A06C8846E66AD9B5BC4B669BC6" , "1080p" , 20, 8 , "1.40 GB");
        torrents.add(torrent);

        return torrents;
    }

    public Torrent(){}

    public Torrent(String hash, String quality, Integer seeds, Integer peers, String size)
    {

        this.hash = hash;
        this.quality = quality;
        this.seeds = seeds;
        this.peers = peers;
        this.size = size;
    }

    public Torrent setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public Torrent setQuality(String quality) {
        this.quality = quality;
        return this;
    }

    public Torrent setSeeds(Integer seeds) {
        this.seeds = seeds;
        return this;
    }

    public Torrent setPeers(Integer peers) {
        this.peers = peers;
        return this;
    }

    public Torrent setSize(String size) {
        this.size = size;
        return this;
    }

    public String getUrl()
    {
        return String.format("https://yts.ag/torrent/download/%s" , hash);
    }

    public String getHash()
    {
        return hash;
    }

    public String getQuality()
    {
        return quality;
    }

    public Integer getSeeds()
    {
        return seeds;
    }

    public Integer getPeers()
    {
        return peers;
    }

    public String getSize()
    {
        return size;
    }
}