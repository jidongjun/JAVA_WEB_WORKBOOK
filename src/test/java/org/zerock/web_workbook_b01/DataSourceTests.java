package org.zerock.web_workbook_b01;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException{
        @Cleanup
        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);
    }
}
