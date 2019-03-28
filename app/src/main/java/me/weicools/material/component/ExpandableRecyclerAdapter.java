package me.weicools.material.component;

import android.content.Context;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.weicools.material.component.model.MnType;

public abstract class ExpandableRecyclerAdapter<T extends ListItem>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public class HeaderViewHolder extends RecyclerView.ViewHolder {
    ImageView arrow;

    public HeaderViewHolder (View view, ImageView imageView) {
      super(view);
      this.arrow = imageView;
      view.setOnClickListener(v -> handleClick());
    }

    public void handleClick () {
      if (ExpandableRecyclerAdapter.this.toggleExpandedItems(getLayoutPosition(), false)) {
        ExpandableRecyclerAdapter.openArrow(this.arrow);
      } else {
        ExpandableRecyclerAdapter.closeArrow(this.arrow);
      }
      new Handler().postDelayed(ExpandableRecyclerAdapter.this::notifyDataSetChanged, 1500);
    }

    public void bind (int i) {
      this.arrow.setRotation(ExpandableRecyclerAdapter.this.isExpanded(i) ? 180.0f : 0.0f);
    }
  }

  public static final int MODE_NORMAL = 0;
  public static final int MODE_ACCORDION = 1;
  private static final int ARROW_ROTATION_DURATION = 500;
  
  public static final int HEADER = MnType.HEAD.getValue();
  public static final int NORMAL = MnType.NOR.getValue();
  public static final int DIVIDER = MnType.DIV.getValue();
  public static final int SUB_HEADER = MnType.SUB.getValue();
  
  protected List<T> allItems = new ArrayList<>();
  protected List<T> visibleItems = new ArrayList<>();
  private List<Integer> indexList = new ArrayList<>();
  private SparseIntArray expandMap = new SparseIntArray();

  private int mode;
  protected Context mContext;

  public ExpandableRecyclerAdapter (Context context) {
    this.mContext = context;
  }

  @Override
  public long getItemId (int i) {
    return (long) i;
  }

  @Override
  public int getItemCount () {
    return this.visibleItems == null ? 0 : this.visibleItems.size();
  }

  @Override
  public int getItemViewType (int i) {
    return this.visibleItems.get(i).ItemType;
  }

  public boolean toggleExpandedItems (int i, boolean z) {
    if (isExpanded(i)) {
      collapseItems(i, z);
      return false;
    }

    expandItems(i, z);
    if (this.mode == 1) {
      collapseAllExcept(i);
    }
    return true;
  }

  public void expandItems (int i, boolean z) {
    int i2 = 0;
    int i3 = i;
    for (int intValue = this.indexList.get(i) + 1; intValue < this.allItems.size(); intValue++) {
      int i4 = this.allItems.get(intValue).ItemType;
      this.allItems.size();
      if (i4 == HEADER || i4 == DIVIDER || i4 == NORMAL) {
        break;
      }
      i3++;
      i2++;
      this.visibleItems.add(i3, this.allItems.get(intValue));
      this.indexList.add(i3, intValue);
    }

    notifyItemRangeInserted(i + 1, i2);

    this.expandMap.put(this.indexList.get(i), 1);
    if (z) {
      notifyItemChanged(i);
    }
  }

  public void collapseItems (int i, boolean z) {
    int i2 = 0;
    for (int intValue = this.indexList.get(i) + 1; intValue < this.allItems.size(); intValue++) {
      int i3 = this.allItems.get(intValue).ItemType;
      this.allItems.size();
      if (i3 == HEADER || i3 == DIVIDER || i3 == NORMAL) {
        break;
      }
      i2++;
      int i4 = i + 1;
      this.visibleItems.remove(i4);
      this.indexList.remove(i4);
    }

    notifyItemRangeRemoved(i + 1, i2);

    this.expandMap.delete(this.indexList.get(i));
    if (z) {
      notifyItemChanged(i);
    }
  }

  public boolean isExpanded (int i) {
    return this.expandMap.get(this.indexList.get(i), -1) >= 0;
  }

  public void setItems (List<T> list) {
    this.allItems = list;
    ArrayList arrayList = new ArrayList<>();
    this.expandMap.clear();
    this.indexList.clear();

    for (int i = 0; i < list.size(); i++) {
      int i2 = this.allItems.get(i).ItemType;
      if (i2 == HEADER || i2 == DIVIDER || i2 == NORMAL) {
        this.indexList.add(i);
        arrayList.add(list.get(i));
      }
    }

    this.visibleItems = arrayList;
    notifyDataSetChanged();
  }

  public void notifyItemInserted (int i, int i2) {
    incrementIndexList(i, i2, 1);
    incrementExpandMapAfter(i, 1);

    if (i2 >= 0) {
      notifyItemInserted(i2);
    }
  }

  public void removeItemAt (int i) {
    int intValue = this.indexList.get(i);
    this.allItems.remove(intValue);
    this.visibleItems.remove(i);
    incrementIndexList(intValue, i, -1);
    incrementExpandMapAfter(intValue, -1);
    notifyItemRemoved(i);
  }

  private void incrementExpandMapAfter (int i, int i2) {
    SparseIntArray sparseIntArray = new SparseIntArray();
    for (int i3 = 0; i3 < this.expandMap.size(); i3++) {
      int keyAt = this.expandMap.keyAt(i3);
      if (keyAt >= i) {
        keyAt += i2;
      }
      sparseIntArray.put(keyAt, 1);
    }
    this.expandMap = sparseIntArray;
  }

  private void incrementIndexList (int i, int i2, int i3) {
    List<Integer> arrayList = new ArrayList<>();
    for (int i4 = 0; i4 < this.indexList.size(); i4++) {
      if (i4 == i2 && i3 > 0) {
        arrayList.add(i);
      }
      int intValue = this.indexList.get(i4);
      if (intValue >= i) {
        intValue += i3;
      }
      arrayList.add(intValue);
    }
    this.indexList = arrayList;
  }

  public void collapseAll () {
    collapseAllExcept(-1);
  }

  public void collapseAllExcept (int i) {
    int size = this.visibleItems.size() - 1;
    while (size >= 0) {
      int itemViewType = getItemViewType(size);
      if (size != i && ((itemViewType == HEADER || itemViewType == DIVIDER || itemViewType == NORMAL) && isExpanded(
          size))) {
        collapseItems(size, true);
      }
      size--;
    }
  }

  public void expandAll () {
    int size = this.visibleItems.size() - 1;
    while (size >= 0) {
      int itemViewType = getItemViewType(size);
      if ((itemViewType == HEADER || itemViewType == DIVIDER || itemViewType == NORMAL) && !isExpanded(size)) {
        expandItems(size, true);
      }
      size--;
    }
  }

  public static void openArrow (View view) {
    view.animate().setDuration(500).rotation(180.0f);
  }

  public static void closeArrow (View view) {
    view.animate().setDuration(500).rotation(0.0f);
  }

  public int getMode () {
    return this.mode;
  }

  public void setMode (int i) {
    this.mode = i;
  }
}
