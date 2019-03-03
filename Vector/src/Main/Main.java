package Main;

import fytyr.idea_projects.course_java.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(new double[]{2.5, 7.3, 6.8});
        Vector vector2 = new Vector(10, new double[]{3, 6.8, 3, 7});
        Vector emptyVector = new Vector(7);
        Vector copyVector = new Vector(vector);
        Vector vector3 = new Vector(2, new double[]{4, 6.7, 3.7});

        System.out.println(vector.toString());
        System.out.println(vector2.toString());
        System.out.println(emptyVector.toString());
        System.out.println(copyVector.toString());
        System.out.println(vector3.toString());

        System.out.println(vector3.getSize());

        vector.addVector(vector3);
        System.out.println(vector.toString());

        vector.subVector(vector3);
        System.out.println(vector.toString());

        vector.multiplyOnScalar(2);
        System.out.println(vector.toString());

        vector.turn();
        System.out.println(vector.toString());

        System.out.println(vector.getLength());

        vector.setComponent(1, -4);
        System.out.println(vector.getComponent(1));

        System.out.println(vector3.equals(new Vector(2, new double[]{4, 6.7, 3.7})));

        System.out.println(vector3.hashCode() + "; " + new Vector(2, new double[]{4, 6.7, 3.7}).hashCode());

        Vector vector4 = Vector.addition(vector2, vector3);
        System.out.println(vector4.toString());

        Vector vector5 = Vector.subtraction(vector2, vector3);
        System.out.println(vector5.toString());

        System.out.println(Vector.multiplicationScalar(vector2, vector3));
    }
}
