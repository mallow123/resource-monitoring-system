import service.ResourceService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ResourceService service = new ResourceService();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n==== RESOURCE MONITOR PRO ====");
            System.out.println("1. Add Log");
            System.out.println("2. View Logs");
            System.out.println("3. View Detailed Logs");
            System.out.println("4. High CPU Systems");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch(ch) {
                case 1: service.add(); break;
                case 2: service.view(); break;
                case 3: service.viewDetailed(); break;
                case 4: service.highCPU(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }
}
