package com.example.tareaejercicio2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndividualPostActivity extends AppCompatActivity {
    TextView txtPost;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_post);

        txtPost = findViewById(R.id.txtPost);

        apiService = RetrofitClient.getClient().create(ApiService.class);


        int postId = getIntent().getIntExtra("postId", 1);
        obtenerPostIndividual(postId);

    }

    private void obtenerPostIndividual(int id) {
        Call<Post> call = apiService.getPostById(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Post post = response.body();
                    txtPost.setText("ID: " + post.getId() +
                            "\n\nTítulo:\n" + post.getTitle() +
                            "\n\nContenido:\n" + post.getBody());
                } else {
                    txtPost.setText("No se encontró el post");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                txtPost.setText("Error: " + t.getMessage());
            }
        });
    }
}
