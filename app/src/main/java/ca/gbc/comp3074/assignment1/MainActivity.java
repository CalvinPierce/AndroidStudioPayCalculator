package ca.gbc.comp3074.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edit_hours, edit_rate;
    private TextView lbl_pay, lbl_tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lbl_pay = (TextView)findViewById(R.id.lbl_pay_output);
        lbl_tax = (TextView)findViewById(R.id.lbl_tax_output);
        edit_hours = (EditText)findViewById(R.id.edit_hours_worked);
        edit_rate = (EditText)findViewById(R.id.edit_hourly_rate);

        findViewById(R.id.btn_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double hours = Double.parseDouble(edit_hours.getText().toString());
                double rate = Double.parseDouble(edit_rate.getText().toString());
                double pay = 0, tax = 0;
                if(hours <= 40){
                    pay = hours * rate;
                } else {
                    pay = (hours - 40) * rate * 1.5 + 40 * rate;
                }
                tax = pay * 0.18;
                pay = pay - tax;
                lbl_pay.setText(String.format("%.2f", pay));
                lbl_tax.setText(String.format("%.2f", tax));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_about:
                Intent i = new Intent(this, AboutActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}