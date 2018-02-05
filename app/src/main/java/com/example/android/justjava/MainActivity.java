package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int price;
    int pricePerCup=5;
    int priceTopping1=1;
    int priceTopping2=2;
    String priceMessage;
    boolean hasTopping1;
    boolean hasTopping2;
    static final String SOME_VALUE1 = "quantity";
    static final String SOME_VALUE2 = "price";
    static final String SOME_VALUE3 = "priceMessage";

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save custom values into the bundle
        savedInstanceState.putInt(SOME_VALUE1, quantity);
        savedInstanceState.putInt(SOME_VALUE2, price);
        savedInstanceState.putString(SOME_VALUE3,priceMessage);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);}

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance
        quantity = savedInstanceState.getInt(SOME_VALUE1);
        price = savedInstanceState.getInt(SOME_VALUE2);
        priceMessage = savedInstanceState.getString(SOME_VALUE3);
        display(quantity);
        displayMessage("$"+price);
        displayMessage(priceMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view) {
        if(quantity==100)
        {Toast.makeText(this,"You cannot have more than 100 cups of coffee",Toast.LENGTH_LONG).show();
        return;}
        quantity++;
        display(quantity);
    }
    public void decrement(View view) {
        if(quantity==1)
        {Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_LONG).show();
            return;}
            quantity--;
        display(quantity);
    }
    public int calculatePrice(boolean hasTopping1,boolean hasTopping2)
    {if(hasTopping1==false && hasTopping2==false)
        price=quantity*pricePerCup;
    else if (hasTopping1==true && hasTopping2==true)
        price=quantity*(pricePerCup+priceTopping1+priceTopping2);
    else if (hasTopping1)
        price=quantity*(pricePerCup+priceTopping1);
    else
        price=quantity*(pricePerCup+priceTopping2);
    return price;}


    public void submitOrder(View view) {
        CheckBox topping1CheckBox = (CheckBox)findViewById(R.id.check_box_topping1);
        CheckBox topping2CheckBox = (CheckBox)findViewById(R.id.check_box_topping2);
         boolean hasTopping1 = topping1CheckBox.isChecked();
         boolean hasTopping2 = topping2CheckBox.isChecked();
         EditText nameField = (EditText)findViewById(R.id.name_field);
         String name= nameField.getText().toString();
        if(name.matches(""))
        {Toast.makeText(this,"You have to enter your name first!",Toast.LENGTH_LONG).show();
            return;}
        price=calculatePrice(hasTopping1,hasTopping2);
        priceMessage="Name: "+name;
        priceMessage=priceMessage+"\nTotal: $"+ price +"\nQuantity: "+ (quantity+(quantity/3))+" cups of coffee. "+"\nTopping:";
        if (quantity/3==1)
        priceMessage=priceMessage+(quantity/3)+" is free."+"\nToppings: ";
        else if (quantity/3>1)
            priceMessage=priceMessage+(quantity/3)+" are free."+"\nToppings: ";
        if(hasTopping1==false && hasTopping2==false)
            priceMessage=priceMessage+"\nYou haven't chosen a topping!";
        else if (hasTopping1==true && hasTopping2==true)
            priceMessage=priceMessage+getText(R.string.topping1)+" and "+getText(R.string.topping2);
        else if (hasTopping1)
            priceMessage=priceMessage+getText(R.string.topping1);
        else
            priceMessage=priceMessage+getText(R.string.topping2);
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}