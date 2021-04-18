package cse312.demo.Service;

import cse312.demo.Dao.UserOperator;
import cse312.demo.Dao.UserRepository;
import cse312.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService{
  final UserRepository userRepository;
  static List<User> onlineUser;
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.onlineUser = new ArrayList<>();
  }

  public boolean insertUser(User user) {
    if(this.checkUser(user.getUserName())) return false;
    userRepository.save(user);
    return true;
  }

  public boolean checkUser(String username) {
    List<User> userDB = userRepository.findAll();
    for(User user: userDB) {
      System.out.println(user.getUserName());
      if(user.getUserName().equals(username)) return true;
    }
    return false;
  }

  public boolean validateUser(String username, String password) {
    List<User> userDB = userRepository.findAll();
    System.out.println(userDB.size());
    for(User user: userDB) {
      if(user.getUserName().equals(username) && user.getPassword().equals(password)) return true;
    }
    return false;
  }

  public List<User> getAllOnlineUser() {
    return onlineUser;
  }

  public void addOnlineUser(User user) {
    onlineUser.add(user);
  }

  public void removeLogoutUser(String username) {
    User user = userRepository.findByUserName(username);
    onlineUser.remove(user);
  }

  public List<User> getAllUser(){
    return userRepository.findAll();
  }

  public boolean updateProfile(String username, String password) {
    User user = userRepository.findByUserName(username);
    if(user == null) return false;
    user.setPassword(password);
    userRepository.save(user);
    return true;
  }

}
