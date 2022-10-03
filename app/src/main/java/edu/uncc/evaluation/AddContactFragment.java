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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.uncc.evaluation.databinding.FragmentAddContactBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContactFragment.
     */
    public static AddContactFragment newInstance(String param1, String param2) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentAddContactBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    Button setDepartment;
    Button buttonCancel;
    Button submitButton;

    EditText editTextContactName;
    EditText editTextPhoneNumber;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Add Contact");

        setDepartment = binding.buttonSetDepartment;
        buttonCancel = binding.buttonCancel;
        submitButton = binding.buttonSubmit;

        editTextContactName = binding.editTextContactName;
        editTextPhoneNumber = binding.editTextContactPhone;

        TextView group = binding.textViewGroupSelected;

        // Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                String phone_number;

                // Get the name and phone number from EditTexts
                name = editTextContactName.getText().toString();
                phone_number = editTextPhoneNumber.getText().toString();

                // Patterns to check against for validation
                String phone_pattern = "^\\d{3}-\\d{3}-\\d{4}$";
                String name_pattern = "^[a-zA-z ]*$";

                // Validation check
                if (!name.matches(name_pattern) || name.equals("")) {
                    Toast.makeText(getActivity(), "Please enter a valid name.", Toast.LENGTH_SHORT).show();
                } else if(!(phone_number.matches(phone_pattern))) {
                    Toast.makeText(getActivity(), "Please enter a valid phone number in the format of ###-###-####", Toast.LENGTH_LONG).show();
                } else if (group.getText().toString().equals("Group: N/A")) {
                    Toast.makeText(getActivity(), "Please select a group.", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(name, phone_number, department);
                    mListener.createContact(contact);
                }
            }
        });

        // Set button
        setDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.set();
            }
        });

        // Cancel button
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancel();
            }
        });
    }

    // Used to update the content when arriving back to the fragment
    TextView group;
    public String department = "";

    @Override
    public void onResume() {
        group = binding.textViewGroupSelected;
        if (!department.equals(""))
        {
            group.setText(department);
        }
        super.onResume();
    }

    AddContactFragment.IListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddContactFragment.IListener) {
            mListener = (AddContactFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    public interface IListener{
        void cancel();
        void set();
        void createContact(Contact contact);
    }

    public void updateDepartment(String department) {
        this.department = department;
    }
}