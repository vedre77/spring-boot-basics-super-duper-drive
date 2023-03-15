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
import java.util.Objects;

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

    public Credential updateCredentialFromForm(Credential credential, CredentialForm credentialForm) {
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setPassword(credentialForm.getPassword());
        return credential;
    }
    public void addCredential(CredentialForm credentialForm, String userName) {
        // check if credential exists
        String credentialId = credentialForm.getCredentialId();
        if (!Objects.equals(credentialId, "")) {
            int id = Integer.parseInt(credentialId);
            Credential credential = credentialsMapper.getSingleCredential(id);
            Credential updatedCredential = updateCredentialFromForm(credential, credentialForm);
            credentialsMapper.updateSingleCredential(encryptPassword(updatedCredential));
            return;
        }
        // create new credential
        Credential credential = new Credential();
        Credential updatedCredential = updateCredentialFromForm(credential, credentialForm);
        Integer userId = userMapper.getUser(userName).getUserid();
        updatedCredential.setUserId(userId);
        credentialsMapper.insertCredential(encryptPassword(updatedCredential));
    }

    public Credential getCredential(Integer credentialId) {
        return credentialsMapper.getSingleCredential(credentialId);
    }

    public void updateCredential(Credential credential) {
        credentialsMapper.updateSingleCredential(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredentials(credentialid);
    }
}
