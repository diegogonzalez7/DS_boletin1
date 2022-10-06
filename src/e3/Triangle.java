package e3;

public record Triangle(float angle0, float angle1, float angle2) {
    public Triangle{
        if(angle0+angle1+angle2!=180)
            throw new IllegalArgumentException();
    }
    public Triangle(Triangle t){
        this(t.angle0, t.angle1, t.angle2);
    }
    public boolean isRight(){
        return this.angle0 == 90 || this.angle1 == 90 || this.angle2 == 90;
    }
    public boolean isAcute(){
        return this.angle0<90 && this.angle1<90 && this.angle2<90;
    }
    public boolean isObtuse(){
        return this.angle0 > 90 || this.angle1 > 90 || this.angle2 > 90;
    }
    public boolean isEquilateral(){
        return this.angle0== this.angle1 && this.angle0 == this.angle2;
    }
    public boolean isIsosceles(){
        return ((this.angle0 == this.angle1)  && (this.angle0!=this.angle2)) ||
                ((this.angle0 == this.angle2) && (this.angle0 != this.angle1)) ||
                ((this.angle1 == this.angle2) && (this.angle1 != this.angle0));

    }
    public boolean isScalene(){
        return (this.angle0 != this.angle1) && (this.angle0 != this.angle2) && (this.angle1 != this.angle2) ;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (getClass()!=o.getClass()) return false;

        Triangle t = (Triangle) o;
        if (angle0 == t.angle0 || angle0 == t.angle1 || angle0 == t.angle2 ) {
            if (angle1 == t.angle0 || angle1 == t.angle1 || angle1 == t.angle2) {
                return angle2 == t.angle0 || angle2 == t.angle1 || angle2 == t.angle2;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (int)(angle0*angle1*angle2);
    }
}
