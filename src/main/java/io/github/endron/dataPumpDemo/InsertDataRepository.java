package io.github.endron.dataPumpDemo;

import org.springframework.data.repository.CrudRepository;

/**
 * This repository is used to push the data to the database.
 */
public interface InsertDataRepository extends CrudRepository<InsertData, Long> {
    /*
     * No implementation needed as everything is in CrudRepository. 
     */
}
