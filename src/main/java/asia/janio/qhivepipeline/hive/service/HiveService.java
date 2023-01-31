package asia.janio.qhivepipeline.hive.service;

import asia.janio.qhivepipeline.hive.config.HiveConnection;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.*;

@Service
@Log4j2
public class HiveService {
    @PostConstruct
    public static void run() {
        System.out.println("Hive Application init");
        Connection conn = HiveConnection.getInstance().getConnection();
        try {
            Statement statement = conn.createStatement();
//            String query = "MERGE INTO analytics.order_details as od USING ( SELECT pps.agent_application_id_id, pps.xero_organisation_name, pps.weight_policy, pps.volumetric_weight_factor FROM ( SELECT agent_application_id_id, Max(event_timestamp) AS max_time FROM janiobackend.janio_payment_paymentsettings_delta WHERE j_date = '2023-01-29' GROUP BY agent_application_id_id ) AS temp LEFT JOIN ( SELECT agent_application_id_id, xero_organisation_name, weight_policy, volumetric_weight_factor, event_timestamp, op FROM janiobackend.janio_payment_paymentsettings_delta WHERE j_date = '2023-01-29' ) AS pps ON pps.agent_application_id_id = temp.agent_application_id_id AND pps.event_timestamp = temp.max_time WHERE op != 'D' ) ps ON od.agent_application_id = ps.agent_application_id_id WHEN MATCHED THEN UPDATE SET xero_organisation_name = ps.xero_organisation_name, weight_policy = ps.weight_policy, volumetric_weight_factor = ps.volumetric_weight_factor, event_timestamp = '2023-01-29 10:24:08.412866'";
            String query = "show databases";
            ResultSet rs = getResultSet(statement, query);
            printResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Connection Close ERROR: ", e);
            }
        }
    }

    private static ResultSet getResultSet(Statement statement, String query) throws SQLException {
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
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
    }
}