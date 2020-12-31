package fr.umlv.database;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.Map;

/**
 * The class Database.
 */
public class Database {
    /**
     * The constant DATABASEPATH, path of the database.
     */
    public static final String DATABASEPATH = "jdbc:h2:./db";
    /**
     * DBI : access to the database
     */
    private final DBI dbi;

    /**
     * Instantiates a new Database with DATABASEPATH.
     */
    public Database() {
        dbi = new DBI(DATABASEPATH);
    }

    /**
     * Insert into database a method with its classname, its name, its desc and its return value.
     *
     * @param returnValue the return value
     * @param className   the class name
     * @param methodName  the method name
     * @param desc        the desc
     */
    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static void insertMethodReturnValue(String returnValue, String className, String methodName, String desc) {
        DBI dbi = new DBI(DATABASEPATH);
        dbi.useHandle(handle -> handle.execute("INSERT INTO METHOD VALUES(NULL,?,?,?,?)", className, methodName,desc, returnValue));
    }

    /**
     * Create tables : CLASS, FIELD, METHOD, FIELDUNUSED, METHODUNUSED
     */
    public void createTables() {
        dbi.useHandle(handle -> {
            if (handle.createQuery("SHOW TABLES;").list().size() == 0) {
                handle.execute("create table CLASS(id INT IDENTITY,name VARCHAR(255));");
                handle.execute("create table METHOD(id INT IDENTITY,classname VARCHAR(255),methodname VARCHAR(255),methoddesc VARCHAR(255),value VARCHAR(255));");
                handle.execute("create table FIELD(id INT IDENTITY,classname VARCHAR(255),fieldname VARCHAR(255),fielddesc VARCHAR(255));");
                handle.execute("create table FIELDUNUSED(id INT IDENTITY,classname VARCHAR(255),fieldname VARCHAR(255),fielddesc VARCHAR(255));");
                handle.execute("create table METHODUNUSED(id INT IDENTITY,classname VARCHAR(255),methodname VARCHAR(255),methoddesc VARCHAR(255))");
            }
        });
    }

    /**
     * Insert into a table the element
     *
     * @param table   the table
     * @param element the element
     */
    public void insert(String table, String element) {
        dbi.useHandle(handle -> handle.execute("INSERT INTO " + table + " VALUES(NULL,?)", element));
    }

    /*public void insertInMethodUnused(String table, String className, String methodName, String desc) {
        dbi.useHandle(handle -> handle.execute("INSERT INTO " + table + " VALUES(NULL,?,?,?)", className, methodName, desc));
    }*/


    /**
     * Insert into FIELDUNUSED, every unused fields (classsname, fieldname and desc) from a class.
     *
     * @param table     the table
     * @param className the class name
     * @param fieldName the field name
     * @param desc      the desc
     */
    public void insertInFieldUnused(String table, String className, String fieldName, String desc) {
        dbi.useHandle(handle -> handle.execute("INSERT INTO " + table + " VALUES(NULL,?,?,?)", className, fieldName, desc));
    }

    /**
     * Insert into FIELD, every fields (classsname, fieldname and desc) from a class, except unused.
     *
     * @param className the class name
     * @param fieldName the field name
     * @param desc      the desc
     */
    public void insertField(String className, String fieldName,String desc) {
        dbi.useHandle(handle -> handle.execute("INSERT INTO FIELD VALUES(NULL,?,?,?)", className, fieldName,desc));
    }

    /**
     * Get all elements from table.
     *
     * @param tableName the table name
     * @return the list of elements from tableName.
     */
    public List<Map<String, Object>> get(String tableName) {
        Handle handle = dbi.open();
        List<Map<String, Object>> list = handle.createQuery("select * from " + tableName + ";").list();
        handle.close();
        return list;
    }
}
