package org.example.journalapp;

import org.example.journalapp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRespositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    void contextLoads() {
    }

    @Test
    void testSaveNewUser(){
        userRepositoryImpl.getUserForSA();
    }

}
