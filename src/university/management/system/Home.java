package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Home extends JFrame {
    String username;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Home("").setVisible(true);
        });
    }
    
    public Home(String username) {
        super("University Management System");
        this.username = username;

        // Set a suitable layout manager (example: BorderLayout)
        setLayout(new BorderLayout());

        // Load university icon
        ImageIcon universityIcon = createImageIcon("/path/to/pay_fees_icon.png");
        if (universityIcon != null) {
            JLabel backgroundLabel = new JLabel(universityIcon);
            add(backgroundLabel, BorderLayout.CENTER);
        } else {
            // Handle case where icon loading fails
            System.err.println("Error loading university icon.");
        }
        
        JLabel titleLabel = new JLabel("University Management System", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 55));
        add(titleLabel, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Students menu
        JMenu studentMenu = new JMenu("STUDENTS");
        studentMenu.setForeground(Color.BLUE);
        menuBar.add(studentMenu);
        
        JMenuItem addStudentItem = new JMenuItem("ADD STUDENT");
        studentMenu.add(addStudentItem);
        
        JMenuItem updateStudentItem = new JMenuItem("UPDATE STUDENT DETAILS");
        studentMenu.add(updateStudentItem);
        
        JMenuItem viewStudentItem = new JMenuItem("VIEW STUDENT DETAILS");
        studentMenu.add(viewStudentItem);
        
        addStudentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddStudent().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        updateStudentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new UpdateStudent().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        viewStudentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new StudentDetails().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        // Teachers menu
        JMenu teacherMenu = new JMenu("TEACHERS");
        teacherMenu.setForeground(Color.RED);
        menuBar.add(teacherMenu);
        
        JMenuItem addTeacherItem = new JMenuItem("ADD TEACHER");
        teacherMenu.add(addTeacherItem);
        
        JMenuItem updateTeacherItem = new JMenuItem("UPDATE TEACHER DETAILS");
        teacherMenu.add(updateTeacherItem);
        
        JMenuItem viewTeacherItem = new JMenuItem("VIEW TEACHER DETAILS");
        teacherMenu.add(viewTeacherItem);
        
        addTeacherItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddTeacher().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });
   
        updateTeacherItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new UpdateTeacher().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        viewTeacherItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new TeacherDetails().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        // Examinations menu
        JMenu examMenu = new JMenu("EXAMINATIONS");
        examMenu.setForeground(Color.GREEN);
        menuBar.add(examMenu);
        
        JMenuItem examScheduleItem = new JMenuItem("SCHEDULE EXAM");
        examMenu.add(examScheduleItem);
        
        JMenuItem viewExamItem = new JMenuItem("VIEW EXAM DETAILS");
        examMenu.add(viewExamItem);
        
        examScheduleItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                 //   new ScheduleExam().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        viewExamItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new ExaminationDetails().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        // Fees menu
        JMenu feeMenu = new JMenu("FEES");
        feeMenu.setForeground(Color.MAGENTA);
        menuBar.add(feeMenu);
        
        JMenuItem feePaymentItem = new JMenuItem("PAY FEES");
        feeMenu.add(feePaymentItem);
        
        JMenuItem feeDetailsItem = new JMenuItem("VIEW FEE STRUCTURE");
        feeMenu.add(feeDetailsItem);
        
        feePaymentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    payFees(); // Call the method to handle fee payment
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        feeDetailsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    new FeeStructure().setVisible(true); // Replace with appropriate constructor
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exceptions properly
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjusts frame size based on components
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true); // Make the frame visible
    }
    
    // Method to create ImageIcon with error handling
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // Method to handle fee payment functionality without PreparedStatement
    private void payFees() throws SQLException {
        Conn con = new Conn();

        // Example query: Update fee payment status for student based on username
        String username = this.username; // Assuming username is a member variable
        String query = "UPDATE fees SET status='Paid' WHERE student_id=" + getStudentId(username);

        Statement stmt = con.getStatement(); // Using the existing Statement from Conn

        try {
            int updatedRows = stmt.executeUpdate(query);
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "Fee payment successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update fee payment status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            stmt.close();
            con.close();
        }
    }

    // Method to retrieve student ID based on username (example)
    private int getStudentId(String username) {
        // Replace with your logic to fetch student ID from database based on username
        // For example, you might execute a SELECT query to fetch student ID
        // and return it here.
        return 0; // Placeholder, replace with actual implementation
    }
}