package mx.ramsesmartinez.textimputlayout;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editTextPhone;
    private TextInputLayout tilEmail;
    private TextInputLayout tilName;
    private TextInputLayout tilPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botones
        Button buttonAccept = (Button) findViewById(R.id.button_accept);
        Button buttonCancel = (Button) findViewById(R.id.button_cancel);

        buttonAccept.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        //TextImputLayout
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        tilName = (TextInputLayout) findViewById(R.id.til_name);
        tilPhone = (TextInputLayout) findViewById(R.id.til_phone);

        //EditTexts
        editTextEmail = (EditText) findViewById(R.id.edit_text_email);
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextPhone = (EditText) findViewById(R.id.edit_text_phone);

        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isValidEmail(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isValidName(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isValidPhone(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_accept:
                //Validate data
                validateData();
                break;
            case R.id.button_cancel:
                break;
        }
    }

    private boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if (!pattern.matcher(name).matches() || name.length() > 30) {
            tilName.setError("Nombre inválido");
            return false;
        } else {
            tilName.setError(null);
        }
        return true;
    }

    private boolean isValidPhone(String phone) {
        if (!Patterns.PHONE.matcher(phone).matches()) {
            tilPhone.setError("Teléfono inválido");
            return false;
        } else {
            tilPhone.setError(null);
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Email inválido");
            return false;
        } else {
            tilEmail.setError(null);
        }
        return true;
    }

    private void validateData() {
        String email = editTextEmail.getText().toString();
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (isValidName(name) &&  isValidPhone(phone) && isValidEmail(email) ) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout_main);
            Snackbar.make(coordinatorLayout, "Registro almacenado", Snackbar.LENGTH_SHORT).show();
        }
    }
}