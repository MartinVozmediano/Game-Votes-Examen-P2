import java.util.ArrayList;
import java.util.Comparator;

public class GestionVideojuegos {

    private ArrayList<Videojuego> videojuegosLista;

    public GestionVideojuegos() {

        videojuegosLista = new ArrayList<>();
        predefinir();

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

    public void agregar(Videojuego juego) throws Exception {
        if (juego != null && unico(juego)){
            videojuegosLista.add(juego);
            ordenarId();
        }else {
            throw new Exception("El dato ingresado ya existe");
        }
    }

    public void ordenarId() {
        videojuegosLista.sort(Comparator.comparingInt(Videojuego::getCodigoId).reversed());
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
