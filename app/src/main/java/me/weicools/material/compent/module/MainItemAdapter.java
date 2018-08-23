package me.weicools.material.compent.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.launcher.ARouter;
import java.util.List;
import me.weicools.material.compent.R;
import me.weicools.material.compent.config.RouterConfig;
import me.weicools.material.compent.config.RouterData;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
public class MainItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Context mContext;
  private List<RouterData> mDataList;

  MainItemAdapter (Context context, @NonNull List<RouterData> dataList) {
    this.mContext = context;
    this.mDataList = dataList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
    if (viewType == 0) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_title, parent, false);
      return new TitleViewHolder(view);
    } else {
      View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
      return new ItemViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder (@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    RouterData data = mDataList.get(position);
    if (data.getType() == 0) {
      ((TitleViewHolder) viewHolder).tvItemTitle.setText(data.getName());
    } else {
      ItemViewHolder holder = (ItemViewHolder) viewHolder;
      holder.tvItemTitle.setText(data.getName());
      holder.itemView.setOnClickListener(v -> ARouter.getInstance().build(data.getPath()).navigation());
    }
  }

  @Override
  public int getItemCount () {
    return mDataList.size();
  }

  @Override
  public int getItemViewType (int position) {
    return mDataList.get(position).getType();
  }

  static class ItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_item_title) TextView tvItemTitle;

    ItemViewHolder (@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  static class TitleViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_item_title) TextView tvItemTitle;

    TitleViewHolder (@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
