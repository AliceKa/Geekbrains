package ru.geekbrains.lesson5;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Employee {

    private String FIO;
    private String profession;
    private String email;
    private String telephone;
    private int salary;
    private int age;

    public Employee() {
        this("Surname Name Fathername", "Profession", "email", "888888888", 0, 18);
    }

    public Employee(String _FIO, String _profession, String _email, String _telephone, int _salary, int _age) {
        FIO =_FIO;
        profession = _profession;
        email = _email;
        telephone = _telephone;
        salary = _salary;
        age = _age;
    }

    public void getInfo() {
        System.out.println("----------");
        System.out.println("Employee with FIO "+this.FIO);
        System.out.println("Has a profession of " + this.profession);
        System.out.println("Has an email " + this.email);
        System.out.println("Has a telephone " + this.telephone);
        System.out.println("Has a salary of " + this.salary + " RUB");
        System.out.println("Has an age of "+this.age + " y.o");
    }

    public static void main(String[] args) {
        Employee[] employeeArray = new Employee[5];
        employeeArray[0] = new Employee("Ivanov Ivan Ivanovich", "Engineer", "ivivan@mailbox.com", "892312312", 43000, 30);
        employeeArray[1] = new Employee("Syabitova Rosa Georgievna", "Designer", "gl11@mailbox.com", "89121891919", 129000, 25);
        employeeArray[2] = new Employee("Petrov Petr Petrovich", "Accounter", "best1818@mailbox.com", "89231200000", 30000, 19);
        employeeArray[3] = new Employee("Zaharov Zahar Zaharovich", "Unemployed", "zahar11n@mailbox.com", "8911111111", 0, 45);
        employeeArray[4] = new Employee("Germanov German Germanovich", "Blogger", "germ1819@mailbox.com", "8923123333", 80000, 58);

        int sortedAge = 40;

        System.out.println("Employees of an age " + sortedAge + " are: ");
        for (Employee item : employeeArray) {
            if (item.age >=sortedAge) {
                item.getInfo();
            }
        }
    }
}
