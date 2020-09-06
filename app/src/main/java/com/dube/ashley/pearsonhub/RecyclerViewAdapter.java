package com.dube.ashley.pearsonhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.MyViewHolder>
{
    private Context mContext;
    private List<CategoryHandler> mData;

    public RecyclerViewAdapter(Context mContext, List<CategoryHandler> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.tv_book_price.setText("R "+mData.get(position).getPrice());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_book_title;
        TextView tv_book_price;
        ImageView img_book_thumbnail;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_book_title=(TextView) itemView.findViewById(R.id.book_title_id);
            tv_book_price=(TextView) itemView.findViewById(R.id.book_Price_id);
            img_book_thumbnail=(ImageView) itemView.findViewById(R.id.book_img_id);
        }
    }
}
