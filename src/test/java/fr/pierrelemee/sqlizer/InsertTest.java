package fr.pierrelemee.sqlizer;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTest extends TestCase {

    protected Connection connection;

    public InsertTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/data/Chinook_Sqlite_AutoIncrementPKs.sqlite");
    }


    @Before
    public void before() {

    }

    @After
    public void after() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            System.err.println("Impossible to rollback");
        }

    }

    @Test
    public void testInsertSimple() throws Exception {
        this.connection.setAutoCommit(false);

        Insert insert = Query
                .insert()
                .into("Artist")
                .value("name", "Francis Lalanne");

        this.connection.createStatement().executeUpdate(insert.toSQL());

        ResultSet result = this.connection.createStatement().executeQuery("select count(*) as total from Artist");

        assertTrue(result.next());
        assertEquals(276, result.getInt("total"));

        this.connection.rollback();
    }
}
