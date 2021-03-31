package com.sampleassignment.yozotechnologies.model.retrofit;

import com.sampleassignment.yozotechnologies.model.entities.PostsModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyApi {
    @GET("posts")
    Observable<List<PostsModel>> getPosts();

}
