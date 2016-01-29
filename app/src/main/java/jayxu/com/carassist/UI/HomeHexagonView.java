package jayxu.com.carassist.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jayxu.com.carassist.MODEL.Coordinate;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 1/28/2016.
 */

    public class HomeHexagonView  extends View {

        private Paint mHexagonPaint=new Paint();
    private static final String TAG = ScoreHexagonView.class.getSimpleName() ;
        private static Path mHexagonBoarderPath;

        private Coordinate mHexagonDimension;
        private Coordinate mViewDimension;
        private Coordinate mStartingCoordinate;

        private Rect HomeImgBound;
        public HomeHexagonView(Context context) {
            super(context);

            init();
        }

        public HomeHexagonView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public HomeHexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init(){
            //initalzing the paintbrush for the Hexagon boarders.
            mHexagonPaint.setColor(UsefulConstants.UnpaintedHexagonColor);
            mHexagonPaint.setStrokeWidth(8);
            mHexagonPaint.setStyle(Paint.Style.STROKE);
            mHexagonPaint.setAntiAlias(true);

            DisplayMetrics screenSize=getResources().getDisplayMetrics();

            float screenWidth=screenSize.widthPixels;
            float screenHeight=screenSize.heightPixels;

            //setting the height/width of the Entire View
            mViewDimension=new Coordinate(screenWidth,(float)(screenHeight*0.8));
            //below is the logic that will make sure the shortest side is being used as dimension referrence to set the dimensions of a perfect hexagon.
            //setting the Hexagon Dimensions to make a perfect hexagon
            if(mViewDimension.getX()/2*Math.sqrt(3.0)<=mViewDimension.getY()){
                //use width as referrece, to calculated the height needed to draw a equal side hexagon
                mHexagonDimension=new Coordinate((float)(mViewDimension.getX()*0.5),(float)(mViewDimension.getX()/2.0*Math.sqrt(3.0)*0.5));
            }else{
                //use height as referrece, to calculated the height needed to draw a equal side hexagon
                mHexagonDimension=new Coordinate((float)(mViewDimension.getY()/Math.sqrt(3.0)*2.0*0.5),(float)(mViewDimension.getY()*0.5));
            }

            Coordinate ViewCenter=mViewDimension.getCenter();

            //Using the center of the View as a referrence point, calculate the bound for the car image by adding/substracting the Hexagon Width/2  and Height/2
            HomeImgBound =new Rect( (int)(ViewCenter.getX()-(mHexagonDimension.getX()/2.0)),
                    (int)(ViewCenter.getY()-(mHexagonDimension.getY()/2.0)),
                    (int)(ViewCenter.getX()+(mHexagonDimension.getX()/2.0)),
                    (int)(ViewCenter.getY()+(mHexagonDimension.getY()/2.0)));
            //set the starting Coordinate.
            mStartingCoordinate=new Coordinate((float)(ViewCenter.getX()+(mHexagonDimension.getX()/4.0)),
                    (float)(ViewCenter.getY()-(mHexagonDimension.getY()/2.0)));

            //the Path variable for the Hexagon boards
            mHexagonBoarderPath =new Path();
            //The Path variable for the line that extends to indicate the score/battery left
            mHexagonBoarderPath.moveTo(mStartingCoordinate.getX(), mStartingCoordinate.getY());

        }

        @Override
        public void onDraw(Canvas canvas){
            init();
            //Draw the batter sign in the middle.
            int CarImgId=R.drawable.ic_car;


            Drawable CarImg=getResources().getDrawable(CarImgId);
            CarImg.setBounds(HomeImgBound);
            CarImg.draw(canvas);

            mHexagonBoarderPath.rLineTo(-mHexagonDimension.getX()/2, 0);
            mHexagonBoarderPath.rLineTo(-mHexagonDimension.getX()/4, mHexagonDimension.getY()/2);
            mHexagonBoarderPath.rLineTo(mHexagonDimension.getX()/4, mHexagonDimension.getY()/2);
            mHexagonBoarderPath.rLineTo(mHexagonDimension.getX()/2, 0);
            mHexagonBoarderPath.rLineTo(mHexagonDimension.getX()/4, -mHexagonDimension.getY()/2);
            mHexagonBoarderPath.rLineTo(-mHexagonDimension.getX()/4, -mHexagonDimension.getY()/2);
            canvas.drawPath(mHexagonBoarderPath,mHexagonPaint);

            ArrayList<Coordinate> endpoints=getHomeHexagonEndPoints();
            if(endpoints!=null){
                //put all 4 icons on the Hexagon edges
                ImageView home_health=(ImageView)HomeFragment.getHomeRootView().findViewById(R.id.home_health);
                home_health.setX(endpoints.get(0).getX()-home_health.getWidth()/2);
                home_health.setY(endpoints.get(0).getY()-home_health.getHeight()/2);

                ImageView home_money=(ImageView)HomeFragment.getHomeRootView().findViewById(R.id.home_money);
                home_money.setX(endpoints.get(1).getX()-home_money.getWidth()/2);
                home_money.setY(endpoints.get(1).getY()-home_money.getHeight()/2);

                ImageView home_chargstation=(ImageView)HomeFragment.getHomeRootView().findViewById(R.id.home_chargeStation);
                home_chargstation.setX(endpoints.get(3).getX()-home_chargstation.getWidth()/2);
                home_chargstation.setY(endpoints.get(3).getY()-home_chargstation.getHeight()/2);

                ImageView home_mpg=(ImageView)HomeFragment.getHomeRootView().findViewById(R.id.home_mpg);
                home_mpg.setX(endpoints.get(4).getX()-home_mpg.getWidth()/2);
                home_mpg.setY(endpoints.get(4).getY()-home_mpg.getHeight()/2);
            }

        }

        public static ArrayList<Coordinate> getHomeHexagonEndPoints(){
            //this method will return the 6 end points;
            ArrayList<Coordinate> endpoint= new ArrayList<>();
            PathMeasure pm= new PathMeasure(mHexagonBoarderPath, false);
            float[] aCoordinate= new float[2];
            Coordinate aPoint=new Coordinate();
            for(int i=0; i<6 ; i++){
                pm.getPosTan((float)(pm.getLength()/6.0*i), aCoordinate , null);
                try {
                    aPoint=new Coordinate(aCoordinate[0], aCoordinate[1]);
                    endpoint.add(aPoint);
                }catch (NullPointerException e){
                    Log.d("~~~~~", "The Home Hexagon has not been drawn yet!");
                }
            }
            return endpoint;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            this.setMeasuredDimension(parentWidth, parentHeight);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }




}
