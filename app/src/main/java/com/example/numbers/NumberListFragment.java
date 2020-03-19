package com.example.numbers;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NumberListFragment extends Fragment {
    private static final String KEY_LAST_NUMBER = "last_number";

    private static final int COLS_NUM_VERTICAL = 3;
    private static final int COLS_NUM_HORIZONTAL = 4;

    private int mLastNumber;

    static NumberListFragment newInstance(int numberCount) {
        NumberListFragment fragment = new NumberListFragment();
        fragment.mLastNumber = numberCount;

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_LAST_NUMBER, fragment.mLastNumber);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            mLastNumber = arguments.getInt(KEY_LAST_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        if (savedInstanceState != null) {
            mLastNumber = savedInstanceState.getInt(KEY_LAST_NUMBER);
        }

        int columnsNum = COLS_NUM_VERTICAL;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columnsNum = COLS_NUM_HORIZONTAL;
        }

        RecyclerView numbersListView = view.findViewById(R.id.numbers_list);
        NumberListAdapter listAdapter = new NumberListAdapter(getActivity(), mLastNumber);
        numbersListView.setLayoutManager(new GridLayoutManager(getActivity(), columnsNum));
        numbersListView.setAdapter(listAdapter);

        Button addNumberBtn = view.findViewById(R.id.add_number_btn);
        addNumberBtn.setOnClickListener(v -> listAdapter.setDataSize(++mLastNumber));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_LAST_NUMBER, mLastNumber);
    }
}
