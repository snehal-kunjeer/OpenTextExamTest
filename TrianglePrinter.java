public class TrianglePrinter {
    public static void main(String[] args) {
        triangle(3, 4); // Base case: 3 rows, max 4 stars
        triangle(5, 2); // Bonus: any M x N
    }

    static void triangle(int M, int N) {
        for (int i = 1; i <= M; i++)
            System.out.println("*".repeat(Math.min(i, N)));
    }
}