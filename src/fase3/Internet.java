package fase3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * La clase de Internet es de instancia única y simula un servidor web que
 * almacena páginas web.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class Internet {

	private static Internet miInternet;
	private ListaWebs webList;

	/**
	 * Crea la única instancia de la clase.
	 * 
	 * @return La única instancia de esta clase
	 */
	public static Internet getInstance() {
		if (miInternet == null) {
			miInternet = new Internet();
		}
		return miInternet;
	}

	/**
	 * Constructora vacia de la clase.
	 */
	private Internet() {

	}

	/**
	 * Devuelve la lista de las webs que hay en internet.
	 * 
	 * @return Lista de webs
	 */
	public ListaWebs getWebList() {
		return this.webList;
	}

	/**
	 * Carga las webs contenidas en el fichero indicado.
	 * 
	 * @param nomFich: Nombre del fichero que contiene las webs
	 * @throws FileNotFoundException
	 */
	private void cargarWebs(String nomFich) throws FileNotFoundException {
		webList = new ListaWebs();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File(nomFich));
		String[] datos;
		Web web;

		System.out.println("Cargando webs...");

		while (sc.hasNextLine()) {
			datos = sc.nextLine().split("\\s+");
			web = new Web(datos[0], Integer.parseInt(datos[1]));
			webList.anadirWeb(web);
		}
//		for(int i = 0; i < 10; i++) {
//			System.out.println(webList.getWebList()[i].getDirection());
//		}
//		System.out.println();
	}

	/**
	 * Carga los enlaces contenidos en el fichero indicado.
	 * 
	 * @param nomFich: Nombre del fichero que contiene los enlaces
	 * @throws FileNotFoundException
	 */
	private void cargarEnlaces(String nomFich) throws FileNotFoundException {
		// Ver ayuda en siguiente apartado
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File(nomFich));
		String[] datos;

		System.out.println("Cargando enlaces...");

		while (sc.hasNextLine()) {
			datos = sc.nextLine().split("\\s+");
			webList.anadirEnlace(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
		}
//		for(int i = 0; i < 5; i++) {
//			System.out.println("La web '"+webList.getWebList()[i].getDirection()+"' tiene estos enlaces:");
//			for (int j = 0; j < 5; j++) {
//				System.out.println(webList.getWebList()[i].getLista().getWebList()[j].getDirection());
//			}
//			System.out.println();
//		}
//		System.out.println();
	}

	/**
	 * Inicializa la clase cargando las webs y los enlaces.
	 * 
	 * @param nomFichWebs:    Nombre del fichero que contiene las webs
	 * @param nomFichEnlaces: Nombre del fichero que contiene los enlaces
	 * @throws FileNotFoundException
	 */
	public void inicializar(String nomFichWebs, String nomFichEnlaces) throws FileNotFoundException {
		cargarWebs(nomFichWebs);
		cargarEnlaces(nomFichEnlaces);
	}

	/**
	 * Dado un string que contiene una palabra, imprime por pantalla las webs que
	 * tienen dicha palabra clave.
	 * 
	 * @param sPalabra: String con la palabra
	 */
	public ListaWebs buscadorWeb(String sPalabra) {
		Diccionario miDiccionario = Diccionario.getInstance();
		try {
			Palabra word = miDiccionario.buscarPalabra(sPalabra);
			System.out.println("\nHay " + word.getWebList().getSize() + " coincidencias para la palabra '"
					+ word.getWord() + "':");
			for (int i = 0; i < word.getWebList().getSize(); i++) {
				System.out.println(" - " + word.getWebList().getWebList()[i].getDirection());
			}
			return word.getWebList();
		} catch (Exception e) {
			try {
				Web web = webList.buscarWebPorURL(sPalabra);
				if (web != null) {
					System.out.println("\nLa web '" + web.getDirection() + "' tiene " + web.getLista().getSize()
							+ " páginas asociadas:");
					for (int i = 0; i < web.getLista().getSize(); i++) {
						System.out.println(" - " + web.getLista().getWebList()[i].getDirection());
					}
					return web.getLista();
				}
			} catch (Exception er) {
				System.out.println("\nNo hay resultados para '" + sPalabra + "'.");
			}
//			System.out.println("¿Deseas añadirla? (s/n)");
		}
		return new ListaWebs();
	}

	/**
	 * Dadas dos URL indica si existe un camino de enlaces desde la URL de origen
	 * hasta la de destino.
	 * 
	 * @param url1: URL de origen
	 * @param url2: URL de destino
	 * @return: true si existe el camino, false si no existe
	 */
	public boolean estanConectados(String url1, String url2) {
		Web web1 = webList.buscarWebPorURL(url1);
		Web web2 = webList.buscarWebPorURL(url2);

		Queue<Web> cola = new LinkedList<Web>();
		HashSet<Web> visitados = new HashSet<>();
		boolean exist = false;

		cola.add(web1);
		visitados.add(web1);
		while (!cola.isEmpty()) { // Falta la condicion de que 
			Web aux = cola.poll();
			if (aux.equals(web2)) {
				exist = true;
				return exist;
			} else {
				for (int i = 0; i < aux.getLista().getSize(); i++) {
					Web newWeb = aux.getLista().getWebList()[i];
					if (!visitados.contains(newWeb)) {
						cola.add(newWeb);
						visitados.add(newWeb);
					}
				}
			}
		}
		return exist;
	}

	/**
	 * Dadas dos URL imprime el camino más corto desde la URL de origen hasta la de
	 * destino, si es que existe.
	 * 
	 * @param url1: URL de origen
	 * @param url2: URL de destino
	 */
	public void imprimirCamino(String url1, String url2) {
//		System.out.println(estanConectados(url1, url2));
		if (estanConectados(url1, url2)) {
			Web web1 = webList.buscarWebPorURL(url1);
			Web web2 = webList.buscarWebPorURL(url2);

			HashMap<Web, Web> visitados = new HashMap<Web, Web>();
			Queue<Web> cola = new LinkedList<Web>();
			LinkedList<String> camino = new LinkedList<String>();
			boolean encontrado = false;

			cola.add(web1);
			visitados.put(web1, null);
			while (!cola.isEmpty()) {
				Web aux = cola.poll();
				if (aux.equals(web2)) {
					encontrado = true;
				} else {
					for (int i = 0; i < aux.getLista().getSize(); i++) {
						Web newWeb = aux.getLista().getWebList()[i];
						if (!visitados.containsKey(newWeb)) {
							cola.add(newWeb);
							visitados.put(newWeb, aux);
						}
					}
				}
			}
			if (encontrado) {
				Web actual = web2;
				while (actual != null) {
					camino.addFirst(actual.getDirection());
					actual = visitados.get(actual);
				}
				System.out.println("\nEste es el camino mas corto desde la web origen hasta la web destino:");
				for (String web : camino) {
					System.out.println(" - " + web);
				}
			}
		} else {
			System.out.println("\nNo existe ningun camino entre estos dos sitios web.");
		}
	}

}
