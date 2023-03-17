package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // find login button
    @FindBy(id="logout-button")
    private WebElement logoutButton;

//    @FindBy(id="submitMessage")
//    private WebElement submitButton;
//
//    @FindBy(className = "chatMessageUsername")
//    private WebElement firstMessageUsername;
//
//    @FindBy(className = "chatMessageText")
//    private WebElement firstMessageText;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

//    public ChatMessage getFirstMessage() {
//        ChatMessage result = new ChatMessage();
//        result.setMessageText(firstMessageText.getText());
//        result.setUsername(firstMessageUsername.getText());
//        return result;
//    }
}
