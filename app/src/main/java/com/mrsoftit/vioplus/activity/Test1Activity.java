package com.mrsoftit.vioplus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;
import com.mrsoftit.vioplus.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test1Activity extends AppCompatActivity {

    private PickerUI mPickerUI;
    private CheckBox mRandomColor;
    private CheckBox mUseBlur;
    private CheckBox mItemsClickables;
    private CheckBox mAutoDismiss;
    private Button btSlide;
    private int currentPosition = -1;
    private List<String> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        findViews();
        setListeners();

        //Populate list
        options = Arrays.asList(getResources().getStringArray(R.array.countries_array));

        //Populate list
        mPickerUI.setItems(this, options);
        mPickerUI.setColorTextCenter(R.color.background_picker);
        mPickerUI.setColorTextNoCenter(R.color.background_picker);
        mPickerUI.setBackgroundColorPanel(R.color.background_picker);
        mPickerUI.setLinesColor(R.color.background_picker);
        mPickerUI.setItemsClickables(false);
        mPickerUI.setAutoDismiss(false);

        mPickerUI.setOnClickItemPickerUIListener(
                new PickerUI.PickerUIItemClickListener() {

                    @Override
                    public void onItemClickPickerUI(int which, int position, String valueResult) {
                        currentPosition = position;
                        Toast.makeText(Test1Activity.this, valueResult, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void findViews() {
        btSlide = (Button) findViewById(R.id.bt_slide);
        mPickerUI = (PickerUI) findViewById(R.id.picker_ui_view);

    }

    private void setListeners() {
        btSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int randomColor = getRandomColor();
                PickerUISettings pickerUISettings =new PickerUISettings.Builder().withItems(options)
                                .withBackgroundColor(randomColor)
                                .build();

                mPickerUI.setSettings(pickerUISettings);

                if(currentPosition==-1) {
                    mPickerUI.slide();
                }
                else{
                    mPickerUI.slide(currentPosition);
                }
            }
        });
    }

    private int getRandomColor() {
        // generate the random integers for r, g and b value
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return Color.rgb(r, g, b);
    }
}
