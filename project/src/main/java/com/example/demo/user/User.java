package com.example.demo.user;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "BIRTH", nullable = false)
    private LocalDate birth;
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    @Column(name = "PHONE_NUMBER")
    private String phone;
    @Column(name = "GENDER")
    private String gender;

    @Override
    public String toString() {
        return "User [birth=" + birth + ", country=" + country + ", gender=" + gender + ", name=" + name + ", phone="
                + phone + "]";
    }  
    
    
}
