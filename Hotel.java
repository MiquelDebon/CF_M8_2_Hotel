import java.util.Scanner;

public class Hotel {
    //Attributes    ______________________________________________________________
    static private Scanner entrada = new Scanner(System.in);
    private String name;
    private int amountRooms;
    private int amountFloors;
    private float totalSurface;

    //Constructor ______________________________________________________________
    public Hotel(){};

    public Hotel(String name){
        this.name = name;
        this.amountRooms = 200;
        this.amountFloors = 4;
        this.totalSurface = 2000;
    }

    public Hotel(String name, int amountRooms, int amountFloors, float totalSurface) {
        this.name = name;
        this.amountRooms = amountRooms;
        this.amountFloors = amountFloors;
        this.totalSurface = totalSurface;
    }

    //GETTERS ______________________________________________________________
    public String getName(){
        return this.name;
    }
    public int getAmountRooms(){
        return this.amountRooms;
    }
    public int getAmountFloors(){
        return  this.amountFloors;
    }
    public float getTotalSurface(){
        return this.totalSurface;
    }


    public String toString() {
        return  "    Hotel Name: " + this.name  +
                ", Rooms: " + this.amountRooms +
                ", Floors: " + this.amountFloors +
                ", Surface: " + this.totalSurface;
    }

    public String toStringMultiLinea() {
        return    "   Name:     " + this.name  +
                "\n   Rooms:    " + this.amountRooms +
                "\n   Floors:   " + this.amountFloors +
                "\n   Surface:  " + this.totalSurface;
    }



    //Setters   ______________________________________________________________
    public void setName(String name){
        this.name = name;
    }
    public void setAmountRooms(int amountRooms){
        this.amountRooms = amountRooms;
    }
    public void setAmountFloors(int amountFloors){
        this.amountFloors = amountFloors;
    }
    public void setTotalSurface(float totalSurface){
        this.totalSurface = totalSurface;
    }


    //Other Methods   ______________________________________________________________
    public String calcularManteniment(){
        int people = 0;
        final int roomsxPerson = 20;
        int cost = 0;

        people = (int)Math.ceil(this.totalSurface / roomsxPerson);
        cost = people * 1500;

        return String.format(  "✅🧹Mantenimiento: "+
                        "\n     The hotel '%s' has %.2f m2, %d rooms" +
                        "\n     We need %d people with a cost of %d €."
                , this.getName(), this.getTotalSurface(), this.getAmountRooms(), people, cost);

    }
}