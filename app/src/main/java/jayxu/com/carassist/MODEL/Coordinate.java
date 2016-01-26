package jayxu.com.carassist.MODEL;

/**
 * Created by Yuchen on 1/25/2016.
 */
public class Coordinate {
    private float X;
    private float Y;
    public Coordinate(){
        X=0;
        Y=0;
    }
    public Coordinate( float x, float y){
        X=x;
        Y=y;
    }
    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public Coordinate getCenter(){
        return new Coordinate((float)(this.getX()/2.0), (float)(this.getY()/2.0));
    }


}
