package com.example.appmci.drawerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appmci.R;

import javax.annotation.Nullable;

public class ProfileFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @androidx.annotation.Nullable ViewGroup container,
                             @androidx.annotation.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.drawer_fragment_profile, container, false);

        return v;
    }
}
