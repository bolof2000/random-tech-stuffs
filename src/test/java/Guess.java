import java.util.Scanner;

public class Guess {

    public static void main(String[] argv){

        while (true){
            System.out.print("Command > ");
            Scanner in = new Scanner(System.in);   // accepting data from user
            String cmd = in.next();

            if(cmd.equals("F2C")){
                double f = in.nextDouble();
                double c = (f-32)*5./9.;
                System.out.println("Fahrenheit: " + f +" " + "Celsius " + c);
            }
           else if(cmd.equals("C2F")){

                double c = in.nextDouble();
                double f = (c * (9/5)) + 32;
                System.out.println("Celsius:" + c + "Fahrenheit" + f );
            }

            else {
                System.out.println("Not possible conversion try again");

            }
            break;


        }

    }
}
