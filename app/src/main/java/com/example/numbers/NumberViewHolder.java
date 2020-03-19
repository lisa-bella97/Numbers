package com.example.numbers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NumberViewHolder extends RecyclerView.ViewHolder {
    private final TextView mTitle;

    NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.list_number_txt_view);
    }

    void setValue(Integer value) {
        mTitle.setText(String.valueOf(value));
    }

    void setTextColor(int color) {
        mTitle.setTextColor(color);
    }
}
