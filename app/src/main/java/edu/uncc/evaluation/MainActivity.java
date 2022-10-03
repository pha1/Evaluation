/**
 * In Class 06
 * InClass06
 * Phi Ha
 */

package edu.uncc.evaluation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.uncc.evaluation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragment.IListener, AddContactFragment.IListener, GroupsFragment.IListener, DetailFragment.IListener{

    ActivityMainBinding binding;

    public ArrayList<Contact> contacts = new ArrayList<>();
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the name from the Welcome Activity and store it in the Main Activity
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(WelcomeActivity.STRING_NAME_KEY)){
            name = getIntent().getStringExtra(WelcomeActivity.STRING_NAME_KEY);
        }

        // Load the initial Main Fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, MainFragment.newInstance(name, contacts), "Main Fragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Replace the current Fragment to the AddContactFragment
     */
    @Override
    public void addContact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new AddContactFragment(), "Add Contact Fragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Replace the current Fragment with the DetailFragment given the selected Contact Object
     * @param contact The Contact selected from the ArrayList/ListView
     */
    @Override
    public void viewDetails(Contact contact) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, DetailFragment.newInstance(contact), "Details Fragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Pop the BackStack any time a cancel/back button is clicked
     */
    @Override
    public void cancel() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Updates the Add Contact page with the Selected Department
     * @param department The String value of Department selected from the list
     */
    @Override
    public void selectDepartment(String department) {
        AddContactFragment addContactFragment = new AddContactFragment();
        addContactFragment = (AddContactFragment) getSupportFragmentManager().findFragmentByTag("Add Contact Fragment");
        addContactFragment.updateDepartment(department);
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Replace the current Fragment with the GroupFragment
     */
    @Override
    public void set() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new GroupsFragment(), "Group Fragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Add the created Contact Object to the ArrayList of Contacts in the Main Activity, then pop the BackStack
     * @param contact The Contact Object created from AddContactFragment
     */
    @Override
    public void createContact(Contact contact) {
        contacts.add(contact);
        getSupportFragmentManager().popBackStack();
    }
}