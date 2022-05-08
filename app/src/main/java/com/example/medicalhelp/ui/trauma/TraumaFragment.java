package com.example.medicalhelp.ui.trauma;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentTraumaBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class TraumaFragment extends Fragment {
    FragmentTraumaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTraumaBinding.inflate(
                inflater,
                container,
                false
        );
        TextView traumaTitle = binding.traumaTitle;
        TextView traumaContent = binding.traumaContent;
        _initiateComponent(traumaTitle, traumaContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView traumaTitle, TextView traumaContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_trauma);
        requireActivity().runOnUiThread(() -> {
            traumaTitle.setText(_thisEntry.title);
            traumaContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}