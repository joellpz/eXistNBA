package net.xeill.elpuig;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws XQException {
        ExistController ec = new ExistController();
//        XQResultSequence xqrs = ec.executeQuery("for $game in doc('/db/nba/games.xml')/NBAGames/Game where $game/teamVisitor='Miami Heat' return $game");
//        XQResultSequence xqrs = ec.executeQuery("doc('/db/nba/games.xml')/NBAGames/Game where teamVisitor='Miami Heat' return game/");
//        ec.printResultSequence(xqrs);


        Scanner sc = new Scanner(System.in);
        ExistPlayerController playerController = new ExistPlayerController(ec, sc);
        ExistTeamController teamsController = new ExistTeamController(ec, sc);
        ExistSeasonController seasonController = new ExistSeasonController(ec, sc);
        ExistGameController gameController = new ExistGameController(ec, sc);


        ExistMenu menu = new ExistMenu(sc, playerController, teamsController,seasonController,gameController);
        String action, table;
        boolean rep = true;
        do {
            try {
                rep = true;
                action = menu.actionsMenu();
                if (!action.equalsIgnoreCase("exit")) {
                    table = menu.tableMenu();
                    switch (table) {
                        case "players" -> {
                            switch (action){
                                case "select" -> playerController.executeQuery();
                                case "insert" -> playerController.insertPlayer();
                                case "delete" -> playerController.deletePlayer();
                                case "update" -> playerController.updatePlayer();
                            }
                        }
                        case "teams" -> {
                            switch (action){
                                case "select" -> teamsController.executeQuery();
                                case "insert" -> teamsController.insertTeam();
                                case "delete" -> teamsController.deleteTeam();
                                case "update" -> teamsController.updateTeam();
                            }
                        }
                        case "seasons" -> {
                            switch (action){
                                case "select" -> seasonController.executeQuery();
                                case "insert" -> seasonController.insertSeason();
                                case "delete" -> seasonController.deleteSeason();
                                case "update" -> seasonController.updateSeason();
                            }

                        }
                        case "games" -> {
                            switch (action){
                                case "select" -> gameController.executeQuery();
                                case "insert" -> gameController.insertGame();
                                case "delete" -> gameController.deleteGame();
                                case "update" -> gameController.updateGame();
                            }
                        }
                        default -> {}
                    }
                } else {
                    rep = false;
                }
            } catch (RuntimeException e) {
                System.out.println(" ** ERROR from the MENU ** ");
            }
            //xqrs = ec.executeQuery("for $book in doc('/db/apps/foaf/books.xml')/library/book where $book/year < 1960 return $book");
            //ec.printResultSequence(xqrs);
        } while (rep);
    }

}


//TODO * Conexión con la base de datos mediante Java.
//TODO * Manejo de la conexión mediante un menú de terminal que debe tener:
//TODO * Diferentes opciones que permitan consulta sobre la información. Ejemplos:
//TODO  - Seleccionar todos los elementos que contengan un texto concreto.
//TODO  - Seleccionar todos los elementos que cumplan una condición.
//TODO  - Seleccionar elementos concretos.
//TODO * Posibilidad de modificar un registro concreto de información. Ejemplo:
//TODO  - Seleccionar un elemento concreto y permitir su modificación.
//TODO * Posibilidad de modificar diferentes registros de información.
//TODO * Posibilidad de eliminar un registro concreto de información.
//TODO * Posibilidad de eliminar un conjunto de registros de información que cumplan un condición.
