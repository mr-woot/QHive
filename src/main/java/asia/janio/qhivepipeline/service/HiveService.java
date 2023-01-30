package asia.janio.qhivepipeline.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Service
public class HiveService {
    final
    DataSource hiveDataSource;

    public HiveService(@Qualifier("hiveDataSource") DataSource hiveDataSource) {
        this.hiveDataSource = hiveDataSource;
    }

    @PostConstruct
    public void run() {
        try {
            Connection conn = hiveDataSource.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("show databases");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            // Print column names (a header).
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println("");
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(" | ");
                    System.out.print(rs.getString(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
