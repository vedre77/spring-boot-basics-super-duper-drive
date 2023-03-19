package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NoteTests extends CloudStorageApplicationTests {

    @Test
    public void testNoteCRUD() throws InterruptedException {
        // Create a new note
        WebDriver driver = this.getDriver();
        String noteTitle = "test title";
        String noteDescription = "test description";
        testGetHomePage();
        HomePage homePage = new HomePage(driver);
        homePage.toggleNoteTab();
        NoteTab noteTab = new NoteTab(driver);
        noteTab.postNote(noteTitle, noteDescription);
        homePage.toggleNoteTab();
        Assertions.assertTrue(driver.getPageSource().contains(noteTitle));
        // Edit the note
        noteTab.editNote("change");
        homePage.toggleNoteTab();
        Assertions.assertTrue(driver.getPageSource().contains("change"));
        // Delete the note
        noteTab.deleteNote();
        homePage.toggleNoteTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            noteTab.checkNoteDeleted();
        });
    }
}