package com.example.medicalhelp.ui.hemorrhage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentHemorrhageBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class HemorrhageFragment extends Fragment {
    FragmentHemorrhageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHemorrhageBinding.inflate(
                inflater,
                container,
                false
        );
        TextView hemorrhageTitle = binding.hemorrhageTitle;
        TextView hemorrhageContent = binding.hemorrhageContent;
        _initiateComponent(hemorrhageTitle, hemorrhageContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView hemorrhageTitle, TextView hemorrhageContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_hemorrhage);
        requireActivity().runOnUiThread(() -> {
            hemorrhageTitle.setText(_thisEntry.title);
            hemorrhageContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}