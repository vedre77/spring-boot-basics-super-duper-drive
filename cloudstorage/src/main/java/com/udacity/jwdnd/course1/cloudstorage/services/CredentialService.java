package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialsMapper credentialsMapper;

    @Autowired // automatic dependency injection
    private EncryptionService encryptionService;

    public CredentialService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating Credential Service Bean");
    }
    // we need methods to encrypt and decrypt password
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


    public List<Credential> getAllCredentials(String username) {
        return this.credentialsMapper.getCredentialList(username);
    }
    public void addCredential(Credential credential, int userid) {
        credentialsMapper.insertCredential(encryptPassword(credential), userid);
    }

    public void updateCredential(Credential credential) {
        credentialsMapper.updateCredential(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.deleteCredentials(credentialid);
    }
}
