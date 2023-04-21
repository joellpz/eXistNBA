package net.xeill.elpuig;

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
    public String tableMenu() {
        int opt = -1;
        do {
            System.out.println("1. Players");
            System.out.println("2. Teams");
            System.out.println("3. Seasons");
            System.out.println("4. Games");
            System.out.println("0. Atrás");
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" ** Error en el Formato del Input **");
            }
        } while (opt < 1 || opt > 5);
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
            default -> throw  new RuntimeException();
        }
    }

    public String actionsMenu() {
        int opt = -1;
        do {
            System.out.println("1. Seleccionar");
            System.out.println("2. Insertar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            try {
                opt = Integer.parseInt(sc.nextLine());
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
            default -> {
                return null;
            }
        }
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
