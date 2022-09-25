package me.alnmgdev.sje.Game.RayCast;

import me.alnmgdev.sje.Engine.Engine.EngineHandler;
import me.alnmgdev.sje.Game.RayCast.Objects.MainMap;

public class Ray implements MainMap {

    private double initialAngle=0;
    private double initialPosX = 0;
    private double rayAngle = 0;
    private double rayPosX = 0;
    private double rayPosY = 0;
    private double initialPosY = 0;
    private double distance = 0;

    private double fixAngle = 0;

    private int mapCell = 0;

    private boolean isVertical = false;


    double dist(double ax, double ay, double bx, double by, double angle){
        return Math.sqrt(((bx-ax)*(bx-ax)) + ((by-ay)*(by-ay)));
    }

    public Ray(double ix,double iy,double ia, double fa){
        setInitialAngle(ia);
        setInitialPosX(ix);
        setInitialPosY(iy);
        setFixAngle(fa);

    }

    public void CastRay(){
         int r=0, mx=0, my=0, dof=0, fmx,fmy;
        double rx=0,ry=0,ra=0,xo=0,yo=0, pa=0,px=0,py=0, fdist = 0, fa;
        pa = getInitialAngle();
        px = getInitialPosX();
        py = getInitialPosY();

        fa = getFixAngle();

        ra = pa;
        if(ra >Math.toRadians(360)){
            ra-=Math.toRadians(360);
        }
        if(ra < 0){
            ra +=Math.toRadians(360);
        }




       // for(r=0; r<rays; r++){

            //check horizontal lines
            dof = 0;
            double htan = (-1/Math.tan(ra));
            double hDist = 1000000, hx = 0, hy = 0;
            if(ra > Math.toRadians(180)) {
                ry = (((int) py/mapS) * mapS) - 0.001f;
                rx = (py - ry) * htan + px;
                yo = -mapS;
                xo = -yo * htan;
            }
            if(ra < Math.toRadians(180)) {
                ry = (((int) py /mapS) * mapS) + mapS;
                rx = (py - ry) * htan + px;
                yo =  mapS;
                xo = -yo * htan;

            }

            if(ra == 0|| ra == (float) Math.toRadians(360)) {
                rx = px;
                ry = py;
                dof = 32;

            }

            int hmx=0,hmy=0;
            while (dof<32){
                mx = (int) (rx)/mapS;
                my = (int) (ry)/mapS;

                if(mx < mapX && my < mapY && mx >= 0 && my >= 0 && map[my][mx] > 0){
                    hx = rx;
                    hy = ry;
                    hmy = my;
                    hmx = mx;
                    hDist = dist(px,py,hx,hy,ra);
                    dof=32;

                }else {
                    rx += xo;
                    ry += yo;
                    dof ++;
                }
            }



            //check vertical lines
            dof = 0;
            double vtan = (float) -Math.tan(ra);
            double vDist=1000000, vx=0, vy=0;

            if(ra > Math.toRadians(90) && ra < Math.toRadians(270)) {
                rx = (((int) px/mapS) * mapS) - 0.001f;
                ry = (px - rx) * vtan + py;
                xo = -mapS;
                yo = -xo * vtan;
            }
            if(ra < Math.toRadians(90) || ra > Math.toRadians(270)) {
                rx = (((int) px /mapS) * mapS) + mapS;
                ry = (px - rx) * vtan + py;
                xo =  mapS;
                yo = -xo * vtan;

            }
            if(ra == Math.toRadians(90) || ra == (float) Math.toRadians(270)) {
                rx = px;
                ry = py;
                dof = 32;

            }
        int vmx=0,vmy=0;
            while (dof<32){
                mx = (int) (rx)/mapS;
                my = (int) (ry)/mapS;

                if(mx < mapX && my < mapY && mx >= 0 && my >= 0 && map[my][mx] > 0){

                    vx = rx;
                    vy = ry;
                    vDist = dist(px,py,vx,vy, ra);

                    vmx = mx;
                    vmy = my;

                    dof=32;

                }else {
                    rx += xo;
                    ry += yo;
                    dof ++;
                }
            }

            //get final ray distance

            if(hDist > vDist){
                rx = vx;
                ry = vy;
                mx = vmx;
                my = vmy;
                fdist = vDist;
                setVertical(true);

            }
            if(hDist < vDist){
                rx = hx;
                ry = hy;
                mx = hmx;
                my = hmy;
                fdist = hDist;
                setVertical(false);
            }

            //fix fish eye

           double ca = fa - ra;
            if(ca >Math.toRadians(360)){
                ca-=Math.toRadians(360);
            }
            if(ca < 0){
                ca +=Math.toRadians(360);
            }
            fdist = fdist*Math.cos(ca);


            setDistance(fdist);
            setRayPosX(rx);
            setRayPosY(ry);
            setRayAngle(ra);
            setMapCell(map[my][mx]);
            //end

    }

    public double getInitialAngle() {
        return initialAngle;
    }

    public void setInitialAngle(double initialAngle) {
        this.initialAngle = initialAngle;
    }

    public double getInitialPosX() {
        return initialPosX;
    }

    public void setInitialPosX(double initialPosX) {
        this.initialPosX = initialPosX;
    }

    public double getInitialPosY() {
        return initialPosY;
    }

    public void setInitialPosY(double initialPosY) {
        this.initialPosY = initialPosY;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public double getRayPosX() {
        return rayPosX;
    }

    public void setRayPosX(double rayPosX) {
        this.rayPosX = rayPosX;
    }

    public double getRayPosY() {
        return rayPosY;
    }

    public void setRayPosY(double rayPosY) {
        this.rayPosY = rayPosY;
    }

    public double getRayAngle() {
        return rayAngle;
    }

    public void setRayAngle(double rayAngle) {
        this.rayAngle = rayAngle;
    }

    public double getFixAngle() {
        return fixAngle;
    }

    public void setFixAngle(double fixAngle) {
        this.fixAngle = fixAngle;
    }

    public int getMapCell() {
        return mapCell;
    }

    public void setMapCell(int mapCell) {
        this.mapCell = mapCell;
    }
}
