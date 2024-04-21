import java.util.Scanner;

public class RightTriangle extends Triangle{
    @Override
    public void SetTriangle(){
        Scanner in = new Scanner(System.in);
        int side_a = in.nextInt();
        int side_b = in.nextInt();
        int side_c = in.nextInt();
        if ((side_a + side_b > side_c && side_a + side_c > side_b && side_b + side_c > side_a) && (side_a * side_a + side_b * side_b == side_c * side_c)) {
            this.side_a = side_a;
            this.side_b = side_b;
            this.side_c = side_c;
        } else {
            System.out.println("Этот треугольник не прямоугольный, либо его не существует");
            SetTriangle();
        }
    }
}
