// Importing the required modules all together...

import java.io.*;
import java.util.*;


public class SMS {
    public static void main(String[] arga) {

        Scanner sc = new Scanner(System.in);

        System.out.println("----WELCOME TO STUDENTS' MANAGEMENT SYSTEM----");

        StudentService service = new StudentService(sc);

        while (true) {

            // MENU...

            System.out.println("1 -> Add Students' name");
            System.out.println("2 -> Add Details");
            System.out.println("3 -> Display all Names");
            System.out.println("4 -> Display all Students' Details");
            System.out.println("5 -> Display details of entered student");
            System.out.println("6 -> Delete entered students' names");
            System.out.println("7 -> Delete entered students' details");
            System.out.println("8 -> Update details");
            System.out.println("0 -> Exit the system");
            System.out.print("ENTER THE CODE HERE -> ");

            int user = sc.nextInt();
            sc.nextLine();


            switch (user) {

                case 1:
                    service.AddNames();
                    break;

                case 2:
                    service.AddDetails();
                    break;

                case 3:
                    service.DisplayNames();
                    break;

                case 4:
                    service.DisplayDetails();
                    break;

                case 5:
                    service.SelectiveDetails();
                    break;

                case 6:
                    service.DeleteNames();
                    break;

                case 7:
                    service.DeleteDetails();
                    break;

                case 8:
                    service.UpdateDetails();
                    break;

                case 0:
                    System.out.println("Exiting System...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}

// All operations are inside the class...

class StudentService {

    Scanner sc;

    // To increase the reusability of Scanner...

    StudentService(Scanner sc) {
        this.sc = sc;
    }

    // To add names...

    void AddNames() {

        File file = new File("Data.txt");

        try {
            boolean created = file.createNewFile();

            if (created) {
                System.out.println("New File named Data.txt has been created successfully...");
            } else {
                System.out.println("File named Data.txt exists, let's add data to it...");
            }

            FileWriter writer = new FileWriter(file, true);

            System.out.println("Enter the names from the next line (Write 'q' to stop writing):");

            while (true) {

                String name = sc.nextLine().trim();

                if (name.equalsIgnoreCase("q")) {
                    break;
                }

                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty, please enter a valid name...");
                    continue;
                }

                writer.write(name + "\n");
            }

            System.out.println("All changes have been saved successfully...");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // To add details...

    void AddDetails() {

        File DetFile = new File("Details.txt");

        try {
            DetFile.createNewFile();

            // Check if Data.txt exists
            File nameFile = new File("Data.txt");
            if (!nameFile.exists()) {
                System.out.println("No students found! Add names first.");
                return;
            }

            ArrayList<String> names = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(nameFile));
            String line;

            while ((line = reader.readLine()) != null) {
                names.add(line.trim());
            }
            reader.close();

            System.out.print("Enter the student's name whose details you want to save -> ");
            String match = sc.nextLine().trim();

            boolean found = false;

            for (String name : names) {

                if (name.trim().equalsIgnoreCase(match)) {

                    found = true;

                    BufferedReader check = new BufferedReader(new FileReader(DetFile));
                    String l;
                    boolean exists = false;

                    while ((l = check.readLine()) != null) {
                        if (l.startsWith(name + ",")) {
                            exists = true;
                            break;
                        }
                    }
                    check.close();

                    if (exists) {
                        System.out.println("Details for the entered student already exist...");
                        return;
                    }

                    System.out.println("WRITE DETAILS FOR THE STUDENT " + name + ":");

                    System.out.print("Enter Roll Number -> ");
                    String rnum = sc.nextLine();

                    System.out.print("Enter Mobile Number -> ");
                    String mnum = sc.nextLine();
                    if (mnum.length() != 10) {
                        System.out.println("Invalid Mobile Number...");
                        return;
                    }

                    System.out.print("Enter course -> ");
                    String course = sc.nextLine();
                    if(course.trim().isEmpty()){
                        System.out.println("Course cannot be empty.");
                        return;
                    }

                    System.out.print("Enter Address -> ");
                    String adr = sc.nextLine().trim();

                    if (adr.isEmpty()) {
                        System.out.println("Address cannot be empty.");
                        return;
                    }

                    String record = name + "," + rnum + "," + mnum + ","+course+","+ adr;

                    BufferedWriter writer = new BufferedWriter(new FileWriter(DetFile, true));

                    writer.write(record);
                    writer.newLine();
                    writer.close();

                    System.out.println("Details saved successfully...");
                    return;
                }
            }

            if (!found) {
                System.out.println("Student not found!");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // To display all names...

    void DisplayNames(){

        File DispFile = new File("Data.txt");

        if(!DispFile.exists()){
            System.out.println("No name found.Add names first...");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DispFile));
            String line;
            boolean empty = true;

            System.out.println("\n--- STUDENTS NAMES ---");

            while((line = reader.readLine()) != null){
                empty = false;
                System.out.println(line);
            }

            if(empty){
                System.out.println("No names stored yet...");
            }

            System.out.println("---------------\n");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // To display all students' details...

    void DisplayDetails(){

        File DispDetails = new File("Details.txt");

        if(!DispDetails.exists()){
            System.out.println("No detail found. Add details first...");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DispDetails));

            String line;
            boolean empty = true;

            System.out.println("\n--- STUDENTS DETAILS ---");

            while((line = reader.readLine()) != null){
                empty = false;

                String[] parts = line.split(",");

                if(parts.length < 5) {
                    System.out.println("Corrupted record: " + line);
                    continue;
                }

                System.out.println("NAME -> "+parts[0]);
                System.out.println("ROLL NUMBER -> "+parts[1]);
                System.out.println("MOBILE NUMBER -> "+parts[2]);
                System.out.println("COURSE -> "+parts[3]);
                System.out.println("ADDRESS -> "+parts[4]);

                System.out.println("--------------------------------");

            }
            if(empty){
                System.out.println("No Details stored yet...");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // To display information of entered student...

    void SelectiveDetails(){

        File SelFile = new File("Details.txt");
        if(!SelFile.exists()){
            System.out.println("No detail found. Add details first...");
            return;
        }

        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(SelFile));
            System.out.print("Enter the name of the student whose details you want to print -> ");
            String entry = sc.nextLine().trim();

            String line;
            boolean found = false;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                if(parts[0].equalsIgnoreCase(entry)){
                    found = true;

                    System.out.println("\n--- STUDENT - "+entry+" DETAILS ---");
                    System.out.println("NAME -> "+parts[0]);
                    System.out.println("ROLL NUMBER -> "+parts[1]);
                    System.out.println("MOBILE NUMBER -> "+parts[2]);
                    System.out.println("COURSE ->  "+parts[3]);
                    System.out.println("ADDRESS -> "+parts[4]);
                    System.out.println("-------------------------------");

                    break;
                }
            }

            if(!found){
                System.out.println("No details found for "+entry);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    // To delete entered name...

    void DeleteNames(){

        File DelFile = new File("Data.txt");

        if(!DelFile.exists()){
            System.out.println("No name exists...");
            return;
        }
        try {
            ArrayList <String> names = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(DelFile));

            String line;

            while((line = reader.readLine()) != null){
                names.add(line.trim());
            }
            reader.close();

            while (true) { 
                
                System.out.println("Enter names you want to delete (write 'q' to stop) : ");
                String target = sc.nextLine();

                if(target.equalsIgnoreCase("q")){
                    break;
                }

                boolean exists = false;

                for(String n : names){
                    if(n.equalsIgnoreCase(target)){
                        exists = true;
                        break;
                    }
                }

                if(!exists){
                    System.out.println("Name not found in file...");
                    continue;
                }

                Iterator<String> it = names.iterator();
                while (it.hasNext()) {
                    String n = it.next();
                    if (n.equalsIgnoreCase(target)) {
                        it.remove();
                    }
                }

                System.out.println("Deleted "+target);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(DelFile));

            for(String n : names){
                writer.write(n);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error"+e.getMessage());
        }
    }

    // To delete entered name's details...

    void DeleteDetails() {

    File DelDetails = new File("Details.txt");

    if (!DelDetails.exists()) {
        System.out.println("No detail to delete...");
        return;
    }

    try {

        ArrayList<String> details = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(DelDetails));
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                details.add(line.trim());
            }
        }
        reader.close();

        while (true) {

            System.out.print("Enter student name to delete (type 'q' to stop): ");
            String target = sc.nextLine().trim();

            if (target.equalsIgnoreCase("q"))
                break;

            if (target.isEmpty()) {
                System.out.println("Name cannot be empty. Try again.");
                continue;
            }

            boolean found = false;

            for (String d : details) {
                String[] parts = d.split(",");
                if (parts.length > 0) {
                    String name = parts[0].trim();
                    if (name.equalsIgnoreCase(target)) {
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("No details found for: " + target);
                continue;
            }

            Iterator<String> it = details.iterator();
            while (it.hasNext()) {
                String d = it.next();
                String[] parts = d.split(",");
                if (parts.length >= 1 && parts[0].trim().equalsIgnoreCase(target)) {
                    it.remove();
                }
            }

            System.out.println("✔ Deleted details of: " + target);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(DelDetails));

        for (String d : details) {
            writer.write(d);
            writer.newLine();
        }
        writer.close();

        System.out.println("✔ All updates saved to file.");

    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }

    }

    void UpdateDetails() {

    File file = new File("Details.txt");

    if (!file.exists()) {
        System.out.println("No details found! Add details first.");
        return;
    }

    try {
        // Read all details into list
        ArrayList<String> details = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty())
                details.add(line.trim());
        }
        reader.close();

        while (true) {

            System.out.print("\nEnter student's name to update (or 'q' to stop): ");
            String target = sc.nextLine().trim();

            if (target.equalsIgnoreCase("q"))
                break;

            boolean found = false;

            for (int i = 0; i < details.size(); i++) {
                String[] parts = details.get(i).split(",");

                if (parts[0].equalsIgnoreCase(target)) {
                    found = true;

                    System.out.println("\nCurrent Details:");
                    System.out.println("NAME -> " + parts[0]);
                    System.out.println("ROLL NUMBER -> " + parts[1]);
                    System.out.println("MOBILE NUMBER -> " + parts[2]);
                    System.out.println("COURSE -> " + parts[3]);
                    System.out.println("ADDRESS -> " + parts[4]);

                    // Get updated values
                    System.out.print("\nEnter NEW Roll Number -> ");
                    String newRoll = sc.nextLine();

                    System.out.print("Enter NEW Mobile Number -> ");
                    String newMobile = sc.nextLine();
                    if (newMobile.length() != 10) {
                        System.out.println("Invalid Mobile Number.");
                        return;
                    }

                    System.out.print("Enter NEW Course -> ");
                    String newCourse = sc.nextLine();

                    System.out.print("Enter NEW Address -> ");
                    String newAddress = sc.nextLine();

                    // Create updated record
                    String updatedRecord = parts[0] + "," + newRoll + "," + newMobile + "," + newCourse + "," + newAddress;

                    // Replace old record
                    details.set(i, updatedRecord);

                    System.out.println("✔ Details updated successfully!");

                    break;
                }
            }

            if (!found) {
                System.out.println("❌ No student found with name: " + target);
            }
        }

        // Rewrite updated list into file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (String d : details) {
            writer.write(d);
            writer.newLine();
        }

        writer.close();
        System.out.println("\n✔ All updates saved to file.\n");

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    }
}
