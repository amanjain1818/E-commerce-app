package com.example.dell.projectdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.projectdemo.pojo.Newarrival;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SareeFragment extends Fragment   {


    public SareeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ApiInterface apiInterface;
         final RecyclerView saree;
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_saree, container, false);

        saree=view.findViewById(R.id.recycle_saree_list);

        saree.setLayoutManager(new GridLayoutManager(getContext(),2));
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Newarrival>> call = apiInterface.sareeFragment();
        call.enqueue(new Callback<List<Newarrival>>() {
            @Override
            public void onResponse(Call<List<Newarrival>> call, Response<List<Newarrival>> response) {
                List<Newarrival> sareeList = response.body();
                if(sareeList.size() > 0){
                    saree.setAdapter(new SareeAdapter(getContext(),sareeList));
                }else{
                    Toast.makeText(getContext(), "NO SAREE RECORD FOUND", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Newarrival>> call, Throwable t) {
                Toast.makeText(getContext(), "Error- "+t, Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }

}


