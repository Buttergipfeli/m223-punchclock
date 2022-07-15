package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.controller
 * @date 13.07.2022
 */

// Nur MODERATOREN und BENUTZER können /categories anfragen.
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Diese GET-API wird alle Kategorien, welche in der Datenbank abgespeichert sind
    // dem Anfrager senden.
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity(categoryService.findAll(), HttpStatus.OK);
    }

}
