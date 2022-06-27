package com.example.editandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.editandroid.adapter.CartAdapter;
import com.example.editandroid.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// khia báo biến cần dùng
public class CartActivity extends AppCompatActivity {
    private RecyclerView rcv_cart;
    private CartAdapter cartAdapter;
    private List<Cart> cartList;
    private TextView totalMoney,cartEmpty;
    private ImageView arrow_back;
    private LinearLayout cartPay;

    private int idProduct;
    private long money;
    @Override
    // để hiển thị
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // ánh xạ
        rcv_cart = findViewById(R.id.rcv_cart);
        totalMoney = findViewById(R.id.txttongtien);
        cartEmpty = findViewById(R.id.cartEmpty);
        cartPay = findViewById(R.id.cartPay);
        arrow_back = findViewById(R.id.arrow_back);
        // khai báo bundle và lấy dl
        Bundle bundle = getIntent().getExtras();
        idProduct = (int) bundle.get("idProduct");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_cart.setLayoutManager(linearLayoutManager);
        cartList = new ArrayList<>();
        if(cartList.size() == 0)
        {
            cartEmpty.setVisibility(View.VISIBLE);
            cartPay.setVisibility(View.GONE);
        }
        cartAdapter = new CartAdapter(getApplicationContext(), cartList, new IClickCheckBoxItem() {
            @Override
            public void onClickCheckBoxItem(Cart cart, CheckBox b) {
//                if(cart.getAmount() == 0){
//                    b.setChecked(false);
//                    return;
//                }
//                else{
//                    if(b.isChecked()){
//                        money += (cart.getAmount()*cart.getProductPrice());
//                        totalMoney.setText(String.valueOf(money));
//                    }
//                    else{
//                        money -= (cart.getAmount()*cart.getProductPrice());
//                        totalMoney.setText(String.valueOf(money));
//                    }
//                }
            }

            @Override
            public void onClickDecrementItem(TextView amount,Cart cart,CheckBox b) {
                int soluong = cart.getAmount();
                soluong--;
                if(soluong < 0){
                  return;
                }
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference.child("Carts").child(uid).child(String.valueOf(cart.getId())).child("amount").setValue(soluong);
//                if(cart.getAmount() == 0){
//                    b.setChecked(false);
//                    return;
//                }
//                else{
//                    if(b.isChecked()){
//                        money = 0;
//                        money += (soluong*cart.getProductPrice());
//                        totalMoney.setText(String.valueOf(money));
//                    }
//                }

            }
            @Override
            public void onClickIncrementItem(TextView amount ,Cart cart,CheckBox b) {

                int soluong = cart.getAmount();
                soluong++;

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference();
                reference.child("Carts").child(uid).child(String.valueOf(cart.getId())).child("amount").setValue(soluong);

//                if(b.isChecked()){
//                    money = 0;
//                    money += (soluong*cart.getProductPrice());
//                    totalMoney.setText(String.valueOf(money));
//                }
            }
            @Override
            public  void onClickDeleteItem(Cart cart){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setMessage("Bạn có muốn bỏ sản phẩm này ra khỏi giỏ hàng?");

                alertDialog.setNegativeButton("Không", null);
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference();
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        reference.child("Carts").child(uid).child(String.valueOf(cart.getId())).removeValue();
                    }
                });
                alertDialog.show();
            }
        });
        // set adpter cho recycle view
        rcv_cart.setAdapter(cartAdapter);

        getListProductInTheCart();

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {


           }
       },400);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // lấy cái product trong carts trên firebase
    private void getListProductInTheCart(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("Carts").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            // nếu dl tồn tại
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartList.clear();
                money = 0;
                //lấy list product trên carts về và thêm vào cart list
                for(DataSnapshot data : snapshot.getChildren()){
                    // khai báo cart và gán dl trả về từ database về cho cart
                    Cart cart = data.getValue(Cart.class);
                    cartList.add(cart);
                    money+= cart.getAmount()*cart.getProductPrice();

                }
                totalMoney.setText(String.valueOf(money));
                if(cartList.size()>0){
                    cartEmpty.setVisibility(View.GONE);
                    cartPay.setVisibility(View.VISIBLE);
                }
// lắng nghe sự thay đổi của dl nếu nó thay đổi thì nó sẽ update lại cart adapter
                cartAdapter.notifyDataSetChanged();
            }
//
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}