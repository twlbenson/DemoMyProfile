package c346.rp.edu.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strName = etName.getText().toString();
                float fGPA = Float.parseFloat(etGPA.getText().toString());
                int genderid = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", fGPA);
                prefEdit.putInt("gender", genderid);

                prefEdit.commit();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        float fGPA = Float.parseFloat(etGPA.getText().toString());
        int genderid = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", fGPA);
        prefEdit.putInt("gender", genderid);

        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("name", "Ben");
        float fGPA = prefs.getFloat("gpa", 0);
        int genderid = prefs.getInt("gender", R.id.radioButtonMale);

        etName.setText(strName);
        etGPA.setText(fGPA + "");
        rgGender.check(genderid);

    }
}
