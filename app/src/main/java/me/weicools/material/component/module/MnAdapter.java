package me.weicools.material.component.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import me.weicools.material.component.ExpandableRecyclerAdapter;
import me.weicools.material.component.Item;
import me.weicools.material.component.R;
import org.jetbrains.annotations.NotNull;

/**
 * @author Weicools
 */
public class MnAdapter extends ExpandableRecyclerAdapter<Item> {

  public interface OnItemClickListener {
    void onItemClick (View view, Item item);
  }

  public class DividerViewHolder extends RecyclerView.ViewHolder {
    TextView name;

    public DividerViewHolder (View view) {
      super(view);
      this.name = view.findViewById(R.id.item_menu_divider_name);
    }

    public void bind (int i) {
      if (MnAdapter.this.visibleItems.get(i).Text == null) {
        this.name.setVisibility(View.GONE);
        return;
      }
      this.name.setVisibility(View.VISIBLE);
      this.name.setText(MnAdapter.this.visibleItems.get(i).Text);
    }
  }

  public class PlainViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView name;
    View view;

    public PlainViewHolder (View view) {
      super(view);
      this.view = view;
      this.name = view.findViewById(R.id.item_menu_group_name);
      this.icon = view.findViewById(R.id.item_menu_group_image);
    }

    public void bind (final int i) {
      this.name.setText(visibleItems.get(i).Text);
      this.icon.setImageResource(visibleItems.get(i).Icon);
      this.view.setOnClickListener(view -> MnAdapter.this.onItemClickListener.onItemClick(view, visibleItems.get(i)));
    }
  }

  public class SubHeaderViewHolder extends RecyclerView.ViewHolder {
    ImageView badge;
    TextView name;
    View view;

    public SubHeaderViewHolder (View view) {
      super(view);
      this.view = view;
      this.name = view.findViewById(R.id.item_menu_sub_group_name);
      this.badge = view.findViewById(R.id.item_menu_sub_group_badge);
    }

    public void bind (final int i) {
      this.name.setText(MnAdapter.this.visibleItems.get(i).Text);
      this.view.setOnClickListener(
          view -> MnAdapter.this.onItemClickListener.onItemClick(view, MnAdapter.this.visibleItems.get(i)));
      this.badge.setVisibility(MnAdapter.this.visibleItems.get(i).New ? View.VISIBLE : View.INVISIBLE);
    }
  }

  public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
    ImageView badge;
    ImageView icon;
    TextView name;

    public HeaderViewHolder (View view) {
      super(view, view.findViewById(R.id.item_arrow));
      this.name = view.findViewById(R.id.item_menu_group_name);
      this.icon = view.findViewById(R.id.item_menu_group_image);
      this.badge = view.findViewById(R.id.item_menu_group_badge);
    }

    @Override
    public void bind (int i) {
      super.bind(i);
      this.name.setText(MnAdapter.this.visibleItems.get(i).Text);
      this.icon.setImageResource(MnAdapter.this.visibleItems.get(i).Icon);
      this.badge.setVisibility(MnAdapter.this.visibleItems.get(i).New ? View.VISIBLE : View.INVISIBLE);
    }
  }

  private Context context;
  private OnItemClickListener onItemClickListener;

  public MnAdapter (Context context, List<Item> list, OnItemClickListener onItemClickListener) {
    super(context);
    this.context = context;
    this.onItemClickListener = onItemClickListener;
    setItems(list);
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  @NotNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder (@NotNull ViewGroup viewGroup, int i) {
    View view = new View(this.context);
    view.setLayoutParams(new LayoutParams(-1, 2));
    view.setBackgroundColor(this.context.getResources().getColor(R.color.grey_60));
    if (i == NORMAL) {
      return new PlainViewHolder(
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_plain, viewGroup, false));
    }
    if (i == HEADER) {
      return new HeaderViewHolder(
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_group, viewGroup, false));
    }
    if (i == SUB_HEADER) {
      return new SubHeaderViewHolder(
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_sub_group, viewGroup, false));
    }
    if (i == DIVIDER) {
      return new DividerViewHolder(
          LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_divider, viewGroup, false));
    }
    return new PlainViewHolder(
        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_group, viewGroup, false));
  }

  @Override
  public void onBindViewHolder (@NotNull RecyclerView.ViewHolder viewHolder, int i) {
    int itemViewType = getItemViewType(i);
    if (itemViewType == NORMAL) {
      ((PlainViewHolder) viewHolder).bind(i);
    } else if (itemViewType == HEADER) {
      ((HeaderViewHolder) viewHolder).bind(i);
    } else if (itemViewType == SUB_HEADER) {
      ((SubHeaderViewHolder) viewHolder).bind(i);
    } else if (itemViewType == DIVIDER) {
      ((DividerViewHolder) viewHolder).bind(i);
    } else {
      ((SubHeaderViewHolder) viewHolder).bind(i);
    }
  }
}
