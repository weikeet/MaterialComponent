package me.weicools.material.component.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import me.weicools.material.component.Item;
import me.weicools.material.component.R;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhengwei.zhang
 */
public class MnSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
  private Context ctx;
  private List<Item> filteredItems;
  private List<Item> items;
  private OnItemClickListener mOnItemClickListener;

  public interface OnItemClickListener {
    void onItemClick (View view, Item item, int i);
  }

  public class OriginalViewHolder extends RecyclerView.ViewHolder {
    public ImageView badgeNew;
    public View lytParent;
    public TextView tvParent;
    public TextView tvSub;

    public OriginalViewHolder (View view) {
      super(view);
      this.badgeNew = view.findViewById(R.id.badge_new);
      this.tvParent = view.findViewById(R.id.tv_parent);
      this.tvSub = view.findViewById(R.id.tv_sub);
      this.lytParent = view.findViewById(R.id.lyt_parent);
    }
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.mOnItemClickListener = onItemClickListener;
  }

  public MnSearchAdapter (Context context, List<Item> list) {
    this.items = list;
    this.filteredItems = list;
    this.ctx = context;
  }

  @Override
  @NotNull
  public RecyclerView.ViewHolder onCreateViewHolder (@NotNull ViewGroup viewGroup, int i) {
    return new OriginalViewHolder(
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_search, viewGroup, false));
  }

  @Override
  public void onBindViewHolder (@NotNull RecyclerView.ViewHolder viewHolder, final int i) {
    if (viewHolder instanceof OriginalViewHolder) {
      OriginalViewHolder originalViewHolder = (OriginalViewHolder) viewHolder;
      Item item = this.filteredItems.get(i);
      originalViewHolder.tvParent.setText(item.Parent);
      originalViewHolder.tvSub.setText(item.Text);
      originalViewHolder.badgeNew.setVisibility(item.New ? View.VISIBLE : View.GONE);
      originalViewHolder.lytParent.setOnClickListener(view -> {
        if (MnSearchAdapter.this.mOnItemClickListener != null) {
          MnSearchAdapter.this.mOnItemClickListener.onItemClick(view, MnSearchAdapter.this.filteredItems.get(i), i);
        }
      });
    }
  }

  @Override
  public int getItemCount () {
    return this.filteredItems.size();
  }

  @Override
  public Filter getFilter () {
    return new Filter() {
      @Override
      public FilterResults performFiltering (CharSequence charSequence) {
        String toLowerCase = charSequence.toString().toLowerCase();
        if (toLowerCase.isEmpty()) {
          MnSearchAdapter.this.filteredItems = MnSearchAdapter.this.items;
        } else {
          List<Item> arrayList = new ArrayList<>();
          for (Item item : MnSearchAdapter.this.items) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(item.Parent);
            stringBuilder.append(" ");
            stringBuilder.append(item.Text);
            if (stringBuilder.toString().toLowerCase().contains(toLowerCase)) {
              arrayList.add(item);
            }
          }
          MnSearchAdapter.this.filteredItems = arrayList;
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = MnSearchAdapter.this.filteredItems;
        return filterResults;
      }

      @Override
      public void publishResults (CharSequence charSequence, FilterResults filterResults) {
        MnSearchAdapter.this.filteredItems = (ArrayList) filterResults.values;
        MnSearchAdapter.this.notifyDataSetChanged();
      }
    };
  }
}
