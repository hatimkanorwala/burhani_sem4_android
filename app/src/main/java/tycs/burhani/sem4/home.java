package tycs.burhani.sem4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class home extends AppCompatActivity {
    SharedPreferences preferences;
    public static final String myPrefs = "myPrefs";
    Button _home_btn_logout;
    String username;

    protected void attachBaseContext(Context base) {
        // Load saved language and apply locale before super.attachBaseContext
        SharedPreferences prefs = base.getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        String lang = prefs.getString("language", "en");  // Default to "en" if not set
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration(base.getResources().getConfiguration());
        config.setLocale(locale);
        super.attachBaseContext(base.createConfigurationContext(config));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        preferences = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("darkMode", false);
        if(isDarkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        _home_btn_logout = findViewById(R.id.home_btn_logout);

        if (preferences.getAll().containsKey("isLogin")) {
            if (preferences.getString("isLogin", "").equals("1")) {
                if (preferences.getAll().containsKey("username")) {
                    username = preferences.getString("username", "");
                }
                else{
                    redirectToLogin();
                }
            }
            else{
                redirectToLogin();
            }
        }
        else{
            redirectToLogin();
        }
        _home_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("isLogin");
                editor.remove("username");
                editor.commit();
                Intent i = new Intent(home.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
        if(itemid == R.id.menu_home){
            Toast.makeText(this, "Home Menu Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(itemid == R.id.menu_aboutUs){
            Toast.makeText(this, "About Us Menu Clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(home.this, aboutus.class);
            startActivity(i);
        }
        else if(itemid == R.id.menu_contactUs){
            Toast.makeText(this, "Contact Us Menu Clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(home.this,Settings.class);
            startActivity(i);
        }
        else if(itemid == R.id.menu_logout){
            Toast.makeText(this, "Logout Menu Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void redirectToLogin(){
        Intent i = new Intent(home.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}