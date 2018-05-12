package com.example.suhail.loginattempt1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.suhail.loginattempt1.R;

public class UserSettingsActivity extends AppCompatActivity {

    TextView verify_oldpass;
    CardView updatebuttonCardview;
    TextView wrong_password;
    TextView password_match;
    TextView password_mismatch;
    TextView change_password;
    EditText contact_user;
    EditText emial_user;
    EditText old_password;
    EditText new_password;
    EditText confirm_password;
    android.support.v7.widget.Toolbar toolbar;
    String key = "IsfromUserSettings";
    String value = "true";
    LinearLayout reset_password_1;
    LinearLayout reset_password_2;
    LinearLayout reset_password_3;
Button updatebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);


        //<!-----------------------------TOOLBAR------------------------------------------------
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_user_settings);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User Setting");
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//--------------------------------------------------------------------------------------------->


        //<!--------------------------------------------Views-----------------------------------------

        updatebutton= (Button) findViewById(R.id.update_button);
        updatebuttonCardview = (CardView) findViewById(R.id.update_password_button);
        verify_oldpass = (TextView) findViewById(R.id.check_old_pass_user_settings);
        wrong_password = (TextView) findViewById(R.id.wrong_password_user_settings);
        password_match = (TextView) findViewById(R.id.pass_smatch_user_settings);
        password_mismatch = (TextView) findViewById(R.id.pass_mismatch_user_settings);
        old_password = (EditText) findViewById(R.id.old_password_user_setting);
        new_password = (EditText) findViewById(R.id.new_password_user_setting);
        confirm_password = (EditText) findViewById(R.id.confirm_new_password_user_setting);
        contact_user = (EditText) findViewById(R.id.put_contact_user_settings);
        emial_user = (EditText) findViewById(R.id.put_email_user_settings);
        reset_password_1 = (LinearLayout) findViewById(R.id.reset_password_1);
        reset_password_2 = (LinearLayout) findViewById(R.id.reset_password_2);
        reset_password_3 = (LinearLayout) findViewById(R.id.reset_password_3);

//--------------------------------------------------------------------------------------------------------->
      updatebuttonCardview.setVisibility(View.GONE);
        HideChnagePasswordWarnings(4);
        HideResetPasswordView(true);
        ChangePasswordClickListner();
        VerifyOldPassListner();
        UpdatePassButtonClickListner();
    }

    private void VerifyOldPassListner() {

        verify_oldpass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ChangePassword();
                    }
                }
        );

    }

    private void HideChnagePasswordWarnings(int id) {

        switch (id) {

            case 1:
                wrong_password.setVisibility(View.GONE);
                break;
            case 2:
                password_mismatch.setVisibility(View.GONE);
                break;
            case 3:
                password_match.setVisibility(View.GONE);
                break;
            case 4:
                wrong_password.setVisibility(View.GONE);
                password_mismatch.setVisibility(View.GONE);
                password_match.setVisibility(View.GONE);
                break;

            case 5:
                verify_oldpass.setVisibility(View.GONE);
                break;
        }
    }

    private void ShowChnagePasswordWarnings(int id) {

        switch (id) {

            case 1:
                wrong_password.setVisibility(View.VISIBLE);
                break;
            case 2:
                password_mismatch.setVisibility(View.VISIBLE);
                break;
            case 3:
                password_match.setVisibility(View.VISIBLE);
                break;
            case 4:
                wrong_password.setVisibility(View.VISIBLE);
                password_mismatch.setVisibility(View.VISIBLE);
                password_match.setVisibility(View.VISIBLE);
                break;

            case 5:
                verify_oldpass.setVisibility(View.VISIBLE);
        }


    }

    private void ChangePasswordClickListner() {

        change_password = (TextView) findViewById(R.id.change_password_user_setting);
        change_password.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (change_password.getText().toString().equals("Change Password"))

                        {
                            HideResetPasswordView(false);
                            change_password.setText("UNDO");

                        } else {


                            HideResetPasswordView(true);
                            change_password.setText("Change Password");
                            UpdatePasswordButton(1);
                       ShowChnagePasswordWarnings(5);

                        }
                    }
                }
        );

    }


    void HideResetPasswordView(Boolean B) {

        if (B) {
            reset_password_2.setVisibility(View.GONE);
            reset_password_1.setVisibility(View.GONE);
            reset_password_3.setVisibility(View.GONE);
        } else {
            //  reset_password_2.setVisibility(View.VISIBLE);
            reset_password_1.setVisibility(View.VISIBLE);
            //reset_password_3.setVisibility(View.VISIBLE);
        }
    }


    void ChangePassword()

    {

        UpdatePasswordButton(1);

        String old_password_text = old_password.getText().toString();


        if (CheckOldPasswordIsTrue(old_password_text)) {

             ///old_password.setClickable(false);

             HideChnagePasswordWarnings(5);

            reset_password_2.setVisibility(View.VISIBLE);
            reset_password_3.setVisibility(View.VISIBLE);


            confirm_password.addTextChangedListener(
                    new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {


                            String new_password_text = new_password.getText().toString();
                            String confirm_password_text = confirm_password.getText().toString();

                            if (new_password_text.equals(confirm_password_text)) {
                                //------------put code to change password here



                                HideChnagePasswordWarnings(2);
                                ShowChnagePasswordWarnings(3);
                                UpdatePasswordButton(2);


                            } else {

                                HideChnagePasswordWarnings(3);
                                UpdatePasswordButton(1);
                                ShowChnagePasswordWarnings(2);
                                //password mismatch

                            }


                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    }
            );


        } else {
            ShowChnagePasswordWarnings(5);
            ShowChnagePasswordWarnings(1);
        }

    }

    void UpdatePassButtonClickListner(){

updatebutton.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSettingsActivity.this, "working", Toast.LENGTH_SHORT).show();
            }
        }
);

    }


    void UpdatePasswordButton(int id)

    {
        switch (id) {
            case 1:
                updatebuttonCardview.setVisibility(View.GONE);
                break;
            case 2:
                updatebuttonCardview.setVisibility(View.VISIBLE);

        }


    }


    void PasswordTextChangedListner() {
        old_password.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        ShowChnagePasswordWarnings(5);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );

    }



    private void UpdatePassword(String new_password_text) {


    }


    Boolean CheckOldPasswordIsTrue(String old_pass) {
        //--put code here

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // Toast.makeText(this, "pressed bck", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserSettingsActivity.this, MainActivity.class);

        intent.putExtra("key1", 1);
        startActivity(intent);
        finish();
        super.onBackPressed();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back pressed.

            //  Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserSettingsActivity.this, MainActivity.class);
            intent.putExtra("key1", 2);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
