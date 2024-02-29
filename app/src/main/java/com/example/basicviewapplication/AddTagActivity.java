package com.example.basicviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

// AddTagActivity.java
public class AddTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);

        EditText edtTagName = findViewById(R.id.edtTagName);
        Button btnSaveTag = findViewById(R.id.btnSaveTag);
        Button btnCancelAddTag = findViewById(R.id.btnCancelAddTag); // Assuming you have a Cancel button with id btnCancelAddTag

        btnSaveTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tagName = edtTagName.getText().toString();

                // Step 1: Check if the tag name is not empty
                if (!tagName.isEmpty()) {
                    // Step 2: Create a new Tag object
                    Tag newTag = new Tag(tagName);

                    // Step 3: Use Intent to pass the newTag back to the main activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newTag", newTag);
                    setResult(RESULT_OK, resultIntent);

                    // Finish the AddTagActivity
                    finish();
                } else {
                    // Handle the case where the tag name is empty
                    Toast.makeText(AddTagActivity.this, "Tag name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the cancel button click
                setResult(RESULT_CANCELED);
                finish(); // Finish the AddTagActivity
            }
        });
    }
}
