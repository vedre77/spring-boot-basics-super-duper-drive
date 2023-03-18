package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialTab {

    private WebDriver driver;

    @FindBy(id="add-credential-button")
    private WebElement addCredentialButton;
    @FindBy(id="credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id="credential-username")
    private WebElement credentialUsernameInput;
    @FindBy(id="credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(className="credential-password")
    private WebElement credentialPasswordValue;
    @FindBy(id="save-credential-changes")
    private WebElement credentialSubmitButton;
    @FindBy(id="link-to-home")
    private WebElement getHomeLink;

    @FindBy(className="btn-success")
    private WebElement editCredentialButton;

    @FindBy(className="btn-danger")
    private WebElement deleteCredentialButton;

    public CredentialTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void postCredential(String url, String username, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addCredentialButton)).click();
        wait.until(ExpectedConditions.visibilityOf(credentialUrlInput)).sendKeys(url);
        credentialUsernameInput.sendKeys(username);
        credentialPasswordInput.sendKeys(password);
        credentialSubmitButton.click();
        getHomeLink.click();
    }

    public String findDisplayedPassword() {
        return credentialPasswordValue.getText();
    }

    public void editCredential(String newUsername) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(editCredentialButton)).click();
        wait.until(ExpectedConditions.visibilityOf(credentialUsernameInput)).clear();
        credentialUsernameInput.sendKeys(newUsername);
        credentialSubmitButton.click();
        getHomeLink.click();
    }
    public void deleteCredential(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(deleteCredentialButton)).click();
        getHomeLink.click();
    }

    public String checkCredentialDeleted() {
        return credentialPasswordValue.getText();
    }
}
