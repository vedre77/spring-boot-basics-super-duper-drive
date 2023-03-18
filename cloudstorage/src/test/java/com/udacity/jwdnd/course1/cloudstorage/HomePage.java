package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(id="logout-button")
    private WebElement logoutButton;
    // TABS
    @FindBy(id="nav-notes-tab")
    private WebElement noteTabLink;
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialTabLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void toggleNoteTab() {
        noteTabLink.click();
    }
    public void toggleCredentialTab() {
        credentialTabLink.click();
    }
}
