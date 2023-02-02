package asia.janio.qhivepipeline.hive.service;

import lombok.extern.log4j.Log4j2;
import org.apache.hive.jdbc.HiveStatement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class HiveServiceImpl implements HiveService {

    private final JdbcTemplate hiveJdbcTemplate;

    private final DataSource hiveDruidDataSource;

    public HiveServiceImpl(@Qualifier("hiveJdbcTemplate") JdbcTemplate hiveJdbcTemplate, @Qualifier("hiveDruidDataSource") DataSource hiveDruidDataSource) {
        this.hiveJdbcTemplate = hiveJdbcTemplate;
        this.hiveDruidDataSource = hiveDruidDataSource;
    }

    @Override
    public Object select(String hql) {
        Object object = null;
        try {
            HiveStatement stmt = (HiveStatement) hiveDruidDataSource.getConnection().createStatement();
            object = hiveJdbcTemplate.queryForObject(hql, Object.class);
            for(String log: stmt.getQueryLog()) {
                System.out.println("Log: " + log);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    @Override
    public List<String> listAllTables() {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
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
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
            String sql = "describe " + tableName;
            log.info("Running" + sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            return result;
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
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
            Statement statement = hiveDruidDataSource.getConnection().createStatement();
            String sql = "select * from " + tableName;
            log.info("Running" + sql);
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
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }
        return Collections.emptyList();
    }
}