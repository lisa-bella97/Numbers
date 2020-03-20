package com.example.numbers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NumberFragment extends Fragment {
    private static final String NUMBER_KEY = "number";

    @NonNull
    static NumberFragment newInstance(int number) {
        final NumberFragment fragment = new NumberFragment();
        final Bundle bundle = new Bundle();
        bundle.putInt(NUMBER_KEY, number);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_number, container, false);

        int number = 0;
        final Bundle arguments = getArguments();
        if (arguments != null) {
            number = arguments.getInt(NUMBER_KEY);
        }

        final TextView numberTextView = view.findViewById(R.id.number_txt_view);
        numberTextView.setText(String.valueOf(number));
        numberTextView.setTextColor(CommonUtils.getColorOfNumber(getActivity(), number));

        return view;
    }
}
