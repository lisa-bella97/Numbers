package com.example.numbers;

import android.content.res.Resources;

import androidx.fragment.app.FragmentActivity;

import static androidx.core.content.res.ResourcesCompat.getColor;

class Common {
    static int getColorOfNumber(FragmentActivity context, int number) {
        int evenNumberColor = 0;
        int oddNumberColor = 0;
        if (context != null) {
            Resources resources = context.getResources();
            evenNumberColor = getColor(resources, R.color.evenNumberColor, null);
            oddNumberColor = getColor(resources, R.color.oddNumberColor, null);
        }

        return (number % 2 == 0) ? evenNumberColor : oddNumberColor;
    }
}
