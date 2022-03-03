package main;

import java.util.Scanner;
import main.servicios.AutorServicio;
import main.servicios.EditorialServicio;
import main.servicios.LibroServicio;

public class Main {

    public static void main(String[] args) throws Exception {
        LibroServicio sL = new LibroServicio();
        AutorServicio sA = new AutorServicio();
        EditorialServicio sE = new EditorialServicio();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int opcion;
        do{
            menu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    sL.mostrarLibros();
                    break;
                case 2:
                    sA.mostrarAutores();
                    break;
                case 3:
                    sE.mostrarEditoriales();
                    break;
                case 4:
                    sL.crearLibro();
                    break;
                case 5:
                    sA.crearAutor();
                    break;
                case 6:
                    sE.crearEditorial();
                    break;
                case 7:
                    sL.prestarLibro();
                    break;
                case 8:
                    sL.recibirLibro();
                    break;
                case 9:
                    sL.mostrarLibrosDelAutor();
                    break;
                case 10:
                    sL.mostrarLibrosDeLaEditorial();
                    break;
                case 11:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingrese nuevamente.");;
            }
        }while(opcion != 11);
        
    }
    
    public static void menu(){
        System.out.println("******************");
        System.out.println("..:::Menu:::..");
        System.out.println("1. Listar libros");
        System.out.println("2. Listar autores");
        System.out.println("3. Listar Editoriales");
        System.out.println("4. Crear libro");
        System.out.println("5. Crear autor");
        System.out.println("6. Crear editorial");
        System.out.println("7. Prestar libro");
        System.out.println("8. Recibir libro");
        System.out.println("9. Listar libros por autor");
        System.out.println("10. Listar libros por editorial");
        System.out.println("11. Salir");
        System.out.println("\nIngrese una opcion:");
    }
}
