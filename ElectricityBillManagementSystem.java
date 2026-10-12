import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    private int id;
    private String name;
    private String address;
    private int units;

    public Customer(int id, String name, String address, int units) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.units = units;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public int getUnits() { return units; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setUnits(int units) { this.units = units; }

    public double calculateBill() {
        if (units <= 100) return units * 3.00;
        if (units <= 200) return 100 * 3.00 + (units - 100) * 5.00;
        return 100 * 3.00 + 100 * 5.00 + (units - 200) * 7.00;
    }

    public void display() {
        System.out.printf("%-6d %-20s %-25s %-8d Rs. %.2f%n",
                id, name, address, units, calculateBill());
    }
}

public class ElectricityBillManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\\n=== Electricity Bill Management System ===");
            System.out.println("1. Add customer");
            System.out.println("2. View all customers and bills");
            System.out.println("3. Search customer by ID");
            System.out.println("4. Update customer");
            System.out.println("5. Delete customer");
            System.out.println("6. Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewCustomers();
                case 3 -> searchCustomer();
                case 4 -> updateCustomer();
                case 5 -> deleteCustomer();
                case 6 -> {
                    System.out.println("Thank you for using the system.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Customer name: ");
        String name = sc.nextLine().trim();
        System.out.print("Address: ");
        String address = sc.nextLine().trim();
        int units = readInt("Electricity units consumed: ");
        if (name.isEmpty() || address.isEmpty() || units < 0) {
            System.out.println("Name/address cannot be empty and units cannot be negative.");
            return;
        }
        Customer c = new Customer(nextId++, name, address, units);
        customers.add(c);
        System.out.printf("Customer added. Estimated bill: Rs. %.2f%n", c.calculateBill());
    }

    private static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customer records found.");
            return;
        }
        System.out.printf("%-6s %-20s %-25s %-8s %s%n",
                "ID", "Name", "Address", "Units", "Bill");
        System.out.println("--------------------------------------------------------------------------");
        for (Customer c : customers) c.display();
    }

    private static void searchCustomer() {
        int id = readInt("Enter customer ID: ");
        Customer c = findById(id);
        if (c == null) System.out.println("Customer not found.");
        else {
            System.out.printf("%-6s %-20s %-25s %-8s %s%n",
                    "ID", "Name", "Address", "Units", "Bill");
            c.display();
        }
    }

    private static void updateCustomer() {
        int id = readInt("Enter customer ID to update: ");
        Customer c = findById(id);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("New name (leave blank to keep current): ");
        String name = sc.nextLine().trim();
        System.out.print("New address (leave blank to keep current): ");
        String address = sc.nextLine().trim();
        int units = readInt("New units consumed (-1 to keep current): ");
        if (!name.isEmpty()) c.setName(name);
        if (!address.isEmpty()) c.setAddress(address);
        if (units >= 0) c.setUnits(units);
        else if (units != -1) {
            System.out.println("Invalid units; units were not changed.");
        }
        System.out.println("Customer record updated.");
    }

    private static void deleteCustomer() {
        int id = readInt("Enter customer ID to delete: ");
        Customer c = findById(id);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        customers.remove(c);
        System.out.println("Customer record deleted.");
    }

    private static Customer findById(int id) {
        for (Customer c : customers) if (c.getId() == id) return c;
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
