package net.xeill.elpuig;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws XQException {
        ExistController ec = new ExistController();
        Scanner sc = new Scanner(System.in);
        ExistPlayerController playerController = new ExistPlayerController(ec, sc);
        ExistTeamsController teamsController = new ExistTeamsController(ec, sc);

        ExistMenu menu = new ExistMenu(sc,playerController,teamsController);

        menu.principalMenu();
//        XQResultSequence xqrs = ec.executeQuery("for $game in doc('/db/nba/games.xml')/NBAGames/Game where $game/teamVisitor='Miami Heat' return $game/pointsVisitor");
//        ec.printResultSequence(xqrs);
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
