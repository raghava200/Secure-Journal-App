package com.example.Author.model;

import com.mongodb.connection.ProxySettings;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
@NonNull
    private String password;
   @DBRef
    private List<JournalEntry> journalEntryList = new ArrayList<>();
   private List<String> roles=new ArrayList<>();

    public static ProxySettings.Builder builder() {

        return null;
    }
}
