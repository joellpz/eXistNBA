package net.xeill.elpuig;

import com.fasterxml.jackson.core.format.MatchStrength;
import net.xqj.exist.bin.E;

import java.util.List;
import java.util.Scanner;

public class ExistMenu {
    private static ExistPlayerController playerController;
    private static ExistTeamsController teamsController;
    private static Scanner sc;

    public ExistMenu(Scanner sc, ExistPlayerController playerController, ExistTeamsController teamsController) {
        ExistMenu.sc = sc;
        ExistMenu.playerController = playerController;
        ExistMenu.teamsController = teamsController;
    }

    //Menu de Seleccion de Tablas
    public void principalMenu() {
        int opt = -1;
        do {
            System.out.println("1. Players");
            System.out.println("2. Teams");
            System.out.println("3. Seasons");
            System.out.println("4. Games");
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" ** Error en el Formato del Input **");
            }
            switch (opt) {
                case 1 -> {

                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                }
                case 5 -> {
                }
            }
        } while (opt < 1 || opt > 5);
    }

    public static void actionsMenu() {
        int opt = -1;
        do {
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" ** Error en el Formato del Input **");
            }
            System.out.println("1. Seleccionar");
            System.out.println("2. Insertar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Atrás");

            switch (opt) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                case 0 -> {

                }
            }
        } while (opt < 0 || opt > 4);
    }

//    public static void attributesMenu() {
//        List<String> attributes;
//        int opt = -1;
//        do {
//            try {
//                opt = Integer.parseInt(sc.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println(" ** Error en el Formato del Input **");
//            }
//            switch (opt) {
//                case 1 -> {
//                }
//                case 2 -> {
//                }
//                case 3 -> {
//                }
//                case 4 -> {
//                }
//                case 5 -> {
//                }
//                default -> {
//                }
//            }
//        } while (opt < 1 || opt > 5);
//    }


}
