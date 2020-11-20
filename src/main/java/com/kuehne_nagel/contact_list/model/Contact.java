package com.kuehne_nagel.contact_list.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CONTACT")
@SequenceGenerator(name = "CONTACT_SEQ", sequenceName = "CONTACT_SEQ")
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    private long id;
    private String name;
    private String imageUrl;

    public Contact(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
