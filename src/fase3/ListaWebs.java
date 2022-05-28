package fase3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Genera la estructura de almacenamiento de las webs.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class ListaWebs {

	private Web[] webList;
	private static final int initialSize = 100;
	private int size;

	/**
	 * Inicializa la lista web con un tamaño fijo de 100 elementos.
	 */
	public ListaWebs() {
		webList = new Web[initialSize];
		size = 0;
	}

	/**
	 * Devuelve la lista de webs introducidas.
	 * 
	 * @return Lista de webs
	 */
	public Web[] getWebList() {
		return this.webList;
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
	 * Añade una web a la lista.
	 * 
	 * @param web: La web a añadir PRE: Web no está en la lista
	 */
	public void anadirWeb(Web web) {
		if (size == webList.length) {
			int newSize = size + initialSize;
			Web[] newWebList = new Web[newSize];
			for (int i = 0; i < size; i++) {
				newWebList[i] = webList[i];
			}
			webList = newWebList;
		}
		webList[size] = web;
		size++;
	}

	/**
	 * Añade un enlace a una web de la lista.
	 * 
	 * @param idWebOrigen:  Id de la web de origen
	 * @param idWebDestino: Id de la web de destino PRE: Las webs con id idWebOrigen
	 *                      e idWebDestino están en la lista
	 */
	public void anadirEnlace(int idWebOrigen, int idWebDestino) throws NoSuchElementException {
		if (idWebOrigen < size) {
			Web web = webList[idWebOrigen];
			web.getLista().anadirWeb(webList[idWebDestino]);
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Dada una URL, devuelve la web de la lista que tiene dicha URL.
	 * 
	 * @param url: URL a buscar
	 * @return: Web con dicha URL (si está en la lista), si no null.
	 */
	public Web buscarWebPorURL(String url) {
		Queue<Web> cola = new LinkedList<Web>();
		HashSet<Web> visitado = new HashSet<>();
		int i = 0;
		while (i < webList.length) {
			cola.add(webList[i]);
			visitado.add(webList[i]);
			while (!cola.isEmpty()) {
				Web aux = cola.poll();
				if (aux.getDirection().equals(url)) {
					return aux;
				} else {
					for (int j = 0; j < webList[i].getLista().size; j++) {
						Web newWeb = webList[i].getLista().webList[j];
						if (!visitado.contains(newWeb)) {
							cola.add(newWeb);
							visitado.add(newWeb);
						}
					}
				}
			}
			i++;
		}
		return null;
	}
}
