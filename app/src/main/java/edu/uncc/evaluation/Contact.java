package edu.uncc.evaluation;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    String name;
    String phone_number;
    String group;

    public Contact(String name, String phone_number, String group){
        this.name = name;
        this.phone_number = phone_number;
        this.group = group;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        phone_number = in.readString();
        group = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone_number);
        dest.writeString(group);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
