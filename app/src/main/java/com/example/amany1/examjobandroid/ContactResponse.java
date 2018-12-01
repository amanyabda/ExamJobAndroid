package com.example.amany1.examjobandroid;


import java.util.List;

public class ContactResponse {

    private List<Contact> contacts = null;

    public List<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(List<Contact> contacts)

    {
        this.contacts = contacts;
    }
}