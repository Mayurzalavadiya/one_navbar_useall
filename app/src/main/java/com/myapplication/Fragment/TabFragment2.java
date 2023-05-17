package com.myapplication.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapplication.Activity.QuestiuonActivity;
import com.myapplication.Adapter.ContactListAdapter;
import com.myapplication.R;
import com.myapplication.model.ContactList;
import com.myapplication.util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TabFragment2 extends Fragment {

    int CONTACT_CODE = 1000;

    RecyclerView recyclerView;
    ArrayList<ContactList> arrayList;

    AppCompatButton button;
    SearchView searchView;
    ContactListAdapter adapter;

    String[] appPermission = {Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS};
    public TabFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        searchView = view.findViewById(R.id.contact_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.trim().equalsIgnoreCase("")){
                    adapter.filter("");
                }
                else{
                    adapter.filter(newText);
                }
                return false;
            }
        });
        button = view.findViewById(R.id.btn_question);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.Intent(getActivity(), QuestiuonActivity.class);
            }
        });

        recyclerView = view.findViewById(R.id.contact_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //requestStoragePermission();

        if (checkAndRequestPermission()) {
            getAllContacts();
        }

    
        return view;
    }

    public boolean checkAndRequestPermission() {
        List<String> listPermission = new ArrayList<>();
        for (String perm : appPermission) {
            if (ContextCompat.checkSelfPermission(getActivity(), perm) != PackageManager.PERMISSION_GRANTED) {
                listPermission.add(perm);
            }
        }
        if (!listPermission.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermission.toArray(new String[listPermission.size()]), CONTACT_CODE);
            return false;
        }
        return true;
    }

    private void getAllContacts() {
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        ArrayList<String> contactImageist = new ArrayList<>();
        ArrayList<String> contactNameList = new ArrayList<>();
        ArrayList<String> contactNumberList = new ArrayList<>();
        while(cursor.moveToNext()){
            contactNumberList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            contactNameList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contactImageist.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).split(" ")[0]);
        }
        Log.e("TAG", "response"+contactImageist );

        if(cursor.getCount()>0){
            arrayList = new ArrayList<>();
            for(int i=0;i<contactNameList.size();i++){
                ContactList list = new ContactList();
                list.setName(contactNameList.get(i));
                list.setContactNo(contactNumberList.get(i));
                arrayList.add(list);
            }
            removeDuplicates(arrayList);
        }
        cursor.close();
        return;
    }

    private void removeDuplicates(ArrayList<ContactList> arrayList) {
        Set<ContactList> set = new TreeSet<>(new Comparator<ContactList>() {
            @Override
            public int compare(ContactList old, ContactList newName) {
                if(old.getName().equalsIgnoreCase(newName.getName())) {
                    return 0;
                }
                return 1;
            }
        });
        set.addAll(arrayList);
        ArrayList newList = new ArrayList(set);
        adapter = new ContactListAdapter(newList);
        recyclerView.setAdapter(adapter);
        Collections.sort(newList, new Comparator<ContactList>() {
            @Override
            public int compare(ContactList lhs, ContactList rhs) {
                return lhs.getName().trim().compareTo(rhs.getName().trim());
            }
        });
        adapter.notifyDataSetChanged();
    }

//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == CONTACT_CODE) {
//            HashMap<String, Integer> permissionResult = new HashMap<>();
//            int deniedCount = 0;
//            for (int i = 0; i < grantResults.length; i++) {
//                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
//                    permissionResult.put(permissions[i], grantResults[i]);
//                    deniedCount++;
//                }
//            }
//            if (deniedCount == 0) {
//                getAllContacts();
//            } else {
//                for (Map.Entry<String, Integer> entry : permissionResult.entrySet()) {
//                    String permName = entry.getKey();
//                    int permResult = entry.getValue();
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permName)) {
//                        /*showDialogPermission("", "This App needs Read External Storage And Location permissions to work whithout and problems.",*/
//                        showDialogPermission("", "This App needs Read Contact And Send SMS permissions to work whithout and problems.",
//                                "Yes, Grant permissions", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                        checkAndRequestPermission();
//                                    }
//                                },
//                                "No, Exit app", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                        getActivity().finishAffinity();
//                                    }
//                                }, false);
//                    } else {
//                        showDialogPermission("", "You have denied some permissions. Allow all permissions at [Setting] > [Permissions]",
//                                "Go to Settings", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                                Uri.fromParts("package", getActivity().getPackageName(), null));
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(intent);
//                                        getActivity().finish();
//                                    }
//                                }, "No, Exit app", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.dismiss();
//                                        getActivity().finish();
//                                    }
//                                }, false);
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    public AlertDialog showDialogPermission(String title, String msg, String positiveLable, DialogInterface.OnClickListener positiveOnClickListener,
//                                            String negativeLable, DialogInterface.OnClickListener negativeOnClickListener, boolean isCancelable) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(title);
//        builder.setCancelable(isCancelable);
//        builder.setMessage(msg);
//        builder.setPositiveButton(positiveLable, positiveOnClickListener);
//        builder.setNegativeButton(negativeLable, negativeOnClickListener);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        return alertDialog;
//    }

}