package fase3;

import java.util.HashMap;

/**
 * Genera la estructura de almacenamiento de las palabras (HashMap).
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class HashMapPalabras implements InterfacePalabras {

	HashMap<String, Palabra> map;

	/**
	 * Inicializa el hash map vacio.
	 */
	public HashMapPalabras() {
		map = new HashMap<String, Palabra>();
	}

	/**
	 * Añade una palabra a la lista.
	 * 
	 * @param palabra: Palabra a añadir
	 */
	@Override
	public void anadirPalabra(Palabra palabra) {
		map.put(palabra.getWord(), palabra);
	}

	/**
	 * Busca una palabra en la lista y la devuelve.
	 * 
	 * @param sPalabra: Texto de la palabra a buscar
	 */
	@Override
	public Palabra buscarPalabra(String sPalabra) {
		return map.get(sPalabra);
	}
}
