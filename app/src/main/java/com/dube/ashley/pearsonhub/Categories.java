package com.dube.ashley.pearsonhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity
{
    List<CategoryHandler> catBooks;
    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    private LinearLayout click1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mdrawer = (DrawerLayout) findViewById(R.id.category_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mdrawer, R.string.open, R.string.close);

        mdrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getSupportActionBar().setElevation(0);

        catBooks=new ArrayList<>();

        catBooks.add(new CategoryHandler("Accounting",750,"Bcom","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.accounting));
        catBooks.add(new CategoryHandler("Arts and Culture",550,"BA","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.arts));
        catBooks.add(new CategoryHandler("English",650,"BA","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.english));
        catBooks.add(new CategoryHandler("Mathematics",450,"BSc","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.maths));
        catBooks.add(new CategoryHandler("Science",350,"BSc","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.science));
        catBooks.add(new CategoryHandler("Business Management",480,"Bcom","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.business));
        catBooks.add(new CategoryHandler("Arts and Culture",550,"BA","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.arts));
        catBooks.add(new CategoryHandler("English",650,"BA","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.english));
        catBooks.add(new CategoryHandler("Mathematics",450,"BSc","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.maths));
        catBooks.add(new CategoryHandler("Science",350,"BSc","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.science));
        catBooks.add(new CategoryHandler("Business Management",480,"Bcom","AshDee",7,"844713587412","0838642247","Ashley",R.drawable.business));


        RecyclerView rv=(RecyclerView) findViewById(R.id.recyclerview_id);
        rv.setNestedScrollingEnabled(false);
        RecyclerViewAdapter rva=new RecyclerViewAdapter(this,catBooks);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(rva);

        mToggle.setDrawerArrowDrawable(new Categories.HamburgerDrawable(this));

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                User curUsers =snapshot.getValue(User.class);
                assert curUsers != null;
                NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
                View header = navigationView.getHeaderView(0);
                TextView tv = (TextView) header.findViewById(R.id.id_nav_header);
                tv.setText( curUsers.getFirstname()+" "+curUsers.getLastname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(Categories.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        mdrawer = (DrawerLayout) findViewById(R.id.category_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mdrawer, R.string.open, R.string.close);

        mdrawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        getSupportActionBar().setElevation(0);

        mToggle.setDrawerArrowDrawable(new HamburgerDrawable(this));

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Intent home = new Intent(Categories.this, HomeActivity.class);
                        startActivity(home);
                        return true;
                    case R.id.menu_search:
                        Intent search = new Intent(Categories.this, SearchBook.class);
                        startActivity(search);
                        return true;
                    case R.id.menu_sell:
                        Intent sell = new Intent(Categories.this, SellBook.class);
                        startActivity(sell);
                        return true;
                    case R.id.menu_wishlist:
                        Intent wishlist = new Intent(Categories.this, Wishlist.class);
                        startActivity(wishlist);
                        return true;
                    case R.id.menu_listings:
                        Intent listings = new Intent(Categories.this, Listings.class);
                        startActivity(listings);
                        return true;
                    case R.id.menu_logout:
                        Intent logout = new Intent(Categories.this, LoginActivity.class);
                        startActivity(logout);
                        return true;
                }
                return true;
            }
        });

//        click1 = (LinearLayout) findViewById(R.id.card_view_select);
//        click1.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent info = new Intent(Categories.this, Book.class);
//                        startActivity(info);
//                    }
//                });
    }


    public class HamburgerDrawable extends DrawerArrowDrawable {

        public HamburgerDrawable(Context context) {
            super(context);
            setColor(context.getResources().getColor(R.color.colorPrimary));
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);

            setBarLength(100.0f);
            setBarThickness(16.0f);
            setGapSize(20.0f);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}