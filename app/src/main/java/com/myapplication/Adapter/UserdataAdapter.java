package com.myapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.apicalldemo.model.UserListData;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.myapplication.R;
import com.myapplication.model.User;
import com.myapplication.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class UserdataAdapter extends RecyclerView.Adapter<UserdataAdapter.UserViewHolder> {

    List<User> users;
    List<User> temp = new ArrayList<>();

    public Clickdata clickdata;

    public UserdataAdapter(List<User> users, Clickdata clickdata) {
        this.users = users;
        this.temp.addAll(users);
        this.clickdata = clickdata;
    }

    public void filterList(String str) {
        str = str.toLowerCase();
        users.clear();
        if (str.length() == 0) {
            users.addAll(temp);
        } else {
            for (User list : temp) {
                Log.d("match : ", list.getFirstName());
                if (list.getFirstName().toLowerCase().matches("(.*)" + str + "(.*)") || list.getLastName().toLowerCase().matches("(.*)" + str + "(.*)")) {
                    users.add(list);
                }
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemlist = layoutInflater.inflate(R.layout.adapter_user_list, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(itemlist);
        return viewHolder;
    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView fname, lname, email, phone;
        ShapeableImageView imageView;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserdataAdapter.UserViewHolder holder, int position) {

        User user = users.get(position);
        holder.fname.setText(user.getFirstName());
        holder.lname.setText(user.getLastName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone()+" "+user.getCompany().getDepartment());
        Glide.with(holder.itemView.getContext()).load(user.getImage()).centerCrop().placeholder(R.drawable.ic_menu).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Utility.showToast(holder.itemView.getContext(), user.getFirstName());
//                Toast.makeText(holder.itemView.getContext(), user.getFirstName(), Toast.LENGTH_SHORT).show();
                clickdata.OnClick(user.getFirstName(),user.getLastName());
            }
        });
    }

    public interface Clickdata {
        public void OnClick(String fname, String lname);
    }
}
