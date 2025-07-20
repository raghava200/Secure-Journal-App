package com.example.Author.service;

import com.example.Author.model.User;
import com.example.Author.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Used only for registration, never during journal entry save
    public void saveEntry(User user) {
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
     user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);

    }

    // ✅ This version updates journal list, doesn't touch password
    public void updateJournalList(User userWithUpdatedJournals) {
        User existingUser = userRepository.findByUserName(userWithUpdatedJournals.getUserName());
        existingUser.setJournalEntryList(userWithUpdatedJournals.getJournalEntryList());
        userRepository.save(existingUser);
    }

    public void saveAdmin(User user) {
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        //   user.setRoles(Arrays.asList("USER"));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
