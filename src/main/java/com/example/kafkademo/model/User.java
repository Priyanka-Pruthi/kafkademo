package com.example.kafkademo.model;

import java.util.Date;

public record User(String name, int age, Date dateOfBirth, String gender) {

}
