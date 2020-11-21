package com.kuehne_nagel.contact_list;

import com.kuehne_nagel.contact_list.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Test
    public void getContactList(){
        var list = contactService.getContacts(0, 10);
        assert list.getSize() == 10;
    }

    @Test
    public void searchContacts(){
        var searchQuery = "simpson";
        var list = contactService.searchContactsByName(searchQuery, 0, 10);
        list.forEach(contact -> {assert(contact.getName().toLowerCase().contains(searchQuery));});
    }

}
