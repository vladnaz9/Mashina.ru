package dataSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleDataSourceConfig {
    private String url;
    private String driver;
    private String username;
    private String password;

    public SimpleDataSourceConfig() {
    }
}

