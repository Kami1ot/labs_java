import java.util.Scanner;

public class Triangle {
    public int side_a;
    public int side_b;
    public int side_c;

    public void SetTriangle(){
        Scanner in = new Scanner(System.in);
        System.out.print("Сторона а: ");
        int side_a = in.nextInt();
        System.out.print("Сторона b: ");
        int side_b = in.nextInt();
        System.out.print("Сторона c: ");
        int side_c = in.nextInt();
        if (side_a + side_b > side_c && side_a + side_c > side_b && side_b + side_c > side_a) {
            this.side_a = side_a;
            this.side_b = side_b;
            this.side_c = side_c;
        } else {
            System.out.println("Такой треугольник не существует");
            SetTriangle();
        }

    }
    public float getPerimeter(){
        return side_b + side_a + side_c;
    }

    public double getSquare() {
        float p = (float) getPerimeter() /2;
        return Math.sqrt(p * (p - side_a) * (p - side_b) * (p - side_c));
    }

    public int getSide_a() {
        return side_a;
    }

    public int getSide_b() {
        return side_b;
    }

    public int getSide_c() {
        return side_c;
    }
}
