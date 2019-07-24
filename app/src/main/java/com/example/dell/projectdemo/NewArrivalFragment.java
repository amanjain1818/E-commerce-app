package com.example.dell.projectdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class NewArrivalFragment extends Fragment {

     RecyclerView newarrival;
    ApiInterface apiInterface;
    public NewArrivalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_arrival, container, false);
        newarrival=view.findViewById(R.id.recycle_newarrival_list);
        newarrival.setLayoutManager(new GridLayoutManager(getContext(),2));
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Newarrival>> call = apiInterface.newarrivalFragment();
        call.enqueue(new Callback<List<Newarrival>>() {
            @Override
            public void onResponse(Call<List<Newarrival>> call, Response<List<Newarrival>> response) {
                List<Newarrival> newarrivalList = response.body();
                if(newarrivalList.size() > 0){
                    newarrival.setAdapter(new NewarrivalAdapter(getContext(),newarrivalList));
                }else{
                    Toast.makeText(getContext(), "NO NEWARRIVAL  FOUND", Toast.LENGTH_SHORT).show();

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
