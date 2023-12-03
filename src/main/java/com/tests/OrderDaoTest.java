package com.tests;

import static org.mockito.Mockito.*;

import com.dao.OrderDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class OrderDaoTest {

    private OrderDao orderDao;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;

    @BeforeEach
    void setUp() throws SQLException {
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        orderDao = new OrderDao(mockedConnection);
    }

    @Test
    void setOrderOwnerTest() throws SQLException {
        int orderId = 123;
        int userId = 456;

        orderDao.setOrderOwner(orderId, userId);

        verify(mockedConnection, times(1)).prepareStatement("UPDATE orders SET user_id = ? WHERE order_id = ?");
        verify(mockedPreparedStatement, times(1)).setInt(1, userId);
        verify(mockedPreparedStatement, times(1)).setInt(2, orderId);
        verify(mockedPreparedStatement, times(1)).execute();
    }
}
