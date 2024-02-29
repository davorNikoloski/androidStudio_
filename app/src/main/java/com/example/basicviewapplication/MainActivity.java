package com.example.basicviewapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.annotation.Nullable;
import android.widget.Toast;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Tag> tags;
    private LinearLayout tagsContainer;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tags = new ArrayList<>();
        tagsContainer = findViewById(R.id.tagsContainer);
        searchView = findViewById(R.id.searchView);

        Button btnAddTag = findViewById(R.id.btnAddTag);
        Button btnClearTags = findViewById(R.id.btnClearTags);



        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AddTagActivity with animation
                Intent intent = new Intent(MainActivity.this, AddTagActivity.class);
                startActivityForResult(intent, 1, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        btnClearTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tags.clear();
                tagsContainer.removeAllViews(); // Clear all views in tagsContainer
            }
        });

        // Populate tags (replace this with your actual logic for populating tags)
        populateTags();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the submit action if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter and update the displayed tags as the query changes
                filterTags(newText);
                return true;
            }
        });
        
    }

    // Method to populate tags dynamically (replace this with your actual logic)
    private void populateTags() {
        for (Tag tag : tags) {
            addButtonForTag(tag);
        }
    }

    // Method to add a button for a tag
    private void addButtonForTag(final Tag tag) {
        Button tagButton = new Button(this);
        tagButton.setText(tag.getName());

        // Set click listener for the tag button
        tagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show EditTagDialog for the selected tag
                showEditTagDialog(tag);
            }
        });

        // Add the button to the tagsContainer LinearLayout
        tagsContainer.addView(tagButton);
    }

    // Method to show the EditTagDialog
    private void showEditTagDialog(Tag tag) {
        EditTagDialog editTagDialog = new EditTagDialog(this, tag);
        editTagDialog.setOnTagModifiedListener(new EditTagDialog.OnTagModifiedListener() {
            @Override
            public void onTagModified(Tag modifiedTag) {
                // Handle the modified tag
                updateTagInList(tag, modifiedTag);
            }
        });
        editTagDialog.show();
    }


    // Method to update the tag in the list and UI
    private void updateTagInList(Tag oldTag, Tag newTag) {
        int index = tags.indexOf(oldTag);
        if (index != -1) {
            tags.set(index, newTag);
            // Remove all views from the tagsContainer and re-populate the updated tags
            tagsContainer.removeAllViews();
            populateTags();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Handle the result from AddTagActivity
            if (data.hasExtra("newTag")) {
                Tag newTag = (Tag) data.getSerializableExtra("newTag");

                // Add the new tag to the tags list
                tags.add(newTag);

                // Update the UI by adding a button for the new tag
                addButtonForTag(newTag);

                // Optionally, you can show a toast to indicate successful addition
                Toast.makeText(this, "New tag added: " + newTag.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void filterTags(String query) {
        // Clear existing views
        tagsContainer.removeAllViews();

        // Filter tags based on the query
        List<Tag> filteredTags = new ArrayList<>();
        for (Tag tag : tags) {
            if (tag.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredTags.add(tag);
            }
        }

        // Populate the filtered tags
        for (Tag filteredTag : filteredTags) {
            addButtonForTag(filteredTag);
        }
    }


}