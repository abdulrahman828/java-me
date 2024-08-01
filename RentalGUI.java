import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.InputMismatchException;

public class RentalGUI extends JFrame {
	
    private JTextField NameField, idField, carCostField, colorField, companyField, insuranceTypeField, insuranceCostField, CartypeField, daysField;
    private RentalCar rental;
    private Vehicle v;
    public RentalGUI() {
    	
        rental = new RentalCar("RentalCarCompany", 100);
        setTitle("Rental Car System");
        inputPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout()); 

        setSize(400, 300);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void inputPanel() {
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(4,1,15,12));
        input.setBorder(BorderFactory.createTitledBorder("Select your option"));

        NameField = new JTextField(10);
        idField = new JTextField(10);
        carCostField = new JTextField(10);
        colorField = new JTextField(10);
        companyField = new JTextField(10);
        insuranceTypeField = new JTextField(10);
        insuranceCostField = new JTextField(10);
        CartypeField = new JTextField(10);
        daysField = new JTextField(10);
       JLabel name= new JLabel("Name:");
       
        input.add(name);
       
        input.add(NameField);
          
        input.add(new JLabel(  "\n Car ID:"));
        input.add(idField);

        input.add(new JLabel("Car Cost:"));
        input.add(carCostField);

        input.add(new JLabel("Color:"));
        input.add(colorField);

        input.add(new JLabel("Company:"));
        input.add(companyField);

        
       
        input.add(new JLabel("Insurance Type:"));
        input.add(insuranceTypeField);

        input.add(new JLabel("Insurance Cost:"));
        input.add(insuranceCostField);

        input.add(new JLabel("Car Type:"));
        input.add(CartypeField);

        input.add(new JLabel("Days:"));
        input.add(daysField);

        JButton addButton = new JButton("Add Vehicle");
        addButton.addActionListener(e -> {
            try {
                addVehicle(e);
            } catch (InfoException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
            }
        });
          
         input.add(addButton);

        JButton saveButton = new JButton("Save to File");
        input.add(saveButton);
        saveButton.addActionListener(e -> {
            try {
                saveToFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
            }
        });
      
        JButton loadButton = new JButton("Load from File");
        loadButton.addActionListener(e -> {
            try {
                loadFromFile();
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error loading from file: " + ex.getMessage());
            }
        });
        input.add(loadButton);

        JButton showResultsButton = new JButton("Show Results");
        showResultsButton.addActionListener(e -> showResults());
        input.add(showResultsButton);

        JButton RemoveButton = new JButton("Remove Vehicle");
        RemoveButton.addActionListener(e -> {
            try {
               removeVehicle(e);
            
               
            } catch (IOException e1) {
            	 JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
				
			}
        });
        input.add(RemoveButton);
        
      
        getContentPane().add(input);
    }

    private void addVehicle(ActionEvent e) throws InfoException {
        try {
            String name = NameField.getText().trim();
            int id = Integer.parseInt(idField.getText().trim());
            double carCost = Double.parseDouble(carCostField.getText().trim());
            String color = colorField.getText().trim();
            String company = companyField.getText().trim();
            String insuranceType = insuranceTypeField.getText().trim();
            double insuranceCost = Double.parseDouble(insuranceCostField.getText().trim());
            String carType = CartypeField.getText().trim();
            int days = Integer.parseInt(daysField.getText().trim());

            Vehicle vehicle;
            if (carType.equalsIgnoreCase("suv")) {
                vehicle = new SUV(name, id, carCost, color, company, days, insuranceType, insuranceCost);
            } else if (carType.equalsIgnoreCase("sedan")) {
                vehicle = new Sedan(name, id, carCost, color, company, days, insuranceType, insuranceCost);
            } else if (carType.equalsIgnoreCase("pickup")) {
                vehicle = new PickUp(name, id, carCost, color, company, days, insuranceCost, insuranceType);
            } else {
                throw new InfoException("Invalid car type.");
            }

            boolean added = rental.AddV(vehicle);
            if (added) {
                JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add vehicle. Check if the vehicle already exists or the array is full.");
            }
        }catch(InputMismatchException eo) {
        	JOptionPane.showMessageDialog(this, "please enter correct format ");
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numeric values.");
        } catch (InfoException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clear() {
        NameField.setText("");
        idField.setText("");
        carCostField.setText("");
        colorField.setText("");
        companyField.setText("");
        insuranceTypeField.setText("");
        insuranceCostField.setText("");
        CartypeField.setText("");
        daysField.setText("");
    }

    private void saveToFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                rental.saveVehicleToFile(file.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "All vehicles saved to file successfully: " + file.getAbsolutePath());
            } catch (IOException eop) {
                JOptionPane.showMessageDialog(this, "Unable to save data to file.");
            }
        }
    }
    private void loadFromFile() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try  {
                rental.readVehiclesFromFile(file.getAbsolutePath());
              
                
                
                JOptionPane.showMessageDialog(this, "Data loaded from file successfully."+file.getAbsolutePath());
                
                
                
            }catch(FileNotFoundException eop) {
            	 JOptionPane.showMessageDialog(this, "unable to load data");
            }
        }
    }

    private void showResults() {
        JFrame resultsFrame = new JFrame("Results");
        JTextArea resultsTextArea = new JTextArea(rental.toString());
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);

        resultsFrame.getContentPane().add(scrollPane);
        resultsFrame.setSize(400, 300);
        resultsFrame.setLocationRelativeTo(this); 
        resultsFrame.setVisible(true);
    }
    public void removeVehicle(ActionEvent e ) throws IOException{
    	 try {
             String name = NameField.getText().trim();
             int id = Integer.parseInt(idField.getText().trim());
             double carCost = Double.parseDouble(carCostField.getText().trim());
             String color = colorField.getText().trim();
             String company = companyField.getText().trim();
             String insuranceType = insuranceTypeField.getText().trim();
             double insuranceCost = Double.parseDouble(insuranceCostField.getText().trim());
             String carType = CartypeField.getText().trim();
             int days = Integer.parseInt(daysField.getText().trim());

             Vehicle vehicle;
             if (carType.equalsIgnoreCase("suv")) {
                 vehicle = new SUV(name, id, carCost, color, company, days, insuranceType, insuranceCost);
             } else if (carType.equalsIgnoreCase("sedan")) {
                 vehicle = new Sedan(name, id, carCost, color, company, days, insuranceType, insuranceCost);
             } else if (carType.equalsIgnoreCase("pickup")) {
                 vehicle = new PickUp(name, id, carCost, color, company, days, insuranceCost, insuranceType);
             } else {
                 throw new InfoException("Invalid car type.");
             }

             boolean removed = rental.RemoveV(vehicle);
             if (removed) {
                 JOptionPane.showMessageDialog(this, "Vehicle removed successfully.");
                 clear();
             } else {
                 JOptionPane.showMessageDialog(this, "Failed to remove vehicle.");
             } }catch(InputMismatchException eo) {
             	JOptionPane.showMessageDialog(this, "please enter correct format ");
             }catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(this, "Invalid number format. Please enter valid numeric values.");
             } catch (InfoException ex) {
                 JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
             }
    	
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RentalGUI::new);
       
    }
}
