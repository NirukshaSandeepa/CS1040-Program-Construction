import java.util.Scanner;
public class SwimmingCompetition{

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of swimmers");
        int number = input.nextInt();
        Swimmer[] swimmers = new Swimmer[number];
        for (int i=0;i<number;i++){
            System.out.println("Enter the name of swimmer " + (i+1) + ": ");
            String Name =  input.nextLine();
            input.nextLine();
            System.out.println("Enter the ID number of swimmer : ");
            long ID =  input.nextLong();
            System.out.println("Enter true if the swimmer is male if not, enter false : ");
            boolean maleOrNot =  input.nextBoolean();
            swimmers[i] = new Swimmer(Name,ID,maleOrNot);
        }

        System.out.println("Enter number of judges");
        number = input.nextInt();
        Judges[] judges = new Judges[number];
        for (int i=0;i<number;i++){
            System.out.println("Enter the name of judge " + (i+1) + ": ");
            String Name =  input.nextLine();
            input.nextLine();
            System.out.println("Enter the ID number of judge : ");
            long ID =  input.nextLong();
            judges[i] = new Judges(Name,ID);
        }

        System.out.println("Enter number of spectators");
        number = input.nextInt();
        Spectators[] spectators = new Spectators[number];
        for (int i=0;i<number;i++){
            System.out.println("Enter the name of spectator " + (i+1) + ": ");
            String Name =  input.nextLine();
            input.nextLine();
            System.out.println("Enter the ID number of spectator : ");
            long ID =  input.nextLong();
            spectators[i] = new Spectators(Name,ID);
        }

        System.out.println("Enter number of supporting staff members");
        number = input.nextInt();
        SupportingStaff[] supportingStaff = new SupportingStaff[number];
        for (int i=0;i<number;i++){
            System.out.println("Enter the name of supporting staff member " + (i+1) + ": ");
            String Name =  input.nextLine();
            input.nextLine();
            System.out.println("Enter the ID number of supporting staff member : ");
            long ID =  input.nextLong();
            supportingStaff[i] = new SupportingStaff(Name,ID);
        }

        Judges Judge1 = new Judges(judges[0].name,judges[0].IDNumber);
        Judge1.blow();
    }
}

class Swimmer{
    String name;
    long IDNumber;
    boolean isMale;

    public Swimmer(String name, long IDNumber, boolean isMale){
        this.name = name;
        this.IDNumber = IDNumber;
        this.isMale = isMale;
    }

    void swim(){
        System.out.println("The swimmers are swimming right now!");
    }
}

class Judges{
    String name;
    long IDNumber;
    
    public Judges(String name, long IDNumber){
        this.name = name;
        this.IDNumber = IDNumber;
    }

    void blow(){
        System.out.println("Judge blew the whistle!");
    }

}

class Spectators{
    String name;
    long IDNumber;
    
    public Spectators(String name, long IDNumber){
        this.name = name;
        this.IDNumber = IDNumber;
    }
}

class SupportingStaff{
    String name;
    long IDNumber;
    
    public SupportingStaff(String name, long IDNumber){
        this.name = name;
        this.IDNumber = IDNumber;
    }
}