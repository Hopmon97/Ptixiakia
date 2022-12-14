package com.example.welcometomyfuture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyNavDrawer extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nav_drawer);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

    }
    public void ClickMenu(View view)
    //open drawer
    {
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open Drawe Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    //close drawer
    {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public  void ClickHome(View view)
    {
        //Recreate activity
        recreate();
    }

    public void ClickDashBoard(View view)
   //redirect activity to dashboard
    {
        redirectActivity(this,Dashboard.class);
    }

    public void ClickAboutUs(View view)
    //redirect activity to about us
    {
        redirectActivity(this,AboutUs.class);
    }

    public void ClickLogout(View view)
    //close app
    {
        Logout(this);
    }

    public static void Logout(Activity activity)
            //Initialize alert dialog
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure you want to logout?");
        //Positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);

            }
        });
        //Negative answer
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Dismiss dialog
                dialog.dismiss();
            }
            //show dialog
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass)
    {
        //Initialize
        Intent intent = new Intent(activity,aClass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        //close  drawer
        closeDrawer(drawerLayout);
    }
}