package servicios;

import java.util.List;
import java.util.Map;

import modelo.Ordenador;

public interface ServicioOrdenadores {

public int obtenerTotalDeOrdenadores(String marca);
List<Ordenador> obtenerOrdenadores(String marca, int comienzo);
Ordenador obtenerOrdenadoresPorId(int id);
void registrarOrdenador(Ordenador o);
void borrarOrdenador(int id);
void guardarCambiosOrdenador(Ordenador l);
void darBajaOrdenador(int idOrdenador);
void darAltaOrdenador(int idOrdenador);


//funciones de ajax
List <Map<String,Object>> obtenerOrdenadoresParaListado();
Map<String,Object> obtenerDetallesOrdenador(int id);






}
