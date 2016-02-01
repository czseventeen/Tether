package jayxu.com.carassist.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import jayxu.com.carassist.MODEL.Coordinate;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 1/24/2016.
 */
public class ScoreHexagonView extends View {

    private Paint mHexagonPaint=new Paint();
    private Paint mScoreIndicatorPaint =new Paint();
    private static final String TAG = ScoreHexagonView.class.getSimpleName() ;
    private static Path mHexagonBoarderPath;
    private static Path mScoreIndicatorPath;
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

    private Coordinate mEndofIndicatorLine;
//    private float mEndOfIndicatorLineX;
//    private float mEndOfIndicatorLineY;

    private float mMaxPercentage;

    private float mScoreLineLength =80;

    private Rect SteeringWheelImgBound;
    public ScoreHexagonView(Context context) {
        super(context);

        init(UsefulConstants.DefaultInitCode);
    }

    public ScoreHexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(UsefulConstants.DefaultInitCode);
    }

    public ScoreHexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(UsefulConstants.DefaultInitCode);
    }

    private void init(float DesiredPercent){
        //initalzing the paintbrush for the Hexagon boarders.
        mHexagonPaint.setColor(getResources().getColor(R.color.apptheme_color));
        mHexagonPaint.setStrokeWidth(8);
        mHexagonPaint.setStyle(Paint.Style.STROKE);
        mHexagonPaint.setAntiAlias(true);
        //initalizing the paint brush for the score line .
        mScoreIndicatorPaint.setColor(getResources().getColor(R.color.apptheme_color));
        mScoreIndicatorPaint.setStrokeWidth(4);
        mScoreIndicatorPaint.setStyle(Paint.Style.STROKE);
        mScoreIndicatorPaint.setAntiAlias(true);
        //This variable contains the percentage of the score
        mPercentage = 100;
        if(DesiredPercent!=UsefulConstants.DefaultInitCode)
            mPercentage=DesiredPercent;

        mCurrentPercent=1;
//        mWidth=getWidth();
//        mHeight=getHeight();
//        mWidth=this.getWidth();
//        mHeight=this.getLayoutParams().height;
        DisplayMetrics screenSize=getResources().getDisplayMetrics();

        float screenWidth=screenSize.widthPixels;
        float screenHeight=screenSize.heightPixels;

        //setting the height/width of the Entire View
        mViewDimension=new Coordinate((float)(screenWidth),(float)(screenHeight/5.0));
        //below is the logic that will make sure the shortest side is being used as dimension referrence to set the dimensions of a perfect hexagon.
        //setting the Hexagon Dimensions to make a perfect hexagon
        if(mViewDimension.getX()/2*Math.sqrt(3.0)<=mViewDimension.getY()){
            //use width as referrece, to calculated the height needed to draw a equal side hexagon
            mHexagonDimension=new Coordinate((float)(mViewDimension.getX()*0.8),(float)(mViewDimension.getX()/2.0*Math.sqrt(3.0)*0.8));
        }else{
            //use height as referrece, to calculated the height needed to draw a equal side hexagon
            mHexagonDimension=new Coordinate((float)(mViewDimension.getY()/Math.sqrt(3.0)*2.0*0.8),(float)(mViewDimension.getY()*0.8));
        }

        Coordinate ViewCenter=mViewDimension.getCenter();
        //Hard-coding a 20pixel shift downward to make sure text appear correctly.  may need to change this in future for difference screen size
        ViewCenter.setY(ViewCenter.getY()+20);


        //Using the center of the View as a referrence point, calculate the bound for the score image by adding/substracting the Hexagon Width/2  and Height/2
        //unlike the battery image, the score image needs to be fitted into a square. thus using the shortest side as a referrence to draw a square in the center
        float SteeringWheelImgRadius=(float)(mHexagonDimension.getX()/2.0);
        if(mHexagonDimension.getX()>mHexagonDimension.getY())
            SteeringWheelImgRadius=(float)(mHexagonDimension.getY()/2.0);

        SteeringWheelImgBound =new Rect( (int)(ViewCenter.getX()-SteeringWheelImgRadius),
                (int)(ViewCenter.getY()-SteeringWheelImgRadius),
                (int)(ViewCenter.getX()+SteeringWheelImgRadius),
                (int)(ViewCenter.getY()+SteeringWheelImgRadius));
        //set the starting Coordinate.
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
        //the Path variable for the Hexagon boards
        mHexagonBoarderPath =new Path();
        //The Path variable for the line that extends to indicate the score left
        mScoreIndicatorPath =new Path();


        mHexagonBoarderPath.moveTo(mStartingCoordinate.getX(), mStartingCoordinate.getY());
        mCurrentCoordinate=new Coordinate(mStartingCoordinate.getX(),mStartingCoordinate.getY());
//        mCurrentX=mStartingX;
//        mCurrentY=mStartingY;

        mMaxPercentage=101;
    }

    @Override
    public void onDraw(Canvas canvas){
        //Draw the icon in the middle:
//        Paint p=new Paint();
//        p.setColor(Color.WHITE);
//        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.battery100);
//        canvas.drawBitmap(b, 100, 100 , p );

        //Draw the batter sign in the middle.
        int steeringwheelImgId=R.drawable.steering_wheel;


        Drawable SteeringWheelImg=getResources().getDrawable(steeringwheelImgId);
        SteeringWheelImg.setBounds(SteeringWheelImgBound);
        SteeringWheelImg.draw(canvas);

        boolean drawingGrayCircle=false;
        while(mCurrentPercent<=mMaxPercentage){
            if(mCurrentPercent>mPercentage && !drawingGrayCircle){
                //draw the entire Valid Line;
                canvas.drawPath(mHexagonBoarderPath, mHexagonPaint);
                canvas.drawPath(mScoreIndicatorPath, mScoreIndicatorPaint);
//                    mHexagonPaint.setTextSize(100);
//                    canvas.drawText((int)mPercentage+"%", mCurrentX, mCurrentY,  mHexagonPaint);
                //Completed the Valid part with apptheme color. go on the drawing using grey


                //Keep track of where the end of the indicator line is.
                PathMeasure pm= new PathMeasure(mScoreIndicatorPath, false);
                float lastCoordinate[]=new float[2];
                pm.getPosTan(pm.getLength(), lastCoordinate , null);
                //update the Textbox position based on the last point on the indicator line
                TextView percentageText = (TextView)StatFragment.getMyCarView().findViewById(R.id.mystat_ScorePercent);
                try {
                    percentageText.setX(lastCoordinate[0]-50);
                    percentageText.setY(lastCoordinate[1]-60);
                }catch(NullPointerException e){
                    Log.d(TAG, "Percentage Indicator line not drawn yet!");
                }


                //re initialize the paint to paint the rest of the hexagon using gray.
                mHexagonPaint.setColor(UsefulConstants.UnpaintedHexagonColor);
                mHexagonPaint.setStrokeWidth(4);
                //starting to draw the invalid lines.
                mHexagonBoarderPath=new Path();
                mHexagonBoarderPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());

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
        if(mPercentage==100){
            mPercentage++;
        }
        float _17SegmentUnitWidth = (float) ((mHexagonDimension.getX()/4.0)/ mPercentageContainedInEachSegement);
        float _17SegmentUnitHeight= (float) ((mHexagonDimension.getY()/2.0)/ mPercentageContainedInEachSegement);

        float _16SegmentUnitWidth=(float) ((mHexagonDimension.getX()/4.0)/ 16.0);
        float _16SegmentUnitHeight= (float) ((mHexagonDimension.getY()/2.0)/ 16.0);

        if(mCurrentPercent > 0 && mCurrentPercent <= 17){
            //drawing @ 180 degrees
            //1-17%
            mHexagonBoarderPath.rLineTo((float)-2.0*_17SegmentUnitWidth, 0);
            mCurrentCoordinate.setX((float)(mCurrentCoordinate.getX()-2.0*_17SegmentUnitWidth));

            //check if valid line has ended:
            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
//                //Draw a line @ 180 degress to for score
                mScoreIndicatorPath.rLineTo((float) (-mScoreLineLength - ((17 - mCurrentPercent) * 2.0 * _17SegmentUnitWidth)), 0);
                //No need to draw the rest of the hexagon side in GRAY, just gonna skip to drawing next side.
                //move the boarder path/ currentCoordinate to the end of the side
                //mHexagonBoarderPath.rLineTo((-2*_17SegmentUnitWidth)*(17-mCurrentPercent), 0);
                mCurrentCoordinate.setX((float)(mCurrentCoordinate.getX()+(-2.0*_17SegmentUnitWidth)*(17.0-mCurrentPercent)));
                mCurrentPercent=17;

            }


        }else if(mCurrentPercent> 17 && mCurrentPercent <= 34){

            //drawing @ 225 degrees
            //18-34%
            mHexagonBoarderPath.rLineTo(-_17SegmentUnitWidth, +_17SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()-_17SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()+_17SegmentUnitHeight);
            //draw the score percentage display line

            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                mScoreIndicatorPath.rLineTo(-mScoreLineLength, 0);
            }


        }else if(mCurrentPercent > 34 && mCurrentPercent <= 50){
            //35-50% this segment is only 16%
            //drawing @ 315 degrees
            mHexagonBoarderPath.rLineTo(_16SegmentUnitWidth, _16SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()+_16SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()+_16SegmentUnitHeight);
            //draw the score percentage display line

            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                mScoreIndicatorPath.rLineTo(-mScoreLineLength, 0);
            }

        }else if(mCurrentPercent > 50&& mCurrentPercent <= 67){
            //51-67
            //drawing @ 180 degrees
            mHexagonBoarderPath.rLineTo(2*_17SegmentUnitWidth, 0);
            mCurrentCoordinate.setX((float)(mCurrentCoordinate.getX()+2.0*_17SegmentUnitWidth));
            //draw the score percentage display line
            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                mScoreIndicatorPath.rLineTo((float) (mScoreLineLength + ((67.0 - mCurrentPercent) * 2.0 * _17SegmentUnitWidth)), 0);
//                //No need to draw the rest of the hexagon side in GRAY, just gonna skip to drawing next side.
//                //move the boarder path/ currentCoordinate to the end of the side
//                //mHexagonBoarderPath.rLineTo((2*_17SegmentUnitWidth)*(67-mCurrentPercent), 0);
                mCurrentCoordinate.setX((float) (mCurrentCoordinate.getX() + (2.0 * _17SegmentUnitWidth) * (67.0 - mCurrentPercent)));
                mCurrentPercent=67;
            }

        }else if(mCurrentPercent > 67 && mCurrentPercent <= 84){
            //68-84
            //drawing @ 45 degrees
            mHexagonBoarderPath.rLineTo(_17SegmentUnitWidth, -_17SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()+_17SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()-_17SegmentUnitHeight);
            //draw the score percentage display line

            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                mScoreIndicatorPath.rLineTo(+mScoreLineLength, 0);
            }

        }else if(mCurrentPercent > 84 && mCurrentPercent <= mMaxPercentage){
            //85-100, this segment is only 16%
            //drawing @ 135 degrees
            mHexagonBoarderPath.rLineTo(-_16SegmentUnitWidth, -_16SegmentUnitHeight);
            mCurrentCoordinate.setX(mCurrentCoordinate.getX()-_16SegmentUnitWidth);
            mCurrentCoordinate.setY(mCurrentCoordinate.getY()-_16SegmentUnitHeight);
            //draw the score percentage display line

            if(mCurrentPercent==mPercentage){
                mScoreIndicatorPath.reset();
                mScoreIndicatorPath.moveTo(mCurrentCoordinate.getX(), mCurrentCoordinate.getY());
                mScoreIndicatorPath.rLineTo(+mScoreLineLength, 0);
            }
        }
    }

    public void setPercentage(float percentage){
        init(percentage);
    }
    public float getPercentage(){
        if(mPercentage==101)
            return 100;
        return mPercentage;
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

    public Coordinate getLastIndicatorLinePos(){
        return mEndofIndicatorLine;
    }

}
