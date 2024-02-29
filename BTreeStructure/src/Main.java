import com.sun.jdi.Value;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner keyboard=new Scanner(System.in);
        Btree<Integer, Employee> employeeTree = new Btree<>();//creating tree

        int input=0;
        do {
            System.out.println("Enter operation code: ");
            input = keyboard.nextInt();
            switch (input) {//choosing the option
                case 1:
                    boolean g;
                    System.out.println("Enter information:");
                    int id=keyboard.nextInt();
                    String name=keyboard.next();
                    String gender=keyboard.next();
                    if(gender.equalsIgnoreCase("male"))
                         g=true;
                    else
                        g=false;
                    employeeTree.insertEmployee(id,name,g);
                    break;
                case 2:
                    System.out.println("Enter ID to be deleted:");
                    int id1=keyboard.nextInt();
                    employeeTree.deleteEmployee(id1);
                    break;
                case 3:
                    System.out.println("Enter ID to be searched:");
                    int id2=keyboard.nextInt();
                    employeeTree.searchEmployee(id2);
                    break;
                case 4:
                    employeeTree.listAllEmployees();
                    break;
                case 5:
                    System.out.println("Enter bounds of range:");
                    int id3=keyboard.nextInt();
                    int id4=keyboard.nextInt();
                    employeeTree.listAllEmployeesWithRange(id3,id4);
                    break;
                case 6:
                    System.out.println("Stopped!");
                    employeeTree.quit();
                    break;
            }
        } while (input != 6);//repeats until the input is 6.Do while is used in order to execute case 6 as well.

    }

}