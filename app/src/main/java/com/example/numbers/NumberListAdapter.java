package com.example.numbers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private final FragmentActivity mFragmentActivity;
    private final OnClickNumberListener mListener;

    private int mDataSize;

    NumberListAdapter(@NonNull FragmentActivity fragmentActivity, @NonNull OnClickNumberListener listener, int dataSize) {
        mFragmentActivity = Objects.requireNonNull(fragmentActivity);
        mListener = Objects.requireNonNull(listener);
        mDataSize = dataSize;
    }

    void setDataSize(int newDataSize) {
        mDataSize = newDataSize;
        this.notifyItemInserted(mDataSize - 1);
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        final int number = position + 1;
        holder.setValue(number);
        holder.setTextColor(CommonUtils.getColorOfNumber(mFragmentActivity, number));

        holder.itemView.setOnClickListener(v -> mListener.onClickNumber(number));
    }

    @Override
    public int getItemCount() {
        return mDataSize;
    }

    public interface OnClickNumberListener {
        void onClickNumber(int number);
    }
}
