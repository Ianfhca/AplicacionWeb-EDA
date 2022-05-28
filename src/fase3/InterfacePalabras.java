package fase3;

/**
 * Interfaz de la que dependen las distintas estructuras creadas para el
 * diccionario, (ListaPalabras y ABBPalabras).
 * 
 * @author Ian Fernandez e Iker Go�i
 *
 */
public interface InterfacePalabras {
	public void anadirPalabra(Palabra palabra);

	public Palabra buscarPalabra(String sPalabra);
}