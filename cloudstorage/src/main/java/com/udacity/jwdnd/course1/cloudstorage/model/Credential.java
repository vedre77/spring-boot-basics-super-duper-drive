package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

public class Credential {

    private int credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    private int userid;

    public int getCredentialId() {
        return credentialid;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userid;
    }

    public void setCredentialId(int credentialId) {
        this.credentialid = credentialId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public String revealPassword() {
        EncryptionService encryptionService = new EncryptionService();
        String revealedPassword = encryptionService.decryptValue(this.getPassword(), this.getKey());
        return revealedPassword;
    }
}
