package cse312.demo.Dao;


import cse312.demo.Model.Chat;
import cse312.demo.Model.User;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("User")
public class UserOperator implements Dao{

  public static List<User> UserDB = new ArrayList<>();
  public static Map<String,String> UserInfoDB = new HashMap<>();
  public static Map<User, Boolean> onlineUser = new HashMap<>();

  public static List<Chat> ChatDB = new ArrayList<>();


  @Override
  public void insertUser(User user) {
    UserDB.add(user);
    UserInfoDB.put(user.getUserName(),user.getPassword());
  }

  @Override
  public User findUser(String email) {
    return null;
  }

  @Override
  public void updateUser(User user) {

  }


  @Override
  public boolean checkUserName(String username) {
    if(!UserInfoDB.containsKey(username)) return false;
    return true;
  }

  @Override
  public String getPasswordByUserName(String username) {
    return UserInfoDB.get(username);
  }

  @Override
  public Map<User, Boolean> getAllOnlineUser() {
    return onlineUser;
  }

  @Override
  public void removeLogOutUser(User user) {
    onlineUser.remove(user);
  }

  @Override
  public void addOnlineUser(User user) {
    onlineUser.put(user, true);
    System.out.println(onlineUser);
  }

  @Override
  public List<User> getAllUser() {
    return UserDB;
  }

  @Override
  public void insertChat(Chat chat) {
    ChatDB.add(chat);
  }
}
