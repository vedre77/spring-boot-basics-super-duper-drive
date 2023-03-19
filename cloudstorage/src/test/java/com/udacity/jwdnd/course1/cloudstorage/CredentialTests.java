package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CredentialTests extends  CloudStorageApplicationTests {

    @Test
    public void testCredentialCRUD() throws InterruptedException {
        // Create a new credential
        WebDriver driver = this.getDriver();
        String url = "myUrl";
        String username = "meAgain";
        String password = "findMe";
        testGetHomePage();
        HomePage homePage = new HomePage(driver);
        homePage.toggleCredentialTab();
        CredentialTab credentialTab = new CredentialTab(driver);
        credentialTab.postCredential(url, username, password);
        homePage.toggleCredentialTab();
        Assertions.assertTrue(driver.getPageSource().contains(url));
        Assertions.assertNotEquals(credentialTab.findDisplayedPassword(), password);
        // edit a credential
        String newUsername = "newMe";
        credentialTab.editCredential(newUsername);
        homePage.toggleCredentialTab();
        Assertions.assertTrue(driver.getPageSource().contains(newUsername));
        // delete a credential
        credentialTab.deleteCredential();
        homePage.toggleCredentialTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            credentialTab.checkCredentialDeleted();
        });
    }
}
