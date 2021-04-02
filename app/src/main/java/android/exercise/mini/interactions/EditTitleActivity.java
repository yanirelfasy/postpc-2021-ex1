package android.exercise.mini.interactions;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  // TODO:
  //  you can add fields to this class. those fields will be accessibly inside any method
  //  (like `onCreate()` and `onBackPressed()` methods)
  // for any field, make sure to set it's initial value. You CAN'T write a custom constructor
  // for example, you can add this field:
  // `private boolean isEditing = false;`
  // in onCreate() set `this.isEditing` to `true` once the user starts editing, set to `false` once done editing
  // in onBackPressed() check `if(this.isEditing)` to understand what to do
  private boolean isEditing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "start edit" FAB
      2. animate in the "done edit" FAB
      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
      this.isEditing = true;
      editTextTitle.setText(textViewTitle.getText().toString());
      textViewTitle.setVisibility(View.INVISIBLE);
      fabStartEdit.setVisibility(View.INVISIBLE);
      editTextTitle.setVisibility(View.VISIBLE);
      fabEditDone.setVisibility(View.VISIBLE);
      // Focus
      editTextTitle.requestFocus();
      // Placing cursor at the end of the text
      editTextTitle.setSelection(editTextTitle.getText().toString().length());
      // Show keyboard
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(editTextTitle, InputMethodManager.SHOW_IMPLICIT);
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      /*
      TODO:
      1. animate out the "done edit" FAB
      2. animate in the "start edit" FAB

      to complete (1.) & (2.), start by just changing visibility. only add animations after everything else is ready
       */
      this.isEditing = false;
      String strEditText = editTextTitle.getText().toString();
      textViewTitle.setText(strEditText);
      editTextTitle.setVisibility(View.INVISIBLE);
      fabEditDone.setVisibility(View.INVISIBLE);
      this.hideKeyboard(v);
      textViewTitle.setVisibility(View.VISIBLE);
      fabStartEdit.setVisibility(View.VISIBLE);
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked
    /*
    TODO:
    3. animate out the "done-edit" FAB
    4. animate in the "start-edit" FAB

     */
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);

    if(this.isEditing){
      editTextTitle.setVisibility(View.INVISIBLE);
      fabEditDone.setVisibility(View.INVISIBLE);
      textViewTitle.setVisibility(View.VISIBLE);
      fabStartEdit.setVisibility(View.VISIBLE);
    }
    else{
      super.onBackPressed();
    }
  }

  private void hideKeyboard(View view) {
    InputMethodManager manager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (manager != null) {
      manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}