package asia.janio.qhivepipeline.hive.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class HiveService {

    private final JdbcTemplate hiveTemplate;

    public HiveService(@Qualifier("hiveTemplate") JdbcTemplate hiveTemplate) {
        this.hiveTemplate = hiveTemplate;
    }

    private static final String SHOW_DATABASES_QUERY = "show databases";

    @PostConstruct
    public void run() {
        List<Map<String, Object>> databases = hiveTemplate.queryForList(SHOW_DATABASES_QUERY);
        for (Map<String, Object> res : databases) {
            log.info(res);
        }
    }
}
