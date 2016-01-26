package jayxu.com.carassist.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import jayxu.com.carassist.MODEL.Coordinate;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 1/24/2016.
 */
public class ScoreHexagonView extends View {

    private Paint mHexagonPaint=new Paint();
    private Paint mBatteryIndicatorPaint=new Paint();
    private static final String TAG = ScoreHexagonView.class.getSimpleName() ;
    private static Path mHexagonBoarderPath;
    private static Path mBatteryIndicatorPath;
    private static float mPercentage;
    private static float mCurrentPercent;

//    private float mWidth;
//    private float mHeight;

    private Coordinate mHexagonDimension;
    private Coordinate mViewDimension;

    private double mPercentageContainedInEachSegement = 17.0;

    private Coordinate mCurrentCoordinate;
//    private float mCurrentX;
//    private float mCurrentY;

    private Coordinate mStartingCoordinate;
//    private float mStartingX;
//    private float mStartingY;

    private float mEndofValidX;
    private float mEndofValidY;

    private float mMaxPercentage;

    private float mBatteryLineLength=300;
    private int BatteryImgId;
    private Rect BatteryImgBound;
    public ScoreHexagonView(Context context) {
        super(context);

        init();
    }

    public ScoreHexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScoreHexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mHexagonPaint.setColor(getResources().getColor(R.color.apptheme_color));
        mHexagonPaint.setStrokeWidth(8);
        mHexagonPaint.setStyle(Paint.Style.STROKE);
        mHexagonPaint.setAntiAlias(true);

        mBatteryIndicatorPaint.setColor(getResources().getColor(R.color.apptheme_color));
        mBatteryIndicatorPaint.setStrokeWidth(2);
        mBatteryIndicatorPaint.setStyle(Paint.Style.STROKE);
        mBatteryIndicatorPaint.setAntiAlias(true);
        //This variable contains the percentage of the battery/driving score
        mPercentage=100;
        mCurrentPercent=1;

//        mWidth=getWidth();
//        mHeight=getHeight();
//        mWidth=this.getWidth();
//        mHeight=this.getLayoutParams().height;
        DisplayMetrics screenSize=getResources().getDisplayMetrics();

        Coordinate screenDimension=new Coordinate(screenSize.widthPixels, screenSize.heightPixels);
        float screenWidth=screenSize.widthPixels;
        float screenHeight=screenSize.heightPixels;

        //setting the height/width of the Entire View
        mViewDimension=new Coordinate((float)(screenWidth/3.0),(float)(screenHeight/5.0));
        //below is the logic that will make sure the shortest side is being used as dimension referrence to set the dimensions of a perfect hexagon.

        if(mViewDimension.getX()/2*Math.sqrt(3.0)<=mViewDimension.getY()){
            //use width as referrece, to calculated the height needed to draw a equal side hexagon
            mHexagonDimension=new Coordinate(mViewDimension.getX(),(float)(mViewDimension.getX()/2.0*Math.sqrt(3.0)));
        }else{
            //use height as referrece, to calculated the height needed to draw a equal side hexagon
            mHexagonDimension=new Coordinate((float)(mViewDimension.getY()/Math.sqrt(3.0)*2.0),mViewDimension.getY());

        }

        Coordinate ViewCenter=mViewDimension.getCenter();

        //Using the center of the View as a referrence point, calculate the bound for the battery image by adding/substracting the Hexagon Width/Height
        BatteryImgBound=new Rect( (int)(ViewCenter.getX()-(mHexagonDimension.getX()/2.0)),
                (int)(ViewCenter.getY()-(mHexagonDimension.getY()/2.0)),
                (int)(ViewCenter.getX()+(mHexagonDimension.getX()/2.0)),
                (int)(ViewCenter.getY()+(mHexagonDimension.getY()/2.0)));

        mStartingCoordinate=new Coordinate((float)(ViewCenter.getX()+(mHexagonDimension.getX()/4.0)),
                (float)(ViewCenter.getY()-(mHexagonDimension.getY()/2.0)));




//        mWidth=(float)(screenWidth/3.0);
//        mHeight=(float)(screenHeight/5.0);

//        mStartingX=mWidth/4*3;
//        mStartingY=5;
        //below is the logic that will make sure the shortest side is being used as dimension referrence to get a perfect hexagon
//        if(mWidth/2*Math.sqrt(3.0)<=mHeight){
//            //use width as referrece, to calculated the height needed to draw a equal side hexagon
//            mHeight=(float)(mWidth/2.0*Math.sqrt(3.0));
//        }else{
//            //use height as referrece, to calculated the height needed to draw a equal side hexagon
//            mWidth= (float)(mHeight/Math.sqrt(3.0)*2.0);
//        }

        mHexagonBoarderPath =new Path();
        mBatteryIndicatorPath=new Path();


        mHexagonBoarderPath.moveTo(mStartingCoordinate.getX(), mStartingCoordinate.getY());
        mCurrentCoordinate=new Coordinate(mStartingCoordinate.getX(),mStartingCoordinate.getY());
//        mCurrentX=mStartingX;
//        mCurrentY=mStartingY;

        mMaxPercentage=101;

