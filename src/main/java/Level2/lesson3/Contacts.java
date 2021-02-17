package Level2.lesson3;

import java.util.*;

public class Contacts{

    private String surname;
    private String phoneNumber;

    Contacts() {
    }

    Contacts(String surname, String phoneNumber) {
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSurname() {
        return surname;
    }


}
