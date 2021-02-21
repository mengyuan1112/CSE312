package cse312.demo.Dao;

import cse312.demo.Model.User;
import java.util.*;

public interface Dao {

  List<User> userDB = new ArrayList<>();
  Map<String,String> UserInfoDB = new HashMap<>();

  void insertUser(User user);

  User findUser(String email);

  void updateUser(User user);

  boolean checkUserName(String username);

  String getPasswordByUserName(String username);

  List<User> getAllUser();
}
