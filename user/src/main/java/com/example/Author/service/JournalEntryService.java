package com.example.Author.service;

import com.example.Author.model.JournalEntry;
import com.example.Author.model.User;
import com.example.Author.repository.JournalEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);


    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    public List<JournalEntry> getAllJournalEntry() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(String id) {
        return journalEntryRepository.findById(id);
    }

    public void createJournalEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);

        // Prevent duplicate journal titles
        boolean duplicateExists = user.getJournalEntryList().stream()
                .anyMatch(entry -> entry.getTitle().equalsIgnoreCase(journalEntry.getTitle()));

        if (duplicateExists) {

            throw new IllegalArgumentException("A journal with the same title already exists for this user.");
        }

        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntryList().add(saved);

        // ✅ Save journal list only — NOT the password!
        userService.updateJournalList(user);
    }

    public Optional<JournalEntry> updateJournalEntry(String id, JournalEntry entry, String userName) {
        Optional<JournalEntry> existing = journalEntryRepository.findById(id);
        if (existing.isPresent()) {
            JournalEntry journal = existing.get();
            journal.setTitle(entry.getTitle());
            journal.setContent(entry.getContent());
            return Optional.of(journalEntryRepository.save(journal));
        }
        return Optional.empty();
    }

    public void deleteJournalEntry(String id, String userName) {
        User user = userService.findByUserName(userName);
       boolean res= user.getJournalEntryList().removeIf(j -> j.getId().equals(id));
       if(res){
        userService.updateJournalList(user);
        journalEntryRepository.deleteById(id);
    }}

}
