/**
 * In Class 06
 * InClass06
 * Phi Ha
 */

package edu.uncc.evaluation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.uncc.evaluation.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_NAME = "name";
    private static final String ARG_PARAM_CONTACTS = "contacts";

    private String name;
    private ArrayList<Contact> contacts;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name The Username.
     * @param contacts The List of Contacts.
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance(String name, ArrayList<Contact> contacts) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NAME, name);
        args.putParcelableArrayList(ARG_PARAM_CONTACTS, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM_NAME);
            contacts = getArguments().getParcelableArrayList(ARG_PARAM_CONTACTS);
        }
    }

    FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    TextView welcomeMessage;
    TextView contactCount;
    Button addButton;
    ListView listView;
    ContactAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Main");

        welcomeMessage = binding.textViewWelcome;
        contactCount = binding.textViewContactCount;
        addButton = binding.buttonAddContact;

        // Update the Welcome Message and initial contact count
        welcomeMessage.setText("Welcome " + name);
        contactCount.setText("You have " + contacts.size() + " contacts");

        // ListView and Adapter
        listView = binding.listView;
        adapter = new ContactAdapter(getActivity(), R.layout.contact_row_item, contacts);

        // Set Adapter
        listView.setAdapter(adapter);

        // Add Button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addContact();
            }
        });

        // ListView Click to view Details
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mListener.viewDetails(contacts.get(i));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the contact size message
        contactCount.setText("You have " + contacts.size() + " contacts");
    }

    IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainFragment.IListener) {
            mListener = (MainFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    public interface IListener{
        void addContact();
        void viewDetails(Contact contact);
    }
}