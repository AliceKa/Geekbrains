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

    public String toString() {
        return surname + " / " + phoneNumber;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contact = (Contacts) o;
        return Objects.equals(surname, contact.surname) && Objects.equals(phoneNumber, contact.phoneNumber);
    }
}
