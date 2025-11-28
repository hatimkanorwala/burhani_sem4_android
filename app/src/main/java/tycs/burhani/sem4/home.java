package tycs.burhani.sem4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {
    SharedPreferences preferences;
    public static final String myPrefs = "myPrefs";
    String username;
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
    }
    public void redirectToLogin(){
        Intent i = new Intent(home.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}