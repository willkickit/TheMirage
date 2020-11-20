package com.willj.springTweets;

import com.willj.springTweets.controller.GuestController;
import com.willj.springTweets.model.GuestModel;
import com.willj.springTweets.view.GuestView;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"springTweets", "springTweets.resources","springTweets.services"})
@EnableAutoConfiguration

public class Main extends SpringBootServletInitializer{
    public static void main(String[] args) {
        
        GuestView gView = new GuestView();
        GuestModel gModel = new GuestModel();
        //gView.setVisible(true);
        //new GuestController(gView, gModel);
                
    }
    
}
