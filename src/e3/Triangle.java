package e3;

public record Triangle(float angle0, float angle1, float angle2) {
    public Triangle{
        if(angle0+angle1+angle2!=180)
            throw new IllegalArgumentException();
    }
    public Triangle(Triangle t){
        this(t.angle0, t.angle1, t.angle2);
    }
    public boolean isRight(){   //Algún ángulo mide 90º
        return this.angle0 == 90 || this.angle1 == 90 || this.angle2 == 90;
    }
    public boolean isAcute(){   //Todos los ángulos miden menos de 90º
        return this.angle0 < 90 && this.angle1 < 90 && this.angle2 < 90;
    }
    public boolean isObtuse(){  //Al menos un ángulo mide más de 90º
        return this.angle0 > 90 || this.angle1 > 90 || this.angle2 > 90;
    }
    public boolean isEquilateral(){ //Todos los ángulos miden lo mismo
        return this.angle0 == this.angle1 && this.angle0 == this.angle2;
    }
    public boolean isIsosceles(){   //Exactamente dos ángulos miden lo mismo
        if ((this.angle0 == this.angle1)  && (this.angle0!=this.angle2)) return true;
        else if ((this.angle0 == this.angle2) && (this.angle0 != this.angle1)) return true;
        else return (this.angle1 == this.angle2) && (this.angle1 != this.angle0);
    }
    public boolean isScalene(){ //Todos los ángulos son distintos
        return (this.angle0 != this.angle1) && (this.angle0 != this.angle2) && (this.angle1 != this.angle2);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (getClass()!=o.getClass()) return false;

        Triangle t = (Triangle) o;
        return (angle0 == t.angle0 || angle0 == t.angle1 || angle0 == t.angle2)
                && (angle1 == t.angle0 || angle1 == t.angle1 || angle1 == t.angle2)
                && (angle2 == t.angle0 || angle2 == t.angle1 || angle2 == t.angle2);
        }

    @Override
    public int hashCode(){
        return (int)(angle0*angle1*angle2);
    }
}