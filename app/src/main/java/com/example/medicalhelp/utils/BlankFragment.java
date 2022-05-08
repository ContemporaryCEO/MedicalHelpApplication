package com.example.medicalhelp.utils;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.databinding.FragmentBlankBinding;

public class BlankFragment extends Fragment {
    FragmentBlankBinding binding;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentBlankBinding.inflate(
                inflater,
                container,false
        );
        WebView fragmentBlankId = binding.fragmentBlankId;
        fragmentBlankId.getSettings().setJavaScriptEnabled(true);
        String _blankUrl = "file:///android_asset/index.html";
        fragmentBlankId.loadUrl(_blankUrl);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}