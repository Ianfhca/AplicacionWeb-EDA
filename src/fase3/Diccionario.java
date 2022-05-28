package fase3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Diccionario con las palabras que se pueden utilizar para la busqueda de las
 * páginas web.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class Diccionario {

	private static Diccionario miDiccionario;
	private InterfacePalabras wordList;
//	private ABBPalabras arbol;

	/**
	 * Crea la única instancia de la clase.
	 * 
	 * @return La única instancia de la clase
	 */
	public static Diccionario getInstance() {
		if (miDiccionario == null) {
			miDiccionario = new Diccionario();

		}
		return miDiccionario;
	}

	/**
	 * Constructora vacia de la clase.
	 */
	private Diccionario() {
//		wordList = new ListaPalabras();
	}

	/**
	 * Inicializa el diccionario con la clase que se le pasa como parámetro,
	 * (ListaPalabras o ABBPalabras).
	 * 
	 * @param diccionario
	 */
	public void setDiccionario(InterfacePalabras diccionario) {
		this.wordList = diccionario;
	}

	/**
	 * Carga el diccionario desde el fichero indicado.
	 * 
	 * @param nomFich: nombre del fichero que contiene el diccionario
	 * @throws FileNotFoundException
	 */
	private void cargarPalabras(String nomFich) throws FileNotFoundException {
		// wordList = new ListaPalabras();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File(nomFich));
		String datos;
		Palabra word;

		while (sc.hasNextLine()) {
			datos = sc.nextLine();
			word = new Palabra(datos);
			wordList.anadirPalabra(word);
		}
	}

	/**
	 * Asigna a cada palabra del diccionario las webs a las que hace referencia Pre:
	 * Internet y el diccionario ya están cargados
	 */
	private void computarWebsDePalabras() {
		// Ver ayuda en siguiente apartado
		Internet miInternet = Internet.getInstance();
		ListaWebs webList = miInternet.getWebList();
		int start = 0;
		int end = 0;
		String url;
		Palabra word;

		for (int i = 0; i < webList.getSize(); i++) {
			url = webList.getWebList()[i].getDirection();
			for (int j = 4; j < 11; j++) {
				end = j;
				while (end <= url.length()) {
					try {
						word = buscarPalabra(url.substring(start, end));
						word.getWebList().anadirWeb(webList.getWebList()[i]);
					} catch (Exception e) {

					}
					start++;
					end++;
				}
				start = 0;
			}
		}
	}

	/**
	 * Carga el diccionario desde el fichero indicado y asigna a cada palabra del
	 * diccionario las webs a las que hace referencia Pre: Internet ya está cargado
	 * 
	 * @param nomFich: nombre del fichero que contiene el diccionario
	 * @throws FileNotFoundException
	 */
	public void inicializar(String nomFich) throws FileNotFoundException {
		System.out.println("Cargando Palabras... ");
		cargarPalabras(nomFich);
		System.out.println("Computando webs por palabra... ");
		computarWebsDePalabras();

	}

	/**
	 * Busca una palabra en el diccionario y la devuelve
	 * 
	 * @param sPalabra: texto de la palabra a buscar
	 * @return la Palabra (si está en el diccionario), null en caso contrario
	 */
	public Palabra buscarPalabra(String sPalabra) throws NoSuchElementException {
		Palabra word = wordList.buscarPalabra(sPalabra);

		if (word == null) {
			throw new NoSuchElementException();
		} else {
			return word;
		}
	}
}
