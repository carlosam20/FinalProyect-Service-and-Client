package modelo;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;


@Entity
public class Categoria {
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9]{1,60}$", message="solo letras, numeros y espacios")
	private String nombre;
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9]{1,60}$", message="solo letras, numeros y espacios")
	private String descripcion;
	private boolean alta;
	
	
	//Relacion de categoria con libro
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "categoria" , fetch = FetchType.LAZY)
	private List<Ordenador> ordenadores= new ArrayList<Ordenador>();
	
	@Id
	@GeneratedValue
	private long id;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nombre, String descripcion, boolean alta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.alta = alta;
	
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public List<Ordenador> getOrdenadores() {
		return ordenadores;
	}

	public void setOrdenadores(List<Ordenador> ordenadores) {
		this.ordenadores = ordenadores;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	
	

}
