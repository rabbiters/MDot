package biophics.mdot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import biophics.mdot.R;

public class LoginByPhoneActivity extends AppCompatActivity {

    private static final String THAILAND_CODE = "+66";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_phone);

        initValue();
        initInstance();
    }

    private void initValue() {
//        GetSession.setValue("Device_Imei", GetImei.getUniqueIMEIId(this));
//        Log.w(TAG, "Imei::::" + GetSession.getValue("Device_Imei"));
    }

    private EditText editText;

    private void initInstance() {
        editText = findViewById(R.id.editTextPhone);

        findViewById(R.id.btnSubmitPhoneNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                String phoneNumber = THAILAND_CODE + number;

                Intent intent = new Intent(LoginByPhoneActivity.this, LoginVerifyActivity.class);
                intent.putExtra("phone_number", phoneNumber);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
