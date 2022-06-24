package com.example.editandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.editandroid.model.SanPhamMoi;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Product_detail_activity extends AppCompatActivity {
    int soLuong = 1;
    TextView product_name,product_price,product_description,tv_Display;
    ImageView product_image, arrow_back;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    Button increment, decrement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        tv_Display = findViewById(R.id.display);
       product_description = findViewById(R.id.product_description);
       product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
       product_image = findViewById(R.id.img_product);
       arrow_back= findViewById(R.id.arrow_back);
       Bundle bundle= getIntent().getExtras();
       if (bundle==null){
           return;
       }
        SanPhamMoi product= (SanPhamMoi) bundle.get("product");
       product_name.setText(product.getTensp());
       product_price.setText(product.getGiasp().toString());
        Glide.with(getApplicationContext()).load(product.getHinhanh()).into(product_image);
        product_description.setText(product.getMota());
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong+=1;
//
                tv_Display.setText(String.valueOf(soLuong));
                decrement.setEnabled(true);

            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong-=1;
                if (soLuong==0){
                    decrement.setEnabled(false);
                }
                else{
                    decrement.setEnabled(true);

                }

                tv_Display.setText(String.valueOf(soLuong));

            }
        });
                arrow_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}