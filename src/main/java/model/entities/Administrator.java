package model.entities;

public class Administrator extends User implements Employee { 
    
    private int adminNumber;
    private ParkingLot parkingLot;

    public Administrator() {
    }
    
    public Administrator(String id, String name, String username, String password, int adminNumber) {
    super(id, name, username, password);
    this.adminNumber = adminNumber;
    this.parkingLot = null;
}
    
    public Administrator(int adminNumber, ParkingLot parkingLot, String id, String name, String username, String password) {
    super(id, name, username, password);
    this.adminNumber = adminNumber;
    this.parkingLot = parkingLot;
}

    public Administrator(int adminNumber, ParkingLot parkingLot) {
        this.adminNumber = adminNumber;
        this.parkingLot = parkingLot;
    }

    public int getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(int adminNumber) {
        this.adminNumber = adminNumber;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public String toString() {
        return "Administrator{" + "adminNumber=" + adminNumber + ", parkingLot=" + parkingLot + '}';
    }

    @Override
    public boolean verifyUserLogin(String[] loginDetails) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float calculateSalary(float dailySalary) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ParkingLot assignWorkplace(int parkingLotId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
