package cse312.demo.Service;

import cse312.demo.Dao.UserOperator;
import cse312.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService{
  final UserOperator userOperator;

  @Autowired
  public UserService(@Qualifier("User") UserOperator userOperator) {
    this.userOperator = userOperator;
  }


  public boolean insertUser(User user) {
    if(this.checkUser(user.getUserName())) return false;
    this.userOperator.insertUser(user);
    return true;
  }


  public boolean checkUser(String username) {
    return this.userOperator.checkUserName(username);
  }

  public boolean validateUser(String username, String password) {
    if(checkUser(username) && userOperator.getPasswordByUserName(username).equals(password)) return true;
    return false;
  }

  public List<User> getAllUser() {
    return userOperator.getAllUser();
  }

}
