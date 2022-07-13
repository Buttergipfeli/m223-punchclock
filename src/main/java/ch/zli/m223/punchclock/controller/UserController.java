package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.controller
 * @date 12.07.2022
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Principal principal) {
        return new ResponseEntity(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findUserById(@PathVariable("username") String username) {
        return new ResponseEntity(userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        createdUser.setPassword(null);
        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, Principal principal) {
        User editor = userService.findByUsername(principal.getName());
        User changeUser = userService.findById(user.getId());
        if (changeUser == null) {
            return new ResponseEntity(user, HttpStatus.BAD_REQUEST);
        }
        if (editor.getUsername() == user.getUsername()) {
            changeUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity(userService.updateUser(changeUser), HttpStatus.OK);
        } else if (editor.getRolefk().getRole() == "MODERATOR") {
            if (user.getPassword().length() > 0) {
                changeUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            changeUser.setUsername(user.getUsername());
            return new ResponseEntity(changeUser, HttpStatus.OK);
        }
        return new ResponseEntity(user, HttpStatus.NOT_FOUND);
    }

}
