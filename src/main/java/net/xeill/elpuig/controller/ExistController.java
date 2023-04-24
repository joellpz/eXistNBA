package net.xeill.elpuig.controller;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

//import net.xqj.exist.ExistXQDataSource;

/**
 * Controlador General
 */
public class ExistController {
    /**
     * Conexión.
     */
    private XQConnection connection;

    /**
     * Constructor
     */
    public ExistController() {
        try {
            XQDataSource xqs = new ExistXQDataSource();
            xqs.setProperty("serverName", "localhost");
            xqs.setProperty("port", "8080");
            connection = xqs.getConnection("admin","admin");

        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ejecuta la query deseada
     * @param query Query
     * @return resultado
     */
    public XQResultSequence executeQuery(String query) {
        try {
            XQExpression xqe = connection.createExpression();
            XQResultSequence xqrs = xqe.executeQuery(query);
            return xqrs;
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ejecuta el comando definido
     * @param command Comando
     * @return estado
     */
    public boolean executeCommand(String command) {
        try {
            XQExpression xqe = connection.createExpression();
            xqe.executeCommand(command);
            System.out.println("** Instrucción Ejecutada **");
            return true;
        } catch (XQException e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Muestra el resultado de la Query
     * @param xqrs Resultado
     */
    public void printResultSequence(XQResultSequence xqrs) {
        try {
            while (xqrs.next()) {
                System.out.println(xqrs.getItemAsString(null));
            }
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }
}