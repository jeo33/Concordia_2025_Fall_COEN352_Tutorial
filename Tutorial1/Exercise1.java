public class Exercise1 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("wrong number of arguments");
            return;
        }

        System.out.printf("Hi %s, Welcome to COEN351!%n", args[0]);
    }
}
