// public class RailwayReservationSystem {
    import java.util.*;

class Train {
    String trainName;
    int availableSeats;

    public Train(String trainName, int availableSeats) {
        this.trainName = trainName;
        this.availableSeats = availableSeats;
    }
}

class Passenger {
    String passengerName;
    String trainName;
    int seatNumber;

    public Passenger(String passengerName, String trainName, int seatNumber) {
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
    }
}

public class RailwayReservationSystem {
    private static Map<String, Train> trains = new HashMap<>();
    private static Map<String, Passenger> reservations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize trains
        trains.put("Train 1", new Train("Train 1", 100));
        trains.put("Train 2", new Train("Train 2", 150));

        while (true) {
            System.out.println("Railway Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Check Availability");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    cancelTicket(scanner);
                    break;
                case 3:
                    checkAvailability(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();

        if (!trains.containsKey(trainName)) {
            System.out.println("Train not found. Please try again.");
            return;
        }

        Train train = trains.get(trainName);
        if (train.availableSeats <= 0) {
            System.out.println("No seats available. Please try again.");
            return;
        }

        int seatNumber = train.availableSeats;
        train.availableSeats--;

        Passenger passenger = new Passenger(passengerName, trainName, seatNumber);
        reservations.put(passengerName, passenger);

        System.out.println("Ticket booked successfully!");
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Train Name: " + trainName);
        System.out.println("Seat Number: " + seatNumber);
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        if (!reservations.containsKey(passengerName)) {
            System.out.println("Passenger not found. Please try again.");
            return;
        }

        Passenger passenger = reservations.get(passengerName);
        Train train = trains.get(passenger.trainName);

        train.availableSeats++;
        reservations.remove(passengerName);

        System.out.println("Ticket cancelled successfully!");
    }

    private static void checkAvailability(Scanner scanner) {
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();

        if (!trains.containsKey(trainName)) {
            System.out.println("Train not found. Please try again.");
            return;
        }

        Train train = trains.get(trainName);
        System.out.println("Available seats: " + train.availableSeats);
    }
}

// }
