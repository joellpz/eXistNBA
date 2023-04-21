package net.xeill.elpuig;

import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExistPlayerController {
    ExistController existController;
    Scanner sc;

    public ExistPlayerController(ExistController existController, Scanner sc) {
        this.existController = existController;
        this.sc = sc;
    }

    List<String> attributes = new ArrayList(Arrays.asList("name", "position", "college", "draftTeam", "draftPos", "born", "age", "draftYear", "expCareer"));

    public List<String> getAttributes() {
        return attributes;
    }

    public void executeCommand(String type) {
        String cmd = "update " + type + "\\n doc('/db/nba/players.xml')/NBAPlayers/Player where";
        existController.executeCommand(cmd + filter());
    }

    public void executeQuery() {
        String query = "for $i in doc('/db/nba/players.xml')/NBAPlayers/Player where $i/";
        query = query.concat(filter() + " return $i");
        System.out.println(" ** Que valor quieres devolver? **");
        int attr = attributesMenu("query");
        if (attr != -1) query = query.concat("/" + attributes.get(attr));

        existController.printResultSequence(existController.executeQuery(query));
    }

    public String filter() {
        String filter, opt;
        int attr = attributesMenu("command");
        boolean rep;
        do {
            rep = false;
            System.out.println(" ** ¿Qué tipo de filtro que quieres aplicar (<, >, =, !=)? ** ");
            System.out.println("*** Ten cuidado que algunos no aplican a la Tipologia de dato que puedes tratar. ***");
            opt = sc.nextLine();
            if (!opt.equalsIgnoreCase(">") && !opt.equalsIgnoreCase("<")
                    && !opt.equalsIgnoreCase("=") && !opt.equalsIgnoreCase("!="))
                rep = true;
        } while (rep);
        System.out.println(" ** ¿Qué valor quieres comparar? **");
        filter = attributes.get(attr) + " " + opt + " '" + sc.nextLine() + "'";
        return filter;
    }

    public int attributesMenu(String type) {
        int opt = -1;
        if (type.equalsIgnoreCase("query")) {
            do {
                for (int i = 0; i < attributes.size(); i++) {
                    System.out.println(i + 1 + ". " + attributes.get(i));
                }
                try {
                    System.out.println("0. Todo");
                    opt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" ** Error en el Formato del Input **");
                }
            } while (opt < 0 || opt > attributes.size());
        } else if (type.equalsIgnoreCase("command")) {
            do {
                for (int i = 0; i < attributes.size(); i++) {
                    System.out.println(i + 1 + ". " + attributes.get(i));
                }
                try {
                    opt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" ** Error en el Formato del Input **");
                }
            } while (opt < 1 || opt > attributes.size());
        }
        return opt-1;
    }


}
