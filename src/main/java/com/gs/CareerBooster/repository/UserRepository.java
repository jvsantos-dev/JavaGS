        package com.gs.CareerBooster.repository;

        import com.gs.CareerBooster.dao.DataBaseConnection;
        import com.gs.CareerBooster.model.UserModel;
        import oracle.jdbc.proxy.annotation.Pre;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

        @Repository
        public class UserRepository {

            @Autowired
            private DataBaseConnection dataBaseConnection;

            public void save(UserModel user) {
                String sql = "INSERT INTO users (password, name, email, username, area_interest) VALUES (?, ?, ?, ?, ?)";

                try (Connection conn = dataBaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, user.getPassword());
                    stmt.setString(2, user.getName());
                    stmt.setString(3, user.getEmail());
                    stmt.setString(4, user.getUsername());
                    stmt.setString(5, user.getArea_interest());

                    stmt.executeUpdate();
                    System.out.println("Usuário salvo com sucesso");

                } catch (SQLException e) {
                    System.out.println("Erro ao salvar usuário: " + e.getMessage());
                }
            }

            public List<UserModel> findAll() {
                String sql = "SELECT * FROM users";
                List<UserModel> users = new ArrayList<>();

                try (Connection conn = dataBaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        UserModel u = new UserModel();
                        u.setId(rs.getInt("id"));
                        u.setPassword(rs.getString("password"));
                        u.setName(rs.getString("name"));
                        u.setEmail(rs.getString("email"));
                        u.setUsername(rs.getString("username"));
                        u.setArea_interest(rs.getString("area_interest"));

                        users.add(u);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao buscar usuários: " + e.getMessage(), e);
                }

                return users;
            }

            public boolean update(UserModel user) {
                String sql = "UPDATE users SET password = ?, name = ?, email = ?, username = ?, area_interest = ? WHERE id = ?";

                try (Connection conn = dataBaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, user.getPassword());
                    stmt.setString(2, user.getName());
                    stmt.setString(3, user.getEmail());
                    stmt.setString(4, user.getUsername());
                    stmt.setString(5, user.getArea_interest());
                    stmt.setInt(6, user.getId());

                    int rowsUpdate = stmt.executeUpdate();
                    System.out.println("Usuário atualizado com sucesso");

                    return rowsUpdate > 0;

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
                }
            }

            public boolean delete(Integer id) {
                String sql = "DELETE FROM users WHERE id = ?";

                try(Connection conn = dataBaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)){

                    stmt.setInt(1, id);
                    int rowsDelete = stmt.executeUpdate();
                    System.out.println("Usuário deletado com sucesso");

                    return rowsDelete > 0;

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
                }
            }

            public UserModel findById(Integer id) {
                String sql = "SELECT * FROM users WHERE id = ?";

                try(Connection conn = dataBaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    if(rs.next()) {
                        UserModel u = new UserModel();

                        u.setId(rs.getInt("id"));
                        u.setPassword(rs.getString("password"));
                        u.setName(rs.getString("name"));
                        u.setEmail(rs.getString("email"));
                        u.setUsername(rs.getString("username"));
                        u.setArea_interest(rs.getString("area_interest"));
                        return u;
                    }

                    return null;
                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
                }
            }

            public boolean existsByEmail(String email) {
                String sql = "SELECT 1 FROM users WHERE email = ?";

                try (Connection conn = dataBaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();

                    return rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao verificar e-mail: " + e.getMessage(), e);
                }
            }

            public boolean existsByUsername(String username) {
                String sql = "SELECT 1 FROM users WHERE username = ?";

                try (Connection conn = dataBaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();

                    return rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao verificar username: " + e.getMessage(), e);
                }
            }
        }
