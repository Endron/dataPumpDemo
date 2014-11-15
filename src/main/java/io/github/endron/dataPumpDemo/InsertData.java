package io.github.endron.dataPumpDemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class is the data pumped into the database. The table for the data will
 * be created automatically.
 */
@Entity
public class InsertData {

    @Id
    @GeneratedValue
    private Long id;

    private String fieldA;
    private String fieldB;
    private String fieldC;
}
