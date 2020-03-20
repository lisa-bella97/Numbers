package com.example.numbers;

import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import static androidx.core.content.res.ResourcesCompat.getColor;

class CommonUtils {
    static int getColorOfNumber(@Nullable FragmentActivity fragmentActivity, int number) {
        int evenNumberColor = 0;
        int oddNumberColor = 0;
        if (fragmentActivity != null) {
            final Resources resources = fragmentActivity.getResources();
            evenNumberColor = getColor(resources, R.color.evenNumberColor, null);
            oddNumberColor = getColor(resources, R.color.oddNumberColor, null);
        }

        return (number % 2 == 0) ? evenNumberColor : oddNumberColor;
    }
}
