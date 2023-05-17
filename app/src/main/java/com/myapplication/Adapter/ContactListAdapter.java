package com.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.R;
import com.myapplication.model.ContactList;
import com.myapplication.model.User;
import com.myapplication.util.ColorGenerator;
import com.myapplication.util.TextDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyHolder> {


    List<ContactList> arrayList;
    List<ContactList> temp = new ArrayList<>();

    public ContactListAdapter(List<ContactList> arrayList) {
        this.arrayList = arrayList;
        this.temp.addAll(arrayList);
    }

    public void filter(String name){
        arrayList.clear();
        name = name.toLowerCase(Locale.getDefault());
        if(name.length()==0){
            arrayList.addAll(temp);
        }
        else{
            for(ContactList s : temp){
                if(s.getName().toLowerCase(Locale.getDefault()).contains(name) || s.getContactNo().toLowerCase(Locale.getDefault()).contains(name)){
                    arrayList.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuatom_contact,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.contactNo.setText(arrayList.get(position).getContactNo());

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color = generator.getRandomColor();

        TextDrawable drawable2 = TextDrawable.builder().beginConfig()
                .width(100)
                .height(100)
                .endConfig()
                .buildRound(arrayList.get(position).getName().substring(0, 1), color);
        holder.imageView.setImageDrawable(drawable2);

        holder.invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendMessage = view.getResources().getString(R.string.app_name)+"\n\nWatch Entertaining, Fun, News Videos.\n\nDownload App By Tapping Link : \n\nmarket://details?id="+view.getContext().getApplicationContext().getPackageName();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,view.getResources().getString(R.string.app_name));
                intent.putExtra(Intent.EXTRA_TEXT,sendMessage);
                view.getContext().startActivity(Intent.createChooser(intent,"Select Application"));
                    /*SmsManager sms = SmsManager.getDefault();
                    ArrayList<String> parts = sms.divideMessage(sendMessage);
                    sms.sendMultipartTextMessage(arrayList.get(position).getContactNo(),null,parts,null,null);
                    new CommonMethodClass(context,"Invitation Sent Successfully");*/
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+arrayList.get(position).getContactNo()));
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,contactNo;
        Button invite,call;

        AppCompatImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_contact_name);
            contactNo = itemView.findViewById(R.id.custom_contact_number);
            invite = itemView.findViewById(R.id.custom_contact_invite);
            call = itemView.findViewById(R.id.custom_contact_call);
            imageView = itemView.findViewById(R.id.contact_image);
        }
    }
}
