import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;

public class GestionVideojuegos {

    private ArrayList<Videojuego> videojuegosLista;

    public GestionVideojuegos() {

        videojuegosLista = new ArrayList<>();
        predefinir();
        ordenarId();
    }

    public void predefinir() {
        videojuegosLista.add(new Videojuego(10, "Hollow Knight", "Team Cherry",
                "Plataformas", 2017, 50));
        videojuegosLista.add(new Videojuego(11, "Celeste", "Maddy Makes Games",
                "Plataformas", 2018, 20));
        videojuegosLista.add(new Videojuego(12, "Stardew Valley", "ConcernedApe",
                "Simulación", 2016, 15));
        videojuegosLista.add(new Videojuego(13, "Hades", "Supergiant Games",
                "Acción", 2020, 25));
        videojuegosLista.add(new Videojuego(14, "Undertale", "Toby Fox",
                "RPG", 2015, 10));
        videojuegosLista.add(new Videojuego(15, "Cuphead", "Studio MDHR",
                "Acción", 2017, 20));
        videojuegosLista.add(new Videojuego(16, "Dead Cells", "Motion Twin",
                "Acción", 2018, 25));
        videojuegosLista.add(new Videojuego(17, "Slay the Spire", "Mega Crit",
                "Estrategia", 2019, 25));
        videojuegosLista.add(new Videojuego(18, "Disco Elysium", "ZA/UM",
                "RPG", 2019, 40));
        videojuegosLista.add(new Videojuego(19, "Outer Wilds", "Mobius Digital",
                "Simulación", 2019, 25));
        videojuegosLista.add(new Videojuego(20, "Terraria", "Re-Logic",
                "Simulación", 2011, 10));
        videojuegosLista.add(new Videojuego(21, "Balatro", "LocalThunk",
                "Estrategia", 2024, 15));
        videojuegosLista.add(new Videojuego(22, "Shovel Knight", "Yacht Club Games",
                "Plataformas", 2014, 25));
        videojuegosLista.add(new Videojuego(23, "The Binding of Isaac: Rebirth", "Edmund McMillen",
                "Acción", 2014, 15));
        videojuegosLista.add(new Videojuego(24, "Papers, Please", "Lucas Pope",
                "Simulación", 2013, 10));
        videojuegosLista.add(new Videojuego(25, "Katana ZERO", "Askiisoft",
                "Acción", 2019, 15));
        videojuegosLista.add(new Videojuego(26, "Gris", "Nomada Studio",
                "Plataformas", 2018, 15));
        videojuegosLista.add(new Videojuego(27, "Hotline Miami", "Dennaton Games",
                "Acción", 2012, 10));
        videojuegosLista.add(new Videojuego(28, "Baba Is You", "Hempuli",
                "Estrategia", 2019, 15));
        videojuegosLista.add(new Videojuego(29, "Vampire Survivors", "poncle",
                "Acción", 2022, 5));
    }

    public int mostrarCantidad(){
        return videojuegosLista.size();
    }

    public void agregar(Videojuego juego) throws Exception {

        if(juego.getCodigoId() <= 0){
            throw new Exception("El ID ingresado no es valido");
        }

        if(juego.getNombre().isBlank() || juego.getNombre() == null ||
                !juego.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$")){
            throw new Exception("El nombre ingresado no es valido");
        }

        if (juego.getEstudio() == null || juego.getEstudio().isBlank()) {
            throw new Exception("El estudio es obligatorio");

        }

        if (juego.getCategoria() == null || juego.getCategoria().isBlank()) {
            throw new Exception("La categoría es obligatoria");
        }

        validarAnio(juego.getAnioLan());

        if (unico(juego)){
            videojuegosLista.add(juego);
            ordenarId();
        }else {
            throw new Exception("El dato ingresado ya existe");
        }
    }

