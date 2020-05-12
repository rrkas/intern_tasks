package com.rssoft.intern_task0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.btn_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "+91 7008683249"));
                if (ActivityCompat.checkSelfPermission(WelcomeActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String[] perm = {Manifest.permission.CALL_PHONE};
                    ActivityCompat.requestPermissions(WelcomeActivity.this, perm
                            , 102);
                    return;
                }
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_browser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.spectrumcet.com"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://feed")));
                } catch (Exception e) {
                    Toast.makeText(WelcomeActivity.this, "Facebook is not installed in this " +
                            "phone!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager manager = getPackageManager();
                try {
                    Intent intent = manager.getLaunchIntentForPackage("com.instagram.android");
                    if (intent == null) {
                        throw new PackageManager.NameNotFoundException();
                    }
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(WelcomeActivity.this, "Instagram not installed in this Phone!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });
    }
}
