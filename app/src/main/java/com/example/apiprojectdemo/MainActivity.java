package com.example.apiprojectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        recyclerView=findViewById(R.id.recycleview);

        ConnectivityManager connectivityManager =(ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            Dialog dialog = new Dialog(MainActivity
                    .this);
            dialog.setContentView(R.layout.networkerro);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;


            Button button = dialog.findViewById(R.id.btnTrynetwork);
            button.setOnClickListener(v -> recreate());
            dialog.show();

        }else {

            apiInterface.getposts().enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<List<Postpijo>> call, @NonNull Response<List<Postpijo>> response) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {
                        List<Postpijo> postpijoList = response.body();

                        Adapter adapter = new Adapter(postpijoList, MainActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);

                        Toast.makeText(MainActivity.this, "List will show", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "list is  empty", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<List<Postpijo>> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }



    }
}