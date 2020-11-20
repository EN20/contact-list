package com.kuehne_nagel.contact_list.service;

import com.kuehne_nagel.contact_list.model.Contact;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Simple service to fetch and search contacts from database
 */
public interface ContactService {

    /**
     * Get all contacts with pagination support
     * @param index the index of data
     * @param size the size of contacts you want to fetch
     * @return return a page of contacts you have requested
     */
    Page<Contact> getContacts(int index, int size);

    /**
     * fetch a requested contact
     * @param id identification of contact which is the id
     * @return return the requested contact
     */
    Optional<Contact> getContact(long id);

    /**
     * search all the contacts by matching the name requested
     * @param name the search query which is either a part or a whole of the name
     * @param index the index of data
     * @param size the size of contacts you want to fetch
     * @return return the searched result of contacts
     */
    Page<Contact> searchContactsByName(String name, int index, int size);
}
