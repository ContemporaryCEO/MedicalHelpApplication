package com.example.medicalhelp.ui.main;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.medicalhelp.R;
import com.example.medicalhelp.databinding.FragmentMainWindowBinding;
import com.example.medicalhelp.dbs.ApplicationDatabase;
import com.example.medicalhelp.dbs.Entry;
import com.example.medicalhelp.dbs.EntryDao;
import com.example.medicalhelp.utils.AppDbHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainWindowFragment extends Fragment {
    private FragmentMainWindowBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainWindowBinding.inflate(inflater, container, false);
        SearchView searchView = binding.primarySearch;
        ListView searchContainer = binding.searchContainer;
        EntryDao db = AppDbHandler.getApplicationDatabase(
                getContext(),
                ApplicationDatabase.class,
                "default",
                true
        );
        List<Entry> entries = db.getAllEntries();
        ArrayAdapter<String> adapter = _configureAdapterForList(searchContainer, entries);
        _configureOnQuerySearchListener(searchView, adapter);
        _configureOnSearchItemClickListener(searchContainer, entries, adapter);
        return binding.getRoot();
    }

    private void _configureOnSearchItemClickListener(ListView searchContainer, List<Entry> entries, ArrayAdapter<String> adapter) {
        searchContainer.setOnItemClickListener(
                (adapterView, view, i, l) -> {
                    Entry entry1 = _findEntryByTitle(entries, adapter, i);
                    _invokeNavigationTo(entry1);
                }
        );
    }

    private Entry _findEntryByTitle(List<Entry> entries, ArrayAdapter<String> adapter, int i) {
        return entries.stream().filter(
                entry -> Objects.equals(entry.title, adapter.getItem(i))
        ).findFirst().orElseThrow(() -> new RuntimeException(getString(R.string.onSearchOnItemClickExc)));
    }

    private void _invokeNavigationTo(Entry entry1) {
        NavController _thisNavController = Navigation.findNavController(requireActivity(), this.getId());
        _thisNavController.navigate(entry1.id, null, getNavOptions());
    }

    @NonNull
    private NavOptions getNavOptions() {
        return new NavOptions.Builder()
                .setPopUpTo(R.id.nav_home, true).build();
    }

    private void _configureOnQuerySearchListener(SearchView searchView, ArrayAdapter<String> adapter) {
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        switch (query) {
                            case "dino": {
                                _invokeFeatureNavigation(R.id.nav_blank);
                                break;
                            }
                            case "беляш": {
                                _invokeFeatureNavigation(R.id.nav_another);
                                break;
                            }
                            default:
                                _invokeFiltration(query, adapter);
                                break;
                        }
                        return false;
                    }

                    private void _invokeFeatureNavigation(int p) {
                        Entry aVoid = new Entry();
                        aVoid.id = p;
                        _invokeNavigationTo(aVoid);
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        _invokeFiltration(newText, adapter);
                        return false;
                    }
                }
        );
    }

    private void _invokeFiltration(String query, ArrayAdapter<String> adapter) {
        adapter.getFilter().filter(query);
    }

    private ArrayAdapter<String> _configureAdapterForList(ListView searchContainer, List<Entry> entries) {
        List<String> entryTitles = entries
                .stream()
                .map(entry -> entry.title)
                .collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                entryTitles
        );
        searchContainer.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}