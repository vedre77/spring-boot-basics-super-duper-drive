package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {

    private String noteId;
    private String title;
    private String description;

    public String getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
