package com.example.cookingbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Food> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;

    public ListViewAdapter(Context aContext, List<Food> listData, Activity activity) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_food, null);
            holder = new ViewHolder();
            holder.smallIconView = convertView.findViewById(R.id.imageView_smallIcon);
            holder.foodNameView = convertView.findViewById(R.id.textView_foodName);
            holder.showHighResView = convertView.findViewById(R.id.imageView_showHighRes);
            holder.highResView = convertView.findViewById(R.id.textView_highRes);
            holder.leftLayout = convertView.findViewById(R.id.leftLayout);
            holder.rightLayout = convertView.findViewById(R.id.rightLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Food food = this.listData.get(position);
        holder.foodNameView.setText(food.getName());

        final int imageId = this.getMipmapResIdByName(food.getImage());

        //Khởi tạo imageLoader với cấu hình mặc định
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        //Hiển thị ảnh lên giao diện
        imageLoader.displayImage("drawable://" + imageId, holder.smallIconView);

        holder.rightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ImageActivity.class);
                i.putExtra("Image ID", imageId);
                activity.startActivity(i);
            }
        });

        holder.leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, IngredientsActivity.class);
                i.putExtra("URL", food.getUrl());
                activity.startActivity(i);
            }
        });

        return convertView;
    }


    // tìm id ảnh trong resource (mipmap)
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();

        //trả về 0 nếu không tìm thấy
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView smallIconView;
        TextView foodNameView;
        ImageView showHighResView;
        TextView highResView;
        ConstraintLayout leftLayout;
        ConstraintLayout rightLayout;
    }

}