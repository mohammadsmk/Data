package com.example.mohammad.data;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohammad on 9/11/2018.
 */

public class CustomListAdapter extends ArrayAdapter<Product>
{

    Context context;
    int resource;
    ArrayList<Product> products;

    public CustomListAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Product> products)
    {
        super(context, resource, products);
        this.context = context;
        this.resource = resource;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_item , null , true);
        }

        Product product = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
        Picasso.with(context).load(product.getImage()).into(imageView);

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        txtTitle.setText(product.getTitle());

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(product.getName());

        return convertView;
    }
}
