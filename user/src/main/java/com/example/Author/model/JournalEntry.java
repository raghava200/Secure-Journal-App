package com.example.Author.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "journals")
public class  JournalEntry {
    @Id
    private String id;
        private String Author;

    private String title;

    private String Content;
}