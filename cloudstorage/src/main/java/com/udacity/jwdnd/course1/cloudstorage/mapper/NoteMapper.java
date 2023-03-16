package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    // get all notes and make a new one:
    @Select("SELECT * FROM notes")
    List<Note> getAllNotes();

    @Select("SELECT * FROM notes WHERE noteid = #{noteid}")
    Note getSingleNote(int noteid);

    @Insert("INSERT INTO notes (notetitle, notedescription, userid) VALUES" +
            "(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    void insertNote(Note note);

    @Update("UPDATE notes SET notetitle=#{noteTitle}, notedescription=#{noteDescription}"
            + "WHERE noteid=#{noteid}")
    void updateSingleNote(Note note);

    @Delete("DELETE FROM notes WHERE noteid = #{noteid}")
    int deleteNote(int noteid);
}
