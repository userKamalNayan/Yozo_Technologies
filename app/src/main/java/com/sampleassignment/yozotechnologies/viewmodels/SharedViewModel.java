package com.sampleassignment.yozotechnologies.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sampleassignment.yozotechnologies.model.entities.PostsModel;
import com.sampleassignment.yozotechnologies.model.entities.ProfileModel;
import com.sampleassignment.yozotechnologies.model.retrofit.IMyApi;
import com.sampleassignment.yozotechnologies.model.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SharedViewModel extends ViewModel {

    MutableLiveData<List<PostsModel>> postsList;
    MutableLiveData<ProfileModel> profileLiveData;
    IMyApi iMyApi;
    CompositeDisposable compositeDisposable;


    public MutableLiveData<ProfileModel> getProfileLiveData()
    {
        ProfileModel profile = new ProfileModel();
        profile.setName("Kamal Nayan");
        profile.setPhoneNumber("+91-7355732417");
        profileLiveData.setValue(profile);
        return profileLiveData;
    }


    public SharedViewModel() {
        if (postsList == null) {
            postsList = new MutableLiveData<>();
            compositeDisposable = new CompositeDisposable();
            profileLiveData = new MutableLiveData<>();
        }

    }


    private void getPosts() {

        Retrofit retrofit = RetrofitClient.getInstance();
        iMyApi = retrofit.create(IMyApi.class);

        compositeDisposable.add(iMyApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PostsModel>>() {
                    @Override
                    public void accept(List<PostsModel> postsModels) throws Exception {
                        postsList.setValue(postsModels);
                    }
                })
        );


    }

    public MutableLiveData<List<PostsModel>> getPostsList() {
        getPosts();
        return postsList;
    }


}
