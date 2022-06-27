package com.example.editandroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.editandroid.IClickCheckBoxItem;
import com.example.editandroid.R;
import com.example.editandroid.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    List<Cart> cartList;
    int soLuong = 1;
    IClickCheckBoxItem iClickCheckBoxItem;
//
    public CartAdapter(Context context, List<Cart> cartList,IClickCheckBoxItem iClickCheckBoxItem) {
        // đối tượng của lớp đấy gán nó bằng cái tham số truyền vào
        this.context = context;
        this.cartList = cartList;
        this.iClickCheckBoxItem = iClickCheckBoxItem;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // gán cái layout của cartadapter bằng cái cart item ở bên layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        //truyền cái view vào cartviewholder
        return new CartViewHolder(view);
    }

    @Override
    // dùng để set lại dl mình cần
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
       // khai báo cart , lấy đúng vị trí của cart trong cartlisst
        Cart cart = cartList.get(position);

        if(cart == null){
            return;
        }
        holder.productPrice.setText(String.valueOf(cart.getProductPrice()));
        holder.productName.setText(cart.getProductName());
        holder.productAmount.setText(String.valueOf(cart.getAmount()));
        Glide.with(context).load(cart.getProductImage()).into(holder.productImage);

        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickCheckBoxItem.onClickIncrementItem(holder.productAmount,cart, holder.checkBox);
            }
        });
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi đến hàm onClickDecrementItem trong interface
                iClickCheckBoxItem.onClickDecrementItem(holder.productAmount,cart, holder.checkBox);
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickCheckBoxItem.onClickCheckBoxItem(cart, holder.checkBox);
            }
        });
        holder.delete_product.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iClickCheckBoxItem.onClickDeleteItem(cart);


            }
        });

    }

    @Override
    public int getItemCount() {
        if(cartList != null){
            return cartList.size();
        }
        return 0;
    }
    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        ImageView increment, decrement,delete_product;
        TextView productName,productPrice,productAmount;
        CheckBox checkBox;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productAmount = itemView.findViewById(R.id.item_giohang_soluong);
            productImage = itemView.findViewById(R.id.item_giohang_image);
            productName = itemView.findViewById(R.id.item_giohang_tensp);
            productPrice = itemView.findViewById(R.id.item_giohang_gia);
            increment = itemView.findViewById(R.id.item_giohang_cong);
            decrement = itemView.findViewById(R.id.item_giohang_tru);
            checkBox = itemView.findViewById(R.id.checked);
            delete_product = itemView.findViewById(R.id.delete_product);
        }
    }
}
