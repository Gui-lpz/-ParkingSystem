package model.entities;

public class VehicleType {
    private int id;
    private String description;
    private byte numberOfTires;
    private float fee;
    private String type; 
    public VehicleType() {
    }

    public VehicleType(int id, String description, byte numberOfTires, float fee, String type) {
        this.id = id;
        this.description = description;
        this.numberOfTires = numberOfTires;
        this.fee = fee;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getNumberOfTires() {
        return numberOfTires;
    }

    public void setNumberOfTires(byte numberOfTires) {
        this.numberOfTires = numberOfTires;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VehicleType{" + "id=" + id + ", description=" + description + ", numberOfTires=" + numberOfTires + ", fee=" + fee + ", type=" + type + '}';
    }
    


}


