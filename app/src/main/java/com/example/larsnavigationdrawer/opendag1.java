package com.example.larsnavigationdrawer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class opendag1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv_text;
    TextView Whr_text;
    TextView WhrC_text;
    TextView P_text;
    TextView PC_text;
    Button btnShareLink;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    Button buttonWherecom;
    Button buttonProgramcom;
    Button buttonTraildaycom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_opendag1);

        btnShareLink = (Button) findViewById(R.id.btnShareLink);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);





        buttonWherecom = (Button) findViewById(R.id.buttonWherecom);
        buttonProgramcom = (Button) findViewById(R.id.buttonProgramcom);
        buttonTraildaycom = (Button) findViewById(R.id.buttonTraildaycom);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }








        buttonWherecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWherecominfo();
            }
        });
        buttonProgramcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProgramcominfo();
            }
        });
        buttonTraildaycom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTraildaycominfo();
            }
        });







        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(opendag1.this, "Share successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(opendag1.this, "Share cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(opendag1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("Go visit this open day!")
                        .setContentUrl(Uri.parse("https://www.hogeschoolrotterdam.nl/samenwerking/instituten/instituut-voor-communicatie-media-en-informatietechnologie/introductie/"))
                        .build();
                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(linkContent);
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_text = (TextView) findViewById(R.id.tv_text);
        String text = "";
        try{
            InputStream is = getAssets().open("filetest.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        tv_text.setText(text);


        Whr_text = (TextView) findViewById(R.id.Where);
        String Where = "";
        try{
            InputStream is = getAssets().open("Where.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            Where = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        Whr_text.setText(Where);

        WhrC_text = (TextView) findViewById(R.id.WhereCommunicatie);
        String WhereCommunicatie = "";
        try{
            InputStream is = getAssets().open("WhereCommunicatie.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            WhereCommunicatie = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        WhrC_text.setText(WhereCommunicatie);

        P_text = (TextView) findViewById(R.id.Program);
        String Program = "";
        try{
            InputStream is = getAssets().open("Program.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            Program = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        P_text.setText(Program);

        PC_text = (TextView) findViewById(R.id.ProgramCommunicatie);
        String ProgramCommunicatie = "";
        try{
            InputStream is = getAssets().open("ProgramCommunicatie.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            ProgramCommunicatie = new String(buffer);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        PC_text.setText(ProgramCommunicatie);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    public void Browser1(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hogeschoolrotterdam.nl/opleidingen/bachelor/communicatie/voltijd/"));
        startActivity(browserIntent);
    }





    public void openWherecominfo(){
        Intent intent = new Intent(this, communicatiewheremoreinfo.class);
        startActivity(intent);
    }
    public void openProgramcominfo(){
        Intent intent = new Intent(this, communicatieprogrammoreinfo.class);
        startActivity(intent);
    }
    public void openTraildaycominfo(){
        Intent intent = new Intent(this, communicatietraildaysmoreinfo.class);
        startActivity(intent);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opendag1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_mainmenu) {
            Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_Studie1) {
            Intent intent = new Intent(this, opendag1.class);
            startActivity(intent);
        } else if (id == R.id.nav_Studie2) {
            Intent intent = new Intent(this, opendag2.class);
            startActivity(intent);
        } else if (id == R.id.nav_Studie3) {
            Intent intent = new Intent(this, opendag3.class);
            startActivity(intent);
        } else if (id == R.id.nav_Studie4) {
            Intent intent = new Intent(this, opendag4.class);
            startActivity(intent);
        } else if (id == R.id.nav_Studie5) {
            Intent intent = new Intent(this, opendag5.class);
            startActivity(intent);
        } else if (id == R.id.nav_contactpage) {
            Intent intent = new Intent(this, contactpage.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

