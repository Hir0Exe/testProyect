package clases;
/**
 *
 * @author DOCUMENTACIÓN
 */
public class Equipo {
    private String equipo;
    private String continente;
    private String directorTecnico;
    private String nacionalidad;
    private String grupo;
    private int puntos;

    public Equipo(String equipo, String continente, String directorTecnico, String nacionalidad) {
        this.equipo = equipo;
        this.continente = continente;
        this.directorTecnico = directorTecnico;
        this.nacionalidad = nacionalidad;
        this.puntos = 0;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getDirectorTecnico() {
        return directorTecnico;
    }

    public void setDirectorTecnico(String directorTecnico) {
        this.directorTecnico = directorTecnico;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Equipo=" + equipo + ", Continente=" + continente + ", Director Tecnico=" + directorTecnico + ", Nacionalidad=" + nacionalidad;
    }
}
