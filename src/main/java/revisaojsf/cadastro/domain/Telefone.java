/**
 * 
 */
package revisaojsf.cadastro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jeferson
 *
 */

@Entity
@Table(name="TELEFONE")
public class Telefone {
	
	@Id
	@Column(name="ID_TELEFONE", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_telefone;
	@Column(name="DDD", nullable = false)
	private int ddd;
	@Column(name="TELEFONE", nullable = false)
	private String telefone;
	@Column(name="TIPO")
	private String tipo;
	
	public Telefone() {

	}
	
	public Telefone(int ddd, String telefone, String tipo) {
		this.ddd = ddd;
		this.telefone = telefone;
		this.tipo = tipo;
	}
	
	/**
	 * @return the id_telefone
	 */
	public long getId_telefone() {
		return id_telefone;
	}
	/**
	 * @param id_telefone the id_telefone to set
	 */
	public void setId_telefone(long id_telefone) {
		this.id_telefone = id_telefone;
	}
	/**
	 * @return the ddd
	 */
	public int getDdd() {
		return ddd;
	}
	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Telefone [id_telefone=" + id_telefone + ", ddd=" + ddd + ", telefone=" + telefone + ", tipo=" + tipo
				+ "]";
	}
	
}
