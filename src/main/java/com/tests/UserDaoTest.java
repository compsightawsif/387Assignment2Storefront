package com.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dao.UserDao;
import com.exceptions.PasscodeChangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UserDaoTest {

    private UserDao userDao;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        userDao = new UserDao(mockedConnection);
    }

    @Test
    void setPasscode_Success() throws SQLException, PasscodeChangeException {
        int userId = 123;
        int userPasscode = 456;

        boolean result = userDao.setPasscode(userId, userPasscode);

        // Verify interactions with mocks
        verify(mockedConnection, times(1)).prepareStatement("UPDATE user SET user_passcode = ? where user_id = ?");
        verify(mockedPreparedStatement, times(1)).setInt(1, userPasscode);
        verify(mockedPreparedStatement, times(1)).setInt(2, userId);
        verify(mockedPreparedStatement, times(1)).execute();

        // Assert the result
        assertTrue(result);
    }

    @Test
    void setPasscode_Failure() throws SQLException, PasscodeChangeException {
        int userId = 123;
        int userPasscode = 456;

        // Simulate a failure by throwing an SQLException
        when(mockedPreparedStatement.execute()).thenThrow(new SQLException("Simulated SQL Exception"));

        boolean result = userDao.setPasscode(userId, userPasscode);

        // Verify interactions with mocks
        verify(mockedConnection, times(1)).prepareStatement("UPDATE user SET user_passcode = ? where user_id = ?");
        verify(mockedPreparedStatement, times(1)).setInt(1, userPasscode);
        verify(mockedPreparedStatement, times(1)).setInt(2, userId);
        verify(mockedPreparedStatement, times(1)).execute();

        // Assert the result
        assertFalse(result);
    }
}
