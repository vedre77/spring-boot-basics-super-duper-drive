package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {
    private UserMapper userMapper;
    private NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    public Note updateNoteFromForm(Note note, NoteForm noteForm) {
        note.setNoteTitle(noteForm.getTitle());
        note.setNoteDescription(noteForm.getDescription());
        return note;
    }

    public void insertNewNote(NoteForm newNote, String userName) {
        // check if note exists
        String noteId = newNote.getNoteId();
        if (!Objects.equals(noteId, "")) {
            int id = Integer.parseInt(noteId);
            Note note = noteMapper.getSingleNote(id);
            Note updatedNote = updateNoteFromForm(note, newNote);
            noteMapper.updateSingleNote(updatedNote);
            return;
        }
        // create new note
        Note note = new Note();
        Note updatedNote = updateNoteFromForm(note, newNote);
        Integer userId = userMapper.getUser(userName).getUserid();
        updatedNote.setUserId(userId);
        noteMapper.insertNote(updatedNote);
    }

    public void deleteNote(int noteid) {
        noteMapper.deleteNote(noteid);
    }
}
