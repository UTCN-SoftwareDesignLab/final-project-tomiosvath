package com.assignment3.notification;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/return")
    @SendTo("/admin/return")
    public AdminNotification checkIn(CarReturned receiveMessage) throws Exception {
        return new AdminNotification("The " + receiveMessage.getCarManufacturer() + " " + receiveMessage.getCarModel() +
                " was returned by " + receiveMessage.getCustomerName());
    }
}
