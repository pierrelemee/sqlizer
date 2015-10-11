package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.operators.OperatorType;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteTest extends TestCase {

    protected Connection connection;

    public DeleteTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/data/Chinook_Sqlite_AutoIncrementPKs.sqlite");
    }

    @Test
    public void testDeleteSimple() throws Exception {
        this.connection.setAutoCommit(false);

        Delete insert = Query
                .delete()
                .from("Album")
                .where("Title", OperatorType.EQUALS, "Mezmerize");

        this.connection.createStatement().executeUpdate(insert.toSQL());

        ResultSet result = this.connection.createStatement().executeQuery("select count(*) as total from Album");

        assertTrue(result.next());
        assertEquals(346, result.getInt("total"));

        this.connection.rollback();
    }
}
