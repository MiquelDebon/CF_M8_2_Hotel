import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class M8Ex2_MiquelDebon {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Hotel> listaHoteles = new ArrayList<Hotel>();
    static final String stringAlertaNoHotel =  "❌ No hay hoteles!";
    static final String stringAlertNoFoundHotel =  "❌ Not found hotel!";
    static final String stringNoHotelWithThisName = "❌We don't have any Hotel with this name";
    static final String stringAnErrorOccur = "❌ An error has occur!!";
    static final String stringWriteAValidValue = "❌ Write a valid value!!";

    public static void main(String[] args) {
        //Attributes
        int opcionElegida = 0;
        boolean findelPrograma = false;

        //Logic
        do{
            System.out.print( "________🏨Que quieres hacer?___________" +
                    "\n   0. Salir del programa   " +
                    "\n   1. Crear Hotel               4. Listado de Hoteles"  +
                    "\n   2. Calcular mantenimiento    5. Datos de 1 Hotel + mantenimiento" +
                    "\n   3. Borrar Hotel              6. Modificar datos de Hotel" +
                    "\n✏️Escribe aquí tu elección (0-6):👉 ");

            opcionElegida=leerInt();

            System.out.println();

            switch (opcionElegida){
                case 0:
                    System.out.println("\nAdios, gracias por todo 👋");
                    findelPrograma = true;
                    break;
                case 1:
                    crearHotel();
                    break;
                case 2:
                    Manteniment();
                    break;
                case 3:
                    borrarHotel();
                    break;
                case 4:
                    imprimirHoteles();
                    break;
                case 5:
                    dimeInfoHotel();
                    break;
                case 6:
                    modificarHotel();
                    break;
                default:
                    System.out.println("❌ Select a valid option \n");
            }
        }while(!findelPrograma);
    }

    //Metodos ______________________________________________________________

    //Metodo 1
    static void crearHotel(){ //Tenemos dos constructores!!
        //Atributos
        Hotel nuevoHotel ;
        String nombre = "";
        String opcion = "";
        boolean rightConstructor = false;
        int numeroPlantas = 0;
        int numeroHabitacione = 0;
        int superficie = 0;
        int cantidadHoteles = 0;

        System.out.println("☑️Opción 1: Crear Hotel:");
        System.out.print("✏️Creación corta o larga (c/l): ");
        opcion = entrada.nextLine();
        rightConstructor = ((opcion.equalsIgnoreCase("c")) || (opcion.equalsIgnoreCase("l"))) ? true :false;

        if(!rightConstructor){
            System.out.println("❌Valor no valid option to create the Hotel\n");
        }else{
            System.out.print("✏️Introduce el nombre del nuevo Hotel: ");
            nombre = entrada.nextLine();

            if(opcion.equalsIgnoreCase("l")){
                System.out.print("✏️Introduce nº plantas: ");
                numeroPlantas= entrada.nextInt();
                System.out.print("✏️Introduce nº habitaciones: ");
                numeroHabitacione= entrada.nextInt();
                System.out.print("✏️Introduce la superficie m2  ");
                superficie= entrada.nextInt();

                nuevoHotel = new Hotel(nombre, numeroHabitacione, numeroPlantas, superficie);
                listaHoteles.add(nuevoHotel);
            }else if (opcion.equalsIgnoreCase("c")){
                nuevoHotel = new Hotel(nombre);
                listaHoteles.add(nuevoHotel);
            }
            cantidadHoteles = listaHoteles.size();
            System.out.printf("✅Hotel creat correctament. 🏨 Cantitdad de hoteles: %d \n \n", cantidadHoteles);
        }
    }

    //Metodo 2
    static void Manteniment(){
        String hotelQueried = "";
        Hotel hotel;
        String output = "";

        System.out.println("☑️Opcion 2: Calcular el mantenimiento");

        if(listaHoteles.size() != 0){
            System.out.println(stringHotelNamesList());
            System.out.print("✏️ De que hotel quieres saber el mantenimiento? ");
            hotelQueried = entrada.nextLine();
            if(existThisHotel(hotelQueried)){
                hotel = findHotel(hotelQueried);
                output = hotel.calcularManteniment();
                System.out.println(output);
            }else{
                System.out.println(stringAlertNoFoundHotel);
            }
        }else{
            System.out.println(stringAlertaNoHotel);
        }
        System.out.println();
    }


    //Metodo 3 - Borrar Hotel
    static void borrarHotel(){
        Hotel hotel;
        String hotelQueried = "";
        boolean existHotel = false;
        int indexHotel = 0;
        int cantidadOriginalHoteles = listaHoteles.size();

        System.out.println("☑️Opción 3: Borrar Hotel");
        if(listaHoteles.size() != 0){
            System.out.println(stringHotelNamesList());
            do {
                System.out.print("Quin hotel vols borrar, introdueix el seu nom:👉 ");
                hotelQueried = entrada.nextLine();
                existHotel = existThisHotel(hotelQueried);
                if(!existHotel){
                    System.out.println(stringNoHotelWithThisName);
                }
            }while(!existHotel);

            hotel = findHotel(hotelQueried);
            indexHotel = listaHoteles.indexOf(hotel);
            listaHoteles.remove(indexHotel);

            if(cantidadOriginalHoteles-1 == listaHoteles.size()){
                System.out.printf("✅S'ha borrat correctament. 🏨Cantidad de hoteles: %d\n" , listaHoteles.size());

            }else{
                System.out.println(stringAnErrorOccur);
            }
        }else{
            System.out.println(stringAlertaNoHotel);
        }
        System.out.println();
    }

    //Metodo 4 - Imprimir lista de Hoteles
    static void imprimirHoteles(){
        System.out.println("☑️Opcion 4: Imprimir lista de Hoteles");

        if(listaHoteles.size()!=0){
            System.out.println(stringHotelNamesList());
            System.out.println("✅Lista de Hoteles imprimida. \n");
        }else{
            System.out.println(stringAlertaNoHotel + "\n");
        }
    }


    //Metodo 5 - Datos de 1 Hotel
    static void dimeInfoHotel(){
        //Atributos
        Hotel hotel;
        boolean existeEleccion = false;
        String hotelElegido = "";

        System.out.println("☑️Opcion 5 - Datos de un Hotel");

        if(listaHoteles.size() != 0){
            System.out.println("Ahora tenemos estos Hoteles: ");
            System.out.println(stringHotelNamesList());

            do{
                System.out.print("✏️ Escribe el nombre del hotel: ");
                hotelElegido = entrada.nextLine();
                existeEleccion = existThisHotel(hotelElegido);
                if(!existeEleccion){
                    System.out.println(stringNoHotelWithThisName);
                }
            }while(!existeEleccion);

            hotel = findHotel(hotelElegido);
            System.out.println("✅Hay un hotel con este nombre");
            System.out.println(hotel.toStringMultiLinea());
            System.out.println(hotel.calcularManteniment());

        }else{
            System.out.println(stringAlertaNoHotel);
        }
        System.out.println();
    }

    //Metodo 6 - Modificar Hotel
    static void modificarHotel(){
        Hotel hotel = new Hotel();
        String hotelQueried = "";
        int datoaModificar = 0;
        int indiceHotel = 0;
        boolean existeEleccion = false;
        boolean correctDatoAModificar = false;

        System.out.println("☑️Opción 6 - Modificar Hotel");

        if(listaHoteles.size() != 0) {
            System.out.println(stringHotelNamesList());
            do{
                System.out.print("✏️Escribe el NOMBRE del hotel a modificar: ");
                hotelQueried = entrada.nextLine();
                existeEleccion = existThisHotel(hotelQueried);
                if(!existeEleccion){
                    System.out.println(stringNoHotelWithThisName);
                }
            }while(!existeEleccion);

            hotel = findHotel(hotelQueried);
            indiceHotel = listaHoteles.indexOf(hotel);

            do{
                System.out.printf("✅Que quieres modificar de '%s': \n", hotel.getName());
                System.out.print("    1. Nombre \n    2. Nº d'habitacions     \n    3. NºPlantes \n    4. Superficie \n✏️Que opción quieres (1-4): ");
                datoaModificar = leerInt();
                if(datoaModificar > 0 && datoaModificar < 5){
                    correctDatoAModificar = true;
                }else{
                    System.out.println(stringWriteAValidValue);
                }
            }while(!correctDatoAModificar);


            switch (datoaModificar){
                case 1:
                    System.out.printf("🏨Actualmente el hotel se llama '%s'\n",  hotel.getName());
                    System.out.print("✏️Escribe el nuevo nombre: ");
                    listaHoteles.get(indiceHotel).setName(entrada.nextLine());
                    break;
                case 2:
                    System.out.printf("🏨Actualmente el hotel '%s' tiene nº%d  habitaciones\n",hotel.getName(), hotel.getAmountRooms() );
                    System.out.print("✏️Escribe el nuevo numero de habitaciones: ");
                    listaHoteles.get(indiceHotel).setAmountRooms(entrada.nextInt());
                    break;
                case 3:
                    System.out.printf("🏨Actualmente el hotel '%s' tiene nº%d de plantas\n",hotel.getName(), hotel.getAmountFloors());
                    System.out.print("✏️Escribe el nuevo numero de plantas: ");
                    listaHoteles.get(indiceHotel).setAmountFloors(entrada.nextInt());
                    break;
                case 4:
                    System.out.printf("🏨Actualmente el hotel '%s' tiene %.2f metros cuadrados\n",hotel.getName(), hotel.getTotalSurface());
                    System.out.print("✏️Escribe la nueva superficie: ");
                    listaHoteles.get(indiceHotel).setTotalSurface(entrada.nextInt());
                    break;
            }
            System.out.println("✅ Hotel modificado correctamente");
        }else{
            System.out.println(stringAlertaNoHotel);
        }
        System.out.println();
    }

    //Other Methods   ______________________________________________


    public static String stringHotelNamesList(){
        String output =  "";
        output = String.format("🗓️Lista de Nombre de hoteles. Cantidad de hoteles: %d \n", listaHoteles.size());

        for (Hotel hotel : listaHoteles){
            output += String.format(" '%s' ", hotel.getName());
        }
        return output;
    }

    //Metodo d'impresió llistat hotels que s'utlitza en multiples metodes
//    static String stringHotelList(){
//        int cantidadHoteles = listaHoteles.size();
//        Hotel hotel;
//        String output = "";
//
//        output = String.format("🗓️Lista de hoteles. Cantidad de hoteles: %d \n", cantidadHoteles);
//
//        for(int i = 0; i < listaHoteles.size(); i++){
//            hotel = listaHoteles.get(i);
//            output += hotel.toString();
//            if(i != listaHoteles.size()-1){
//                output += "\n";
//            }
//        }
//        return output;
//    }

    public static boolean existThisHotel(String nameHotel){
        Hotel hotel;
        boolean output = false;
        for(int i = 0; i < listaHoteles.size(); i++ ){
            hotel = listaHoteles.get(i);
            if(hotel.getName().equalsIgnoreCase(nameHotel)){
                output = true;
                i = listaHoteles.size()-1;

            }
        }
        return output;

        //Se ha de utilizar la logica, no se puede utlizar esto!!
        //El for each solo se ha de utlizar para ir de inicio a fin!
//        for(Hotel hotel : listaHoteles){
//            if(hotel.getName().equalsIgnoreCase(nameHotel)){
//                return true;
//            }
//        }
    }

    public static Hotel findHotel(String nameHotel){
        Hotel hotel= new Hotel();
        boolean hotelFound = false;

        int i = 0;
        do{
            hotel = listaHoteles.get(i);
            i++;
            hotelFound = (hotel.getName().equalsIgnoreCase(nameHotel)) ? true : false;
        }while(!hotelFound);

        return hotel;
    }

    public static int leerInt() {
        int numero = 0;
        boolean correcto = false;

        do {
            try {
                numero = entrada.nextInt();
                correcto = true;
            } catch (InputMismatchException ex) {
                System.out.print(stringWriteAValidValue);
            }
            entrada.nextLine();
        } while (!correcto);
        return numero;
    }

}
