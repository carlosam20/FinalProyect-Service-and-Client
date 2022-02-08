package datos.servicios;

import java.util.List;

import java.util.Map;


public class ResumenPedido {

	
	private List<Map<String, Object>>ordenadores;
	
//se piden en paso 1:
	private String nombreCompleto;
	
	private String direccion;
	
	private String provincia;
	
	private String pais;
	
	private String codigoPostal;
	//fin paso 1
	
	//se piden en paso 2:
	private String titularTarjeta;

	private String numeroTarjeta;
	
	private String tipoTarjeta;

	//fin paso 2
	
	

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	

	public List<Map<String, Object>> getOrdenadores() {
		return ordenadores;
	}



	public void setOrdenadores(List<Map<String, Object>> ordenadores) {
		this.ordenadores = ordenadores;
	}



	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	
	public String getPais() {
		return pais;
	}



	public void setPais(String pais) {
		this.pais = pais;
	}



	public String getCodigoPostal() {
		return codigoPostal;
	}



	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}



	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}


	
	
}
