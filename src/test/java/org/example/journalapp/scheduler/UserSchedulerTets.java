package org.example.journalapp.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTets {
    @Autowired
    private UserScheduler userScheduler;
    @Test
    public void testFetchUserAndSendEmail(){
        userScheduler.fetchUserAndSendSaMail();
    }
}
