package modelo;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Ordenador {
	
	@OneToOne
	private ProductoCarrito productocarrito;
	
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9]{1,60}$", message="solo letras, numeros y espacios")
	private String tipo;
	private String marca;
	@NumberFormat(style =Style.NUMBER, pattern = "#,###.###")
	private double precio;
	
	
	
	@Transient
	private MultipartFile imagen;
	
	private String modelo;
	private String procesador;
	private String grafica;
	
	@Pattern(regexp = "^[a-zA-Z+0-9]{1,2}$", message="solo letras, numeros y espacios")
	@Size(min = 1, max = 2, message = "Ram debe de tener entre 1 y 2 caracteres")
	private String ram;
	
	private boolean alta;
	
	@NumberFormat(style =Style.NUMBER, pattern = "#,###.###")
	private double peso;
 
	
	//relacion de Ordenador con Categoria
	@ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = Categoria.class, optional = false, fetch = FetchType.EAGER)
	private Categoria categoria;
	
	
	@Transient
	private long idCategoria;
	
	@Id
	@GeneratedValue
	private int id;
	
	
	
	public Ordenador() {
		// TODO Auto-generated constructor stub
	}

	public Ordenador(String tipo, String marca, String modelo, String procesador, String grafica, String ram,
			Double peso, boolean alta, Double precio) {
		super();
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.procesador = procesador;
		this.grafica = grafica;
		this.ram = ram;
		this.peso = peso;
		this.alta = alta;
		this.precio = precio;
		
	}



	public ProductoCarrito getProductocarrito() {
		return productocarrito;
	}

	public void setProductocarrito(ProductoCarrito productocarrito) {
		this.productocarrito = productocarrito;
	}

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getProcesador() {
		return procesador;
	}


	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}


	public String getGrafica() {
		return grafica;
	}


	public void setGrafica(String grafica) {
		this.grafica = grafica;
	}


	public String getRam() {
		return ram;
	}


	public void setRam(String ram) {
		this.ram = ram;
	}


	public double getPeso() {
		return peso;
	}




	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}




	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public long getIdCategoria() {
		return idCategoria;
	}





	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean getAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}



	public MultipartFile getImagen() {
		return imagen;
	}




	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}	
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Ordenador [tipo=" + tipo + ", marca=" + marca + ", precio=" + precio + ", imagen=" + imagen
				+ ", modelo=" + modelo + ", procesador=" + procesador + ", grafica=" + grafica + ", ram=" + ram
				+ ", alta=" + alta + ", peso=" + peso + ", categoria=" + categoria + ", idCategoria=" + idCategoria
				+ ", id=" + id + "]";
	}








	








	








	




	
	
	
	
	
}
