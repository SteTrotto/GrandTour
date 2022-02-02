package com.example.grandtour.ui.visualizza_recensioni;

import android.graphics. Rect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class SpacingitemDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public SpacingitemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}