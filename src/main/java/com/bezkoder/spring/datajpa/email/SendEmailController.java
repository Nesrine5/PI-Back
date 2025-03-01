package com.bezkoder.spring.datajpa.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class SendEmailController {

    @Autowired
    SendEmailService sendEmailService;

    @RequestMapping(value = "/mail/send", method = RequestMethod.POST)
    public void sendMail(@RequestBody EmailSendingRequest emailSendingRequest) {
        sendEmailService.sendEmail(emailSendingRequest.getTo(), 
emailSendingRequest.getTextBody(), emailSendingRequest.getTopic());
    }
    @RequestMapping(value = "/mailRef/send", method = RequestMethod.POST)
    public void sendMailref(@RequestBody EmailSendingRequest emailSendingRequest) {
        sendEmailService.sendEmailRef(emailSendingRequest.getTo(), 
emailSendingRequest.getTextBody(), emailSendingRequest.getTopic());
    }
    @RequestMapping(value = "/mailAcCarte/send", method = RequestMethod.POST)
    public void sendMailAcCarte(@RequestBody EmailSendingRequest emailSendingRequest) {
        sendEmailService.sendEmailAcCarte(emailSendingRequest.getTo(), 
emailSendingRequest.getTextBody(), emailSendingRequest.getTopic());
    }
    @RequestMapping(value = "/mailRefCarte/send", method = RequestMethod.POST)
    public void sendMailrefCarte(@RequestBody EmailSendingRequest emailSendingRequest) {
        sendEmailService.sendEmailRefCarte(emailSendingRequest.getTo(), 
emailSendingRequest.getTextBody(), emailSendingRequest.getTopic());
    }
}