        //Selecting which image to use for the driving score
        BatteryImgId=R.drawable.steering_wheel;
    }

    @Override
    public void onDraw(Canvas canvas){
        //Draw the icon in the middle:
//        Paint p=new Paint();
//        p.setColor(Color.WHITE);
//        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.battery100);
//        canvas.drawBitmap(b, 100, 100 , p );

        //Draw the batter sign in the middle.


        Drawable batteryImg=getResources().getDrawable(BatteryImgId);
        batteryImg.setBounds(BatteryImgBound);
        batteryImg.draw(canvas);

        boolean drawingGrayCircle=false;
        while(mCurrentPercent<=mMaxPercentage){
            if(mCurrentPercent>mPercentage && !drawingGrayCircle){
                //draw the entire Valid Line;
                canvas.drawPath(mHexagonBoarderPath, mHexagonPaint);
                canvas.drawPath(mBatteryIndicatorPath,mBatteryIndicatorPaint);
//                    mHexagonPaint.setTextSize(100);
//                    canvas.drawText((int)mPercentage+"%", mCurrentX, mCurrentY,  mHexagonPaint);
                //Completed the Valid part with apptheme color. go on the drawing using grey
                mHexagonPaint.setColor(Color.WHITE);
                mHexagonPaint.setStrokeWidth(4);
                //starting to draw the invalid lines.
                mHexagonBoarderPath=new Path();
                mHexagonBoarderPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                //Keep track of where the end of the line is.
                mEndofValidX=mCurrentCoordinate.getX();
                mEndofValidY=mCurrentCoordinate.getY();
                //Prevent this section code from exec again.
                drawingGrayCircle=true;
            }
            if(mCurrentPercent==mMaxPercentage){
                //draw the entire inVliad line
                canvas.drawPath(mHexagonBoarderPath, mHexagonPaint);
            }
            // This will call the corresponding Path.rLineTo method to modify the Path Accordingly.
            ComputeNextDot();
            mCurrentPercent++;

        }

    }

    private void ComputeNextDot(){
        float _17SegmentUnitWidth = (float) ((mHexagonDimension.getX()/4.0)/ mPercentageContainedInEachSegement);
        float _17SegmentUnitHeight= (float) ((mHexagonDimension.getY()/2.0)/ mPercentageContainedInEachSegement);

        float _16SegmentUnitWidth=(float) ((mHexagonDimension.getX()/4.0)/ 16.0);
        float _16SegmentUnitHeight= (float) ((mHexagonDimension.getY()/2.0)/ 16.0);

        if(mCurrentPercent > 0 && mCurrentPercent <= 17){
            //drawing @ 315 degrees
            //1-17%
            mHexagonBoarderPath.rLineTo(-2*_17SegmentUnitWidth, 0);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()-2*_17SegmentUnitWidth);

            //check if valid line has ended:
            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(0, -mBatteryLineLength);
            }


        }else if(mCurrentPercent> 17 && mCurrentPercent <= 34){

            //drawing @ 90 degrees
            //18-34%
            mHexagonBoarderPath.rLineTo(-_17SegmentUnitWidth, +_17SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()-_17SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()+_17SegmentUnitHeight);
            //draw the battery percentage display line

            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(-mBatteryLineLength, 0);
            }


        }else if(mCurrentPercent > 34 && mCurrentPercent <= 50){
            //35-50% this segment is only 16%
            //drawing @ 45 degrees
            mHexagonBoarderPath.rLineTo(_16SegmentUnitWidth, _16SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()+_16SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()+_16SegmentUnitHeight);
            //draw the battery percentage display line

            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(-mBatteryLineLength, 0);
            }

        }else if(mCurrentPercent > 50&& mCurrentPercent <= 67){
            //51-67
            //drawing @ 135 degrees
            mHexagonBoarderPath.rLineTo(2*_17SegmentUnitWidth, 0);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()+2*_17SegmentUnitWidth);
            //draw the battery percentage display line
            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(0, +mBatteryLineLength);
            }

        }else if(mCurrentPercent > 67 && mCurrentPercent <= 84){
            //68-84
            //drawing @ 180 degrees
            mHexagonBoarderPath.rLineTo(_17SegmentUnitWidth, -_17SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()+_17SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()-_17SegmentUnitHeight);
            //draw the battery percentage display line

            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(+mBatteryLineLength, 0);
            }

        }else if(mCurrentPercent > 84 && mCurrentPercent <= mMaxPercentage){
            //85-100, this segment is only 16%
            //drawing @ 225 degrees
            mHexagonBoarderPath.rLineTo(-_16SegmentUnitWidth, -_16SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()-_16SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()-_16SegmentUnitHeight);
            //draw the battery percentage display line

            if(mCurrentPercent==mPercentage){
                mBatteryIndicatorPath.reset();
                mBatteryIndicatorPath.moveTo(mCurrentCoordinate.getX(),mCurrentCoordinate.getY());
                mBatteryIndicatorPath.rLineTo(+mBatteryLineLength, 0);
            }
        }
    }

    public void setPercentage(float percentage){
        mPercentage=percentage;
    }
//    public float getmWidth() {
//        return mWidth;
//    }
//
//    public void setmWidth(float mWidth) {
//        this.mWidth = mWidth;
//    }
//
//    public float getmHeight() {
//        return mHeight;
//    }
//
//    public void setmHeight(float mHeight) {
//        this.mHeight = mHeight;
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}