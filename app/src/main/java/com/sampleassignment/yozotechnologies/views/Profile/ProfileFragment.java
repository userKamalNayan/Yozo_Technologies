package com.sampleassignment.yozotechnologies.views.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.sampleassignment.yozotechnologies.R;
import com.sampleassignment.yozotechnologies.model.entities.ProfileModel;
import com.sampleassignment.yozotechnologies.viewmodels.SharedViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    private SharedViewModel profileViewModel;

    Unbinder unbinder;

    @BindView(R.id.profile_name)
    TextInputLayout name;

    @BindView(R.id.profile_number)
    TextInputLayout phone;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(SharedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileViewModel.getProfileLiveData().observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                setValues(profileModel);
            }
        });

        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    private void setValues(ProfileModel profileModel) {
        name.getEditText().setText(profileModel.getName());
        phone.getEditText().setText(profileModel.getPhoneNumber());
    }


}