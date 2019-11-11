package com.example.helloworld.ContactsPackage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.GridItemViewHolder> implements Filterable {

    private Context context;
    private List<Contact> contacts;
    private List<Contact> contactsFull;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public RecycleAdapter(Context context, List<Contact> contacts) {

        this.context = context;
        this.contacts = contacts;
       this.contactsFull = new ArrayList<>(contacts); {
        }

        Log.i("recycler contact", contactsFull.toString());
    }


    @Override

    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        GridItemViewHolder viewHolder = new GridItemViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }


    @Override

    public void onBindViewHolder(GridItemViewHolder holder, final int position) {
        Contact contact = contacts.get(position);

        holder.firstName.setText(contact.getFirstName()+ " " + contact.getLastName());
        // holder.lastName.setText(contact.getLastName());

       holder.emailAddress.setText(contact.getEmailAddress());
        holder.phoneNumber.setText(contact.getPhoneNumber());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"The position is:"+ position,Toast.LENGTH_SHORT).show();
            }
        });

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
         TextView firstName, phoneNumber, emailAddress;
         RecycleAdapter mAdapter;
         CardView cardView;
         LinearLayout cardContent;



        public GridItemViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardViewId);
            firstName = (TextView) itemView.findViewById(R.id.firstName);

            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);
            emailAddress = (TextView) itemView.findViewById(R.id.emailAddress);

            cardContent = (LinearLayout) itemView.findViewById(R.id.card_content);

            itemView.setOnClickListener(this);
        }


        @Override

        public void onClick(View v) {

            mAdapter.onItemHolderClick(this);

            if(cardContent.getVisibility() == View.VISIBLE)
            {
                cardContent.setVisibility(View.GONE);
            } else {
                cardContent.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(contactsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Contact contact : contactsFull) {
                    if(contact.getFirstName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(contact);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contacts.clear();
            contacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
