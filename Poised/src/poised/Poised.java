package poised; // package name


// imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Poised {

    public static void main(String[] args) throws SQLException {
        {
            
            // menu variables
            int MainMenuInput = 0;
            int SubMenuInput = 0;
            int editMenu = 0;
            
            // new project variables
            int newProjNumber = 0;
            String newProjName;
            String newBuildingType;
            String newProjAddress;
            String newProjErf = null;
            int newProjTotalFee = 0;
            String newProjPaidToDate = null;
            String newProjDeadline = null;
            
            // new contractor variables
            String newCntrctrName;
            String newCntrctrNum = null;
            String newCntrctrEmail;
            String newCntrctrAddress;            

            // project completion variable
            String newProjComplete;            

            // project search variable
            String projSearch;            

            // choosing id to update variable
            String updateID;

            // project updating variables
            int updateProjNum;
            String updateProjName;
            String updateBuildingType;
            String updateProjAddress;
            String updateProjErf;
            int updateProjTotalFee;
            String updateProjPaidToDate;
            String updateProjDeadline;
            
            // contractor updating variables
            String updateCntrctrName;
            String updateCntrctrNum;
            String updateCntrctrEmail;
            String updateCntrctrAddress;
            
            // project completion update variable
            String updateProjComplete;
            
            // finalise check variable
            String finaliseCheck = null;            
            
            // check for number input variable
            boolean numCheck = true;
            
            // scanner input choice variable
            String userChoice;
            

            do {

                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/poisepms?useSSL=false", // connecting to the library_db database, via the jdbc:mysql: channel on localhost
                        "otheruser", // username "otheruser" and password "swordfish"
                        "swordfish"
                );
                
                // creating a direct line to the database for running queries
                Statement statement = connection.createStatement();

                // main scanner
                Scanner projectsScanner = new Scanner(System.in);
                

                // main project menu
                System.out.println("Project editor: \nPress 1 to view projects. \nPress 2 to create a project. "
                        + "\nPress 3 to edit a project. \nPress 4 to finalise a project. \nPress 5 to exit.\n---");
                MainMenuInput = projectsScanner.nextInt();
                

                // pressing 1 on the main menu to view projects
                if (MainMenuInput == 1) { 
                    System.out.println("\nProject viewer: \nPress 1 to view all projects. \nPress 2 to search projects. \nPress 3 to view uncompleted projects. "
                            + "\nPress 4 to view projects past their due date. \nPress 5 to go back.\n---");
                    SubMenuInput = projectsScanner.nextInt();
                    System.out.println("");
                    
                    
                    // if 1 is pressed on the sub menu SELECT from the poised table is used and while looped through to display the results
                    if (SubMenuInput == 1) {

                        ResultSet results = statement.executeQuery("SELECT * from poised");

                        while (results.next()) {
                            System.out.println("Project details: "
                                    + "\nProject number: " + results.getInt(1)
                                    + "\nProject name: " + results.getString(2)
                                    + "\nType of building being designed: " + results.getString(3)
                                    + "\nPhysical address of project: " + results.getString(4)
                                    + "\nERF number: " + results.getInt(5)
                                    + "\nTotal project fee: " + results.getInt(6)
                                    + "\nTotal of fee paid to date: " + results.getString(7)
                                    + "\nProject deadline: " + results.getDate(8)
                                    + "\n\nContactor Details:"
                                    + "\nContractor name: " + results.getString(9)
                                    + "\nContractor contact number: " + results.getString(10)
                                    + "\nContractor email address: " + results.getString(11)
                                    + "\nContractor physical address: " + results.getString(12)
                                    + "\n\nComplete: " + results.getString(13) + "\n---\n");
                        }
                        connection.close();
                    }
                    
                    // if 2 is pressed on the sub menu a project can be searched for either by its number or name using SELECT FROM WHERE and OR and while looped through to display the results
                    if (SubMenuInput == 2) {

                        System.out.println("To search for a project enter either its number or name: ");
                        projSearch = projectsScanner.next();

                        String searchResults = "SELECT * FROM poised WHERE project_number = '" + projSearch + "'" + "OR project_name = '" + projSearch + "'";
                        ResultSet results = statement.executeQuery(searchResults);

                        while (results.next()) {
                            System.out.println("\nProject details: "
                                    + "\nProject number: " + results.getInt(1)
                                    + "\nProject name: " + results.getString(2)
                                    + "\nType of building being designed: " + results.getString(3)
                                    + "\nPhysical address of project: " + results.getString(4)
                                    + "\nERF number: " + results.getInt(5)
                                    + "\nTotal project fee: " + results.getInt(6)
                                    + "\nTotal of fee paid to date: " + results.getString(7)
                                    + "\nProject deadline: " + results.getDate(8)
                                    + "\n\nContactor Details:"
                                    + "\nContractor name: " + results.getString(9)
                                    + "\nContractor contact number: " + results.getString(10)
                                    + "\nContractor email address: " + results.getString(11)
                                    + "\nContractor physical address: " + results.getString(12)
                                    + "\n\nComplete: " + results.getString(13) + "\n---\n");
                        }
                        connection.close();
                    }
                    
                    // if 3 is pressed on the sub menu SELECT is and WHERE is used to find projects with 'no' and while looped through to display results
                    if (SubMenuInput == 3) {

                        String completeCheck = "SELECT * FROM poised WHERE project_complete = 'no'";
                        ResultSet results = statement.executeQuery(completeCheck);

                        while (results.next()) {
                            System.out.println("Project details: "
                                    + "\nProject number: " + results.getInt(1)
                                    + "\nProject name: " + results.getString(2)
                                    + "\nType of building being designed: " + results.getString(3)
                                    + "\nPhysical address of project: " + results.getString(4)
                                    + "\nERF number: " + results.getInt(5)
                                    + "\nTotal project fee: " + results.getInt(6)
                                    + "\nTotal of fee paid to date: " + results.getString(7)
                                    + "\nProject deadline: " + results.getDate(8)
                                    + "\n\nContactor Details:"
                                    + "\nContractor name: " + results.getString(9)
                                    + "\nContractor contact number: " + results.getString(10)
                                    + "\nContractor email address: " + results.getString(11)
                                    + "\nContractor physical address: " + results.getString(12)
                                    + "\n\nComplete: " + results.getString(13) + "\n---\n");
                        }
                        connection.close();
                    }
                    
                    // if 4 is pressed on the sub menu SELECT and FROM and WHERE are used to find projects with a date behind the current date and while looped through to display results
                    if (SubMenuInput == 4) {

                        String dateCheck = "SELECT * FROM poised WHERE DATE (project_deadline) < CURDATE()";
                        ResultSet results = statement.executeQuery(dateCheck);

                        while (results.next()) {
                            System.out.println("Project details: "
                                    + "\nProject number: " + results.getInt(1)
                                    + "\nProject name: " + results.getString(2)
                                    + "\nType of building being designed: " + results.getString(3)
                                    + "\nPhysical address of project: " + results.getString(4)
                                    + "\nERF number: " + results.getInt(5)
                                    + "\nTotal project fee: " + results.getInt(6)
                                    + "\nTotal of fee paid to date: " + results.getString(7)
                                    + "\nProject deadline: " + results.getDate(8)
                                    + "\n\nContactor Details:"
                                    + "\nContractor name: " + results.getString(9)
                                    + "\nContractor contact number: " + results.getString(10)
                                    + "\nContractor email address: " + results.getString(11)
                                    + "\nContractor physical address: " + results.getString(12)
                                    + "\n\nComplete: " + results.getString(13) + "\n---\n");
                        }
                        connection.close();
                    }

                }
                
                
                // if 2 is pressed on the main menu all the project details are requested, using error checks where necessary and while loops for the error checks, 
                // then using setStrings to update and excecute new project details to insert into the table
                if (MainMenuInput == 2) {
                    System.out.println("\nEnter the project's details");
                    

                    do {
                        System.out.println("Enter the project's number: ");
                        try {
                            newProjNumber = projectsScanner.nextInt();
                            numCheck = false;
                        } catch (Exception e) {
                            System.out.println("Error - please enter a number.\n");
                            projectsScanner.next();
                            numCheck = true;
                        }
                    } while (numCheck);
                    

                    System.out.println("\nEnter the project's name: ");
                    newProjName = projectsScanner.next();
                    

                    System.out.println("\nEnter the project's building type: ");
                    newBuildingType = projectsScanner.next();
                    

                    System.out.println("\nEnter the project's physical address: ");
                    newProjAddress = projectsScanner.next();
                    

                    do {
                        System.out.println("\nEnter the project's ERF number: ");
                        try {
                            newProjErf = projectsScanner.next();
                            numCheck = false;
                        } catch (Exception e) {
                            System.out.println("Error - please enter a number.");
                            projectsScanner.next();
                            numCheck = true;
                        }
                    } while (numCheck);
                    

                    do {
                        System.out.println("\nEnter the project's total fee: R");
                        try {
                            newProjTotalFee = projectsScanner.nextInt();
                            numCheck = false;
                        } catch (Exception e) {
                            System.out.println("Error - please enter a number.");
                            projectsScanner.next();
                            numCheck = true;
                        }
                    } while (numCheck);
                    

                    do {
                        System.out.println("\nEnter the project's fee paid to date: R");
                        try {
                            newProjPaidToDate = projectsScanner.next();
                            numCheck = false;
                        } catch (Exception e) {
                            System.out.println("Error - please enter a number.");
                            projectsScanner.next();
                            numCheck = true;
                        }
                    } while (numCheck);
                    

                    System.out.println("\nEnter the project's deadline (YYYY-MM-DD): ");
                    newProjDeadline = projectsScanner.next();
                    

                    System.out.println("\n\nEnter the contractor's details");

                    System.out.println("Enter the contractor's name: ");
                    newCntrctrName = projectsScanner.next();
                    

                    do {
                        System.out.println("\nEnter the contractor's contact number: ");
                        try {
                            newCntrctrNum = projectsScanner.next();
                            numCheck = false;
                        } catch (Exception e) {
                            System.out.print("Error - please enter a number.");
                            projectsScanner.next();
                            numCheck = true;
                        }
                    } while (numCheck);
                    

                    System.out.println("\nEnter the contractor's email address: ");
                    newCntrctrEmail = projectsScanner.next();
                    

                    System.out.println("\nEnter the contractor's physical address: ");
                    newCntrctrAddress = projectsScanner.next();
                    

                    do {
                        System.out.println("\nIs the project complete? ");
                        newProjComplete = projectsScanner.next();
                        if (newProjComplete.equalsIgnoreCase("yes")) {
                            break;
                        } else {
                            if (newProjComplete.equalsIgnoreCase("no")) {
                                break;
                            }
                        }
                    } while (!newProjComplete.equalsIgnoreCase("yes") || !newProjComplete.equalsIgnoreCase("no"));
                    
                    
                    // prepared statement for connecting new project details into the table
                    PreparedStatement pst = connection.prepareStatement("INSERT INTO poised (project_number, project_name, "
                            + "building_type, project_address, project_erf, project_total_fee, "
                            + "project_fee_paid, project_deadline, contractor_name, contractor_phone_number, "
                            + "contractor_email, contractor_address, project_complete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    
                    // setStrings for creating new details
                    pst.setInt(1, newProjNumber);
                    pst.setString(2, newProjName);
                    pst.setString(3, newBuildingType);
                    pst.setString(4, newProjAddress);
                    pst.setString(5, newProjErf);
                    pst.setInt(6, newProjTotalFee);
                    pst.setString(7, newProjPaidToDate);
                    pst.setString(8, newProjDeadline);
                    pst.setString(9, newCntrctrName);
                    pst.setString(10, newCntrctrNum);
                    pst.setString(11, newCntrctrEmail);
                    pst.setString(12, newCntrctrAddress);
                    pst.setString(13, newProjComplete);

                    pst.executeUpdate();
                    pst.close();

                    System.out.println("\n---Project created---\n");

                }
                

                // if 3 is pressed on the main menu a project is able to be edited. first the number is found using SELECT FROM WHERE the project number 
                if (MainMenuInput == 3) {                    

                    System.out.println("\n---Project editing---\n");

                    System.out.println("Enter a project's number to update: ");
                    updateID = projectsScanner.next();
                    

                    // select used to find the project number via its input id
                    String check = "SELECT * FROM poised WHERE project_number = '" + updateID + "'";
                    Statement update = connection.createStatement();
                    ResultSet result = update.executeQuery(check);
                    

                    // getString is used to fetch the string id and information
                    while (result.next()) {

                        int project_number = result.getInt(1);
                        String project_name = result.getString(2);
                        String building_type = result.getString(3);
                        String project_address = result.getString(4);
                        String project_erf = result.getString(5);
                        int project_total_fee = result.getInt(6);
                        String project_fee_paid = result.getString(7);
                        String project_deadline = result.getString(8);
                        

                        String contractor_name = result.getString(9);
                        String contractor_phone_number = result.getString(10);
                        String contractor_email = result.getString(11);
                        String contractor_address = result.getString(12);
                        

                        String project_complete = result.getString(13);
                        

                        // variables connecting the new updated information to replace the older infomation
                        updateProjNum = project_number;
                        updateProjName = project_name;
                        updateBuildingType = building_type;
                        updateProjAddress = project_address;
                        updateProjErf = project_erf;
                        updateProjTotalFee = project_total_fee;
                        updateProjPaidToDate = project_fee_paid;
                        updateProjDeadline = project_deadline;
                        

                        updateCntrctrName = contractor_name;
                        updateCntrctrNum = contractor_phone_number;
                        updateCntrctrEmail = contractor_email;
                        updateCntrctrAddress = contractor_address;
                        

                        updateProjComplete = project_complete;
                        
                        // edit menu allowing user to choose what they would like to update
                        System.out.println("\nPress 1 to edit the project's number. \nPress 2 to edit the project's name. \nPress 3 to edit the project's building type."
                                + "\nPress 4 to edit the project's building address. \nPress 5 to edit the project's ERF number. \nPress 6 to edit the project's total fee."
                                + "\nPress 7 to edit the project's total fee paid. \nPress 8 to edit the project's deadline. \n\nPress 9 to edit the contractor's name."
                                + "\nPress 10 to edit the contractor's phone number. \nPress 11 to edit the contractor's email. \nPress 12 to edit the contractor's address."
                                + "\n\nPress 13 to edit the project's completion status. \n\nPress 14 to go back.\n");
                        editMenu = projectsScanner.nextInt();
                        
                        // if 1 is pressed on the edit menu, using a do while loop the project number can be edited using UPDATE, SET and WHERE
                        if (editMenu == 1) {
                            do {
                                System.out.println("\nDo you want to edit the project's number?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the project's new number:");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_number = ? WHERE project_number = '" + updateID + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 2 is pressed on the edit menu, using a do while loop the project name can be edited using UPDATE, SET and WHERE
                        if (editMenu == 2) {
                            do {
                                System.out.println("\nDo you want to edit the project's name?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the project's new name:");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_name = ? WHERE project_name = '" + updateProjName + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 3 is pressed on the edit menu, using a do while loop the building type can be edited using UPDATE, SET and WHERE
                        if (editMenu == 3) {
                            do {
                                System.out.println("\nDo you want to edit the building type?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new building type: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET building_type = ? WHERE building_type = '" + updateBuildingType + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 4 is pressed on the edit menu, using a do while loop the building's address can be edited using UPDATE, SET and WHERE
                        if (editMenu == 4) {
                            do {
                                System.out.println("\nDo you want to edit the building's address?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the building's new address: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_address = ? WHERE project_address = '" + updateProjAddress + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 5 is pressed on the edit menu, using a do while loop the ERF number can be edited using UPDATE, SET and WHERE
                        if (editMenu == 5) {
                            do {
                                System.out.println("\nDo you want to edit the ERF number?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new ERF number: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_erf = ? WHERE project_erf = '" + updateProjErf + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 6 is pressed on the edit menu, using a do while loop the total project fee can be edited using UPDATE, SET and WHERE
                        if (editMenu == 6) {
                            do {
                                System.out.println("\nDo you want to edit the total project fee?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new total project fee: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_total_fee = ? WHERE project_total_fee = '" + updateProjTotalFee + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 7 is pressed on the edit menu, using a do while loop the total paid project fee can be edited using UPDATE, SET and WHERE
                        if (editMenu == 7) {
                            do {
                                System.out.println("\nDo you want to edit the total paid project fee?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new total paid project fee: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_fee_paid = ? WHERE project_fee_paid = '" + updateProjPaidToDate + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 8 is pressed on the edit menu, using a do while loop the project deadline can be edited using UPDATE, SET and WHERE
                        if (editMenu == 8) {
                            do {
                                System.out.println("\nDo you want to edit the project deadline?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new project deadline: (YYYY-MM-DD)");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_deadline = ? WHERE project_deadline = '" + updateProjDeadline + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 9 is pressed on the edit menu, using a do while loop the contractor name can be edited using UPDATE, SET and WHERE
                        if (editMenu == 9) {
                            do {
                                System.out.println("\nDo you want to edit the contractor name?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the new contractor name: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET contractor_name = ? WHERE contractor_name = '" + updateCntrctrName + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 10 is pressed on the edit menu, using a do while loop the contractor's contact number can be edited using UPDATE, SET and WHERE
                        if (editMenu == 10) {
                            do {
                                System.out.println("\nDo you want to edit the contractor's contact number?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the contractor's new contact number: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET contractor_phone_number = ? WHERE contractor_phone_number = '" + updateCntrctrNum + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 11 is pressed on the edit menu, using a do while loop the contractor's email can be edited using UPDATE, SET and WHERE
                        if (editMenu == 11) {
                            do {
                                System.out.println("\nDo you want to edit the contractor's email?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the contractor's new email: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET contractor_email = ? WHERE contractor_email = '" + updateCntrctrEmail + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 12 is pressed on the edit menu, using a do while loop the contractor's address can be edited using UPDATE, SET and WHERE
                        if (editMenu == 12) {
                            do {
                                System.out.println("\nDo you want to edit the contractor's address?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nEnter the contractor's new address: ");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET contractor_address = ? WHERE contractor_address = '" + updateCntrctrAddress + "' AND project_number = '" + updateID + "'");
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                        
                        // if 13 is pressed on the edit menu, using a do while loop the project's completion status can be edited using UPDATE, SET and WHERE
                        if (editMenu == 13) {
                            do {
                                System.out.println("\nDo you want to edit the project's completion status?");
                                userChoice = projectsScanner.next();
                                if (userChoice.equalsIgnoreCase("yes")) {
                                    System.out.println("\nHas the project been completed?");
                                    userChoice = projectsScanner.next();
                                    PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_complete = ? WHERE project_complete = '" + updateProjComplete + "' AND project_number = '" + updateID + "'");
                                    System.out.println(updateProjComplete);
                                    pst.setString(1, userChoice);
                                    pst.executeUpdate();
                                    break;
                                } else {
                                    if (userChoice.equalsIgnoreCase("no")) {
                                        break;
                                    }
                                }
                                System.out.println("Error - please enter Yes or No.");
                            } while (!userChoice.equalsIgnoreCase("yes") || !userChoice.equalsIgnoreCase("no"));
                        }
                    }
                }
                
                
                // if 4 is pressed on the main menu a project is able to be finalised
                // first by using SELECT to find all the projects not complete
                // secondly by allowing the user to find a project by its number only from the projects filtered from the first step                
                if (MainMenuInput == 4) {
                    
                    int project_total_fee = 0;
                    String project_fee_paid = "";
                    double totalOwed = 0;
                    
                    String completeCheck = "SELECT * FROM poised WHERE project_complete = 'no'";
                    ResultSet finaliseSearch = statement.executeQuery(completeCheck);
                    
                    System.out.println("\nTo search for a project to finalise enter either its number: ");
                    projSearch = projectsScanner.next();
                    
                    String searchResults = "SELECT * FROM poised WHERE project_number = '" + projSearch + "'";
                    Statement update = connection.createStatement();
                    ResultSet result = update.executeQuery(searchResults);
                    

                    while (result.next()) {
                        project_total_fee = result.getInt(6);
                        project_fee_paid = result.getString(7);

                    }
                    
                    // the total owed by the customer is calculated minusing the total paid from the overall total fee to be used with the invoice
                    totalOwed = project_total_fee - Integer.parseInt(project_fee_paid);

                    System.out.println("\nAre you sure you want to finalise this project?");
                    finaliseCheck = projectsScanner.next();
                    
                    // when a project is finalised, UPDATE, SET and WHERE are used to update the status
                    if (finaliseCheck.equalsIgnoreCase("yes")) {
                        PreparedStatement pst = connection.prepareStatement("UPDATE poised SET project_complete = 'yes' WHERE project_number = '" + projSearch + "'");
                        pst.executeUpdate();
                        
                        // the customer's id is found using SELECT, FROM and WHERE
                        searchResults = "SELECT * FROM customer WHERE customer_id = 1";
                        Statement update2 = connection.createStatement();
                        result = update2.executeQuery(searchResults);
                        
                        // customer variables
                        String name = "";
                        String contNum = "";
                        String email = "";
                        String address = "";
                        
                        // getStrings used to fetch customer details
                        while (result.next()) {
                            name = result.getString(2);
                            contNum = result.getString(3);
                            email = result.getString(4);
                            address = result.getString(5);
                        }
                        
                        // the customer's invoice is printed
                        if (totalOwed > 0) {
                            String date = "";
                            System.out.println("\nEnter finalised date: ");
                            date = projectsScanner.next();
                            System.out.println("\nInvoice: "
                                    + "\nCustomer name: " + name
                                    + "\nCustomer contact number: " + contNum
                                    + "\nCustomer email: " + email
                                    + "\nCustomer address: " + address
                                    + "\nAmount owed: R" + totalOwed
                                    + "\nDate finalised: " + date);
                        }

                        break;
                    } else {
                        if (finaliseCheck.equalsIgnoreCase("no")) {
                            break;
                        }
                    }
                    System.out.println("Error - please enter Yes or No.");
                    while (!finaliseCheck.equalsIgnoreCase("yes") || !finaliseCheck.equalsIgnoreCase("no"));
                    

                    connection.close();
                }
                
                // pressing 5 on the main menu ends the program
            } while (MainMenuInput != 5);
        }
    }
}
