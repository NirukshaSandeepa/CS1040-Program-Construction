/*
Index No: 210571L
Name    : SANDEEPA H.N.A
*/

import java.util.Scanner; //To prevent the .exe file closing suddenly when opens it, we have to take some user input(in this program -press Enter) to stop that. The program will wait for user to press Enter.

//Interface for backup artists
interface IBackup {
    public void backup(); // Method for backup
}

//Abstract class for artists
abstract class Artist {
    String name; // Name of the artist

    // Constructor for the Artist class
    public Artist(String name) {
        this.name = name;
    }

    // Getter for the name
    public String getName() {
        return name;
    }
}

//Class for singers, inherits from Artist
class Singer extends Artist {
    // Constructor for the Singer class
    public Singer(String name) {
        super(name);
}

    // Method for singing
    public void sing() {
        System.out.println("This is the method sing() in the class Singer.");
}
}

//Class for music tracks
class MusicTrack {
    String name; // Name of the music track
    int duration; // Duration of the music track in seconds

    // Constructor for the MusicTrack class
    public MusicTrack(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    // Getter for the name of the music track
    public String getName() {
        return name;
    }

    // Getter for the duration of the music track
    public int getDuration() {
        return duration;
    }
}

// Class for backup singers, inherits from Singer and implements IBackup
class BackupSinger extends Singer implements IBackup {
    // Constructor for the BackupSinger class
    public BackupSinger(String name) {
        super(name);
    }

    // Method for backup singing
    public void backup() {
        System.out.println("This is the method backup() in the class BackupSinger.");
    }
}

// Class for backup dancers, inherits from Artist and implements IBackup
class BackupDancer extends Artist implements IBackup {
    // Constructor for the BackupDancer class
    public BackupDancer(String name) {
        super(name);
    }

    // Method for backup dancing
    public void backup() {
        System.out.println("This is the method backup() in the class BackupDancer.");
    }
}

//Abstract class for performances
abstract class Performance {
    String mainArtist; // Main artist of the performance
    String performanceName; // Name of the performance
    int year; // Year of the performance
    String venue; // Venue of the performance
    MusicTrack[] trackList; // Array of music tracks in the performance
    BackupSinger[] backupSingers; // Array of backup singers in the performance
    BackupDancer[] backupDancers; // Array of backup dancers in the performance

    // Constructor for the Performance class
    public Performance(String mainArtist, String performanceName, int year, String venue) {
        this.mainArtist = mainArtist;
        this.performanceName = performanceName;
        this.year = year;
        this.venue = venue;
    }

    //Getter of the name of the main artist
    public String getMainArtist() {
        return mainArtist;
    }

    //Getter of the name of the performance
    public String getPerformanceName() {
        return performanceName;
    }

    //Setter of the name of the performance
    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    //Getter of the year
    public int getYear() {
        return year;
    }

    //Getter of the venue
    public String getVenue() {
        return venue;
    }

    // Method for printing welcome message
    public void welcomeMessage() {
        System.out.println("Welcome to the Performance " + performanceName + " by " + mainArtist + "!");
    }

    // Abstract method for starting the performance
    abstract public void startPerformance(); // Abstract method for starting the performance
}

//Class for live performances, inherits from Performance
class LivePerformance extends Performance {
    // Constructor for the LivePerformance class
    public LivePerformance(String mainArtist, String performanceName, int year, String venue,
    MusicTrack[] trackList, BackupSinger[] backupSingers, BackupDancer[] backupDancers) {
        super(mainArtist, performanceName, year, venue);
        this.trackList = trackList;
        this.backupSingers = backupSingers;
        this.backupDancers = backupDancers;
    }

    //Method for starting the performance
    public void startPerformance(){
        welcomeMessage();
        Singer singer = new Singer(mainArtist);
        singer.sing();

        for (BackupSinger backupSinger : backupSingers) {
            backupSinger.backup();
        }

        for (BackupDancer backupDancer : backupDancers) {
            backupDancer.backup();
        }
    }
}

//Class for studio performances, inherits from Performance
class StudioPerformance extends Performance {
    // Constructor for the StudioPerformance class
    public StudioPerformance(String mainArtist, String performanceName, int year, String venue,
    MusicTrack[] trackList, BackupSinger[] backupSingers, BackupDancer[] backupDancers) {
        super(mainArtist, performanceName, year, venue);
        this.trackList = trackList;
        this.backupSingers = backupSingers;
        this.backupDancers = backupDancers;
    }

    // Method for starting the performance
    public void startPerformance() {
        System.out.println("This is the method startPerformance() in the class StudioPerformance.");
    }
}
//Main class for the program
public class MusicalPerformance {
    public static void main(String[] args) {
        // Create a live performance for the Eras Tour by Taylor Swift
        MusicTrack[] trackList = { new MusicTrack("Lavender Haze", 240),
        new MusicTrack("All Too Well", 213),
        new MusicTrack("The lakes", 219),
        new MusicTrack("The Man", 231),
        new MusicTrack("Love Story", 211) };
        BackupSinger[] backupSingers = { new BackupSinger("Jeslyn"),
        new BackupSinger("Melanie") };
        BackupDancer[] backupDancers = { new BackupDancer("Stephanie"),
        new BackupDancer("Jake") };
        LivePerformance ErasTour = new LivePerformance("Taylor Swift", "Eras Tour", 2023, "Glendale",
        trackList, backupSingers, backupDancers);
        ErasTour.startPerformance(); //Start the performance


        System.out.println("\nPress Enter to exit the program");

        // Create a Scanner object to wait for user input
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for user to press Enter

        // Close the Scanner object
        scanner.close();

        // Exit the program
        System.exit(0);

        /* Outputs:
        Welcome to the Performance Eras Tour by Taylor Swift!
        This is the method sing() in the class Singer.
        This is the method backup() in the class BackupSinger.
        This is the method backup() in the class BackupSinger.
        This is the method backup() in the class BackupDancer.
        This is the method backup() in the class BackupDancer. */
        // Note:Since there are two backup singers and two backup dancers in the performance, the each of  backup() methods in BackupSinger class and BackupDancer class calls two times. That's why each of tthese print two times.
    }
}