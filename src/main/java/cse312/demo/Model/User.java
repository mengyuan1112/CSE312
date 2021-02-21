package cse312.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User model to create User object to identify different user
 *
 * @Param (personNumber, userName, password, gender)
 */
public class User {

  private String personName;
  private String userName;
  private String password;
  private String gender;

  public User(@JsonProperty("personName")String personName,
              @JsonProperty("userName")String userName,
              @JsonProperty("password")String password,
              @JsonProperty("gender") String gender) {
    this.personName = personName;
    this.gender = gender;
    this.userName = userName;
    this.password = password;
  }

  public String getGender() {
    return gender;
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
}
