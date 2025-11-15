package com.gs.CareerBooster.repository;

import com.gs.CareerBooster.dao.DataBaseConnection;
import com.gs.CareerBooster.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackRepository {

    @Autowired
    private DataBaseConnection dataBaseConnection;

    public TrackModel save(TrackModel track) {
        String sql = "INSERT INTO tracks (title, description, area) VALUES (?, ?, ?)";

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, track.getTitle());
            stmt.setString(2, track.getDescription());
            stmt.setString(3, track.getArea());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                track.setId(rs.getInt(1));
            }

            System.out.println("Trilha salva com sucesso");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar trilha: " + e.getMessage());
        }

        return track;
    }

    public TrackModel findById(Integer id) {
        String sql = "SELECT * FROM tracks WHERE id = ?";
        TrackModel track = null;

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                track = new TrackModel();
                track.setId(rs.getInt("id"));
                track.setTitle(rs.getString("title"));
                track.setDescription(rs.getString("description"));
                track.setArea(rs.getString("area"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar trilha: " + e.getMessage());
        }

        return track;
    }

    public List<TrackModel> findAll() {
        String sql = "SELECT * FROM tracks";
        List<TrackModel> tracks = new ArrayList<>();

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TrackModel track = new TrackModel();
                track.setId(rs.getInt("id"));
                track.setTitle(rs.getString("title"));
                track.setDescription(rs.getString("description"));
                track.setArea(rs.getString("area"));
                tracks.add(track);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar trilhas: " + e.getMessage());
        }

        return tracks;
    }

    public boolean update(TrackModel track) {
        String sql = "UPDATE tracks SET title = ?, description = ?, area = ? WHERE id = ?";

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, track.getTitle());
            stmt.setString(2, track.getDescription());
            stmt.setString(3, track.getArea());
            stmt.setInt(4, track.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar trilha: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM tracks WHERE id = ?";

        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar trilha: " + e.getMessage());
            return false;
        }
    }
}
