package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    List<Credential> getCredentialList(String username);

    @Select("SELECT * FROM credentials WHERE credentialid = #{credentialid}")
    Credential findSingleCredential(int credentialid);

    @Insert("INSERT INTO credentials (url, username, key, password, userid)" +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userid}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential, int userid);

    @Delete("DELETE FROM credentials WHERE credentialid = #{credentialid}")
    int deleteCredentials(int credentialid);

    @Update("UPDATE credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    public int updateCredential(Credential credential);
}
