package com.jennifer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jennifer.R;
import com.jennifer.controller.SecondActivity;
import com.jennifer.model.User;

import java.util.List;

/**
 * Created by echessa on 7/24/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<User> mItems;
    public RecyclerAdapter(List<User> items) {
        mItems = items;
    }
    private PopupWindow popUpWindow;
    private LayoutInflater layoutInflater;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        User item = mItems.get(i);

        // Data Set
        viewHolder.logImage.setImageResource(item.getImagen());

        viewHolder.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });

        viewHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, SecondActivity.class));
            }
        });



        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
            /*    context.startActivity(new Intent(context, SecondActivity.class));
                Toast.makeText(context, "Your toast message.",
                        Toast.LENGTH_SHORT).show();*/
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup)layoutInflater.inflate(R.layout.popup_info_apuestas,null);
                popUpWindow = new PopupWindow(container,1000,750,true);
 /*               popUpWindow.setBackgroundDrawable(new ColorDrawable(
                        android.graphics.Color.TRANSPARENT));*/
                popUpWindow.showAtLocation(view, Gravity.NO_GRAVITY,500,500);
               // view.setAlpha(1);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popUpWindow.dismiss();
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView logImage;
        private final ImageView doneBtn;
        private final ImageView cancelBtn;
        private final CardView cv;

        ViewHolder(View v) {
            super(v);
            logImage = (ImageView)v.findViewById(R.id.log_image);
            doneBtn = (ImageView)v.findViewById(R.id.done);
            cancelBtn = (ImageView)v.findViewById(R.id.cancel);
            cv = (CardView)v.findViewById(R.id.cv);
        }
    }

}
