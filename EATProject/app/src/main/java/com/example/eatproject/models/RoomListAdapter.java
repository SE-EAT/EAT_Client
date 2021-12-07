package com.example.eatproject.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eatproject.R;
import com.example.eatproject.activities.FindActivity;
import com.example.eatproject.activities.RoomActivity;
import com.example.eatproject.servers.WebService;
import com.example.eatproject.servers.WebserviceResponseListner;

import java.util.ArrayList;
import java.util.List;

public class RoomListAdapter extends ArrayAdapter<Room> {
    private Context mContext;
    int mResource;
    public RoomListAdapter(Context context, int resource, List<Room> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String _id = getItem(position).get_id();
        UserInfo[] users = getItem(position).getUsers();
        String date = getItem(position).getDate();
        Restaurant restaurant = getItem(position).getRestaurant();
        int state = getItem(position).getRoomState();
        Room room = new Room(_id,users,restaurant,date,state);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView restaurantName = (TextView) convertView.findViewById(R.id.textViewRes);
        TextView appointmentDate = (TextView) convertView.findViewById(R.id.textViewDate);

        restaurantName.setText(restaurant.getName());
        appointmentDate.setText(date);
        return convertView;
    }
}