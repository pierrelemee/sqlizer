package fr.pierrelemee.sqlizer.data;

import fr.pierrelemee.sqlizer.Insert;
import fr.pierrelemee.sqlizer.Query;
import fr.pierrelemee.sqlizer.Select;
import fr.pierrelemee.sqlizer.Update;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    protected boolean isDbSync = false;

    public Entity() {}

    public Entity(ResultSet result) throws SQLException {
        this.inflate(result);
        this.isDbSync = true;
    }

    protected String getTableName()
    {
        return this.getClass().getSimpleName();
    }

    public abstract void inflate(ResultSet result) throws SQLException;

    public final void save(Connection connection) throws SQLException, Exception {
        Statement stmt = connection.createStatement();
        Query query = this.isDbSync ? this.getUpdateQuery() : this.getInsertQuery();
        stmt.executeUpdate(query.toSQL(), Statement.RETURN_GENERATED_KEYS);
        ResultSet result = stmt.getGeneratedKeys();
        if (result.next()) {
            if (this.isDbSync) {
                this.onUpdateComplete(result);
            } else {
                this.onInsertComplete(result);
            }
            this.isDbSync = true;
        }
    }

    protected abstract Insert getInsertQuery();

    protected void onInsertComplete(ResultSet resultSet) throws SQLException {}

    protected abstract Update getUpdateQuery();

    protected void onUpdateComplete(ResultSet resultSet) {}

    public static <E extends Entity> E one(Select select, Connection connection, Class<E> clazz) throws SQLException, Exception {
        return one(select.toSQL(), connection, clazz);
    }

    public static <E extends Entity> E one(String query, Connection connection, Class<E> clazz) throws SQLException {
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            return result.next() ? clazz.getDeclaredConstructor(ResultSet.class).newInstance(result) : null;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <E extends Entity> List<E> all(Select select, Connection connection, Class<E> clazz) throws SQLException, Exception {
        return all(select.toSQL(), connection, clazz);
    }

    public static <E extends Entity> List<E> all(String query, Connection connection, Class<E> clazz) throws SQLException {
        List<E> entities = new ArrayList<E>();
        try {
            ResultSet result = connection.createStatement().executeQuery(query);

            while (result.next()) {
                entities.add(clazz.getDeclaredConstructor(ResultSet.class).newInstance(result));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return entities;
    }
}

