package fase3;

import java.io.IOException;
import java.util.Scanner;

import gui.GUI;

/**
 * Simula la aplicación web.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class AplicacionWeb {

	/**
	 * Inicializa todas las clases y sus respectivos métodos.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		GUI gui = new GUI();

		if (gui.getSelection() == 1) {
			// Seleccionar Diccionario
			Scanner sc = new Scanner(System.in);
			System.out.println("+-------------------------------+");
			System.out.println("|  0- Diccionario tipo Lista  	|");
			System.out.println("|  1- Diccionario tipo ABB  	|");
			System.out.println("|  2- Diccionario tipo HashMap  |");
			System.out.println("+-------------------------------+");
			System.out.print("Selecciona el tipo de diccionario: ");

			String diccionario = sc.nextLine();

			// Inicializar Internet
			Internet miInternet = Internet.getInstance();
			miInternet.inicializar("src/ficheros/smallindex", "src/ficheros/smallpld-arc");

			// Inicializar Diccionario
			Diccionario miDiccionario = Diccionario.getInstance();
			switch (diccionario) {
			case "0":
				System.out.println("Creando una lista para el diccionario...");
				ListaPalabras wordList = new ListaPalabras();
				miDiccionario.setDiccionario(wordList);
				miDiccionario.inicializar("src/ficheros/wordsshuffle.txt");
				break;
			case "1":
				System.out.println("Creando un ABB para el diccionario...");
				ABBPalabras arbol = new ABBPalabras();
				miDiccionario.setDiccionario(arbol);
				miDiccionario.inicializar("src/ficheros/wordsshuffle.txt");
				System.out.println("Filtrando diccionario por palabras clave... ");
				arbol.filtrarPalabrasClave();
				break;
			case "2":
				System.out.println("Creando un HashMap para el diccionario...");
				HashMapPalabras hashMap = new HashMapPalabras();
				miDiccionario.setDiccionario(hashMap);
				miDiccionario.inicializar("src/ficheros/wordsshuffle.txt");
				break;
			default:
				System.out.println("Creando un HashMap para el diccionario...");
				HashMapPalabras hashMap2 = new HashMapPalabras();
				miDiccionario.setDiccionario(hashMap2);
				miDiccionario.inicializar("src/ficheros/wordsshuffle.txt");
				break;
			}

			System.out.println("Completado. \n");

			int opcion = 1;
			String word;
			String url1;
			String url2;
			while (opcion != 0) {
				System.out.println("+---------------------+");
				System.out.println("|  0- Salir           |");
				System.out.println("|  1- Buscar webs     |");
				System.out.println("|  2- Mostrar camino  |");
				System.out.println("|  3- Imprimir logo   |");
				System.out.println("+---------------------+");
				System.out.print("¿Que deseas hacer? ");
				try {
					opcion = Integer.parseInt(sc.nextLine());
					switch (opcion) {
					case 1:
						System.out.print("\nBuscar en Ruby o introducir URL: ");
						word = sc.nextLine();
						miInternet.buscadorWeb(word);
						System.out.println();
						break;
					case 2:
						System.out.println("\nMostrar camino entre dos URLs");
						System.out.print(" - Introduce la URL de origen: ");
						url1 = sc.nextLine();
						System.out.print(" - Introduce la URL de destino: ");
						url2 = sc.nextLine();
						miInternet.imprimirCamino(url1, url2);
						System.out.println();
						break;
					case 3:
						imprimirLogo();
					default:
						break;
					}
				} catch (Exception e) {
					System.out.println("\n*ERROR* - Valor inesperado\n");
				}
			}
			System.out.println("\nPrograma finalizado.");
			sc.close();
		}
	}

	public static void imprimirLogo() {
		System.out.println("\n    ¡Bienvenido al buscador Ruby!\n");

		System.out.println("                #/\\#");
		System.out.println("              ##//\\\\\\#");
		System.out.println("            ##//#####\\\\\\#");
		System.out.println("          ##//########\\\\\\#");
		System.out.println("        ##//############\\\\\\#");
		System.out.println("       ##//##############\\\\##");
		System.out.println("      ##||################||##");
		System.out.println("      #&||################||##");
		System.out.println("      &&||################||#&");
		System.out.println("      &&||################||&&");
		System.out.println("       &&\\\\##############//&&");
		System.out.println("        &&\\\\############//#&");
		System.out.println("          &&\\\\########//#&");
		System.out.println("            &&\\\\#####//##");
		System.out.println("               &\\\\//#");
		System.out.println("                &\\/#");
		System.out.println();

		System.out.println("      Ian Fernandez e Iker Goñi\n");
	}
}
