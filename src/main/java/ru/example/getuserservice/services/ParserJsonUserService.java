package ru.example.getuserservice.services;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.example.getuserservice.model.User;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ParserJsonUserService {
//
//    private  User user;
//
//    public ParserJsonUserService(User user) {
//        this.user = user;
//    }

    public ArrayList<User> convertJsonToUser(String date){

        JSONObject fullJsonObject = new JSONObject(date);
        int countPerson = fullJsonObject.getJSONObject("info").getInt("results");
        ArrayList<User> countuser= new ArrayList<>();
        for(int i =0; i < countPerson;i++) {
            User user = new User();
            JSONObject results = fullJsonObject.getJSONArray("results").getJSONObject(i);
            UUID uuid = UUID.fromString(results.getJSONObject("login")
                    .getString("uuid"));
            JSONObject name = results.getJSONObject("name");
            String first = name.getString("first");
            String last = name.getString("last");
            String string = results.getJSONObject("dob")
                    .getString("date");
            ZonedDateTime zdt = ZonedDateTime.parse(string);
            LocalDate dob = zdt.toLocalDate();
            String email = results.getString("email");
            String gender = results.getString("gender").substring(0, 1);
            user.setUuid(uuid)
                    .setFirstName(first)
                    .setLastName(last)
                    .setDob(dob)
                    .setEmail(email)
                    .setGender(gender);
            countuser.add(user);
        }
        return countuser;
    }
}
