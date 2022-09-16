package io.codewithgx.recorddemo.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.codewithgx.recorddemo.model.Users;
import io.codewithgx.recorddemo.utils.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Log("Executing Method addUser()")
    @PostMapping("/")
    public void addUser() {
        log.info("Adding a User");
    }


    @Log("Executing method 2 getAllUsers()")
    @GetMapping("/findAll")
    public List<Users> getAllUsers() {
        log.info("Finding all Students");
        return Collections.singletonList(getUsers());
    }


    @Log("Executing method 3 getAllUsers()")
    @GetMapping("/findById/{id}")
    public Users getUsersById(@PathVariable(name = "id") long id) {
        log.info("Getting Student by Id: {}", id);
        return getUsers();
    }

    @Log("Executing private Method")
    private Users getUsers() {
        return new Users(1, "John", "Allan", "01-01-2001");
    }


    @Log("Executing method 4 deleteUsersById()")
    @DeleteMapping("/delete/{id}")
    public void deleteUsersById(@PathVariable long id) {
        log.info("Deleting Users by id: {}", id);
    }
}
