package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private int noteid;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public int getNoteid() {
        return noteid;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
