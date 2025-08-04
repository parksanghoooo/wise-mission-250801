package com.ll.wisesaying.domain.wiseSaying.repository;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.external.mysql.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    public long save(WiseSaying wiseSaying) {
        String sql = "INSERT INTO wiseSayings (content, author) VALUES (?, ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, wiseSaying.getContent());
            pstmt.setString(2, wiseSaying.getAuthor());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert failed, no rows affected.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1); // 생성된 ID
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public List<WiseSaying> findAllDesc(int offset, int limit) {
        String sql = "SELECT * FROM wiseSayings ORDER BY id DESC LIMIT ? OFFSET ?";
        List<WiseSaying> result = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String content = rs.getString("content");
                    String author = rs.getString("author");
                    result.add(new WiseSaying(id, content, author));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM wiseSayings";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()
        ) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public boolean deleteById(long id) {
        String sql = "DELETE FROM wiseSayings WHERE id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, id);
            int result = pstmt.executeUpdate();
            return result > 0; // 성공 시 1 반환
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WiseSaying findById(long id) {
        String sql = "SELECT id, content, author FROM wiseSayings WHERE id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setLong(1, id);

            // 바인딩 후 try-with-resources
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new WiseSaying(
                            rs.getLong("id"),
                            rs.getString("content"),
                            rs.getString("author")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(WiseSaying wiseSaying) {
        String sql = "UPDATE wiseSayings SET content = ?, author = ? WHERE id = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, wiseSaying.getContent());
            pstmt.setString(2, wiseSaying.getAuthor());
            pstmt.setLong(3, wiseSaying.getId());

            int result = pstmt.executeUpdate();
//            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        String sql = "TRUNCATE TABLE wiseSayings";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
