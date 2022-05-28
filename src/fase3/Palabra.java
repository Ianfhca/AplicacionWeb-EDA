package fase3;

/**
 * Contiene la informacion de la palabra.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class Palabra { // <Palabra extends Comparable<Palabra>

	private String word;
	private ListaWebs webList;

	/**
	 * Crea una palabra con la cadena de caracteres obtenida.
	 * 
	 * @param word: Palabra
	 */
	public Palabra(String word) {
		this.word = word;
		this.webList = new ListaWebs();
	}

	/**
	 * Devuelve la palabra en forma de cadena de caracteres.
	 * 
	 * @return La palabra
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Establece la palabra en forma de cadena de caracteres.
	 * 
	 * @param word: La palabra
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Devuelve la lista de webs a la que pertenece la palabra.
	 * 
	 * @return La lista de webs
	 */
	public ListaWebs getWebList() {
		return webList;
	}

	/**
	 * Establece la lista de webs a la que pertenece la palabra.
	 * 
	 * @param webList: La lista de webs
	 */
	public void setWebList(ListaWebs webList) {
		this.webList = webList;
	}
}
