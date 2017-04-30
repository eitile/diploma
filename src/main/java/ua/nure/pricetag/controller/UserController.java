package ua.nure.pricetag.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.nure.pricetag.entity.User;
import ua.nure.pricetag.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
@EnableWebMvc
public class UserController {

    @Autowired
    private UserService service;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, HttpServletRequest request) {
        try {
            return mapper.writeValueAsString(service.login(user, request));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        service.logout(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUser(@RequestBody User user) {
        service.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@RequestBody User user) {
        service.updateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "userId") BigInteger userId) {
        service.deleteUser(userId);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String getUserByLogin(@PathVariable(name = "login") String login) {
        try {
            return mapper.writeValueAsString(service.getUserByLogin(login));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers() {
        try {
            return mapper.writeValueAsString(service.getAllUsers());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
