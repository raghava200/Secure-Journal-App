package com.example.Author.controller;

import com.example.Author.model.JournalEntry;
import com.example.Author.model.User;
import com.example.Author.repository.UserRepository;
import com.example.Author.service.JournalEntryService;
import com.example.Author.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class  JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<JournalEntry> getAllJournalEntriesOfUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUserName(username);
        if(user!=null) {
            return user.getJournalEntryList();
        } else
        return null;
    }

    @GetMapping("/{userName}/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable String id) {

        return journalEntryService.getJournalEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody JournalEntry journalEntry) {
        try {

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.createJournalEntry(journalEntry, username);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable String id, @RequestBody JournalEntry entry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return journalEntryService.updateJournalEntry(id, entry, userName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal( @PathVariable String id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.deleteJournalEntry(id, userName);
        return ResponseEntity.ok().build();
    }
}

