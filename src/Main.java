import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        MedicineDAO medicineDAO = new MedicineDAO();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            System.out.println("\n\nMedicine Record System");
            System.out.println("1. Add Medicine");
            System.out.println("2. View All Medicines");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice:");

            choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    addMedicine(br, medicineDAO);
                    break;
                case 2:
                    viewAllMedicines(medicineDAO);
                    break;
                case 3:
                    updateMedicine(br, medicineDAO);
                    break;
                case 4:
                    deleteMedicine(br, medicineDAO);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private static void addMedicine(BufferedReader br, MedicineDAO medicineDAO) throws Exception {
        System.out.print("Enter Medicine Name: ");
        String name = br.readLine();
        System.out.print("Enter Manufacturer: ");
        String manufacturer = br.readLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(br.readLine());
        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(br.readLine());

        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setManufacturer(manufacturer);
        medicine.setPrice(price);
        medicine.setQuantity(quantity);

        try {
            medicineDAO.addMedicine(medicine);
            System.out.println("Medicine added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nPress 1 for MAIN MENU");
        int x = Integer.parseInt(br.readLine());
    }

    private static void viewAllMedicines(MedicineDAO medicineDAO) {
        try {
            List<Medicine> medicines = medicineDAO.getAllMedicines();
            for (Medicine medicine : medicines) {
                System.out.println(medicine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Scanner scn = new Scanner(System.in);

        System.out.println("\nPress 1 for MAIN MENU");
        int x = scn.nextInt();
    }

    private static void updateMedicine(BufferedReader br, MedicineDAO medicineDAO) throws Exception{
        System.out.print("Enter Medicine ID to Update: ");
        int id = Integer.parseInt(br.readLine());
        System.out.print("Enter New Name: ");
        String name = br.readLine();
        System.out.print("Enter New Manufacturer: ");
        String manufacturer = br.readLine();
        System.out.print("Enter New Price: ");
        double price = Double.parseDouble(br.readLine());
        System.out.print("Enter New Quantity: ");
        int quantity = Integer.parseInt(br.readLine());

        Medicine medicine = new Medicine();
        medicine.setId(id);
        medicine.setName(name);
        medicine.setManufacturer(manufacturer);
        medicine.setPrice(price);
        medicine.setQuantity(quantity);

        try {
            medicineDAO.updateMedicine(medicine);
            System.out.println("Medicine updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nPress 1 for MAIN MENU");
        int x = Integer.parseInt(br.readLine());
    }

    private static void deleteMedicine(BufferedReader br, MedicineDAO medicineDAO) throws Exception {
        System.out.print("Enter Medicine ID to Delete: ");
        int id = Integer.parseInt(br.readLine());

        try {
            medicineDAO.deleteMedicine(id);
            System.out.println("Medicine deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nPress 1 for MAIN MENU");
        int x = Integer.parseInt(br.readLine());
    }
}
