package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.http.conn.params.ConnManagerParams.setTimeout;

public class HomePage {

    private WebDriver driver = new ChromeDriver();

    // find login button
    @FindBy(id="logout-button")
    private WebElement logoutButton;

    // FILE / NOTE / CREDENTIAL TABS
    @FindBy(id="nav-notes-tab")
    private WebElement noteTabLink;

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

    @FindBy(id="link-to-home")
    private WebElement getHomeLink;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void postNote(String title, String description) throws InterruptedException {
        noteTabLink.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addNoteButton)).click();
        wait.until(ExpectedConditions.visibilityOf(noteTitleInput)).sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        submitNoteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(getHomeLink)).click();
        noteTabLink.click();
    }
    public String getFirstNoteTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds for the element to be visible
        WebElement noteTitleElement = wait.until(ExpectedConditions.visibilityOf(noteTitleText));
        return noteTitleElement.getText();
    }
}
