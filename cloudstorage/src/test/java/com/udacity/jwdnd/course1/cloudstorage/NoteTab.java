package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NoteTab {

    private WebDriver driver = new ChromeDriver();

    @FindBy(id="add-note-button")
    private WebElement addNoteButton;
    // NOTE DISPLAY FIELD
    @FindBy(className="note-title")
    private WebElement noteTitleText;

    // NOTE FORM FIELDS
    @FindBy(id="note-title")
    private WebElement noteTitleInput;

    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id="save-note-changes")
    private WebElement submitNoteButton;

    @FindBy(id="link-to-home")
    private WebElement getHomeLink;

    @FindBy(className="btn-success")
    private WebElement editNoteButton;

    @FindBy(className="btn-danger")
    private WebElement deleteNoteButton;

    public NoteTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String getFirstNoteTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement noteTitleElement = wait.until(ExpectedConditions.visibilityOf(noteTitleText));
        return noteTitleElement.getText();
    }

    public String checkNoteDeleted() {
        return noteTitleText.getText();
    }

    public void postNote(String title, String description) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        addNoteButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput)).sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        submitNoteButton.click();
        getHomeLink.click();
    }

    public void editNote(String newTitle) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        editNoteButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput)).clear();
        noteTitleInput.sendKeys(newTitle);
        submitNoteButton.click();
        getHomeLink.click();
    }

    public void deleteNote(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        deleteNoteButton.click();
        getHomeLink.click();
    }
}
