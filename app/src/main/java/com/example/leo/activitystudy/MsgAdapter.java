package com.example.leo.activitystudy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.recyclerview.*;
import android.support.v7.widget.RecyclerView.Adapter;

import org.w3c.dom.Text;

import java.util.List;

// TODO: 2019/1/15 RecylerView适配器的使用 
//public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
//    private List<Msg> pmsgList;
//
//    static class ViewHolder extends RecyclerView.ViewHolder
//    {
//        LinearLayout leftLayout;
//        LinearLayout rightLayout;
//
//        TextView leftMsg;
//        TextView rightMsg;
//        public ViewHolder(View view)
//        {
//            super(view);
//            leftLayout = (LinearLayout)view.findViewById(R.id.leftMsgLayout);
//            rightLayout = (LinearLayout)view.findViewById(R.id.rightMsgLayout);
//            leftMsg = (TextView)view.findViewById(R.id.leftMsg);
//            rightMsg = (TextView) view.findViewById(R.id.rightMsg);
//        }
//    }
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Msg msg= pmsgList.get(position);
//        if(msg.getType()==Msg.TYPE_RECEIVED)
//        {
//            holder.leftLayout.setVisibility(View.VISIBLE);
//            holder.rightLayout.setVisibility(View.GONE);
//            holder.leftMsg.setText(msg.getContent());
//        }
//        else if(msg.getType()==Msg.TYPE_SEND)
//        {
//
//            holder.rightLayout.setVisibility(View.VISIBLE);
//            holder.leftLayout.setVisibility(View.GONE);
//            holder.rightMsg.setText(msg.getContent());
//        }
//    }
//    public MsgAdapter(List<Msg> msgList)
//    {
//        pmsgList = msgList;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.msg_item),parent,false);
//        return new ViewHolder(view);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return pmsgList.size();
//    }
//}
// TODO: 2019/1/15 RecylerView适配器第二种写法的使用
public class MsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<Msg> pmsgList;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftLayout;
        LinearLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;
        public ViewHolder(View view)
        {
            super(view);
            leftLayout = (LinearLayout)view.findViewById(R.id.leftMsgLayout);
            rightLayout = (LinearLayout)view.findViewById(R.id.rightMsgLayout);
            leftMsg = (TextView)view.findViewById(R.id.leftMsg);
            rightMsg = (TextView) view.findViewById(R.id.rightMsg);
        }
    }

    /**
     *
     * @param holder
     * @param position
     */
    public void onBindMsg(@NonNull ViewHolder holder, int position)
    {
        Msg msg= pmsgList.get(position);
        if(msg.getType()==Msg.TYPE_RECEIVED)
        {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }
        else if(msg.getType()==Msg.TYPE_SEND)
        {

            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder)
        {
            onBindMsg((ViewHolder) holder,position);
        }
    }
    public MsgAdapter(List<Msg> msgList)
    {
        pmsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.msg_item),parent,false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return pmsgList.size();
    }
}