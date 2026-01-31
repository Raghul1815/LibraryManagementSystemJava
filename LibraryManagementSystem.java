import java.util.Scanner;

abstract class Student{
    String name;
    String RegNo;
    String Degree;
    String Dept;

    Student(String name, String RegNo, String Degree, String Dept){
        this.name = name;
        this.RegNo = RegNo;
        this.Degree = Degree;
        this.Dept = Dept;
    }
abstract void displayDetails();
}
class LibraryDetails extends Student{
    LibraryDetails(String name, String RegNo, String Degree, String Dept){
        super(name, RegNo, Degree, Dept);
    }
    void displayDetails() {
        System.out.println("\n\t\t------ Student Details ------\t\t\n");
        System.out.println("Name           : " + name);
        System.out.println("Register No    : " + RegNo);
        System.out.println("Degree         : " + Degree);
        System.out.println("Department     : " + Dept);
    }
}
class Journal {

    private String JournalName;
    private int totalBooks = 20;

    Journal(String journalName) {
        this.JournalName = journalName;
    }

    void issueBook(String studentName) throws Exception {
        if (totalBooks <= 0) {
            throw new Exception("Books not available in " + JournalName + " journal.");
        }
        totalBooks--;
        System.out.println(studentName + " has successfully taken a book from " + JournalName + " journal.");
    }
}
class Validator {

    static boolean isValidName(String name) {
        return name.matches("[A-Za-z ]+");
    }

    // Register Number: 7325-23CS094
    static boolean isValidRegister(String regNo) {
        return regNo.matches("7325(23|24|25|26)(CS|EC|ME|EE|IT|AD|CE|PT|BT|CY|AM)\\d{3}");
    }
}
class InvalidInputException extends Exception {

    InvalidInputException(String message) {
        super(message);
    }
}

class LibraryManagementSystem{
    public static void main(String[] args) {
      
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            if (!Validator.isValidName(name)) {
                throw new InvalidInputException(
                        "Name does not allow any special characters or numbers.");
            }

            System.out.print("Enter Register Number: ");
            String regNo = sc.nextLine();
            if (!Validator.isValidRegister(regNo)) {
                throw new InvalidInputException(
                        "Invalid Register Number Format");
            }

            System.out.println("\nChoose Degree:");
            System.out.println("1. BE");
            System.out.println("2. BTech");
            System.out.print("Enter choice: ");
            int degreeChoice = sc.nextInt();
            sc.nextLine();

            String degree;
            switch (degreeChoice) {
                case 1:
                    degree = "BE";
                    break;
                case 2:
                    degree = "BTech";
                    break;
                default:
                    throw new InvalidInputException("Invalid Degree Choice");
            }

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

           
            Student student = new LibraryDetails(
                    name, regNo, degree, department);

            student.displayDetails();

            Journal science = new Journal("Science Fiction");
            Journal coding = new Journal("Coding");
            Journal agriculture = new Journal("Agriculture");
            Journal foodSafety = new Journal("Food Safety");
            Journal history = new Journal("History");

            System.out.println("\nJournals Available:");
            System.out.println("1. Science Fiction");
            System.out.println("2. Coding");
            System.out.println("3. Agriculture");
            System.out.println("4. Food Safety");
            System.out.println("5. History");

            System.out.print("Choose Journal (1-5): ");

            int journalChoice = sc.nextInt();

            Journal selectedJournal;

            switch (journalChoice) {
                case 1:
                    selectedJournal = science;
                    break;
                case 2:
                    selectedJournal = coding;
                    break;
                case 3:
                    selectedJournal = agriculture;
                    break;
                case 4:
                    selectedJournal = foodSafety;
                    break;
                case 5:
                    selectedJournal = history;
                    break;
                default:
                    throw new InvalidInputException("Invalid Journal Choice");
            }

            selectedJournal.issueBook(name);
        }
        
        catch (InvalidInputException e) {
            System.out.println("INPUT ERROR: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("SYSTEM ERROR: " + e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
