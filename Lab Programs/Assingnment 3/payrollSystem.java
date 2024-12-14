
class Employee {
    private int employeeId;
    private String employeeName;
    private String designation;

    
    public Employee(int employeeId, String employeeName, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    
    public void displayInfo() {
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + designation);
    }
}


class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    
    public HourlyEmployee(int employeeId, String employeeName, String designation, double hourlyRate, int hoursWorked) {
        super(employeeId, employeeName, designation);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate >= 0) {
            this.hourlyRate = hourlyRate;
        } else {
            System.out.println("Invalid hourly rate. Setting to 0.");
            this.hourlyRate = 0;
        }
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked >= 0) {
            this.hoursWorked = hoursWorked;
        } else {
            System.out.println("Invalid hours worked. Setting to 0.");
            this.hoursWorked = 0;
        }
    }

    
    public double calculateWeeklySalary() {
        return hourlyRate * hoursWorked;
    }

    
    

    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
        
    }
}


class SalariedEmployee extends Employee {
    private double monthlySalary;

    
    public SalariedEmployee(int employeeId, String employeeName, String designation, double monthlySalary) {
        super(employeeId, employeeName, designation);
        setMonthlySalary(monthlySalary);
    }

    
    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary >= 0) {
            this.monthlySalary = monthlySalary;
        } else {
            System.out.println("Invalid monthly salary. Setting to 0.");
            this.monthlySalary = 0;
        }
    }

    
    public double calculateWeeklySalary() {
        return monthlySalary / 4;
    }

    

    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Monthly Salary: " + monthlySalary);
        System.out.println("Weekly Salary: " + calculateWeeklySalary());
        
    }
}


class ExecutiveEmployee extends SalariedEmployee {
    private double bonusPercentage;

    
    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage) {
        super(employeeId, employeeName, designation, monthlySalary);
        setBonusPercentage(bonusPercentage);
    }

    
    public void setBonusPercentage(double bonusPercentage) {
        if (bonusPercentage >= 0 && bonusPercentage <= 100) {
            this.bonusPercentage = bonusPercentage;
        } else {
            System.out.println("Invalid bonus percentage. Setting to 0.");
            this.bonusPercentage = 0;
        }
    }

    

    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bonus Percentage: " + bonusPercentage);
        
    }

    
    public double getMonthlySalary() {
        return super.calculateWeeklySalary() * 4;
    }
}


public class PayrollSystem {
    public static void main(String[] args) {
        
        HourlyEmployee hourlyEmp = new HourlyEmployee(101, "Sam", "Lab Assistant", 15.5, 40);
        SalariedEmployee salariedEmp = new SalariedEmployee(102, "Robin rajesh", "Professor", 5000);
        ExecutiveEmployee execEmp = new ExecutiveEmployee(103, "Shivesh", "Dean", 8000, 20);

        
        System.out.println("---- Hourly Employee ----");
        hourlyEmp.displayInfo();
        
        System.out.println("\n---- Salaried Employee ----");
        salariedEmp.displayInfo();
        
        System.out.println("\n---- Executive Employee ----");
        execEmp.displayInfo();
    }
}
