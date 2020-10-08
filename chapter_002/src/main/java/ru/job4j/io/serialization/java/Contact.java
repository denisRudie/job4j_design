package ru.job4j.io.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 2928161736391764907L;
    private final int zipCode;
    private final String phone;
    private final String name;

    public Contact(int zipCode, String phone, String name) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "zipCode=" + zipCode +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        creating temp file
        Contact c = new Contact(143005, "+7(999)999-99-99", "Mike");
        File tempFile = null;
        try {
            tempFile = Files.createTempFile(null, null).toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        object writing
        if (tempFile != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(tempFile))) {
                oos.writeObject(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        object reading
        if (tempFile != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
                Contact contact = (Contact) ois.readObject();
                System.out.println(contact);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}