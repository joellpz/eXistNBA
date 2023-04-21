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
        ExistTeamsController teamsController = new ExistTeamsController(ec, sc);

        ExistMenu menu = new ExistMenu(sc, playerController, teamsController);

        try {
            String action = menu.actionsMenu();
            String table = menu.tableMenu();


            switch (table) {
                case "players" -> {
                    if (action.equalsIgnoreCase("select")) playerController.executeQuery();
                    else playerController.executeCommand(action);
                }
                case "teams" -> {
                }
                case "seasons" -> {

                }
                case "games" -> {
                }
            }

//            switch (action) {
//                case "select" -> {
//
//                }
//                case "insert" -> {
//                }
//                case "update" -> {
//                    /*use "value"*/
//                }
//                case "delete" -> {
//                }
//            }
        } catch (RuntimeException e) {
            System.out.println(" ** ERROR from the MENU ** ");
        }
        //xqrs = ec.executeQuery("for $book in doc('/db/apps/foaf/books.xml')/library/book where $book/year < 1960 return $book");
        //ec.printResultSequence(xqrs);

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
