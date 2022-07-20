package com.openclassrom.watchlist;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
public class WatchlistItem {

    @NotBlank(message = "not blank man!!!!")
    private String title;
    private String rating;
    private String priority;
    private String comment;
    private int id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WatchlistItem(String title, String rating, String priority, String comment, int id) {
        this.title = title;
        this.rating = rating;
        this.priority = priority;
        this.comment = comment;
        this.id = id;
    }

    public WatchlistItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistItem that = (WatchlistItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
