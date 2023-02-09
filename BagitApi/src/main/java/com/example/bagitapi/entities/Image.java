package com.example.bagitapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GeneratorType;

import java.util.Arrays;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String realImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealImage() {
        return realImage;
    }

    public void setRealImage(String data) {
        this.realImage = data;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", realImage=" + realImage +
                '}';
    }
}
