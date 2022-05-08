package com.example.medicalhelp.ui.burns;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentBurnsBinding;
import com.example.medicalhelp.utils.AppDbHandler;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;

public class BurnsFragment extends Fragment {
    FragmentBurnsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBurnsBinding.inflate(
                inflater,
                container,
                false
        );
        TextView burnTitle = binding.burnTitle;
        TextView burnContent = binding.burnContent;
        _initiateComponent(burnTitle, burnContent);
        return binding.getRoot();
    }

    private void _initiateComponent(TextView burnTitle, TextView burnContent) {
        EntryDao db = AppDbHandler.getApplicationDatabase(getContext(),
                ApplicationDatabase.class,
                "default", true);
        Entry _thisEntry = db.getEntryById(R.id.nav_burns);
        requireActivity().runOnUiThread(() -> {
            burnTitle.setText(_thisEntry.title);
            burnContent.setText(_thisEntry.content);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}