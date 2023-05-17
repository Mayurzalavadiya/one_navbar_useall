package com.myapplication.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.apicalldemo.model.UserListData;
import com.myapplication.Activity.GoogleLoginActivity;
import com.myapplication.Adapter.UserdataAdapter;
import com.myapplication.R;
import com.myapplication.api.RestClient;
import com.myapplication.util.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabFragment extends Fragment {

    AppCompatButton button;
    RecyclerView rv;

    SearchView searchView;

    UserdataAdapter userdataAdapter;
    Dialog dialogAddMember;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        rv = view.findViewById(R.id.recycler);
        searchView = view.findViewById(R.id.searchViewResult);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userdataAdapter.filterList(newText);
                return false;
            }
        });

        listdata();

        button = view.findViewById(R.id.btn_google);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.Intent(getActivity(), GoogleLoginActivity.class);
            }
        });

        return view;
    }

    private void listdata() {
        if (Utility.isInternetAvailable(getContext())) {
            Utility.showCustomProgressDialog(getContext());
            RestClient.getInstance().getApiInterface().userlist().enqueue(new Callback<UserListData>() {
                @Override
                public void onResponse(Call<UserListData> call, Response<UserListData> response) {
                    Utility.hideCustomProgressDialog();
                    if (response.body() != null && response.body().getUsers().size() > 0) {
                        UserListData userListData = response.body();
                        Log.e("TAG", "response"+response.body().getSkip() );
                        userdataAdapter = new UserdataAdapter(userListData.getUsers(), new UserdataAdapter.Clickdata() {
                            @Override
                            public void OnClick(String fname, String lname) {
                                addMember( fname,  lname);
                            }
                        });
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rv.setLayoutManager(layoutManager);
                        rv.setAdapter(userdataAdapter);
                    } else {
                        Utility.showMessage(getActivity(), false, "Data Not Available");
                    }
                }

                @Override
                public void onFailure(Call<UserListData> call, Throwable t) {
                    Utility.hideCustomProgressDialog();
                    Utility.showMessage(getActivity(), false, t.getMessage());
                }
            });
        } else {
            Utility.showNoInternetAvailable(getActivity());
        }
    }
    private void addMember(String fname, String lname)
    {
        dialogAddMember = new Dialog(getActivity());
        dialogAddMember.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddMember.setContentView(R.layout.dialog_for_add_member);
        dialogAddMember.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialogAddMember.setCanceledOnTouchOutside(true);

        ImageView imgBook = dialogAddMember.findViewById(R.id.imgBook);
        ImageView imgClose = dialogAddMember.findViewById(R.id.imgClose);
        AppCompatEditText edtName = dialogAddMember.findViewById(R.id.edtName);
        AppCompatTextView txtSubMit = dialogAddMember.findViewById(R.id.txtSubMit);
        AppCompatTextView txtTitle = dialogAddMember.findViewById(R.id.txtTitle);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddMember.dismiss();
            }
        });

        dialogAddMember.show();
    }


}