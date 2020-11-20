package com.kuehne_nagel.contact_list.repository;

import com.kuehne_nagel.contact_list.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A simple repository to fetch/store contacts from/to database
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * Jpa method to search all the contacts by the given name
     * @param name the name you want to search
     * @param pageable PageRequest to support pagination
     * @return return the search result
     */
    Page<Contact> findByNameContainsIgnoreCase(String name, Pageable pageable);
}
