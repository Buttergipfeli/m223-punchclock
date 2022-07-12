package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@Valid @RequestBody Entry entry) {
        if (entry.getCheckIn().isAfter(entry.getCheckOut())) {
            return new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(entryService.createEntry(entry), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> deleteEntry(@PathVariable("id") Long id) {
        entryService.deleteEntryById(id);
        return entryService.findAll();
    }

    @PutMapping
    public ResponseEntity<?> updateEntry(@Valid @RequestBody Entry entry) {
        if (entry.getCheckIn().isAfter(entry.getCheckOut())) {
            return new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        }
        entryService.updateEntry(entry);
        return new ResponseEntity(entryService.findAll(), HttpStatus.OK);
    }
}
