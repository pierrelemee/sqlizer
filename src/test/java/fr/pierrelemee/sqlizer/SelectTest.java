package fr.pierrelemee.sqlizer;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest extends TestCase {

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::resource:data/Chinook_Sqlite_AutoIncrementPKs.sqlite");
    }

    @Test
    public void testSelectSimple() throws Exception {
        Select select = Query.select()
                .field("count(*)", "total")
                .from("Artist");

        ResultSet result = this.getConnection().createStatement().executeQuery(select.toSQL());
        int count = 0;
        assertTrue(result.next());
        assertEquals(275, result.getInt("total"));
    }

    @Test
    public void testSelectWhere() throws Exception {
        Select select = Query.select()
                .field("count(*)", "total")
                .from("Artist")
                .where("name", OperatorType.LIKE, "The %");

        ResultSet result = this.getConnection().createStatement().executeQuery(select.toSQL());

        assertTrue(result.next());
        assertEquals(14, result.getInt("total"));
    }
}
