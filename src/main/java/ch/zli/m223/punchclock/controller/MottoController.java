package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Motto;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.MottoService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.controller
 * @date 13.07.2022
 */

@RestController
@RequestMapping("/mottos")
public class MottoController {

    @Autowired
    private MottoService mottoService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Motto>> getAllMottos() {
        return new ResponseEntity(mottoService.getAllMottos(), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Motto> createMotto(@Valid @RequestBody Motto motto, Principal principal) {
        if (motto.getCategoryfk().getId() == null) {
            if (categoryService.findById(motto.getCategoryfk().getId()) == null) {
                motto.setCategoryfk(categoryService.createCategory(motto.getCategoryfk()));
            }
        }
        motto.setOwnerfk(userService.findByUsername(principal.getName()));
        return new ResponseEntity(mottoService.createMotto(motto), HttpStatus.CREATED);
    }

}
