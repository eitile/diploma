package ua.nure.pricetag.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.pricetag.entity.User;
import ua.nure.pricetag.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User login(User user, HttpServletRequest request) {
        User userByLogin = userRepository.findUserByLogin(user.getLogin());
        if(userByLogin.getPassword().equals(user.getPassword())) {
            request.getSession().setAttribute("uaser", userByLogin);
            return userByLogin;
        }
        return null;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(BigInteger userId) {
        userRepository.delete(userId);
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }


    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
