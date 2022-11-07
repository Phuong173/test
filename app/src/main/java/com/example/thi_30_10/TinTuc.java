package com.example.thi_30_10;

public class TinTuc {
    int id;
    String title;
    String description;
    String link;
    String pubDate;
    public TinTuc() {
    }


    public TinTuc(String title, String description, String link, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    public TinTuc(int id, String title,String link ,String pubDate ) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
