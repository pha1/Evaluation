/**
 * In Class 06
 * InClass06
 * Phi Ha
 */

package edu.uncc.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.uncc.evaluation.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomeBinding binding;

    final static String TAG = "test";
    final static String STRING_NAME_KEY = "NAME_KEY";
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setTitle(getResources().getString(R.string.welcome));

        EditText enterName = findViewById(R.id.editTextPersonName);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the name
                name = enterName.getText().toString();

                // Pattern to compare
                String pattern = "^[a-zA-z ]*$";

                // Check if the name is valid, if so start the Main Activity
                if (!name.matches(pattern)) {
                    Toast.makeText(WelcomeActivity.this, "Please enter a valid name.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra(STRING_NAME_KEY, name);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}