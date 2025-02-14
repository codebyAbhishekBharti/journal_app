package org.example.journalapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journalEntry")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String content;
    private LocalDateTime date;
}