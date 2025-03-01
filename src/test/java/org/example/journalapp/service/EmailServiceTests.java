package org.example.journalapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendEmail("manuraj082004@gmail.com",
                "Testing Java Mail Sender",
                "Agar ye message tum padh rahe ho to mera code sahi se implement ho gya hai");
    }
}
