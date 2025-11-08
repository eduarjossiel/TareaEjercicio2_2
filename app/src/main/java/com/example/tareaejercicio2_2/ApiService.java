package com.example.tareaejercicio2_2;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    @GET("posts")
    Call<List<Post>> getPosts();


    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") int id);
}
