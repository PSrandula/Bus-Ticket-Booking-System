import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    String name;
    int seatNo;
    String source;
    String destination;

    public Booking(String name, int seatNo, String source, String destination) {
        this.name = name;
        this.seatNo = seatNo;
        this.source = source;
        this.destination = destination;
    }
}

class BusBookingSystem {
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Method to check if a seat number is already booked
    private boolean isSeatBooked(int seatNo) {
        for (Booking booking : bookings) {
            if (booking.seatNo == seatNo) {
                return true;
            }
        }
        return false;
    }

    // Method to validate if input is not empty or only whitespace
    private String validateNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Method to validate seat number input
    private int validateSeatNumber() {
        int seatNo = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter seat number (1–50): ");
            if (scanner.hasNextInt()) {
                seatNo = scanner.nextInt();
                scanner.nextLine();
                if (seatNo > 0 && seatNo <= 50 && !isSeatBooked(seatNo)) {
                    valid = true;
                } else if (isSeatBooked(seatNo)) {
                    System.out.println("This seat is already booked. Please choose another.");
                } else {
                    System.out.println("Invalid seat number. Please choose between 1 and 50.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return seatNo;
    }

    public void bookseat() {
        String name = validateNonEmptyInput("Enter your name: ");
        int seatNo = validateSeatNumber();
        String source = validateNonEmptyInput("Enter source: ");
        String destination = validateNonEmptyInput("Enter destination: ");

        Booking newBooking = new Booking(name, seatNo, source, destination);
        bookings.add(newBooking);
        System.out.println("\nYour seat is booked successfully!");
    }

    public void viewReservations() {
        if (bookings.isEmpty()) {
            System.out.println("No reservations made yet.");
            return;
        }

        System.out.println("\nAll reservations:");
        System.out.printf("%-10s %-10s %-15s %-15s%n", "Seat No:", "Name:", "Source:", "Destination:");
        for (Booking booking : bookings) {
            System.out.printf("%-10d %-10s %-15s %-15s%n", booking.seatNo, booking.name, booking.source, booking.destination);

        }
    }

    public void editReservation() {
        System.out.print("Enter seat number to edit: ");
        int seatToEdit = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.seatNo == seatToEdit) {
                String newName = validateNonEmptyInput("Enter new name: ");
                String newSource = validateNonEmptyInput("Enter new source: ");
                String newDestination = validateNonEmptyInput("Enter new destination: ");
                booking.name = newName;
                booking.source = newSource;
                booking.destination = newDestination;

                System.out.println("Reservation edited successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Reservation not found.");
        }
    }

    public void printTicket() {
        System.out.print("Enter seat number to print ticket: ");
        int seatToPrint = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.seatNo == seatToPrint) {
                System.out.println("Ticket for Seat No. " + booking.seatNo);
                System.out.println("Passenger Name: " + booking.name);
                System.out.println("Passenger Source: " + booking.source);
                System.out.println("Passenger Destination: " + booking.destination);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Reservation not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BusBookingSystem system = new BusBookingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---------------------------------------------------");
            System.out.println("  **********|| Bus Booking System ||**********");
            System.out.println("---------------------------------------------------");
            System.out.println("\n");
            System.out.println("1. Book a seat");
            System.out.println("2. View reservations");
            System.out.println("3. Edit a reservation");
            System.out.println("4. Print a ticket");
            System.out.println("5. Exit\n");
            System.out.print("Enter Your Choice: \n");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        system.bookseat();
                        break;
                    case 2:
                        system.viewReservations();
                        break;
                    case 3:
                        system.editReservation();
                        break;
                    case 4:
                        system.printTicket();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 5);

        scanner.close();
    }
}