    public ArrayList<Videojuego> buscarNombre(String nombre){
        ArrayList<Videojuego> resultado = new ArrayList<>();
        for (Videojuego v : videojuegosLista){
            if (v.getNombre().equals(nombre)){
                resultado.add(v);
            }
        }
        return resultado;
    }

    public void validarAnio(int anio) throws Exception {
        if (anio < 1980 || anio > Year.now().getValue()) {
            throw new Exception("El año debe estar entre 1980 y el año actual");
        }
    }

    public int busquedaBinaria(int codigo) {
        int inicio = 0;
        int fin = videojuegosLista.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int actual = videojuegosLista.get(medio).getCodigoId();
            if (actual == codigo) {
                return medio;
            }
            if (actual > codigo) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    public void editar(int indice, String nombre, String estudio,
                       String categoria, String anioTexto) throws Exception {
        if (indice < 0 || indice >= videojuegosLista.size()) {
            throw new Exception("No hay un videojuego seleccionado");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (estudio == null || estudio.isBlank()) {
            throw new Exception("El estudio es obligatorio");
        }
        if (categoria == null || categoria.isBlank()) {
            throw new Exception("La categoría es obligatoria");
        }
        int anio;
        try {
            anio = Integer.parseInt(anioTexto.trim());
        } catch (NumberFormatException e) {
            throw new Exception("El año debe ser un número entero");
        }
        validarAnio(anio);



        Videojuego v = videojuegosLista.get(indice);
        v.setNombre(nombre.trim());
        v.setEstudio(estudio.trim());
        v.setCategoria(categoria);
        v.setAnioLan(anio);
    }

    public ArrayList<Videojuego> buscarTexto(String texto, String criterio) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese un texto para buscar");
        }
        ArrayList<Videojuego> resultado = new ArrayList<>();
        for (Videojuego v : videojuegosLista) {
            boolean enNombre = v.getNombre().contains(texto);
            boolean enEstudio = v.getEstudio().contains(texto);
            boolean coincide;
            if ("Nombre".equals(criterio)) {
                coincide = enNombre;
            } else if ("Estudio".equals(criterio)) {
                coincide = enEstudio;
            } else {
                coincide = enNombre || enEstudio;
            }
            if (coincide) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public void eliminar(int indice) throws Exception {
        if (indice < 0 || indice >= videojuegosLista.size()) {
            throw new Exception("No hay un videojuego seleccionado");
        }
        videojuegosLista.remove(indice);
    }

    public void restarVotos(int indice, int cantidad) throws Exception {
        if (indice < 0 || indice >= videojuegosLista.size()) {
            throw new Exception("No hay un videojuego seleccionado");
        }
        Videojuego v = videojuegosLista.get(indice);
        if (v.getVotos() < cantidad) {
            throw new Exception("El videojuego solo tiene " + v.getVotos() + " votos");
        }
        v.setVotos(v.getVotos() - cantidad);
    }

    public ArrayList<Videojuego> obtenerRanking() {
        ArrayList<Videojuego> copia = new ArrayList<>(videojuegosLista);
        copia.sort(Comparator.comparingInt(Videojuego::getVotos).reversed());
        return copia;
    }

    public Videojuego obtener(int indice) {
        return videojuegosLista.get(indice);
    }

    public ArrayList<Videojuego> buscarEstudio(String nombre){
        ArrayList<Videojuego> resultado = new ArrayList<>();
        for (Videojuego v : videojuegosLista){
            if (v.getEstudio().equals(nombre)){
                resultado.add(v);
            }
        }
        return resultado;
    }

    public void ordenarId() {
        videojuegosLista.sort(Comparator.comparingInt(Videojuego::getCodigoId).reversed());
    }

    public ArrayList<Videojuego> getLista() {
        return videojuegosLista;
    }

    public boolean unico(Videojuego juego) {
        for (Videojuego v : videojuegosLista) {
            if (v.getCodigoId() == juego.getCodigoId() || v.getNombre().equals(juego.getNombre())) {
                return false;
            }
        }
        return true;
    }



}
