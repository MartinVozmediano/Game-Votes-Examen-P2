public class Videojuego {

    int codigoId;
    String nombre;
    String estudio;
    String categoria;
    int anioLan;
    int votos;

    public Videojuego(int codigoId, String nombre, String estudio, String categoria, int anioLan, int votos) {
        this.codigoId = codigoId;
        this.nombre = nombre;
        this.estudio = estudio;
        this.categoria = categoria;
        this.anioLan = anioLan;
        this.votos = votos;
    }

    public Videojuego(int codigoId, String nombre, String estudio, String categoria, int anioLan) {
        this.codigoId = codigoId;
        this.nombre = nombre;
        this.estudio = estudio;
        this.categoria = categoria;
        this.anioLan = anioLan;
    }

    public int getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(int codigoId) {
        this.codigoId = codigoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnioLan() {
        return anioLan;
    }

    public void setAnioLan(int anioLan) {
        this.anioLan = anioLan;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
