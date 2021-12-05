package staff;

import truck.AirportFireTruck;

public class Test {

    public static void main(String[] args) {

        AirportFireTruck airportFireTruck = new AirportFireTruck.Builder(false).build();
        Person red = new Person("Red Adair");
        IDCard idCard = new IDCard(new RFIDChip());
        IDCardEncoder idCardEncoder = new IDCardEncoder();
        idCardEncoder.encode(airportFireTruck.getCentralUnit(), idCard, red.getName(), "password");

        airportFireTruck.getCabin().getLeftDoor().getIdCardReceiver().read(idCard);
    }

}
