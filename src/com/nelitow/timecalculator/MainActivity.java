package com.nelitow.timecalculator;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	NumberPicker np1;
	NumberPicker np2;
	NumberPicker np3;
	Button add;
	Button sub;
	Button mult;
	Button div;
	Button now;
	TextView timeOut;
	int Hour;
	int Min;
	int Sec;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initiateNumberPickers();
		initiateButtons();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void initiateNumberPickers() {
		np1 = (NumberPicker) findViewById(R.id.np1);
		np1.setMaxValue(24);
		np1.setMinValue(0);
		np2 = (NumberPicker) findViewById(R.id.np2);
		np2.setMaxValue(60);
		np2.setMinValue(0);
		np3 = (NumberPicker) findViewById(R.id.np3);
		np3.setMaxValue(60);
		np3.setMinValue(0);
	}

	public void initiateButtons() {
		add = (Button) findViewById(R.id.Button_Add);
		sub = (Button) findViewById(R.id.Button_Subtract);
		mult = (Button) findViewById(R.id.Button_Multiply);
		div = (Button) findViewById(R.id.Button_Divide);
		now = (Button) findViewById(R.id.Button_Now);
		timeOut = (TextView) findViewById(R.id.TV_Time);

		add.setOnClickListener((android.view.View.OnClickListener) this);
		sub.setOnClickListener((android.view.View.OnClickListener) this);
		mult.setOnClickListener((android.view.View.OnClickListener) this);
		div.setOnClickListener((android.view.View.OnClickListener) this);
		now.setOnClickListener((android.view.View.OnClickListener) this);
	}

	@Override
	public void onClick(View v) {
		if (add.getId() == ((Button) v).getId()) {
			add();
			setTime();
			clearNP();
		}
		if (now.getId() == ((Button) v).getId()) {
			setNPToNow();
		}
	}

	public void setNPToNow() {
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		np1.setValue(today.hour);
		np2.setValue(today.minute);
		np3.setValue(today.second);
	}

	public void setTime() {
		String SecT;
		String MinT;
		String HourT;
		if (Sec < 10) {
			SecT = "0" + Sec;
		} else {
			SecT = Sec + "";
		}
		if (Min < 10) {
			MinT = "0" + Min;
		} else {
			MinT = Min + "";
		}
		if (Hour < 10) {
			HourT = "0" + Hour;
		} else {
			HourT = Hour + "";
		}
		timeOut.setText(HourT + ":" + MinT + ":" + SecT);
	}

	public void add() {
		if (Sec + np3.getValue() >= 60) {
			Sec = Sec + np3.getValue();
			Min++;
			Sec = Sec - 60;
		} else {
			Sec = Sec + np3.getValue();
		}

		if (Min + np2.getValue() >= 60) {
			Min = Min + np2.getValue();
			Hour++;
			Min = Min - 60;
		} else {
			Min = Min + np2.getValue();
		}

		Hour = Hour + np1.getValue();

	}

	public void clearNP() {
		np1.setValue(0);
		np2.setValue(0);
		np3.setValue(0);
	}
}