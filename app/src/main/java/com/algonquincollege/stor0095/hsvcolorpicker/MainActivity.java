package com.algonquincollege.stor0095.hsvcolorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 *  An android color picking app using HSV values.
 *
 *  The controller handles the events for the view
 *
 *  @author Geemakun Storey (stor0095@algonquinlive.com)
 */

// symbol: \u00B0

public class MainActivity extends AppCompatActivity implements Observer
        ,SeekBar.OnSeekBarChangeListener  {
    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String DegreeSymbol = "°";
    private static final String PercentSymbol = "%";

    // Instance Variables
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueLightSB;
    private TextView mHueTextTitle;
    private TextView mSatTextTitle;
    private TextView mValueTextTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Instantiate a new aboutDiag but not not show it yet
        mAboutDialog = new AboutDialogFragment();


        // Instantiate a new HSV model
        //TODO: Put the new HSV model here
        mModel = new HSVModel();
        mModel.setHueColor(HSVModel.MIN_Value);
        mModel.setSatColor(HSVModel.MIN_Value);
        mModel.setValueLight(HSVModel.MIN_Value);
        mModel.addObserver(this);
        // Reference each View
        mColorSwatch = (TextView)findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar)findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar)findViewById(R.id.saturationSB);
        mValueLightSB = (SeekBar)findViewById(R.id.valueLightSB);
        // Reference for seekbar text views
        mHueTextTitle = (TextView)findViewById(R.id.hueTextView);
        mSatTextTitle = (TextView)findViewById(R.id.saturationTextView);
        mValueTextTitle = (TextView)findViewById(R.id.valueLightTextView);

        //TODO: set the max for the seekbars
        mHueSB.setMax((int) HSVModel.MAX_HUE);
        mSaturationSB.setMax((int) HSVModel.MAX_SATVALUE);
        mValueLightSB.setMax((int) HSVModel.MAX_SATVALUE);

        //TODO: Register the event handler for each seekbar
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueLightSB.setOnSeekBarChangeListener(this);
        // Intialize the View to the values of the Model
         this.updateView();

        //On Long Click Display for Color Swatch
        // On long click display a toast message that shows the answer
        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //  H: 240° S: 100% V: 100%
                updateToastMessage();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch ( item.getItemId() ) {
            case R.id.action_about:
                mAboutDialog.show( getFragmentManager(), ABOUT_DIALOG_TAG );
                return true;
            default:
                break;
        }
        return false;
    }
    // Update color swatch when user presses one of the colored buttons, display a toast message with the HSV values when presses
    public void onClick(View v) {
            Button clickedButton = (Button) v;
            switch (clickedButton.getId()) {
                case R.id.blackButton:
                    mModel.blackButton();
                    updateToastMessage();
                    break;
                case R.id.blueButton:
                   mModel.blueButton();
                    updateToastMessage();
                    break;
                case R.id.greenButton:
                   mModel.greenButton();
                    updateToastMessage();
                    break;
                case R.id.redButton:
                    mModel.asRed();
                    updateToastMessage();
                    break;
                case R.id.silverButton:
                    mModel.silverButton();
                    updateToastMessage();
                    break;
                case R.id.purpleButton:
                    mModel.purpleButton();
                    updateToastMessage();
                    break;
                case R.id.limeButton:
                    mModel.limeButton();
                    updateToastMessage();
                    break;
                case R.id.yellowButton:
                    mModel.yellowButton();
                    updateToastMessage();
                    break;
                case R.id.navyButton:
                    mModel.navyButton();
                    updateToastMessage();
                    break;
                case R.id.grayButton:
                    mModel.grayButton();
                    updateToastMessage();
                    break;
                case R.id.tealButton:
                    mModel.tealButton();
                    updateToastMessage();
                    break;
                case R.id.oliveButton:
                    mModel.oliveButton();
                    updateToastMessage();
                    break;
                case R.id.maroonButton:
                    mModel.maroonButton();
                    updateToastMessage();
                    break;
                case R.id.magentaButton:
                    mModel.magentaButton();
                    updateToastMessage();
                    break;
                case R.id.cyanButton:
                    mModel.cyanButton();
                    updateToastMessage();
                    break;
            }
    }

    // EVENT HANDLER FOR THE SEEKBAR(s)
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        // If user doesn't cause this event exit this method
        if (fromUser == false) {
            return;
        }
        // Check which seekbar caused the event
        // Get the seekbar's progress then set the new value to the model

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHueColor(mHueSB.getProgress());
                mHueTextTitle.setText("HUE: " + mModel.getHue() + DegreeSymbol);
                break;
            case R.id.saturationSB:
                mModel.setSatColor(mSaturationSB.getProgress());
                mSatTextTitle.setText("SATURATION: " + mModel.getSaturation() + PercentSymbol);
                break;
            case R.id.valueLightSB:
                mModel.setValueLight(mValueLightSB.getProgress());
                mValueTextTitle.setText("VALUE / LIGHTNESS: " + mModel.getValueLight() + PercentSymbol);
                break;
                //TODO: Continue for saturation and light/value
        }
    }

    @Override
    public  void onStartTrackingTouch(SeekBar seekBar) {
        // No operations

    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Set seekbar text back to orginal state
        mHueTextTitle.setText("Hue");
        mSatTextTitle.setText("Saturation");
        mValueTextTitle.setText("Value / Lightness");
    }

    // Refresh View to display current data of the Model
    @Override
    public void update(Observable observable, Object data){
        this.updateView();
    }

    //TODO: Update SeekerBar'// STOPSHIP: 2016-10-12
    private void updateHueSB() {
        mHueSB.setProgress((int) mModel.getHue());
    }
    private void updateSaturationSB() {
        mSaturationSB.setProgress((int)mModel.getSaturation());
    }
    private void updatevalueLightSB() {
        mValueLightSB.setProgress((int)mModel.getValueLight());
    }
    //TODO: Updatecolorswatch
    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(mModel.getCurrentlySelectedColor());
    }
    // Update the view
    public void updateView(){
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updatevalueLightSB();
    }
    // Display toast message with current HSV values
    public void updateToastMessage() {
        Toast.makeText(MainActivity.this, "H: "
                        + mModel.getHue() + DegreeSymbol + " S: "
                        + mModel.getSaturation() + PercentSymbol + " V: "
                        + mModel.getValueLight() + PercentSymbol
                , Toast.LENGTH_SHORT).show();
    }
}
