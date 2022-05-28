package fase3;

/**
 * Genera la estructura de almacenamiento de las palabras (Lista).
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class ListaPalabras implements InterfacePalabras {

	private Palabra[] wordList;
	private static final int initialSize = 100;
	private int size;

	/**
	 * Inicializa la lista de palabras con un tamaño fijo de 100 elementos.
	 */
	public ListaPalabras() {
		wordList = new Palabra[initialSize];
		size = 0;
	}

	/**
	 * Devuelve la lista de webs introducidas.
	 * 
	 * @return Lista de webs
	 */
	public Palabra[] getWordList() {
		return this.wordList;
	}

	/**
	 * Devuelve la longitud de la lista de palabras.
	 * 
	 * @return La longitud de la lista
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Añade una palabra a la lista.
	 * 
	 * @param palabra: Palabra a añadir
	 */
	@Override
	public void anadirPalabra(Palabra palabra) {
		if (size == wordList.length) {
			int newSize = size + initialSize;
			Palabra[] newWordList = new Palabra[newSize];
			for (int i = 0; i < size; i++) {
				newWordList[i] = wordList[i];
			}
			wordList = newWordList;
		}
		wordList[size] = palabra;
		size++;
	}

	/**
	 * Busca una palabra en la lista y la devuelve.
	 * 
	 * @param sPalabra: Texto de la palabra a buscar
	 */
	@Override
	public Palabra buscarPalabra(String sPalabra) {
		return busquedaDicotomica(wordList, sPalabra, 0, size);
	}

	/**
	 * Busca una palabra en la lista y la devuelve.
	 * 
	 * @param sPalabra: Texto de la palabra a buscar
	 * @return La palabra (si está en la lista), null en caso contrario
	 */
//	public Palabra buscarPalabra(String sPalabra) {
//		return busquedaDicotomica(wordList, sPalabra, 0, size);
//	}

	/**
	 * Realiza una busqueda dicotómica en la lista de palabras y devuelve dicha
	 * palabra.
	 * 
	 * @param lista: La lista de palabras
	 * @param word:  Cadena de caracteres de la palabra a buscar
	 * @param izq:   Índice del comienzo de la lista
	 * @param der:   Índice del final de la lista
	 * @return La palabra (si está en la lista), null en caso contrario
	 */
	private Palabra busquedaDicotomica(Palabra[] lista, String word, int izq, int der) {
		if (izq > der)
			return null;
		int medio = (izq + der) / 2;
		if (lista[medio].getWord().equals(word))
			return wordList[medio];
		else if (lista[medio].getWord().compareTo(word) > 0)
			return busquedaDicotomica(lista, word, izq, medio - 1);
		else
			return busquedaDicotomica(lista, word, medio + 1, der);
	}
}