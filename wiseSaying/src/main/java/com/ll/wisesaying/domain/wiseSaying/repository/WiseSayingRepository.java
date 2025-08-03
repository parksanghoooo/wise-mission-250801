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
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1); // 생성된 ID
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public List<WiseSaying> findAllDesc() {
        String sql = "SELECT id, content, author FROM wiseSayings ORDER BY id DESC";
        List<WiseSaying> list = new ArrayList<>();

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String author = rs.getString("author");

                list.add(new WiseSaying(id, content, author));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
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


}
