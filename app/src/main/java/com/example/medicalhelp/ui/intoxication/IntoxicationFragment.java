package com.example.medicalhelp.ui.intoxication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentIntoxicationBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class IntoxicationFragment extends Fragment {
    FragmentIntoxicationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIntoxicationBinding.inflate(
                inflater,
                container,
                false
        );
        TextView intoxicationTitle = binding.intoxicationTitle;
        TextView intoxicationContent = binding.intoxicationContent;
        _initiateComponent(intoxicationTitle, intoxicationContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView intoxicationTitle, TextView intoxicationContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_intoxication);
        requireActivity().runOnUiThread(() -> {
            intoxicationTitle.setText(_thisEntry.title);
            intoxicationContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}