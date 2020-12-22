package com.example.morr;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {


    // card no should not be empty and should be of 16 digits
    @Test
    public void cardNumberCheck(){
        boolean result = MainActivity.checkRegistrationFields("72676376","27/04","542","kanchan","Rautela");
        assertFalse(result);
    }
    // date should not be empty and should be of 5 digits
    @Test
    public void dateCheck(){
        boolean result = MainActivity.checkRegistrationFields("2548715372676376","2/04","542","kanchan","Rautela");
        assertFalse(result);
    }
    // securityCode should not be empty and should be of 3 digits
    @Test
    public void securityCodeCheck(){
        boolean result = MainActivity.checkRegistrationFields("2548715372676376","27/04","52","kanchan","Rautela");
        assertFalse(result);
    }
    // FirstName should not be empty and should only contain alphabets and spaces
    @Test
    public void firstNameCheck(){
        boolean result = MainActivity.checkRegistrationFields("2548715372676376","27/04","524","kanch2an","Rautela");
        assertFalse(result);
    }
    // LastName should not be empty and should only contain alphabets and spaces
    @Test
    public void lastNameCheck(){
        boolean result = MainActivity.checkRegistrationFields("2548715372676376","27/04","524","kanchan","Raut5ela");
        assertFalse(result);
    }
}