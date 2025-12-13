package com.machinery.grievance_system.dao.impl;

import com.machinery.grievance_system.dao.TokenDao;
import com.machinery.grievance_system.model.RefreshToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TokenDaoImpl implements TokenDao {

    private final JdbcTemplate jdbcTemplate;

    public TokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RefreshToken> rowMapper = new RowMapper<RefreshToken>() {
        @Override
        public RefreshToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            RefreshToken t = new RefreshToken();
            t.setTokenId(rs.getInt("token_id"));
            t.setUserId(rs.getInt("user_id"));
            t.setToken(rs.getString("token"));

            Timestamp ts = rs.getTimestamp("expires_at");
            if (ts != null) {
                t.setExpiresAt(ts.toLocalDateTime());
            }

            t.setRevoked(rs.getBoolean("is_revoked"));
            return t;
        }
    };

    @Override
    public boolean saveToken(RefreshToken token) {
        String sql = "INSERT INTO refresh_tokens1 (user_id, token, expires_at, is_revoked) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                token.getUserId(),
                token.getToken(),
                token.getExpiresAt(),
                token.isRevoked() ? 1 : 0
        ) > 0;
    }

    @Override
    public RefreshToken findByToken(String tokenValue) {
        String sql = "SELECT * FROM refresh_tokens1 WHERE token = ? AND is_revoked = 0";
        List<RefreshToken> list = jdbcTemplate.query(sql, rowMapper, tokenValue);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean revokeToken(String tokenValue) {
        String sql = "UPDATE refresh_tokens1 SET is_revoked = 1 WHERE token = ?";
        return jdbcTemplate.update(sql, tokenValue) > 0;
    }
}
