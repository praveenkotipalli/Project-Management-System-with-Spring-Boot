package com.praveen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.model.PlanType;
import com.praveen.model.Subscription;
import com.praveen.model.User;
import com.praveen.service.SubscriptionService;
import com.praveen.service.UserService;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "https://project-management-system-frontend-livid.vercel.app")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUSerSubscription(
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUserSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(
        @RequestHeader("Authorization") String jwt,
        @RequestParam PlanType planType
    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);
        
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
