import java.util.InputMismatchException;
import java.util.Scanner;

// EmployeeSystem represents a console-based Employee Management System.
// It allows the user to perform full CRUD operations (Create, Read, Update, Delete)
// using parallel arrays to store employee data.

public class EmployeeManagementSystem {

    // // Command line text colors
    final static String RED = "\u001b[31;1m";
    final static String BLUE = "\u001b[36;1m";
    final static String GREEN = "\u001b[32;1m";
    final static String RESET = "\u001b[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Database of employees using arrays
        int employeeNumber = 10; // Maximum capacity
        String[] name = new String[employeeNumber];
        int[] age = new int[employeeNumber];
        double[] monthlySalary = new double[employeeNumber];
        int empIndex = 0; // Tracks the current number of saved employees

        System.out.println(BLUE + "\n=== Employee Management System ===" + RESET);

        // Main application loop
        while (true) {
            System.out.println("\n" + BLUE + "- Enter 1 to show employee information" + RESET);
            System.out.println(BLUE + "- Enter 2 to add an employee" + RESET);
            System.out.println(BLUE + "- Enter 3 to update an employee" + RESET);
            System.out.println(BLUE + "- Enter 4 to remove an employee" + RESET);
            System.out.println(BLUE + "- Enter 0 to exit" + RESET);
            System.out.print("> ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Clear the previous input

                switch (option) {
                    case 1: showEmployeeInformation(scanner, name, age, monthlySalary, empIndex); break;
                    case 2: empIndex = addEmployee(scanner, name, age, monthlySalary, empIndex, employeeNumber); break;
                    case 3: updateEmployee(scanner, name, age, monthlySalary, empIndex); break;
                    case 4: empIndex = removeEmployee(scanner, name, age, monthlySalary, empIndex); break;
                    case 0: System.out.println(GREEN + "- Exiting system. Have a great day! :D" + RESET); System.exit(0);
                    default: System.out.println(RED + "*) Invalid option. Please try again." + RESET);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear the bad input to prevent infinite loops
                System.out.println(RED + "*) Invalid input! Please enter a number." + RESET);
            }
        }
    }

    // Displays the details of a specific employee chosen by the user.
    public static void showEmployeeInformation(Scanner scanner, String[] name, int[] age, double[] monthlySalary,
            int empIndex) {
        if (empIndex == 0) {
            System.out.println(RED + "*) There are no employees to show.\n" + RESET);
            return;
        }

        while (true) {
            boolean existence = false;
            System.out.println(BLUE + "\n[ Show Employee Information ]" + RESET);
            System.out.println("- Enter 0 to step back to the menu");
            System.out.println("- Enter the employee number to view details:");

            // Print a list of current employees
            for (int i = 0; i < empIndex; i++) {
                System.out.println("  " + (i + 1) + "- [" + name[i] + "]");
            }
            System.out.print("> ");

            try {
                int num = scanner.nextInt();
                scanner.nextLine();

                if (num == 0) {
                    return; // Exit method
                }

                // Search for the selected employee index
                for (int i = 0; i < empIndex; i++) {
                    if ((num - 1) == i) {
                        existence = true;
                        System.out.println(GREEN + "\n--- Employee Details ---" + RESET);
                        System.out.println("Name: " + name[i]);
                        System.out.println("Age: " + age[i]);
                        System.out.println("Monthly salary: $" + monthlySalary[i]);
                        System.out.println("Yearly salary: $" + (monthlySalary[i] * 12) + "\n");
                        break;
                    }
                }
                if (!existence) {
                    System.out.println(RED + "*) Employee not found." + RESET);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(RED + "*) Invalid input! Please enter a number." + RESET);
            }
        }
    }

    // Handles gathering and saving data for a new employee.
    public static int addEmployee(Scanner scanner, String[] name, int[] age, double[] monthlySalary, int empIndex,
            int employeeNumber) {
        // Prevent exceeding the array limits
        if (empIndex >= employeeNumber) {
            System.out.println(RED + "*) Database full! You can't add more employees." + RESET);
            return empIndex;
        }

        while (true) {
            System.out.println(BLUE + "\n[ Add New Employee ]" + RESET);
            System.out.print("- Enter the name: ");
            name[empIndex] = scanner.nextLine();

            try {
                System.out.print("- Enter the age: ");
                age[empIndex] = scanner.nextInt();
                scanner.nextLine();

                System.out.print("- Enter the monthly salary: ");
                monthlySalary[empIndex] = scanner.nextDouble();
                scanner.nextLine();

                // Display a summary for confirmation
                System.out.println(BLUE + "\n--- Confirmation ---" + RESET);
                System.out.println("Name: " + name[empIndex]);
                System.out.println("Age: " + age[empIndex]);
                System.out.println("Salary: $" + monthlySalary[empIndex]);

                System.out.println("\n- Enter 1 to save");
                System.out.println("- Enter 2 to discard");
                System.out.println("- Enter any other number to re-type the information");
                System.out.print("> ");

                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1) {
                    System.out.println(GREEN + "- Employee successfully added!" + RESET);
                    return ++empIndex; // Increase the count of total employees
                } else if (option == 2) {
                    // Erase the temporary data and cancel
                    age[empIndex] = 0;
                    name[empIndex] = null;
                    monthlySalary[empIndex] = 0.0;
                    System.out.println(RED + "- Addition cancelled." + RESET);
                    return empIndex;
                }
                // If any other number, the loop restarts to re-enter data

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(RED + "*) Invalid input! Age and salary must be numbers. Let's start over." + RESET);
                // Wipe the corrupted data before looping back
                age[empIndex] = 0;
                name[empIndex] = null;
                monthlySalary[empIndex] = 0.0;
            }
        }
    }

