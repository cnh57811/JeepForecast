package com.cgavlabs.jeepforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViews();
    setSupportActionBar(toolbar);
  }

  private void findViews() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
  }
}
