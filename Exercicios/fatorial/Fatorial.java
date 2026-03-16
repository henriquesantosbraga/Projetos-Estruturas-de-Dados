public class Fatorial {
    public static long calcularFatorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * calcularFatorial(n - 1);
    }

    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Fatorial de " + numero + " é: " + calcularFatorial(numero));
    }
}