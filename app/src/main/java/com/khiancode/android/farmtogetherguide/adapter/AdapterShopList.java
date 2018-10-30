package com.khiancode.android.farmtogetherguide.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.khiancode.android.farmtogetherguide.R;
import com.khiancode.android.farmtogetherguide.model.ItemShopModel;

import java.util.ArrayList;

public class AdapterShopList extends RecyclerView.Adapter<AdapterShopList.VersionViewHolder> {

    ArrayList<ItemShopModel> model;
    Context context;
    OnItemClickListener clickListener;

    public AdapterShopList(Context applicationContext, ArrayList<ItemShopModel> model) {
        this.context = applicationContext;
        this.model = model;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_shop_item, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        String img = "file:///android_asset/images/" + model.get(i).getPathImg();

        Glide.with(context)
                .load(Uri.parse(img))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(versionViewHolder.imgItem);
    }

    @Override
    public int getItemCount() {
        return model == null ? 0 : model.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgItem;
        public VersionViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
