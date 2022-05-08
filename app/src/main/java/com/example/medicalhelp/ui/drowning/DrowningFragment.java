package com.example.medicalhelp.ui.drowning;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentDrowningBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class DrowningFragment extends Fragment {
    FragmentDrowningBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDrowningBinding.inflate(
                inflater,
                container,
                false
        );
        TextView drowningTitle = binding.drowningTitle;
        TextView drowningContent = binding.drowningContent;
        _initiateComponent(drowningTitle, drowningContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView drowningTitle, TextView drowningContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_drowning);
        requireActivity().runOnUiThread(() -> {
            drowningTitle.setText(_thisEntry.title);
            drowningContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}