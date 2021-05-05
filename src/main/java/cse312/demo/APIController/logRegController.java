package cse312.demo.APIController;


import java.util.*;

// import com.sun.istack.internal.NotNull;
import cse312.demo.Dao.UserRepository;
import cse312.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cse312.demo.Model.User;


@CrossOrigin(origins = "http://cse312-12.dcsl.buffalo.edu")
@RestController
@RequestMapping("/")
public class logRegController {
  public final UserService userService;
//  public final UserRepository userRepository;

  @Autowired
  public logRegController(UserService userService, UserRepository userRepository) {
    this.userService = userService;

//    this.userRepository = userRepository;
  }

  @PostMapping("/registerform")
  public boolean userRegister(@RequestBody User user) {
    if(userService.checkUser(user.getUserName())) return false;
    userService.insertUser(user);
    userService.addOnlineUser(user);
//    userRepository.insert(user);
    return true;
  }

  @PostMapping("/loginform")
  public boolean userLogin( @RequestBody Map<String, String> loginInfo) {
    if (!userService.validateUser(loginInfo.get("userName"),loginInfo.get("password"))) {
      return false;
    }
    return true;
  }

  @GetMapping("/onlineuserfresh")
  public HashMap<Integer, List<User>> userFresher(){
    HashMap<Integer, List<User>> userList = new HashMap<>();
    userList.put(userService.getAllOnlineUser().size(), userService.getAllOnlineUser());
    return userList;
  }

  @GetMapping("/alluser")
  public List<User> allUser(){
    return userService.getAllUser();
  }


  @PostMapping("/logout")
  public void logOutUser(@RequestBody Map<String, String> logoutInfo){
    userService.removeLogoutUser(logoutInfo.get("userName"));
  }

  @PostMapping("/updateProfile")
  public boolean updateProfile(@RequestBody Map<String, String> profile) {
    if(userService.updateProfile(profile.get("userName"), profile.get("password"))) return true;
    return false;
  }

  @GetMapping("/")
  public String getAllUser() {
    return "this is first page";
  }

}
