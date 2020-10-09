package com.example.monitorbebe.lists.resumo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorbebe.R;
import com.example.monitorbebe.ui.main.TouchHelper;


public class ResumoListFragment extends Fragment {


    public static ResumoListFragment newInstance(ResumoAdapter adapter) {
        Bundle args = new Bundle();
        ResumoListFragment fragment = new ResumoListFragment();
        args.putSerializable("adapter", adapter);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewList);
        ResumoAdapter adapter = (ResumoAdapter) getArguments().getSerializable("adapter");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper());
        touchHelper.attachToRecyclerView(recyclerView);
        return view;
    }


}
