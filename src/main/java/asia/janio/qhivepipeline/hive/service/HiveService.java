package asia.janio.qhivepipeline.hive.service;

import java.util.List;

public interface HiveService {
    Object select(String hql);
    List<String> listAllTables();
    List<String> describeTable(String tableName);
    List<String> selectFromTable(String tableName);
}