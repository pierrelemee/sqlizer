package fr.pierrelemee.sqlizer.data.entities;

import fr.pierrelemee.sqlizer.Insert;
import fr.pierrelemee.sqlizer.Query;
import fr.pierrelemee.sqlizer.Update;
import fr.pierrelemee.sqlizer.Value;
import fr.pierrelemee.sqlizer.data.Entity;
import fr.pierrelemee.sqlizer.operators.OperatorType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Artist extends Entity {

    protected int id;
    protected String name;

    public Artist() {
        super();
    }

    public Artist(ResultSet result) throws SQLException {
        super(result);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void inflate(ResultSet result) throws SQLException {
        this.id = result.getInt("ArtistId");
        this.name = result.getString("Name");
    }

    @Override
    protected Insert getInsertQuery() {
        return Query
                .insert()
                .into(this.getTableName())
                .value("ArtistId", Value.fromNumbers(this.id)[0])
                .value("Name", this.name);
    }

    @Override
    protected void onInsertComplete(ResultSet result) throws SQLException {
        this.id = result.getInt(1);
    }

    @Override
    protected Update getUpdateQuery() {
        try {
            return Query
                    .update()
                    .table(this.getTableName())
                    .set("Name", this.name)
                    .where("ArtistId", OperatorType.EQUALS, this.id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Artist {id: %d, name: %s}", this.id, this.name);
    }
}
