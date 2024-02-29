package com.example.basicviewapplication;
import androidx.annotation.NonNull;
import java.io.Serializable;

// Tag.java
public class Tag implements Serializable{
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        // Use @NonNull annotation to indicate that 'name' should not be null
        this.name = name;
    }
}
