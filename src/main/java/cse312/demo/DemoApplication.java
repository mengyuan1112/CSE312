package cse312.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class DemoApplication implements ServletContextInitializer {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize","1024000");

  }
}
