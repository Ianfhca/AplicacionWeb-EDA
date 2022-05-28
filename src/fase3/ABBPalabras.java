package fase3;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Genera la estructura de almacenamiento de las palabras (Arbol Binario de
 * Busqueda).
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class ABBPalabras implements InterfacePalabras {

	NodoABBPalabras root;

	/**
	 * Constructor vacío.
	 */
	public ABBPalabras() {

	}

	/**
	 * Comprueba que el árbol no sea vacío.
	 * 
	 * @return true si root es nulo
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * Añade una palabra al árbol.
	 * 
	 * @param palabra: palabra a añadir
	 */
	@Override
	public void anadirPalabra(Palabra palabra) {
		if (this.isEmpty())
			root = new NodoABBPalabras(palabra);
		else
			root.anadirPalabra(palabra);
	}

	/**
	 * Busca una palabra en el árbol y la devuelve.
	 * 
	 * @param sPalabra: texto de la palabra a buscar
	 * @return la Palabra (si está en el árbol), null en caso contrario
	 */
	@Override
	public Palabra buscarPalabra(String sPalabra) {
		if (this.isEmpty())
			return null;
		else
			return root.buscarPalabra(sPalabra);
	}

	/**
	 * Devuelve una lista con todas aquellas palabras del árbol que no sean palabra
	 * clave de ninguna web.
	 * 
	 * @return lista con las palabras a eliminar
	 */
	private LinkedList<Palabra> obtenerPalabrasAEliminar() {
		if (this.isEmpty()) {
			LinkedList<Palabra> noclave = new LinkedList<Palabra>();
			return noclave;
		} else {
			return root.obtenerPalabrasAEliminar();
		}
	}

	/**
	 * Elimina del árbol la palabra pasada como parámetro. Pre: la palabra como
	 * mucho está una vez en el diccionario.
	 * 
	 * @param pal: palabra a eliminar
	 */
	private void eliminarPalabra(Palabra pal) {
		root = root.eliminarPalabra(pal);
	}

	/**
	 * Haciendo uso de los métodos anteriores, obtiene la lista de palabras del
	 * árbol a eliminar y elimina cada una de ellas.
	 */
	public void filtrarPalabrasClave() {
		if (!this.isEmpty()) {
			LinkedList<Palabra> noclave = this.obtenerPalabrasAEliminar();
//			System.out.println(noclave.size());
			Iterator<Palabra> iter = noclave.iterator();
			while (iter.hasNext()) {
				eliminarPalabra(iter.next());
			}
		}
//		int tamanio = noclave.size();
//		for (int i = 0; i < tamanio; i++) {
//			eliminarPalabra(noclave.remove());
//		}
//		System.out.println(noclave.size());
	}

	/**
	 * Recorre el arbol hasta el nivel deseado.
	 * 
	 * @param int: el nivel deseado (0 = raiz)
	 */
	public void recorrerHasta(int nivel) {
		root.recorrerHasta(nivel);
	}
}
