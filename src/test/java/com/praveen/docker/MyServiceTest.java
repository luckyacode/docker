package com.praveen.docker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MyServiceTest {
    @InjectMocks
    MyService myService;

    @Test
    public void testDelete(){
            assertEquals("Test Delete",myService.testDelete());
    }

    @Test
    public void testStatic(){
        assertEquals("Static is tested",MyService.testStatic());
    }

    @Test
    public void testPrivate(){
//        assertEquals("Private tested",myService.testPrivate());
    }


}
