package com.ll.wisesaying.domain.wiseSaying.repository;

import com.ll.wisesaying.domain.wiseSaying.model.entity.WiseSaying;
import com.ll.wisesaying.external.mysql.util.DBUtil;

import java.sql.*;

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

}
