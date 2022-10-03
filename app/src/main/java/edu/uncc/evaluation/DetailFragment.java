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
import android.widget.TextView;

import edu.uncc.evaluation.databinding.FragmentDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_CONTACT = "contact";

    private Contact contact;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contact Parameter 1.
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(Contact contact) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = getArguments().getParcelable(ARG_PARAM_CONTACT);
        }
    }

    FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentDetailBinding.inflate(inflater, container, false);

       return binding.getRoot();
    }

    TextView contactName;
    TextView contactPhoneNumber;
    TextView contactGroup;

    Button back;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Detail");

        contactName = binding.textViewContactName;
        contactPhoneNumber = binding.textViewContactPhone;
        contactGroup = binding.textViewContactGroup;

        // Set the values of the Contact Object passed to this Fragment
        contactName.setText(contact.name);
        contactPhoneNumber.setText(contact.phone_number);
        contactGroup.setText(contact.group);

        back = binding.buttonBack;

        // Back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancel();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DetailFragment.IListener){
            mListener = (DetailFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IListener");
        }
    }

    IListener mListener;

    public interface IListener{
        void cancel();
    }
}