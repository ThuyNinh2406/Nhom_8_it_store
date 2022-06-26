package com.example.editandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.editandroid.model.Cart;
import com.example.editandroid.model.SanPhamMoi;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Product_detail_activity extends AppCompatActivity {
    //Phần giỏ hàng
    TextView tensp,giasp,mota;
    Button btnthem;
    ImageView imghinhanh;
    Toolbar toolbar;

    //
    int soLuong = 1;
    TextView product_name,product_price,product_description,tv_Display;
    ImageView product_image, arrow_back,showCart;
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
       btnthem = findViewById(R.id.btnthemvaogiohang);
       showCart = findViewById(R.id.showCart);

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
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] giaStr = product.getGiasp().split(" ");
                String[] giaNumber = giaStr[0].split(",");
                String money = "";
                for(String gia : giaNumber){
                    money =money + gia;
                }
                Cart cart = new Cart(product.getId(),product.getHinhanh(),product.getTensp(),Long.valueOf(money),soLuong);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                reference.child("Carts")
                        .child(uid)
                        .child(String.valueOf(product.getId()))
                        .setValue(cart, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Product_detail_activity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        finish();
                    }
        });
        showCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_detail_activity.this,CartActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("idProduct",product.getId());
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }


}