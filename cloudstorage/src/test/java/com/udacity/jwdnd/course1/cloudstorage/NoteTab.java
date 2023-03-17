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
    //
    @FindBy(id="save-note-changes")
    private WebElement submitNoteButton;
    public NoteTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="link-to-home")
    private WebElement getHomeLink;

    public void postNote(String title, String description) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        addNoteButton.click();
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput)).sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        submitNoteButton.click();
        getHomeLink.click();
    }

    public String getFirstNoteTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement noteTitleElement = wait.until(ExpectedConditions.visibilityOf(noteTitleText));
        return noteTitleElement.getText();
    }
}
