package com.example.leo.activitystudy.TelephoneBook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.activitystudy.R;

import java.util.List;

public class TelephoneBookAdapter extends ArrayAdapter<TelephoneBook> {
    private int resourceImgId;
    public TelephoneBookAdapter(Context context, int textViewResourceImgid, List<TelephoneBook>objects)
    {
        super(context,textViewResourceImgid,objects);
        resourceImgId=textViewResourceImgid;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        TelephoneBook telephoneBook = getItem(position);
        View view;
        RecyclerView.ViewHolder viewHolder;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceImgId, parent, false);
         //   viewHolder = new RecyclerView.ViewHolder();
        }
        else{
            view = convertView;
        }


        ImageView telImage = (ImageView)view.findViewById(R.id.TelephoneImage);
        TextView telName = (TextView)view.findViewById(R.id.TelephoneName);
        TextView telNum = (TextView)view.findViewById(R.id.TelephoneNum);
        telImage.setImageResource(telephoneBook.getImageId());
        telName.setText(telephoneBook.getName());
        telNum.setText(telephoneBook.getTelphoneNum());
        return  view;
    }
}
