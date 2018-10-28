package com.khukhuna.jbossoutreach.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.khukhuna.jbossoutreach.R;
import com.khukhuna.jbossoutreach.models.Repository;
import com.khukhuna.jbossoutreach.network.GitHubClient;
import com.khukhuna.jbossoutreach.network.GitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GitHubService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = GitHubClient.getClient().create(GitHubService.class);
        getData("JbossOutreach");

    }


    public void getData(String organization){
        Call<List<Repository>> call = service.getProfile(organization);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
