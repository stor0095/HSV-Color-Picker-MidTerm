package model;

import java.util.Observable;
import android.graphics.Color;
/**
 * The model holds the data.
 * This file holds the data for the HSV values to be implemented by the view
 *
 * Here are the domain ranges --- the list of legal values --- for each HSV value:
     Hue
     The hue's domain range is: 0 to 359 degrees (inclusive)
     Saturation
     The saturation's domain range is: 0 to 100% (inclusive)
     Value / Lightness
     The value's domain range is: 0 to 100% (inclusive)
 */
public class HSVModel extends Observable {
    // Class variables
    public static float MAX_HUE = 360f;
    public static float MAX_SATVALUE = 100f;
    public static float MIN_Value = 0f;
   // private static final float[] HSV_TO_COLOR = new float[3];

    // Instance variables
    private float hue;
    private float saturation;
    private float valueLight;

    // Instantiate a new instance of this class
    // Intialize hue, sat, and vL to max values
    public HSVModel() {
        this(MAX_HUE, MAX_SATVALUE, MAX_SATVALUE);
    }

    public HSVModel(float hue, float saturation, float valueLight) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.valueLight = valueLight;
    }

    // Set HSV Values for buttons
    public void blackButton() {
        this.setHSV(MIN_Value, MIN_Value, MIN_Value);
    }
    public void asRed(){
        this.setHSV(MAX_HUE,MAX_SATVALUE,MAX_SATVALUE);
    }
    public void greenButton() {
        this.setHSV(120f,MAX_SATVALUE,MAX_SATVALUE);
    }
    public void purpleButton() {
        this.setHSV(296f, MAX_SATVALUE, MAX_SATVALUE);
    }
    public void limeButton() {
        this.setHSV(120f, 75.6f, 80.4f);
    }
    public void yellowButton() {
        this.setHSV(60f, MAX_SATVALUE, MAX_SATVALUE);
    }
    public void silverButton() {
        this.setHSV(MIN_Value, MIN_Value, 75.3f);
    }
    public void navyButton() {
        this.setHSV(240f, MAX_SATVALUE, 50.2f);
    }
    public void grayButton() {
        this.setHSV(MAX_HUE, MIN_Value, 66f);
    }
    public void tealButton() {
        this.setHSV(180f, MAX_SATVALUE, 50.2f);
    }
    public void blueButton() {this.setHSV(240f, MAX_SATVALUE, MAX_SATVALUE);}
    public void oliveButton() {this.setHSV(60f, MAX_SATVALUE, 50.2f);}
    public void maroonButton() {this.setHSV(MIN_Value, MAX_SATVALUE, 50f);}
    public void magentaButton() {this.setHSV(300f, MAX_SATVALUE, MAX_SATVALUE);}
    public void cyanButton() {this.setHSV(180f, MAX_SATVALUE, MAX_SATVALUE);}

    // Getters
    public float getHue() {return hue;}
    public float getSaturation() {return saturation;}
    public float getValueLight() {return valueLight;}

    // Setters
    public void setHueColor(float hue) {
        this.hue = hue;
        this.updateObservers();
    }
    public void setSatColor(float saturation) {
        this.saturation = saturation;
        this.updateObservers();
    }
    public void setValueLight(float valueLight){
        this.valueLight = valueLight;
        this.updateObservers();
    }

    // Set HSV
    public void setHSV(float hue, float saturation, float valueLight){
        this.hue = hue;
        this.saturation = saturation;
        this.valueLight = valueLight;

        this.updateObservers();
    }
    // Update the model
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();;
    }
    // Convert hsv to color to be called in the view
    public int getCurrentlySelectedColor(){
        return Color.HSVToColor(new float[]{getHue(),(1f/100f)*(float)getSaturation(),(1f/100f)*(float)getValueLight()});
    }
//    mColorSwatch.setBackgroundColor(Color.HSVToColor( new float[]{mModel.getHue(),(1f/100f) *(float)mModel.getSaturtaion(),(1f/100f) *(float)mModel.getValue()}));

}