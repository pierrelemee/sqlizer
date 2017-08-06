package fr.pierrelemee.sqlizer.data.entities;

import fr.pierrelemee.sqlizer.Select;
import fr.pierrelemee.sqlizer.data.Entity;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EntityTest extends TestCase {

    protected Connection connection;

    public EntityTest() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:src/test/resources/data/Chinook_Sqlite_AutoIncrementPKs.sqlite");
    }

    @Before
    public void before() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    @After
    public void after() throws SQLException {
        this.connection.rollback();
    }

    @Test
    public void testGetArtistById() throws Exception {
        Artist artist = Entity.one(Select
            .select()
                .from("Artist")
                .where("ArtistId", OperatorType.EQUALS, 94),
                this.connection,
                Artist.class
        );
        assertNotNull(artist);
        assertEquals("Jimi Hendrix", artist.getName());
    }
}
