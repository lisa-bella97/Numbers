package com.example.numbers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private static final String TRANSACTION_OPEN_NUMBER_FRAGMENT = "openNumberFragment";

    private final FragmentActivity mContext;

    private int mDataSize;

    NumberListAdapter(FragmentActivity context, int dataSize) {
        mContext = Objects.requireNonNull(context);
        mDataSize = dataSize;
    }

    void setDataSize(int newDataSize) {
        mDataSize = newDataSize;
        this.notifyItemInserted(mDataSize - 1);
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        int data = position + 1;
        holder.setValue(data);
        holder.setTextColor(Common.getColorOfNumber(mContext, data));

        holder.itemView.setOnClickListener(v -> {
            FragmentManager fragmentManager = mContext.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            NumberFragment fragment = NumberFragment.newInstance(data);
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(TRANSACTION_OPEN_NUMBER_FRAGMENT).commit();
        });
    }

    @Override
    public int getItemCount() {
        return mDataSize;
    }
}
