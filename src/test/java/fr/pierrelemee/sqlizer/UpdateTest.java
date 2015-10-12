package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.operators.OperatorType;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTest extends TestCase {

    protected Connection connection;

    public UpdateTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/data/Chinook_Sqlite_AutoIncrementPKs.sqlite");
    }

    @Test
    public void testUpdateSimple() throws Exception {
        this.connection.setAutoCommit(false);

        Update update = Query
                .update()
                .table("Album")
                .set("Title", "Steal this album")
                .where("Title", OperatorType.EQUALS, "Mezmerize");

        this.connection.createStatement().executeUpdate(update.toSQL());

        ResultSet result = this.connection.createStatement().executeQuery("select count(*) as total from Album where Title = \"Mesmerize\"");

        assertTrue(result.next());
        assertEquals(0, result.getInt("total"));

        this.connection.rollback();
    }
}
