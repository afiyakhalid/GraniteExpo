package com.graniteexpo.demo.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
    @Table(name = "vendors")
    public class Vendor {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private String name;

        @Column(name = "contact_email")
        private String contactEmail;

        @Column(name = "is_active")
        private boolean isActive = true;

        // Constructors
        public Vendor() {}
        public Vendor(UUID id, String name, String contactEmail) {
            this.id = id;
            this.name = name;
            this.contactEmail = contactEmail;
        }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}

