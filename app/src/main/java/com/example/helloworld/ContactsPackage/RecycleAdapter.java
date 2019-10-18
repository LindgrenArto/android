package com.example.helloworld.ContactsPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.List;



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.GridItemViewHolder> {

    private Context context;

    private List<Contact> contacts;

    private AdapterView.OnItemClickListener mOnItemClickListener;



    public RecycleAdapter(Context context, List<Contact> contacts) {

        this.context = context;

        this.contacts = contacts;

    }


    @Override

    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.contact, parent, false);

        return new GridItemViewHolder(itemView, this);

    }


    @Override

    public void onBindViewHolder(GridItemViewHolder holder, int position) {

        //

    }




    @Override

    public int getItemCount() {

        return contacts.size();
    }



    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {

        this.mOnItemClickListener = onItemClickListener;

    }



    private void onItemHolderClick(GridItemViewHolder itemHolder) {

        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(null, itemHolder.itemView,

                    itemHolder.getAdapterPosition(), itemHolder.getItemId());

        }

    }

    public class GridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitle, mPosition;

        public RecycleAdapter mAdapter;



        public GridItemViewHolder(View itemView, RecycleAdapter mAdapter) {

            super(itemView);

            this.mAdapter = mAdapter;

            mTitle = (TextView) itemView.findViewById(R.id.item_title);

            mPosition = (TextView) itemView.findViewById(R.id.item_position);

            itemView.setOnClickListener(this);

        }


        @Override

        public void onClick(View v) {

            mAdapter.onItemHolderClick(this);

        }
}
