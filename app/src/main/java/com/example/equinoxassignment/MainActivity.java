package com.example.equinoxassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.equinoxassignment.Adapter.DataAdapter;
import com.example.equinoxassignment.Model.DataModel;
import com.example.equinoxassignment.Model.DataViewModel;
import com.example.equinoxassignment.Network.Retrofit;
import com.example.equinoxassignment.Repository.DataRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;
    private RecyclerView recyclerView;
    private List<DataModel> dataModelList;
    private DataRepository dataRepository;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataRepository = new DataRepository(getApplication());
        dataModelList = new ArrayList<>();
        dataAdapter = new DataAdapter(this, dataModelList);


        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getAllData().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModelList) {
                dataAdapter.getAllData(dataModelList);
                recyclerView.setAdapter(dataAdapter);

                Log.d("main", "onChanged: " + dataModelList);
            }
        });

        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit();
        Call<List<DataModel>> call = retrofit.api.getAllData();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                if (response.isSuccessful()) {
                    dataRepository.insert(response.body());
                    Log.d("main", "onResponse: " + response.body());
                }
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong,Please Try Again...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}