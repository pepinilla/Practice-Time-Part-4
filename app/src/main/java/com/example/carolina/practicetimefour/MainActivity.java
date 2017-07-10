package com.example.carolina.practicetimefour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.button)
    Button button;
    FileHelper fileHelper;
    ActivityHelper activityHelper = new ActivityHelper();
    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onViewClicked(View v) {
        String json = fileHelper.readFileFromAssets(this, "data.json");
        //text.setText(json);
        try {
            JSONArray jsonArray = new JSONArray(json);
            displayMessage("There are " + jsonArray.length() + " items");

            for (int i =0; i <jsonArray.length(); i++){
                JSONObject item = (JSONObject) jsonArray.get(i);
                String itemName = item.getString("name");
                double itemPrice = item.getDouble("price");
                StringBuilder builder = new StringBuilder(itemName)
                        .append(" (")
                        .append(itemPrice)
                        .append(")\n");
                text.append(builder.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void displayMessage(String message) {
        activityHelper.log(this, text, message, true);
        scrollView.scrollTo(0, scrollView.getBottom());
    }
}
