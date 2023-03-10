package asia.janio.qhivepipeline.hive.service;

import asia.janio.qhivepipeline.hive.config.HiveConnection;
import lombok.extern.log4j.Log4j2;
import org.apache.hive.jdbc.HiveStatement;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class HiveServiceImpl implements HiveService {

    @Override
    public ResultSet select(String hql) {
        ResultSet rs;
        try {
            HiveStatement st = (HiveStatement) HiveConnection.getInstance().getConnection().createStatement();
            log.info("Running: " + hql);
            rs = st.executeQuery(hql);
            for (String line: st.getQueryLog()) {
                log.info(line);
            }
            log.info(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public List<String> listAllTables() {
        List<String> result = new ArrayList<>();
        try {
            HiveStatement statement = (HiveStatement) HiveConnection.getInstance().getConnection().createStatement();
            String sql = "show tables";
            log.info("Running: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException throwable) {
            log.error(throwable.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> describeTable(String tableName) {
        if (StringUtils.isEmpty(tableName)){
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        try {
            HiveStatement statement = (HiveStatement) HiveConnection.getInstance().getConnection().createStatement();
            String sql = "describe " + tableName;
            log.info("Running: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException throwable) {
            log.error(throwable.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> selectFromTable(String tableName) {
        if (StringUtils.isEmpty(tableName)){
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        try {
            HiveStatement statement = (HiveStatement) HiveConnection.getInstance().getConnection().createStatement();
            String sql = "select * from " + tableName;
            log.info("Running: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            String str = null;
            while (resultSet.next()) {
                str = "";
                for (int i = 1; i < columnCount; i++) {
                    str += resultSet.getString(i) + " ";
                }
                str += resultSet.getString(columnCount);
                log.info(str);
                result.add(str);
            }
            return result;
        } catch (SQLException throwable) {
            log.error(throwable.getMessage());
        }
        return Collections.emptyList();
    }
}