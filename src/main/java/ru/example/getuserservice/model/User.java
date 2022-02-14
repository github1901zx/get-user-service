package ru.example.getuserservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Component
public class User {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String email;

}
