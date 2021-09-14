package shapes_task;

public final class Triangle extends Shape {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double sideA, double sideB, double sideC, String color) {
        super(color);

        if (Double.isNaN(heron(sideA, sideB, sideC))) {
            throw new IllegalArgumentException("This is not triangle");
        }

        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public boolean isRightTriangle() {
        //Such a comparison is necessary, since we are dealing with a floating point numbers.
        return Math.abs((Math.pow(getSideA(), 2) + Math.pow(getSideB(), 2)) - Math.pow(getSideC(), 2)) < Shape.PRECISION_DELTA;
    }

    public double getHypotenuse() {
        if (isRightTriangle()) {
            return getSideC();
        } else {
            throw new IllegalStateException("To have a hypotenuse triangle must be the right"); //https://en.wikipedia.org/wiki/Hypotenuse
        }
    }

    @Override
    public void draw() {
        super.draw();
        if (isRightTriangle()) {
            System.out.println("Hypotenuse length: " + getHypotenuse());
        } else {
            System.out.println("This triangle have't hypotenuse, sorry.");
        }
        System.out.println("Side A: " + getSideA());
        System.out.println("Side B: " + getSideB());
        System.out.println("Side C: " + getSideC());
    }

    private double heron(double a, double b, double c) {
        //https://en.wikipedia.org/wiki/Heron%27s_formula
        double hapfP = (a + b + c) / 2;
        return Math.sqrt(hapfP * (hapfP - a) * (hapfP - b) * (hapfP - c));
    }

    @Override
    public double getArea() {
        if (isRightTriangle()) {
            return (getSideA() * getSideB()) / 2;
        } else {
            //Using Heron's formula
            return heron(getSideA(), getSideB(), getSideC());
        }
    }
}
