package com.example.basicviewapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import androidx.annotation.NonNull;



// EditTagDialog.java
public class EditTagDialog extends Dialog implements View.OnClickListener {

    public interface OnTagModifiedListener {
        void onTagModified(Tag modifiedTag);
    }

    private final Tag tag;
    private OnTagModifiedListener onTagModifiedListener;
    private EditText edtModifiedTagName;

    public EditTagDialog(@NonNull Context context, Tag tag) {
        super(context);
        setContentView(R.layout.activity_edit_tag_dialog);

        this.tag = tag;

        Button btnSaveChanges = findViewById(R.id.btnSaveChanges);
        Button btnCancel = findViewById(R.id.btnCancel);
        edtModifiedTagName = findViewById(R.id.edtModifiedTagName);

        btnSaveChanges.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        edtModifiedTagName.setText(tag.getName());

    }

    public void setOnTagModifiedListener(OnTagModifiedListener listener) {
        this.onTagModifiedListener = listener;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btnSaveChanges) {
            // Implement logic to save the changes to the tag
            EditText edtModifiedTagName = findViewById(R.id.edtModifiedTagName);
            String modifiedName = edtModifiedTagName.getText().toString();
            tag.setName(modifiedName);

            // Notify the listener about the tag modification
            if (onTagModifiedListener != null) {
                onTagModifiedListener.onTagModified(tag);
            }

            // Dismiss the dialog after saving changes
            dismiss();
        } else if (viewId == R.id.btnCancel) {
            dismiss(); // Close the dialog
        }
    }
}
