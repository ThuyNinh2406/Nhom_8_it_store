package com.example.editandroid;

import android.widget.CheckBox;
import android.widget.TextView;

import com.example.editandroid.model.Cart;
// định nghĩa hàm
public interface IClickCheckBoxItem {
    void onClickCheckBoxItem(Cart cart, CheckBox b);
    // giảm soluong
    void onClickDecrementItem(TextView amount,Cart cart,CheckBox b);
    // tăng soluong
    void onClickIncrementItem(TextView amount,Cart cart,CheckBox b);
    void onClickDeleteItem(Cart cart);
}
