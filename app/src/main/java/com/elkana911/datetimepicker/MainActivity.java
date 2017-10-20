package com.elkana911.datetimepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etDate = (EditText) findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                etDate.setText(prettyDate(MainActivity.this, dayOfMonth, monthOfYear, year));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Get Current Time
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                etTime.setText(String.format("%02d:%02d", hourOfDay, minute));
//                                etTime.setText(hourOfDay + ":" + minute);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.datetimepicker);
                dialog.setTitle("Pilih Tanggal");

                DatePicker datePicker = dialog.findViewById(R.id.datePicker1);
                datePicker.setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();
            }
        });
    }

    public static String prettyDate(Context ctx, int dayOfMonth, int monthOfYear, int year) {
        // param range based on
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth == mDay && monthOfYear == mMonth && year == mYear) {
            return ctx.getString(R.string.label_today);
        }

        if (dayOfMonth == (mDay+1) && monthOfYear == mMonth && year == mYear) {
            return ctx.getString(R.string.label_tomorrow);
        }

        c.set(year, monthOfYear, dayOfMonth);
        String hari = "Hari ini";
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                hari = "Minggu";
                break;
            case 2:
                hari = "Senin";
                break;
            case 3:
                hari = "Selasa";
                break;
            case 4:
                hari = "Rabu";
                break;
            case 5:
                hari = "Kamis";
                break;
            case 6:
                hari = "Jumat";
                break;
            case 7:
                hari = "Sabtu";
        }

        return hari + ", " + new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(c.getTime());
//        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(c.getTime());
//        return "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
    }
}
