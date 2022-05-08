package com.example.medicalhelp.ui.asphyxia;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentAsphyxiaBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class AsphyxiaFragment extends Fragment {
    FragmentAsphyxiaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAsphyxiaBinding.inflate(
                inflater,
                container,
                false
        );
        TextView asphyxiaTitle = binding.asphyxiaTitle;
        TextView asphyxiaContent = binding.asphyxiaContent;
        _initiateComponent(asphyxiaTitle, asphyxiaContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView asphyxiaTitle, TextView asphyxiaContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_asphyxia);
        requireActivity().runOnUiThread(() -> {
            asphyxiaTitle.setText(_thisEntry.title);
            asphyxiaContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}