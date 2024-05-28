package org.example.unittestingusingmockito.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;
    private String name;
    private String email;
    private String address;
    private Integer phoneNo;
    private Integer zip;

}
