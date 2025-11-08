package com.example.tareaejercicio2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnVerPost;
    ApiService apiService;
    List<Post> listaPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnVerPost = findViewById(R.id.btnVerPost);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        obtenerPosts();


        btnVerPost.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IndividualPostActivity.class);
            intent.putExtra("postId", 1);
            startActivity(intent);
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            Post postSeleccionado = listaPosts.get(position);
            Intent intent = new Intent(MainActivity.this, IndividualPostActivity.class);
            intent.putExtra("postId", postSeleccionado.getId());
            startActivity(intent);
        });
    }

    private void obtenerPosts() {
        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPosts = response.body();
                    ArrayAdapter<Post> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            listaPosts
                    );
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

