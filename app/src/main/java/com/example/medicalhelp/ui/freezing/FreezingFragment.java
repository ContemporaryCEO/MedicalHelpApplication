package com.example.medicalhelp.ui.freezing;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentFreezingBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class FreezingFragment extends Fragment {
    FragmentFreezingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFreezingBinding.inflate(
                inflater,
                container,
                false
        );
        TextView freezingTitle = binding.freezingTitle;
        TextView freezingContent = binding.freezingContent;
        _initiateComponent(freezingTitle, freezingContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView freezingTitle, TextView freezingContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_freezing);
        requireActivity().runOnUiThread(() -> {
            freezingTitle.setText(_thisEntry.title);
            freezingContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}