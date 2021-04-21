package cse312.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * User model to create User object to identify different user
 *
 * @Param (personNumber, userName, password, gender)
 */
@Document(collection = "userdb")
public class User {

  private String personName;
  @Id
  private String userName;
  private String password;
  private byte[] salt;
  private byte[] saltedPwd;

  public User(@JsonProperty("personName")String personName,
              @JsonProperty("userName")String userName,
              @JsonProperty("password")String password) throws NoSuchAlgorithmException {
    this.personName = personName;
    this.userName = userName;
    this.password = password;
    this.salt = createSalt();
    this.saltedPwd = hashPwd();
  }


  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public String getPersonName() {
    return personName;
  }

  private byte[] getSalt() {return salt;}

  private byte[] hashPwd() throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("MD5");
    digest.update(this.salt);
    byte[] hash = digest.digest(this.password.getBytes());
    return hash;
  }

  private byte[] createSalt() {
    byte[] bytes = new byte[10];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(bytes);
    return bytes;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
