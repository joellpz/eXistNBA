package net.xeill.elpuig.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para el archivo de los Temporadas.
 */
public class ExistSeasonController {
    /**
     * Variable para definir el Path del documento en ExistDB.
     */
    private static final String EXISTDB_PATH = "doc('/db/apps/nba/seasons.xml')/NBASeasons";
    /**
     * Variable para definir el elemento base que contiene el archivo.
     */
    private static final String BASE_ELEMENT = "Season";
    /**
     * Lista de Campos
     */
    private final List<String> fields = new ArrayList(Arrays.asList("year", "league", "champion", "MVP", "ROTY", "PPG_Leader", "RGP_Leader", "APG_Leader", "WS_Leader", "link"));
    /**
     * Controlador General
     */
    private final ExistController existController;
    /**
     * Scanner
     */
    private final Scanner sc;

    /**
     * Constructor base.
     *
     * @param existController Controlador General
     * @param sc              Scanner
     */
    public ExistSeasonController(ExistController existController, Scanner sc) {
        this.existController = existController;
        this.sc = sc;
    }

    /**
     * Función para insertar un Temporada Nuevo.
     */
    public void insertSeason() {
        String newSeason = "<" + BASE_ELEMENT + ">\n";
        for (String attr : fields) {
            System.out.println("Valor para " + attr + ": ");
            if (attr.equalsIgnoreCase(fields.get(0)))
                newSeason = newSeason.split(">\n")[0].concat(" " + attr + "='" + sc.nextLine() + "'>\n");
            else newSeason = newSeason.concat("   <" + attr + ">" + sc.nextLine() + "</" + attr + ">\n");
        }
        String query = "update insert \n" + newSeason + " </" + BASE_ELEMENT + "> into " + EXISTDB_PATH;
        System.out.println(query);
        existController.executeCommand(query);
    }

    /**
     * Función para actualizar un Temporada.
     */
    public void updateSeason() {
        System.out.println(" ** Qué elemento quieres actualizar? **");
        System.out.println(" ** Introduce el " + fields.get(0) + ": ");
        String query = "update value \n" +
                EXISTDB_PATH + "/" + BASE_ELEMENT + "[@" + fields.get(0) + "='" + sc.nextLine() + "']/";
        System.out.println(" ** Que elemento quieres actualizar? **");
        int atr = attributesMenu("command");
        query = query.concat(fields.get(atr) + "\n" +
                "with ");
        System.out.println(" ** Que valor quieres poner? **");
        query = query.concat("'" + sc.nextLine() + "'");
        System.out.println("Update: "+query);
        existController.executeCommand(query);

    }

    /**
     * Función para eliminar un Temporada.
     */
    public void deleteSeason() {
        System.out.println(" ** Selecciona que quieres comparar para eliminar **");
        String query = "update delete " + EXISTDB_PATH + "/" + BASE_ELEMENT + "[" + filter() + "]";
        existController.executeCommand(query);
    }

    /**
     * Función para definir la consulta "select" para enviarsela al Controlador General.
     */
    public void executeQuery() {
        String query = "for $i in " + EXISTDB_PATH + "/" + BASE_ELEMENT + " where $i/";
        query = query.concat(filter() + " return $i");
        System.out.println(" ** Que valor quieres devolver? **");
        int attr = attributesMenu("query");
        if (attr != -1) query = query.concat("/" + fields.get(attr));
        System.out.println("Query: "+query);
        existController.printResultSequence(existController.executeQuery(query));
    }

    /**
     * Permite definir el filtro con el que queremos realizar la consulta o comando.
     * @return Query de filtro
     */
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
                    && !opt.equalsIgnoreCase("=") && !opt.equalsIgnoreCase("!=")) {
                rep = true;
                System.out.println(" ** Valor Incorrecto **");
            }
        } while (rep);
        System.out.println(" ** ¿Qué valor quieres comparar? **");
        filter = fields.get(attr) + " " + opt + " '" + sc.nextLine() + "'";
        if (attr == 0) filter = "@" + filter;
        return filter;
    }

    /**
     * Menu para listar los Campos
     *
     * @param type Tipologia de acción
     * @return indice del campo seleccionado
     */
    public int attributesMenu(String type) {
        int opt = -1;
        System.out.println(" ** Selecciona la opción que quieras. **");
        if (type.equalsIgnoreCase("query")) {
            do {
                for (int i = 0; i < fields.size(); i++) {
                    System.out.println(i + 1 + ". " + fields.get(i));
                }
                try {
                    System.out.println("0. Todo");
                    opt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" ** Error en el Formato del Input **");
                }
            } while (opt < 0 || opt > fields.size());
        } else if (type.equalsIgnoreCase("command")) {
            do {
                for (int i = 0; i < fields.size(); i++) {
                    System.out.println(i + 1 + ". " + fields.get(i));
                }
                try {
                    opt = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" ** Error en el Formato del Input **");
                }
            } while (opt < 1 || opt > fields.size());
        }
        return opt - 1;
    }
}
