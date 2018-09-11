package com.example.mohammad.data;

import android.app.Application;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    ListView lstProduct;
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstProduct = (ListView) findViewById(R.id.listView);

        CustomListAdapter adapter = new CustomListAdapter(getApplicationContext() ,R.layout.row_item , products);
        lstProduct.setAdapter(adapter);

        String url = "http://quocnguyen.16mb.com/products.json";
        final ProgressDialog Mdialog;
        Mdialog = new ProgressDialog(this);
        Mdialog.setMessage("Loading");
        Mdialog.setCancelable(false);
        Mdialog.show();

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray jsonArray = response.getJSONArray("products");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonProduct = jsonArray.getJSONObject(i);

                        String mName = jsonProduct.getString("name");
                        String mTitle = jsonProduct.getString("price");
                        String mImage = jsonProduct.getString("image");

                        products.add(new Product(mImage , mTitle , mName));
                    }

                    ((BaseAdapter) lstProduct.getAdapter()).notifyDataSetChanged();
                    Mdialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Mdialog.dismiss();
            }
        };

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET , url ,null , listener ,errorListener);
        AppController.getInstance().addToRequestQueue(req);
    }
}
