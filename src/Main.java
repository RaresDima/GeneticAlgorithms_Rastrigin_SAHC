import java.util.Random;
import java.util.Scanner;

class Main {

    private static int compute_fitness(int x){

        return (int)(Math.pow(x, 3) - (60 * Math.pow(x, 2)) + (900 * x) + 100);
    }

    public static void main(String[] args) {

        /**
         *   VARIABLE DECLARATION & INITIALIZATION
         */

        Random random_generator = new Random(System.currentTimeMillis());

        final int[] starting_bitset = {random_generator.nextInt(2),
                                       random_generator.nextInt(2),
                                       random_generator.nextInt(2),
                                       random_generator.nextInt(2),
                                       random_generator.nextInt(2)};

        bitset hilltop     = new bitset(5),
               max_hilltop = new bitset(5),
               sa_hilltop  = new bitset(5);

        Scanner input_stream = new Scanner(System.in);
        System.out.print("INPUT VALUE: ");

        int x = input_stream.nextInt();

        hilltop.from_int(x);
        max_hilltop.from_int(x);
        sa_hilltop.from_int(x);

        int max_fitness          = compute_fitness(hilltop.to_int()),
            fitness              = compute_fitness(hilltop.to_int()),
            current_best_fitness = compute_fitness(hilltop.to_int());

        /**
        *   SAHC ALGORITHM
        */

        System.out.println("\nSTARTING VALUE:\t\t" + max_hilltop.to_int() + "\t" + max_fitness + "\n");

        while (true) {

            hilltop.copy_from(max_hilltop);
            sa_hilltop.copy_from(max_hilltop);
            current_best_fitness = max_fitness;

            System.out.println("CURRENT VALUE:\t\t" + max_hilltop.to_int() + "\t" + max_fitness);

            //  NEIGHBOUR SELECTION
            for (int i = 0; i < hilltop.length; ++i) {

                hilltop.flip(i);

                fitness = compute_fitness(hilltop.to_int());

                System.out.println("  NEIGHBOUR_" + i + ":\t\t" + hilltop.to_int() + "\t" + fitness);

                if (fitness >= current_best_fitness) {
                    current_best_fitness = fitness;
                    sa_hilltop.copy_from(hilltop);
                }

                hilltop.flip(i);
            }

            System.out.println();

            if (current_best_fitness > max_fitness) {
                max_fitness = current_best_fitness;
                max_hilltop.copy_from(sa_hilltop);
            }
            else
                break;
        }

        /**
        *   RESULT DISPLAY
        */

        System.out.println("FITNESS:\t\t\t" + max_fitness);
        System.out.println("DECIMAL HILLTOP:\t" + max_hilltop.to_int());
        System.out.print("BINARY HILLTOP:\t\t");
        for (int i = 0; i < max_hilltop.length; ++i)
            System.out.print(max_hilltop.bits[i] + " ");

        System.out.println();
    }
}
