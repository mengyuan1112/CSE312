package cse312.demo.APIController;


import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import cse312.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cse312.demo.Model.User;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/")
public class logRegController {
  public final UserService userService;

  @Autowired
  public logRegController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/registerform")
  public boolean userRegister(@RequestBody User user) {
    if(userService.checkUser(user.getUserName())) return false;
    userService.insertUser(user);
    return true;
  }

  @PostMapping("/loginform")
  public boolean userLogin(@NotNull @RequestBody Map<String, String> loginInfo) {
    if (!userService.validateUser(loginInfo.get("userName"),loginInfo.get("password"))) {
      return false;
    }
    List<User> allUser = userService.getAllUser();
    User user;
    System.out.println("hi");
    for(User person: allUser) {
      if(person.getUserName().equals(loginInfo.get("userName"))) {
        user = person;
        userService.addOnlineUser(user);
        break;
      }
    }
    return true;
  }

  @GetMapping("/onlineuserfresh")
  public HashMap<Integer, Set<User>> userFresher(){
    HashMap<Integer, Set<User>> userList = new HashMap<>();
    userList.put(userService.getAllOnlineUser().size(), userService.getAllOnlineUser().keySet());
    return userList;
  }

  @GetMapping("/alluser")
  public List<User> allUser(){
    return userService.getAllUser();
  }


  @PostMapping("/logout")
  public void logOutUser(@NotNull @RequestBody Map<String, String> logoutInfo){
    List<User> allUser = userService.getAllUser();
    User user = null;
    for(User person: allUser) {
      if(person.getUserName().equals(logoutInfo.get("userName"))) {
        user = person;
        break;
      }
    }
    userService.removeLogoutUser(user);
  }

  @GetMapping("/")
  public String getAllUser() {
    return "this is first page";
  }

}
