package us.mattgreen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final static FileOutput outFile = new FileOutput("animals.txt");
    private final static FileInput inFile = new FileInput("animals.txt");

    public static void main(String[] args) {
        ArrayList<Talkable> zoo = new ArrayList<>();

        zoo.add(animalGenerator());

        for (Talkable thing : zoo) {
            printOut(thing);
        }
        outFile.fileClose();
        inFile.fileRead();
        inFile.fileClose();

        FileInput indata = new FileInput("animals.txt");
        String line;
        while ((line = indata.fileReadLine()) != null) {
            System.out.println(line);
        }
    }

    public static void printOut(Talkable p) {
        System.out.println(p.getName() + " says=" + p.talk());
        outFile.fileWrite(p.getName() + " | " + p.talk());
    }

    public static Talkable animalGenerator(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of animal is desired?");
        String desiredAnimal = scanner.nextLine();
        if(desiredAnimal.toLowerCase().equals("dog")){
            System.out.println("What is the dog's name?");
            String name = scanner.nextLine();
            System.out.println("Is the dog friendly? (Y/N)");
            String friendly = scanner.nextLine();
            if (friendly.toUpperCase().charAt(0) == 'Y'){
                return (new Dog(true, name));
            }else{
                return (new Dog(false, name));
                //The question was whether the dog was friendly, so if it's anything but yes, it will be false because
                //the dog is not friendly.
                }
            }else if(desiredAnimal.toLowerCase().equals("cat")){
                System.out.println("What is the cat's name?");
                String name = scanner.nextLine();
                System.out.println("How many mice has the cat slaughtered?");
                int victims;
                try {
                    victims = Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("Invalid input; zero will be assumed.");
                    //I have no explanation for why I just assumed zero other than I didn't feel like writing a loop.
                    victims = 0;
                }
                return (new Cat(victims, name));
            }else if(desiredAnimal.toLowerCase().equals("teacher")){
            System.out.println("What is the teacher's name?");
            String name = scanner.nextLine();
            System.out.println("How old is the teacher?");
            int age;
            try {
                age = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Invalid input; zero will be assumed.");
                //I have no explanation for why I just assumed zero other than I didn't feel like writing a loop.
                age = 0;
            }
            return (new Teacher(age, name));
        }else{
            return null;
        }
    }
}
