/**
 * In Class 06
 * InClass06
 * Phi Ha
 */

package edu.uncc.evaluation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_row_item, parent, false);
        }

        Contact contact = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewPhoneNumber = convertView.findViewById(R.id.textViewPhoneNumber);
        TextView textViewGroup = convertView.findViewById(R.id.textViewGroup);

        // Set the text value of each Contact Object that is in the ArrayList
        textViewName.setText(contact.name);
        textViewPhoneNumber.setText(contact.phone_number);
        textViewGroup.setText(contact.group);

        return convertView;
    }
}
