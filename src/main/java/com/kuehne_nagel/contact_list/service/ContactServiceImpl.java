package com.kuehne_nagel.contact_list.service;

import com.kuehne_nagel.contact_list.model.Contact;
import com.kuehne_nagel.contact_list.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public Page<Contact> getContacts(int index, int size) {
        var contacts = contactRepository.findAll(normalizePageRequest(index, size));
        if (contacts.isEmpty()) {
            initialDataSource();
            contacts = getContacts(index, size);
        }
        return contacts;
    }

    public Optional<Contact> getContact(long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Page<Contact> searchContactsByName(String name, int index, int size) {
        return contactRepository.findByNameContainsIgnoreCase(name, normalizePageRequest(index, size));
    }

    /**
     * initial the data source from a csv file for the first time
     */
    private void initialDataSource() {
        try {
            var file = ResourceUtils.getFile("classpath:static/contacts.csv");
            List<Contact> contacts = Files.readAllLines(file.toPath())
                    .stream()
                    .map(s -> s.split(","))
                    .map(strings -> new Contact(strings[0], strings[1]))
                    .collect(Collectors.toList());

            contactRepository.saveAll(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Primefaces data table uses index and size for pagination but spring uses the page number and the page length,
     * this method converts the primefaces format to spring format
     * @param index the index of data you want to fetch
     * @param size the size of data you want to fetch
     * @return return page request appropriate for spring
     */
    private PageRequest normalizePageRequest(int index, int size){
        return PageRequest.of(index > 0 ? index / size : index, size, Sort.by(Sort.Order.asc("id")));
    }

}
