package com.example.numbers;

import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import static androidx.core.content.res.ResourcesCompat.getColor;

class Common {
    static int getColorOfNumber(@Nullable FragmentActivity context, int number) {
        int evenNumberColor = 0;
        int oddNumberColor = 0;
        if (context != null) {
            final Resources resources = context.getResources();
            evenNumberColor = getColor(resources, R.color.evenNumberColor, null);
            oddNumberColor = getColor(resources, R.color.oddNumberColor, null);
        }

        return (number % 2 == 0) ? evenNumberColor : oddNumberColor;
    }
}
