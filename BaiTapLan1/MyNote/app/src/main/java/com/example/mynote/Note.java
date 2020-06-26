package com.example.mynote;

import java.util.Calendar;
import java.util.Date;

public class Note {
    private String title;
    private String content;
    private String lastUpdate;
    private String id;

    public Note() {
        this.id = "";
        this.title = "";
        this.content = "";
        this.lastUpdate = Calendar.getInstance().getTime().toString();
    }

    public Note(String title, String content, String lastUpdate) {
        this.title = title;
        this.content = content;
        this.lastUpdate = lastUpdate;
    }
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.lastUpdate = Calendar.getInstance().getTime().toString();
    }
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return this.title + this.content;
    }


}
