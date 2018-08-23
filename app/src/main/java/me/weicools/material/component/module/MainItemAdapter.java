package me.weicools.material.component.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.launcher.ARouter;
import java.util.List;
import java.util.Random;
import me.weicools.material.component.R;
import me.weicools.material.component.config.RouterData;
import me.weicools.material.component.utils.UiUtils;

/**
 * @author Weicools Create on 2018.08.23
 *
 * desc:
 */
public class MainItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private Random mRandom;
  private Context mContext;
  private List<RouterData> mDataList;

  MainItemAdapter (Context context, @NonNull List<RouterData> dataList) {
    this.mContext = context;
    this.mDataList = dataList;
    mRandom = new Random();
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
      ((TitleViewHolder) viewHolder).tvItemTitle.setBackgroundColor(getRandomColor());
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

  private int getRandomColor () {
    int val = mRandom.nextInt(10);
    int colorId;
    switch (val) {
      case 0:
        colorId = R.color.amber_400;
        break;
      case 1:
        colorId = R.color.blue_400;
        break;
      case 2:
        colorId = R.color.blue_grey_400;
        break;
      case 3:
        colorId = R.color.cyan_400;
        break;
      case 4:
        colorId = R.color.deep_orange_400;
        break;
      case 5:
        colorId = R.color.deep_purple_400;
        break;
      case 6:
        colorId = R.color.purple_400;
        break;
      case 7:
        colorId = R.color.indigo_400;
        break;
      case 8:
        colorId = R.color.light_blue_400;
        break;
      case 9:
        colorId = R.color.lime_400;
        break;
      default:
        colorId = R.color.colorPrimary;
        break;
    }

    return ContextCompat.getColor(mContext, colorId);
  }
}
