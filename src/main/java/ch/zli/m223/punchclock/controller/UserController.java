package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Motto;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.MottoService;
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

    @Autowired
    private MottoService mottoService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id, Principal principal) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        User requester = userService.findByUsername(principal.getName());
        if (requester.getId() == user.getId() || requester.getRolefk().getRole().equals("MODERATOR")) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity("No permission", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable("id") Long id, Principal principal) {
        User editor = userService.findByUsername(principal.getName());
        User changeUser = userService.findById(id);
        if (user.getPassword().length() < 1) {
            return new ResponseEntity(user, HttpStatus.BAD_REQUEST);
        }
        if (editor.getRolefk().getRole().equals("MODERATOR") || changeUser.getId() == editor.getId()) {
            changeUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity(userService.updateUser(changeUser), HttpStatus.OK);
        }
        return new ResponseEntity(user, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity("User does not exist", HttpStatus.NOT_FOUND);
        }
        List<Motto> userMottos = mottoService.getAllMottosByUser(user);
        if (userMottos != null) {
            mottoService.deleteAllCertainMottos(userMottos);
        }
        userService.deleteUser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
