package com.weiwei.material.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author weiwei
 * @date 2022.03.03
 */
public class GridDividerDecoration extends RecyclerView.ItemDecoration {

  private final int spanCount;
  private final Paint dividerPaint;
  private final Rect bounds = new Rect();

  public GridDividerDecoration(@Px int dividerSize, @ColorInt int dividerColor, int spanCount) {
    this.dividerPaint = new Paint();
    this.dividerPaint.setColor(dividerColor);
    this.dividerPaint.setStrokeWidth(dividerSize);
    this.dividerPaint.setStyle(Paint.Style.STROKE);
    this.dividerPaint.setAntiAlias(true);
    this.spanCount = spanCount;
  }

  @Override
  public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    drawHorizontal(c, parent);
    drawVertical(c, parent);
  }

  private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    final int itemCount = parent.getAdapter().getItemCount();
    final int childCount = parent.getChildCount();
    final int lastRowChildCount = getLastRowChildCount(itemCount);
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);

      if (isChildInLastRow(parent, child, itemCount, lastRowChildCount)) {
        continue;
      }

      parent.getDecoratedBoundsWithMargins(child, bounds);
      final int y = bounds.bottom;
      final int startX = bounds.left;
      final int stopX = bounds.right;
      canvas.drawLine(startX, y, stopX, y, dividerPaint);
    }
  }

  private void drawVertical(Canvas canvas, RecyclerView parent) {
    final int childCount = parent.getChildCount();
    final boolean isRTL =
        ViewCompat.getLayoutDirection(parent) == ViewCompat.LAYOUT_DIRECTION_RTL;
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);

      if (isChildInLastColumn(parent, child)) {
        continue;
      }

      parent.getDecoratedBoundsWithMargins(child, bounds);
      final int x = isRTL ? bounds.left : bounds.right;
      final int startY = bounds.top;
      final int stopY = bounds.bottom;
      canvas.drawLine(x, startY, x, stopY, dividerPaint);
    }
  }

  private int getLastRowChildCount(int itemCount) {
    final int gridChildRemainder = itemCount % spanCount;
    return gridChildRemainder == 0 ? spanCount : gridChildRemainder;
  }

  private boolean isChildInLastRow(
      RecyclerView parent, View child, int itemCount, int lastRowChildCount) {
    return parent.getChildAdapterPosition(child) >= itemCount - lastRowChildCount;
  }

  private boolean isChildInLastColumn(RecyclerView parent, View child) {
    return parent.getChildAdapterPosition(child) % spanCount == spanCount - 1;
  }
}
