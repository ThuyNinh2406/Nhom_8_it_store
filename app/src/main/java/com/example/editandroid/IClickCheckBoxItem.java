package com.example.editandroid;

import android.widget.CheckBox;
import android.widget.TextView;

import com.example.editandroid.model.Cart;

public interface IClickCheckBoxItem {
    void onClickCheckBoxItem(Cart cart, CheckBox b);
    void onClickDecrementItem(TextView amount,Cart cart,CheckBox b);
    void onClickIncrementItem(TextView amount,Cart cart,CheckBox b);
    void onClickDeleteItem(Cart cart);
}
