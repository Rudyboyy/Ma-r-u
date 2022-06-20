package com.example.maru.model;

import java.util.Arrays;
import java.util.List;

public class Employee {

    private String name;
    private String mail;

    public Employee(String mail) {
//        this.name = name;
        this.mail = mail;
    }
    public static List<Employee> DUMMY_EMPLOYEE = Arrays.asList(
    new Employee("eddy.moitout@lamzone.com"),
    new Employee("thibault.monfils@lamzone.com"),
    new Employee("jessica.pote@lamzone.com"),
    new Employee("marie.rouana@lamzone.com"),
    new Employee("sarah.croche@lamzone.com"),
    new Employee("brice.glace@lamzone.com"),
    new Employee("alain.proviste@lamzone.com"),
    new Employee( "cecile.onxa@lamzone.com"),
    new Employee("oussama.lairbizar@lamzone.com"),
    new Employee("justin.ptipeu@lamzone.com"));

    public String getMail() {
        return mail;
    }


}

