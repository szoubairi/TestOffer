package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

public class UserDTO {

    private String name;
    private LocalDate birth;
    private String country;
    private String phone;
    private String gender;
    private int age;


    public UserDTO(String name, LocalDate birth, String country, String phone, String gender) {
        this.name = name;
        this.birth = birth;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }
    
    public UserDTO() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirth() {
        return birth;
    }
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        this.age = Period.between(this.birth, LocalDate.now()).getYears();
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [birth=" + birth + ", country=" + country + ", gender=" + gender + ", name=" + name + ", phone="
                + phone + "]";
    }    
    
}
