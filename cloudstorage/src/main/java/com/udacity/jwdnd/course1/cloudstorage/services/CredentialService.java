package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private UserMapper userMapper;
    private CredentialsMapper credentialsMapper;

    @Autowired // automatic dependency injection
    private EncryptionService encryptionService;

    public CredentialService(CredentialsMapper credentialsMapper, UserMapper userMapper) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
    }

    // we need methods to encrypt and decrypt the password
    public Credential encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String key = Base64.getEncoder().encodeToString(salt);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public Credential decryptPassword(Credential credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        return credential;
    }

    public List<Credential> getAllCredentials() {
        return this.credentialsMapper.getCredentialList();
    }
    public void addCredential(CredentialForm credentialForm, String userName) {
        Credential credential = new Credential();
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setPassword(credentialForm.getPassword());

        Integer userId = userMapper.getUser(userName).getUserid();
        credential.setUserId(userId);

        credentialsMapper.insertCredential(encryptPassword(credential));
    }

    public Credential getCredential(Integer credentialId) {
        return credentialsMapper.getSingleCredential(credentialId);
    }

    public void updateCredential(Credential credential) {
        credentialsMapper.updateCredential(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredentials(credentialid);
    }
}
