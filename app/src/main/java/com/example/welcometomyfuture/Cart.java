package com.example.welcometomyfuture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.welcometomyfuture.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Cart extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    RecyclerView recview;
    TextView rateview;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();

        bottom_navigation=findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),Geoponos.class));
                        return true;
                    case R.id.nav_products:
                        startActivity(new Intent(getApplicationContext(),ProductsActivity.class));
                        return true;
                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(),Cart.class));
                        return true;

                }
                return false;
            }
        });
        rateview=findViewById(R.id.rateview);
        getroomdata();
    }

    public void getroomdata()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        myadapter adapter=new myadapter(products, rateview);
        recview.setAdapter(adapter);

        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());

        rateview.setText("Total Amount : INR "+sum);
    }
}