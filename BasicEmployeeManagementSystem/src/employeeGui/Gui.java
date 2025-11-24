package employeeGui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import employee.Employee;

import java.sql.*;

public class Gui extends JFrame implements ActionListener {
    private JLabel labelTitle, labelID, labelFirstName, labelLastName, labelPhoneNumber, labelDepartment, labelPosition,
            labelGender, labelSalary;
    private JTextField ID, firstName, lastName, phoneNumber, department, position, salary;
    private JComboBox<String> genderBox;
    private JTable tabelEmployee;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton add, remove, search, update, list, clean;
    private JPanel pnlTitle, panel, panel2;
    private Connection connection;
   
   
    public Gui() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://yabu\\SQLEXPRESS:57984;databaseName=dbEmployee;encrypt=true;" +
                    "trustServerCertificate=true";
            connection = DriverManager.getConnection(url, "yabu", "0000");
            System.out.println("Connected");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(this, "Class not found", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(Gui.this, "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    Gui.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    Gui.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
       
        Container cp = getContentPane();
        setSize(800, 500);
        setTitle("Employee Management System");
        cp.setBackground(new Color(0xADD8E6)); 
        cp.setLayout(new BorderLayout());
        
        pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(0, 0, 128));

        labelTitle = new JLabel("Welcome To Employee Management System", JLabel.CENTER);
        labelTitle.setForeground(Color.white);
        labelTitle.setFont(new Font("serif Fonts", Font.ITALIC, 30));
        pnlTitle.add(labelTitle);
        cp.add(pnlTitle,BorderLayout.NORTH);
        
        
        panel = new JPanel();
        panel.setBorder(new LineBorder(new Color (0, 0, 128),1));
        panel.setBackground(new Color(0xADD8E6));
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints x = new GridBagConstraints();
        x.fill = GridBagConstraints.HORIZONTAL;
        x.insets = new Insets(5, 5, 5, 5);

        x.gridx = 0;
        x.gridy = 0;
        x.anchor = GridBagConstraints.EAST;
        labelID = new JLabel("ID");
        labelID.setForeground(Color.black);
        panel.add(labelID, x);
        x.gridx = 1;
        x.gridy = 0;
        x.anchor = GridBagConstraints.WEST;
        ID = new JTextField(10);
        panel.add(ID, x);

        x.gridx = 0;
        x.gridy = 1;
        x.anchor = GridBagConstraints.EAST;
        labelFirstName = new JLabel("First Name");
        labelFirstName.setForeground(Color.black);
        panel.add(labelFirstName, x);
        x.gridx = 1;
        x.gridy = 1;
        x.anchor = GridBagConstraints.WEST;
        firstName = new JTextField(15);
        panel.add(firstName, x);

        x.gridx = 0;
        x.gridy = 2;
        x.anchor = GridBagConstraints.EAST;
        labelLastName = new JLabel("Last Name");
        labelLastName.setForeground(Color.black);
        panel.add(labelLastName, x);
        x.gridx = 1;
        x.gridy = 2;
        x.anchor = GridBagConstraints.WEST;
        lastName = new JTextField(15);
        panel.add(lastName, x);

        x.gridx = 0;
        x.gridy = 3;
        x.anchor = GridBagConstraints.EAST;
        labelPhoneNumber = new JLabel("Phone Number");
        labelPhoneNumber.setForeground(Color.black);
        panel.add(labelPhoneNumber, x);
        x.gridx = 1;
        x.gridy = 3;
        x.anchor = GridBagConstraints.WEST;
        phoneNumber = new JTextField(15);
        panel.add(phoneNumber, x);

        x.gridx = 0;
        x.gridy = 4;
        x.anchor = GridBagConstraints.EAST;
        labelDepartment = new JLabel("Department");
        labelDepartment.setForeground(Color.black);
        panel.add(labelDepartment, x);
        x.gridx = 1;
        x.gridy = 4;
        x.anchor = GridBagConstraints.WEST;
        department = new JTextField(20);
        panel.add(department, x);

        x.gridx = 0;
        x.gridy = 5;
        x.anchor = GridBagConstraints.EAST;
        labelPosition = new JLabel("Position");
        labelPosition.setForeground(Color.black);
        panel.add(labelPosition, x);
        x.gridx = 1;
        x.gridy = 5;
        x.anchor = GridBagConstraints.WEST;
        position = new JTextField(20);
        panel.add(position, x);

        x.gridx = 0;
        x.gridy = 6;
        x.anchor = GridBagConstraints.EAST;
        labelGender = new JLabel("Gender");
        labelGender.setForeground(Color.black);
        panel.add(labelGender, x);
        x.gridx = 1;
        x.gridy = 6;
        x.anchor = GridBagConstraints.WEST;
        String[] genders = {"Female", "Male"};
        genderBox = new JComboBox<>(genders);
        panel.add(genderBox, x);

        x.gridx = 0;
        x.gridy = 7;
        x.anchor = GridBagConstraints.EAST;
        labelSalary = new JLabel("Salary");
        labelSalary.setForeground(Color.black);
        panel.add(labelSalary, x);
        x.gridx = 1;
        x.gridy = 7;
        x.anchor = GridBagConstraints.WEST;
        salary = new JTextField(10);
        panel.add(salary, x);
        
   

        tableModel = new DefaultTableModel(new Object[]{"ID", "First_Name", "Last_Name", "Phone_Number", "Department", "Position", "Gender",
        		"Salary"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tabelEmployee = new JTable(tableModel);
        tabelEmployee.setBackground(new Color(230, 230, 250));
        scrollPane = new JScrollPane(tabelEmployee);
        cp.add(scrollPane, BorderLayout.EAST);

        panel2 = new JPanel();
        panel2.setBackground(new Color(0, 0, 128));
        panel2.setLayout(new FlowLayout());

        add = new JButton("Add");
        add.addActionListener(this);
        add.setToolTipText("Add employee");
        panel2.add(add);

        update = new JButton("Update");
        update.addActionListener(this);
        update.setToolTipText("Update the data of employee by id ");
        panel2.add(update);

        remove = new JButton("Remove");
        remove.addActionListener(this);
        remove.setToolTipText("Delete employee by id ");
        panel2.add(remove);

        list = new JButton("List");
        list.addActionListener(this);
        list.setToolTipText("List all employee");
        panel2.add(list);

        search = new JButton("Search");
        search.addActionListener(this);
        search.setToolTipText("Search employee by id");
        panel2.add(search);

        clean = new JButton("Clean");
        clean.addActionListener(this);
        clean.setToolTipText("Clean the field");
        panel2.add(clean);

        cp.add(panel, BorderLayout.CENTER);
        cp.add(panel2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
         new Gui().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            handleAddEmployee();
            
        } else if (e.getSource() == remove) {
            handleRemoveEmployee();
        } else if (e.getSource() == search) {
            handleSearchEmployee();
        } else if (e.getSource() == update) {
            handleUpdateEmployee();
        } else if (e.getSource() == list) {
            handleListEmployees();
        } else if (e.getSource() == clean) {
            handleClearFields();
        }
    }

    private void handleAddEmployee() {
        String empID = ID.getText().trim();
        String firstName = this.firstName.getText().trim();
        String lastName = this.lastName.getText().trim();
        String phoneNumber = this.phoneNumber.getText().trim();
        String department = this.department.getText().trim();
        String position = this.position.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String salary = this.salary.getText().trim();

        if (empID.isEmpty()|| firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||
                department.isEmpty() || position.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validateName(firstName)) {
            JOptionPane.showMessageDialog(this, "Invalid first name. Only letters and spaces are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateName(lastName)) {
            JOptionPane.showMessageDialog(this, "Invalid last name. Only letters and spaces are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validatePhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number. Only digits are allowed or contain more digit"
            		, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateSalary(salary)) {
            JOptionPane.showMessageDialog(this, "Invalid salary. Salary must be a number greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Employee (ID, First_Name, Last_Name, Phone_Number, Department, Position, Gender, Salary) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, empID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phoneNumber);
            statement.setString(5, department);
            statement.setString(6, position);
            statement.setString(7, gender);
            statement.setString(8, salary);

            int rowsInserted = statement.executeUpdate();
         
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void handleRemoveEmployee() {
        String empID = ID.getText().trim();
        if (empID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this employee?", 
        		"Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE ID = ?")) {
                statement.setString(1, empID);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Employee removed successfully!",
                    		"Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdateEmployee() {
        String empID = ID.getText().trim();
        String firstName = this.firstName.getText().trim();
        String lastName = this.lastName.getText().trim();
        String phoneNumber = this.phoneNumber.getText().trim();
        String department = this.department.getText().trim();
        String position = this.position.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String salary = this.salary.getText().trim();

        if (empID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||
                department.isEmpty() || position.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validateName(firstName)) {
            JOptionPane.showMessageDialog(this, "Invalid first name. Only letters and spaces are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateName(lastName)) {
            JOptionPane.showMessageDialog(this, "Invalid last name. Only letters and spaces are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validatePhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number. Only digits are allowed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validateSalary(salary)) {
            JOptionPane.showMessageDialog(this, "Invalid salary. Salary must be a number greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this employee?", 
        		"Confirm Update", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Employee SET First_Name = ?, Last_Name = ?, Phone_Number = ?, Department = ?, "
                    + "Position = ?, Gender = ?, Salary = ? WHERE ID = ?")) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, phoneNumber);
                statement.setString(4, department);
                statement.setString(5, position);
                statement.setString(6, gender);
                statement.setString(7, salary);
                statement.setString(8, empID);

                int rowsUpdated = statement.executeUpdate();
               
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Employee updated successfully!", 
                    		"Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    private void handleSearchEmployee() {
        String empID = ID.getText().trim();
        if (empID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement( "SELECT * FROM Employee WHERE ID = ?")) {
            statement.setString(1, empID);
            ResultSet resultSet = statement.executeQuery();
            
            tableModel.setRowCount(0); 
            if (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getString("ID"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("Phone_Number"),
                        resultSet.getString("Department"),
                        resultSet.getString("Position"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Salary")
                });
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleListEmployees() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee");
             ResultSet resultSet = statement.executeQuery()) {
        	 
            tableModel.setRowCount(0); 
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getString("ID"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("Phone_Number"),
                        resultSet.getString("Department"),
                        resultSet.getString("Position"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Salary")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }

    private void handleClearFields() {
        ID.setText("");
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        department.setText("");
        position.setText("");
        salary.setText("");
        genderBox.setSelectedIndex(0);
        tableModel.setRowCount(0); 
    }
    public static boolean validateName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

   
    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{10}$");
    }


    
    public static boolean validateSalary(String salaryInput) {
        try {
            double salary = Double.parseDouble(salaryInput);
            return salary > 0;
        } catch (NumberFormatException e) {
            return false;  
        }
    }
   
    }

