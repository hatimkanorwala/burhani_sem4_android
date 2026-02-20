package tycs.burhani.sem4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class newDashboard extends AppCompatActivity {
    TextView _userAuthID,_userAuthEmail;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    Button _userAuthLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        _userAuthID = findViewById(R.id.userAuthID);
        _userAuthEmail = findViewById(R.id.userAuthEmail);
        _userAuthLogout = findViewById(R.id.userAuthLogout);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            _userAuthID.setText("User ID: " + currentUser.getUid());
            _userAuthEmail.setText("User Email: " + currentUser.getEmail());
        }
        _userAuthLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(newDashboard.this, "User Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(newDashboard.this,newLogin.class);
                startActivity(i);
                finish();
            }
        });
    }
}