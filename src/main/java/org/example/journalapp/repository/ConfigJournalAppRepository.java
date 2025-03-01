package org.example.journalapp.repository;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.ConfigJournalApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId> {
}