package model.entities;

/**
 * @author Lab07 Guiselle López
 */
public class Clerk extends User implements Employee {
    
    private int employeeCode;
    private String schedule;
    private int age;
    private ParkingLot parkingLot;

    public Clerk() {
        super();
    }

    public Clerk(int employeeCode, String schedule, int age, ParkingLot parkingLot) {
        this.employeeCode = employeeCode;
        this.schedule = schedule;
        this.age = age;
        this.parkingLot = parkingLot;
    }

    public Clerk(int employeeCode, String schedule, int age, ParkingLot parkingLot, String username, String password) {
        super(username, password);
        this.employeeCode = employeeCode;
        this.schedule = schedule;
        this.age = age;
        this.parkingLot = parkingLot;
    }

    public Clerk(int employeeCode, String schedule, int age, ParkingLot parkingLot, String id, String name, String username, String password) {
        super(id, name, username, password);
        this.employeeCode = employeeCode;
        this.schedule = schedule;
        this.age = age;
        this.parkingLot = parkingLot;
    }

    public int getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public String toString() {
        return "Clerk{" + "id=" + getId() + ", name=" + getName() + ", employeeCode=" + employeeCode + ", schedule=" + schedule + ", age=" + age + '}';
    }

    @Override
    public boolean verifyUserLogin(String[] loginDetails) {
        // Lógica para verificar login (ejemplo básico)
        return this.getUsername().equals(loginDetails[0]) && this.getPassword().equals(loginDetails[1]);
    }

    @Override
    public float calculateSalary(float dailySalary) {
        // Ejemplo de cálculo: salario diario por 30 días
        return dailySalary * 30;
    }

    @Override
    public ParkingLot assignWorkplace(int parkingLotId) {
        // Aquí iría la lógica para buscar el objeto ParkingLot por ID y asignarlo
        return this.parkingLot;
    }
}