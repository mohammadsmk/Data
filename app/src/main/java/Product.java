package com.example.mohammad.data;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mohammad on 9/11/2018.
 */

public class Product
{
    String image;
    String title;
    String name;

    public Product(String image , String title , String name)
    {
        this.image = image;
        this.title = title;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

