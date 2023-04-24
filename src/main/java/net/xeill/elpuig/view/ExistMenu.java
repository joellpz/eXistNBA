package net.xeill.elpuig.view;

import net.xeill.elpuig.controller.ExistGameController;
import net.xeill.elpuig.controller.ExistPlayerController;
import net.xeill.elpuig.controller.ExistSeasonController;
import net.xeill.elpuig.controller.ExistTeamController;

import java.util.Scanner;

/**
 * Clase de los Menus
 */
public class ExistMenu {
    private static ExistPlayerController playerController;
    private static ExistTeamController teamsController;
    private static ExistSeasonController seasonController;
    private static ExistGameController gameController;
    private static Scanner sc;

    public ExistMenu(Scanner sc, ExistPlayerController playerController, ExistTeamController teamsController, ExistSeasonController seasonController, ExistGameController gameController) {
        ExistMenu.sc = sc;
        ExistMenu.playerController = playerController;
        ExistMenu.teamsController = teamsController;
        ExistMenu.seasonController = seasonController;
        ExistMenu.gameController = gameController;

    }

    /**
     * Menu de Tablas
     * @return tabla
     */
    //Menu de Seleccion de Tablas
    public String tableMenu() {
        int opt = -1;
        do {
            System.out.println(" ** Selecciona la Tabla **");
            System.out.println("1. Players");
            System.out.println("2. Teams");
            System.out.println("3. Seasons");
            System.out.println("4. Games");
            System.out.println("0. Atrás");
            try {
                opt = Integer.parseInt(sc.nextLine());
                if (opt < 0 || opt > 4) System.out.println(" ** Error en el Formato del Input **");
            } catch (NumberFormatException e) {
                System.out.println(" ** Error en el Formato del Input **");
            }
        } while (opt < 0 || opt > 5);
        switch (opt) {
            case 1 -> {
                return "players";
            }
            case 2 -> {
                return "teams";
            }
            case 3 -> {
                return "seasons";
            }
            case 4 -> {
                return "games";
            }
            case 0 -> {
                return "back";
            }
            default -> throw new RuntimeException();
        }
    }

    /**
     * Menu de Acciones
     * @return acción
     */
    public String actionsMenu() {
        int opt = -1;
        do {
            System.out.println(" ** Que quieras hacer? **");
            System.out.println("1. Seleccionar");
            System.out.println("2. Insertar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Salir");
            try {
                opt = Integer.parseInt(sc.nextLine());
                if (opt < 0 || opt > 4) System.out.println(" ** Error en el Formato del Input **");
            } catch (NumberFormatException e) {
                System.out.println(" ** Error en el Formato del Input **");
            }
        } while (opt < 0 || opt > 4);
        switch (opt) {
            case 1 -> {
                return "select";
            }
            case 2 -> {
                return "insert";
            }
            case 3 -> {
                return "update";
            }
            case 4 -> {
                return "delete";
            }
            case 0 -> {
                return "exit";
            }
            default -> {
                return null;
            }
        }
    }
}
