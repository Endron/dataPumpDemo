package io.github.endron.dataPumpDemo;

import org.springframework.data.repository.CrudRepository;

/**
 * This repository is used to push the data to the database. As it is a
 * CrudRepository the data pump shuld work with most things Spring Data provides
 * access to. InsertData must be redisignded for non JPA data stores.
 */
public interface InsertDataRepository extends CrudRepository<InsertDataRepository, Long> {
    /*
     * No implementation needed as everything is in CrudRepository. 
     */
}
