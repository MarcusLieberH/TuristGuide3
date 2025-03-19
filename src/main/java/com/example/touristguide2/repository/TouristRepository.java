package com.example.touristguide2.repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.model.TouristTags;
import com.example.touristguide2.model.TouristTowns;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Hent alle attraktioner
    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT * FROM TouristAttraction";
        List<TouristAttraction> attractions = jdbcTemplate.query(sql, attractionRowMapper);

        for (TouristAttraction a : attractions) {
            List<TouristTags> tags = getTagsForAttraction(a.getName());
            a.setTags(tags);
        }

        return attractions;
    }

    // Hent en attraktion via navn
    public TouristAttraction getAttractionByName(String name) {
        String sql = "SELECT * FROM TouristAttraction WHERE name = ?";
        try {
            TouristAttraction attraction = jdbcTemplate.queryForObject(sql, attractionRowMapper, name);
            System.out.println("Attraction found: " + attraction);
            return attraction;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No attraction found for name: " + name);
            return null;
        }
    }

    // Tilføj en attraktion og tilknyttede tags
    public TouristAttraction addAttraction(TouristAttraction attraction, List<String> tags) {
        String sql = "INSERT INTO TouristAttraction (name, description, town, image) VALUES (?, ?, ?, ?)";

        // Brug KeyHolder til at få det genererede attraction_id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, attraction.getName());
            ps.setString(2, attraction.getDescription());
            ps.setString(3, attraction.getTowns().name());
            ps.setString(4, attraction.getImage());
            return ps;
        }, keyHolder);

        // Hent den genererede attraction_id
        Number generatedId = keyHolder.getKey();
        if (generatedId == null) {
            throw new RuntimeException("Fejl: Kunne ikke hente attraction_id fra databasen.");
        }

        Long attractionId = generatedId.longValue();
        attraction.setId(attractionId);

        // Indsæt tags i AttractionTags-tabellen
        if (tags != null && !tags.isEmpty()) {
            for (String tagName : tags) {
                String tagSql = "INSERT INTO AttractionTags (attraction_id, tag) VALUES (?, ?)";
                jdbcTemplate.update(tagSql, attractionId, tagName);
            }
        }

        return attraction;
    }


    // Opdater en attraktion
    // Opdater en attraktion
    public TouristAttraction updateAttraction(TouristAttraction attraction) {
        // Først opdater billede, beskrivelse og by
        String sql = "UPDATE TouristAttraction SET description = ?, town = ?, image = ? WHERE name = ?";
        jdbcTemplate.update(sql, attraction.getDescription(), attraction.getTowns().name(), attraction.getImage(), attraction.getName());

        // Hent attraction_id fra databasen
        TouristAttraction updatedAttraction = getAttractionByName(attraction.getName());
        if (updatedAttraction == null || updatedAttraction.getId() == null) {
            throw new RuntimeException("Fejl: Kunne ikke finde attraction_id for " + attraction.getName());
        }

        // Fjern eksisterende tags
        String deleteTagsSql = "DELETE FROM AttractionTags WHERE attraction_id = ?";
        jdbcTemplate.update(deleteTagsSql, updatedAttraction.getId());

        // Tilføj de nye tags
        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            for (TouristTags tag : attraction.getTags()) {
                String tagSql = "INSERT INTO AttractionTags (attraction_id, tag) VALUES (?, ?)";
                jdbcTemplate.update(tagSql, updatedAttraction.getId(), tag.name());
            }
        }

        return updatedAttraction;
    }


    // Slet en attraktion
    public boolean deleteAttraction(String name) {
        String sql = "DELETE FROM TouristAttraction WHERE name = ?";
        return jdbcTemplate.update(sql, name) > 0;
    }

    // Hent tags for en attraktion
    public List<TouristTags> getTagsForAttraction(String attractionName) {
        String sql = "SELECT tag FROM AttractionTags " +
                "JOIN TouristAttraction ON AttractionTags.attraction_id = TouristAttraction.attraction_id " +
                "WHERE TouristAttraction.name = ?";
        return jdbcTemplate.query(sql, new Object[]{attractionName}, (rs, rowNum) ->
                TouristTags.valueOf(rs.getString("tag"))
        );
    }

    // RowMapper til at mappe resultater til Java-objekter
    private final RowMapper<TouristAttraction> attractionRowMapper = (rs, rowNum) -> {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setId(rs.getLong("attraction_id"));
        attraction.setName(rs.getString("name"));
        attraction.setDescription(rs.getString("description"));
        attraction.setTown(TouristTowns.fromDisplayName(rs.getString("town")));
        attraction.setImage(rs.getString("image"));
        return attraction;
    };
    public void updateTagsForAttraction(TouristAttraction attraction, List<String> tags) {
        // Først fjern de gamle tags
        String deleteTagsSql = "DELETE FROM AttractionTags WHERE attraction_id = (SELECT attraction_id FROM TouristAttraction WHERE name = ?)";
        jdbcTemplate.update(deleteTagsSql, attraction.getName());

        // Tilføj de nye tags
        if (tags != null && !tags.isEmpty()) {
            TouristAttraction updatedAttraction = getAttractionByName(attraction.getName());
            for (String tag : tags) {
                String tagSql = "INSERT INTO AttractionTags (attraction_id, tag) VALUES (?, ?)";
                jdbcTemplate.update(tagSql, updatedAttraction.getId(), tag);
            }
        }
    }

}
