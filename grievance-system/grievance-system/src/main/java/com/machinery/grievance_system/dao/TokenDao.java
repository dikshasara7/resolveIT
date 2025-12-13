package com.machinery.grievance_system.dao;

import com.machinery.grievance_system.model.RefreshToken;

public interface TokenDao {
    boolean saveToken(RefreshToken token);
    RefreshToken findByToken(String token);
    boolean revokeToken(String token);
}