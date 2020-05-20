package com.rssoft.intern_task0;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.rssoft.intern_task0.fragments.AcademicFragment;
import com.rssoft.intern_task0.fragments.HomeFragment;
import com.rssoft.intern_task0.fragments.LibraryFineCalculatorFragment;
import com.rssoft.intern_task0.fragments.SyllabusFragment;
import com.rssoft.intern_task0.fragments.TimeTableFragment;
import com.rssoft.intern_task0.fragments.ToDoFragment;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        container = findViewById(R.id.frame_container);
        getSupportFragmentManager().beginTransaction().replace(container.getId(),
                new HomeFragment()).commit();
        setTitle("Important Apps");
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        TextView usermail = navigationView.getHeaderView(0).findViewById(R.id.user_mail);
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            usermail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        findViewById(R.id.btn_msg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
//                startActivity(intent);
//            }
//        });
//
//        findViewById(R.id.btn_phone).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:" + "+91 7008683249"));
//                if (ActivityCompat.checkSelfPermission(WelcomeActivity.this,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    String[] perm = {Manifest.permission.CALL_PHONE};
//                    ActivityCompat.requestPermissions(WelcomeActivity.this, perm
//                            , 102);
//                    return;
//                }
//                startActivity(intent);
//            }
//        });
//
//        findViewById(R.id.btn_browser).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.spectrumcet.com"));
//                startActivity(intent);
//            }
//        });
//
//        findViewById(R.id.btn_fb).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://feed")));
//                } catch (Exception e) {
//                    Toast.makeText(WelcomeActivity.this, "Facebook is not installed in this " +
//                            "phone!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        findViewById(R.id.btn_insta).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PackageManager manager = getPackageManager();
//                try {
//                    Intent intent = manager.getLaunchIntentForPackage("com.instagram.android");
//                    if (intent == null) {
//                        throw new PackageManager.NameNotFoundException();
//                    }
//                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                    startActivity(intent);
//                } catch (PackageManager.NameNotFoundException e) {
//                    Toast.makeText(WelcomeActivity.this, "Instagram not installed in this Phone!", Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_imp_apps:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new HomeFragment()).commit();
                setTitle("Important Apps");
                break;
            case R.id.menu_timetable:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new TimeTableFragment()).commit();
                setTitle("Time Table");
                break;
            case R.id.menu_syllabus:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new SyllabusFragment()).commit();
                setTitle("Syllabus");
                break;
            case R.id.menu_academic:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new AcademicFragment()).commit();
                setTitle("Academic Section");
                break;
            case R.id.menu_library_fine_calc:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new LibraryFineCalculatorFragment()).commit();
                setTitle("Library Fine Calculator");
                break;
            case R.id.menu_todo:
                getSupportFragmentManager().beginTransaction().replace(container.getId(),
                        new ToDoFragment()).commit();
                setTitle("To-Do List");
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
        }
        drawerLayout.closeDrawers();
        return true;
    }
}
