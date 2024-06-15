import java.util.ArrayList;

class StudyRoom{
    private int roomNumber;
    private int capacity;
    private boolean isAvailable;

    public StudyRoom(int roomNumber, int capacity){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.isAvailable = true;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getIsAvailable(){
        if (isAvailable){
            return "Available";
        }
        else{
            return "Not Available";
        }
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

}

public class StudyRoomReservationSystem{

    // Reserves the study room with the specified room number if it is available
    public synchronized void reserveStudyRoom(int roomNumber){
        for (StudyRoom room :studyRooms){
            if (room.getRoomNumber()==roomNumber){
                room.setIsAvailable(false);
            }
        }
    }

    // Releases the study room with the specified room number
    public synchronized void releaseStudyRoom(int roomNumber){
        for (StudyRoom room :studyRooms){
            if (room.getRoomNumber()==roomNumber){
                room.setIsAvailable(true);
            }
        }
    }

    // Displays the status (availability) of all study rooms
    public void displayStudyRoomStatus(){
        for (StudyRoom room :studyRooms){
            System.out.println("Room Number: " + room.getRoomNumber() + "Capacity: " + room.getCapacity() + "Availability: " + room.getIsAvailable());
        }
        }


    public static void main(String[] args) {
        StudyRoomReservationSystem reservationSystem = new StudyRoomReservationSystem();
        public ArrayList<StudyRoom> studyRooms = new ArrayList<StudyRoom>();
        // Create StudyRoom objects
        studyRooms.add(new StudyRoom(1, 4));
        studyRooms.add(new StudyRoom(2, 6));
        studyRooms.add(new StudyRoom(3, 8));

        reservationSystem.displayStudyRoomStatus();


    Runnable room1 = new Runnable(){
        public void run(){
        try{
            thread1.sleep(100);
            reserveStudyRoom(1);
        }
        catch(Exception StudyRoomReservationSystemException){
            StudyRoomReservationSystemException();
        }
        releaseStudyRoom(1);
    }
    };
    Thread thread1 = new Thread(room1);

    Runnable room2 = new Runnable(){
        public void run(){
        try{
            reserveStudyRoom(2);
            thread1.sleep(100);
        }
        catch(Exception StudyRoomReservationSystemException){
            StudyRoomReservationSystemException();
        }
        releaseStudyRoom(2);
    }
    };
    Thread thread2 = new Thread(room2);

    Runnable room3 = new Runnable(){
        public void run(){
        try{
            thread1.sleep(100);
            reserveStudyRoom(3);
        }
        catch(Exception StudyRoomReservationSystemException){
            StudyRoomReservationSystemException();
        }
        releaseStudyRoom(3);
        }
    };
    Thread thread3 = new Thread(room3);

    thread1.start();
    thread2.start();
    thread3.start();

}
}

// This custom exception class throws when a reservation is attempted for a study room that is already occupied
StudyRoomReservationSystemException extends Exception  {
    public void StudyRoomReservationSystemException(){
        System.out.println("Study Room is already occupied");
    }
}