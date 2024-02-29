
public class Btree<Key extends Comparable<Key>, Value> {
    private Node root;

    public Btree() {
        this.root = root;
    }


    public void insertEmployee(Key id, String name, boolean gender) {// Inserts a new Employee into the B-tree
        // by creating a new Node and inserting it into the appropriate position
        Employee employee = new Employee(id, name, gender);
        root = insertNode(root, id, (Value)employee);
    }

    private Node insertNode(Node node, Key key, Value employee) {// Recursive helper function to insert a new Node into the B-tree
        if (node == null) {
            return new Node(key, employee);
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = insertNode(node.left, key, employee);
        } else if (compare > 0) {
            node.right = insertNode(node.right, key, employee);
        } else {
            node.employee = employee;
        }

        return node;
    }

    public void deleteEmployee(Key id) {//Deletes an Employee from the B-tree based on the provided id by locating the node, addressing various circumstances.

        root = deleteNode(root, id);
    }

    private Node deleteNode(Node node, Key key) {// Recursive helper function to delete a Node from the B-tree
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = deleteNode(node.left, key);
        } else if (compare > 0) {
            node.right = deleteNode(node.right, key);
        } else {
            // Found the node to delete
            if (node.left == null) {
                // Node has no left child, replace it with the right child
                return node.right;
            } else if (node.right == null) {
                // Node has no right child, replace it with the left child
                return node.left;
            } else {
                // Node has both left and right children  // Find the successor (minimum value in the right subtree)
                Node successor = findMinNode(node.right);
                // Replace the node's key and value with the successor's key and value
                node.key = successor.key;
                node.employee = successor.employee;
                // Delete the successor node from the right subtree
                node.right = deleteNode(node.right, successor.key);
            }
        }

        return node;
    }

    private Node findMinNode(Node node) { // Finds and returns the node with the minimum key value in the given subtree
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    public void searchEmployee(Key id) {// Uses the provided id to search for an employee in the B-tree and prints the employee's data if found.
        // If no record is discovered, a message is printed.

        Node result = searchNode(root, id);
        if (result != null) {
            Employee employee = (Employee) result.employee;
            System.out.println(employee.getId()+" "+employee.getName()+" "+(employee.getGender() ? "Male" : "Female"));
        } else {
            System.out.println("No record found.");
        }
    }

    private Node searchNode(Node node, Key key) {
        if (node == null || key == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        } else if (compare < 0) {
            return searchNode(node.left, key);
        } else {
            return searchNode(node.right, key);
        }


    }
    public void listAllEmployees() {
        inOrderTraversal(root);
    } // Traverses the B-tree in order and prints details of all Employees

    public void listAllEmployeesWithRange(Key minID, Key maxID) {// Traverses the B-tree in order and prints details of Employees within the specified ID range
        boolean found=inOrderTraversalWithRange(root, minID, maxID);
        if(!found)
            System.out.println("No record found.");

    }

    private void inOrderTraversal(Node node) {// Traverse the B-tree in-order and print employee details
        if (node != null) {
            inOrderTraversal(node.left);
            Employee employee = (Employee) node.employee;
            System.out.println(employee.getId()+" "+employee.getName()+" "+(employee.getGender() ? "Male" : "Female"));

            inOrderTraversal(node.right);
        }
    }

    private boolean inOrderTraversalWithRange(Node node, Key minID, Key maxID) {
        // Iterate through the B-tree in order inside the given ID range, printing any matching employee information.
        // If at least one employee is located inside the range, return true; otherwise, return false.

        boolean found=false;

            if(node==null)
                return false;
            int compareMin = minID.compareTo(node.key);
            int compareMax = maxID.compareTo(node.key);

            if (compareMin <= 0 && compareMax >= 0) {
                Employee employee = (Employee) node.employee;
                System.out.println(employee.getId() + " " + employee.getName() + " " + (employee.getGender() ? "Male" : "Female"));
                found=true;
                return found;
            }

            if (compareMin < 0) {
                found=inOrderTraversalWithRange(node.left, minID, maxID);
            }

            if (compareMax > 0) {
                found=inOrderTraversalWithRange(node.right, minID, maxID);
            }


        return found;
    }


    public void quit() {
        System.exit(0);
    }// Exits the program




    public class Node {
            private Value employee;
            private Key key;
            private Node left;
            private Node right;

            public Node(Key key, Value employee) {
                this.key = key;
                this.employee = employee;
            }


        }

    }





