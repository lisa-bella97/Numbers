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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class NumberListFragment extends Fragment implements NumberListAdapter.OnClickNumberListener {
    private static final String KEY_LAST_NUMBER = "last_number";
    private static final String TRANSACTION_OPEN_NUMBER_FRAGMENT = "openNumberFragment";

    private static final int COLS_NUM_VERTICAL = 3;
    private static final int COLS_NUM_HORIZONTAL = 4;

    private int mLastNumber;

    @NonNull
    static NumberListFragment newInstance(int numberCount) {
        final NumberListFragment fragment = new NumberListFragment();
        fragment.mLastNumber = numberCount;

        final Bundle bundle = new Bundle();
        bundle.putInt(KEY_LAST_NUMBER, fragment.mLastNumber);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        if (arguments != null) {
            mLastNumber = arguments.getInt(KEY_LAST_NUMBER);
        }

        if (savedInstanceState != null) {
            mLastNumber = savedInstanceState.getInt(KEY_LAST_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);

        if (savedInstanceState != null) {
            mLastNumber = savedInstanceState.getInt(KEY_LAST_NUMBER);
        }

        int columnsNum = COLS_NUM_VERTICAL;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columnsNum = COLS_NUM_HORIZONTAL;
        }

        final RecyclerView numbersListView = view.findViewById(R.id.numbers_list);
        final FragmentActivity fragmentActivity = getActivity();
        final NumberListAdapter listAdapter = new NumberListAdapter(Objects.requireNonNull(fragmentActivity), this, mLastNumber);
        numbersListView.setLayoutManager(new GridLayoutManager(fragmentActivity, columnsNum));
        numbersListView.setAdapter(listAdapter);

        final Button addNumberBtn = view.findViewById(R.id.add_number_btn);
        addNumberBtn.setOnClickListener(v -> listAdapter.setDataSize(++mLastNumber));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_LAST_NUMBER, mLastNumber);
    }

    @Override
    public void onClickNumber(int number) {
        final FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();

        final NumberFragment fragment = NumberFragment.newInstance(number);
        transaction.replace(R.id.fragment_container, fragment).addToBackStack(TRANSACTION_OPEN_NUMBER_FRAGMENT).commit();
    }
}
