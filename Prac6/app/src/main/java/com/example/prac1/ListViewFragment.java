package com.example.prac1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prac1.databinding.FragmentListViewBinding;

public class ListViewFragment extends Fragment {

    FragmentListViewBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String data = getArguments().getString("keyList");
        }

        binding.listView.setAdapter(new MyAdapter());

        binding.listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Log.e("TAG", "Сlick on element");
            Toast.makeText(requireContext(), "Сlick on element", Toast.LENGTH_SHORT).show();
        });

    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 207;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getLayoutInflater().inflate(R.layout.list_item, null);
        }
    }
}