package fase3;

//import auxiliares.Node;

/**
 * Contiene la informacion de la página web.
 * 
 * @author Ian Fernandez e Iker Goñi
 *
 */
public class Web {

	private String direction;
	private int value;
	private ListaWebs lista;

	/**
	 * Crea una web con la dirección y el valor obtenidos.
	 * 
	 * @param direction: La URL de la página web
	 * @param value:     El valor entero asociado a la web
	 */
	public Web(String direction, int value) {
		this.direction = direction;
		this.value = value;
		this.lista = new ListaWebs();
	}

	/**
	 * Devuelve la dirección URL.
	 * 
	 * @return La dirección URL de la web
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Establece la dirección URL.
	 * 
	 * @param direction: La dirección URL de la web
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Devuelve el valor entero asociado a la web.
	 * 
	 * @return El valor entero asociado a la web
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Establece el valor entero asociado a la web.
	 * 
	 * @param value: El valor entero asociado a la web
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Devuelve una lista de los enlaces asociados a la web.
	 * 
	 * @return La lista de enlaces de la web
	 */
	public ListaWebs getLista() {
		return lista;
	}

	/**
	 * Establece una lista de los enlaces asociados a la web.
	 * 
	 * @param lista: La lista de enlaces de la web
	 */
	public void setLista(ListaWebs lista) {
		this.lista = lista;
	}
}
