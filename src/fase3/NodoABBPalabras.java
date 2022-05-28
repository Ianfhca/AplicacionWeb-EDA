package fase3;

import java.util.LinkedList;

/**
 * Genera la estructura de tipo nodo que tiene un enlace para cada sub-arbol y
 * el contenido palabras.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class NodoABBPalabras {

	Palabra info;
	NodoABBPalabras left;
	NodoABBPalabras right;

	/**
	 * Inicializa el nodo con la palabra obtenida.
	 * 
	 * @param info
	 */
	public NodoABBPalabras(Palabra info) {
		this.info = info;
	}

	/**
	 * Comprueba que el nodo no tenga hijos.
	 * 
	 * @return True si es hoja y False si no lo es
	 */
	public boolean isLeaf() {
		return left == null && right == null;
	}

	/**
	 * Devuelve si tiene o no hijo izquierdo.
	 * 
	 * @return True si tiene hijo izquierdo
	 */
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * Devuelve si tiene o no hijo derecho.
	 * 
	 * @return True si tiene hijo derecho
	 */
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * Elimina el elemento más pequeño del arbol y lo devuelve.
	 * 
	 * @return Elemento mínimo
	 */
	public ResultadoMin removeMin() {
		ResultadoMin resul = new ResultadoMin();

		if (!this.hasLeft()) {// El mínimo es el actual
			resul.valor = this.info;
			resul.nodo = this.right;
		} else { // El mínimo está en el subárbol izquierdo
			ResultadoMin resulLeft = this.left.removeMin();
			this.left = resulLeft.nodo;
			resul.valor = resulLeft.valor;
			resul.nodo = this;
		}
		return resul;
	}

	/**
	 * Añade una palabra al árbol.
	 * 
	 * @param palabra: palabra a añadir
	 */
	public void anadirPalabra(Palabra palabra) {
		String actual = this.info.getWord();

		if (actual.compareTo(palabra.getWord()) > 0) {
			if (this.hasLeft()) {
				this.left.anadirPalabra(palabra);
			} else {
				this.left = new NodoABBPalabras(palabra);
			}
		} else if (actual.compareTo(palabra.getWord()) < 0) {
			if (this.hasRight()) {
				this.right.anadirPalabra(palabra);
			} else {
				this.right = new NodoABBPalabras(palabra);
			}
		}
	}

	/**
	 * Busca una palabra en el árbol y la devuelve.
	 * 
	 * @param sPalabra: texto de la palabra a buscar
	 * @return la Palabra (si está en el árbol), null en caso contrario
	 */
	public Palabra buscarPalabra(String sPalabra) {
		String actual = this.info.getWord();
		Palabra palabra = null;

		if (actual.compareTo(sPalabra) == 0) {
			palabra = this.info;
			return palabra;
		} else if (actual.compareTo(sPalabra) > 0) {
			if (this.hasLeft()) {
				palabra = this.left.buscarPalabra(sPalabra);
			} else {
				return palabra;
			}
		} else if (actual.compareTo(sPalabra) < 0) {
			if (this.hasRight()) {
				palabra = this.right.buscarPalabra(sPalabra);
			} else {
				return palabra;
			}
		}
		return palabra;
	}

	/**
	 * Devuelve una lista con todas aquellas palabras del árbol que no sean palabra
	 * clave de ninguna web.
	 * 
	 * @return lista con las palabras a eliminar
	 */
	public LinkedList<Palabra> obtenerPalabrasAEliminar() {
		LinkedList<Palabra> noclave = new LinkedList<Palabra>();

		if (this.hasLeft()) {
			noclave.addAll(this.left.obtenerPalabrasAEliminar());
		}
		if (this.info.getWebList().getSize() <= 0) {
			noclave.add(this.info);
		}
		if (this.hasRight()) {
			noclave.addAll(this.right.obtenerPalabrasAEliminar());
		}
		return noclave;
	}

	/**
	 * Elimina recursivamente del árbol la palabra pasada como parámetro Pre: la
	 * palabra como mucho está una vez en el diccionario.
	 * 
	 * @param pal:  palabra a eliminar
	 * @param node: sub-arbol auxiliar (inicialmente toma el valor de root)
	 * @return el arbol NodoABBPalabras sin la palabra deseada
	 */
	public NodoABBPalabras eliminarPalabra(Palabra pal) {
		if (this.info.getWord().compareTo(pal.getWord()) == 0) {// Caso (a): this es el nodo a eliminar
			if (!this.hasLeft()) {
				return this.right; // Caso (a1)
			} else if (!this.hasRight()) {
				return this.left; // Caso (a2)
			} else {
				ResultadoMin min = this.right.removeMin();
				this.right = min.nodo;
				this.info = min.valor;
				return this;
			}
		} else if (this.info.getWord().compareTo(pal.getWord()) > 0) {
			if (this.hasLeft())
				this.left = this.left.eliminarPalabra(pal);
			return this;
		} else if (this.info.getWord().compareTo(pal.getWord()) < 0) {
			if (this.hasRight())
				this.right = this.right.eliminarPalabra(pal);
			return this;
		}
		return this;

//		FORMA ALTERNATIVA DE ELIMINAR (NO TERMINA DE EJECUTAR)
//		if(node == null) return node;
//		
//		//Palabra actual > Palabra buscada
//        if(node.info.getWord().compareTo(pal.getWord()) > 0) {
//            node.left = eliminarPalabra(node.left, pal);
//        //Palabra actual > Palabra buscada
//        } else if(node.info.getWord().compareTo(pal.getWord()) < 0) {
//        	node.right = eliminarPalabra(node.right, pal);
//        ////Palabra actual = Palabra buscada
//        } else {
//            //Si solo tiene 0 o 1 sub-arbol izq o der
//            if (node.left == null) {
//                return node.right;
//            } else if (node.right == null) {
//                return node.left;
//            //Si tiene 2 sub-arboles
//            } else {
//                node.info = min(node.right);
//                node.right = eliminarPalabra(node.right, node.info);
//            }
//        }
//        return node;
	}

//	private Palabra min(NodoABBPalabras node) {
//		if (node.left != null) {
//			return min(node.left);
//		}
//		return node.info;
//	}

	/**
	 * Imprime el arbol en preorden hasta el nivel deseado.
	 * 
	 * @param int: el nivel deseado (0 = raiz)
	 */
	public void recorrerHasta(int nivel) {
		System.out.println(this.info.getWord());
		if (nivel > 0) {
			if (this.hasLeft()) {
				this.left.recorrerHasta(nivel - 1);
			}
			if (this.hasRight()) {
				this.right.recorrerHasta(nivel - 1);
			}
		}
	}
}