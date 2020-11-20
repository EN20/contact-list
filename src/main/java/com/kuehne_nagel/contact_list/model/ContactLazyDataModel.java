package com.kuehne_nagel.contact_list.model;

import com.kuehne_nagel.contact_list.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contact lazy data model to support lazy pagination
 */
@RequiredArgsConstructor
public class ContactLazyDataModel extends LazyDataModel<Contact> {

    private final ContactService contactService;

    Page<Contact> contacts;

    @Override
    public List<Contact> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        if (filterBy.isEmpty() || filterBy.get("name").getFilterValue() == null)
            contacts = contactService.getContacts(first, pageSize);
        else
            contacts = contactService.searchContactsByName(filterBy.get("name").getFilterValue().toString(), first, pageSize);
        return contacts.get().collect(Collectors.toList());
    }

    @Override
    public Contact getRowData(String rowKey) {
        return contactService.getContact(Long.parseLong(rowKey)).orElseThrow();
    }

    @Override
    public Object getRowKey(Contact contact) {
        return contact.getId();
    }

    @Override
    public int getRowCount() {
        return contacts != null ? (int)contacts.getTotalElements() : 0;
    }
}
