import java.util.Scanner;

public class Defihelman {
   
    public static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
           
            base = (base * base) % mod;
            exp = exp >> 1; 
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of P: ");
        int p = sc.nextInt();
        System.out.print("Enter the value of G: ");
        int g =sc.nextInt();
        System.out.print("Enter the value of XA: ");
        int xa =sc.nextInt();
        System.out.print("Enter the value of XB: ");
        int xb = sc.nextInt();
        sc.close();
        int ya =modExp(g, xa, p);
        int yb =modExp(g, xb, p);
        System.out.println("Value of YA: " + ya);
        System.out.println("Value of YB: " + yb);

        int kone = modExp(yb, xa, p);
        int ktwo = modExp(ya, xb, p);

        System.out.println("Shared Key computed by A: " + kone);
        System.out.println("Shared Key computed by B: " + ktwo);
    }
}
