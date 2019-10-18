package com.example.helloworld.ContactsPackage;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.helloworld.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecycleAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<Contact> contacts;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contacts =  new ArrayList<Contact>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.contactRecycler);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new RecycleAdapter(this, contacts);
        recyclerView.setAdapter(mAdapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://digitradecontacs2019.azurewebsites.net/api/contacts";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                             for(int i = 0; i < response.length(); i++) {

                                 contact = new Contact();
                                 contact.firstName = response.getJSONObject(i).getString("firstName");
                                 contact.lastName = response.getJSONObject(i).getString("lasttName");
                                 contact.phoneNumber = response.getJSONObject(i).getInt("phoneNumber");
                                 contact.streetAddress = response.getJSONObject(i).getString("streetAddress");
                                 contact.city = response.getJSONObject(i).getString("city");
                                 contact.postalCode = response.getJSONObject(i).getInt("postalCode");
                                 contact.emailAddress = response.getJSONObject(i).getString("emailAddress");

                                 contacts.add(contact);

                                 Log.i("Contacts", contacts.toString());
                                 }
                        } catch(Exception e) {
                            //error
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                     Log.i("Contacts", "EI toimi");

                    }
                });

                AzureSingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


    }

}
