package cse312.demo.APIController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class chatController {
    @GetMapping("/chat/{username}")
    public ModelAndView index(@PathVariable String username, HttpServletRequest request) throws UnknownHostException {
        if(StringUtils.isEmpty(username)){
            username = "Unknown";
        }
        ModelAndView mav = new ModelAndView("/chat");
        mav.addObject("username", username);
        System.out.println(username);
        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat/" + username);
        System.out.println("ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat/" + username);
        return mav;
    }
}
