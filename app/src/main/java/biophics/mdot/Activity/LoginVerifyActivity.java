package biophics.mdot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import biophics.mdot.R;
import biophics.mdot.Utility.AlertDialogUtil;

public class LoginVerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_veify);

        initValue();
        initInstance();

        sendVerificationCode();
    }

    private FirebaseAuth mAuth;
    private String phoneNumber;
    private String verificationId;

    private void initValue() {
        mAuth = FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("phone_number");
    }

    private EditText editText;

    private void initInstance() {
        editText = findViewById(R.id.editTextCode);

        findViewById(R.id.buttonSignIn).setOnClickListener((v) -> {
            String code = editText.getText().toString().trim();

            if (code.isEmpty() || code.length() < 6) {
                editText.setError(getString(R.string.error_verify_code));
                editText.requestFocus();
                return;
            }
            verifyCode(code);
        });
    }

    private void sendVerificationCode() {
        AlertDialogUtil.showLoading(this);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                10,
                TimeUnit.SECONDS,
//                TaskExecutors.MAIN_THREAD,
                this,
                mCallBack
        );
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginVerifyActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        //TODO
                        Toast.makeText(LoginVerifyActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            LoginVerifyActivity.this.verificationId = verificationId;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            //TODO
            Toast.makeText(LoginVerifyActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    };

}