    // Allows modifying specific fields of an existing employee.
    public static void updateEmployee(Scanner scanner, String[] name, int[] age, double[] monthlySalary, int empIndex) {
        if (empIndex == 0) {
            System.out.println(RED + "*) There is no one to update.\n" + RESET);
            return;
        }

        while (true) {
            boolean existence = false;
            System.out.println(BLUE + "\n[ Update Employee ]" + RESET);
            System.out.println("- Enter 0 to step back to the menu");
            System.out.println("- Enter the employee number to update:");

            for (int i = 0; i < empIndex; i++) {
                System.out.println("  " + (i + 1) + "- [" + name[i] + "]");
            }
            System.out.print("> ");

            try {
                int num = scanner.nextInt();
                scanner.nextLine();

                if (num == 0) {
                    return;
                }

                // Locate the employee
                for (int i = 0; i < empIndex; i++) {
                    if ((num - 1) == i) {
                        existence = true;
                        System.out.println(GREEN + "\n--- Current Details ---" + RESET);
                        System.out.println("Name: " + name[i]);
                        System.out.println("Age: " + age[i]);
                        System.out.println("Salary: $" + monthlySalary[i] + "\n");

                        while (true) {
                            System.out.println("What do you want to update?");
                            System.out.println("- Enter 1 to update the name");
                            System.out.println("- Enter 2 to update the age");
                            System.out.println("- Enter 3 to update the salary");
                            System.out.print("> ");

                            try {
                                int option = scanner.nextInt();
                                scanner.nextLine();

                                if (option == 1) {
                                    System.out.print("- Enter the new name: ");
                                    name[i] = scanner.nextLine();
                                    System.out.println(GREEN + "- Name updated!" + RESET);
                                    break;
                                } else if (option == 2) {
                                    System.out.print("- Enter the new age: ");
                                    age[i] = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println(GREEN + "- Age updated!" + RESET);
                                    break;
                                } else if (option == 3) {
                                    System.out.print("- Enter the new salary: ");
                                    monthlySalary[i] = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println(GREEN + "- Salary updated!" + RESET);
                                    break;
                                } else {
                                    System.out.println(RED + "*) Invalid input." + RESET);
                                }
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                System.out.println(RED + "*) Invalid input! Please enter a number." + RESET);
                            }
                        }
                    }
                }
                if (!existence) {
                    System.out.println(RED + "*) Employee not found." + RESET);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(RED + "*) Invalid input! Please enter a number." + RESET);
            }
        }
    }

    // Handles deleting an employee and reorganizing the arrays to fill the gap.
    public static int removeEmployee(Scanner scanner, String[] name, int[] age, double[] monthlySalary, int empIndex) {
        int employeeNumber = empIndex; // Store original count for looping purposes

        if (empIndex == 0) {
            System.out.println(RED + "*) There is no one to remove.\n" + RESET);
            return empIndex;
        }

        while (true) {
            boolean existence = false;
            System.out.println(BLUE + "\n[ Remove Employee ]" + RESET);
            System.out.println("- Enter 0 to step back to the menu");
            System.out.println("- Enter the employee number to remove:");

            for (int i = 0; i < empIndex; i++) {
                System.out.println("  " + (i + 1) + "- [" + name[i] + "]");
            }
            System.out.print("> ");

            try {
                int num = scanner.nextInt();
                scanner.nextLine();

                if (num == 0) {
                    return empIndex;
                }

                // Locate the employee to delete
                for (int i = 0; i < empIndex; i++) {
                    if ((num - 1) == i) {
                        existence = true;

                        // Clear their data
                        age[i] = 0;
                        name[i] = null;
                        monthlySalary[i] = 0.0;

                        // Shift all remaining employees down by one slot to fill the gap
                        for (int l = i; l < employeeNumber; l++) {
                            // If we hit the absolute end of the array, just wipe it
                            if (l == (employeeNumber - 1)) {
                                age[l] = 0;
                                name[l] = null;
                                monthlySalary[l] = 0.0;
                                break;
                            }
                            // Otherwise, copy the data from the index above
                            age[l] = age[l + 1];
                            name[l] = name[l + 1];
                            monthlySalary[l] = monthlySalary[l + 1];
                        }

                        System.out.println(GREEN + "- Employee removed successfully." + RESET);
                        return --empIndex; // Decrease the count of total employees
                    }
                }
                if (!existence) {
                    System.out.println(RED + "*) Employee not found." + RESET);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(RED + "*) Invalid input! Please enter a number." + RESET);
            }
        }
    }
}