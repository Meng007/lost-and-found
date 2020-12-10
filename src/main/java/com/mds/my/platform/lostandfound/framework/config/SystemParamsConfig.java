package com.mds.my.platform.lostandfound.framework.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 13557
 */
@Component
@ConfigurationProperties(prefix = "mds")
public class SystemParamsConfig {
    private String systemName;
    private static String filePath;
    private String version;

    public static String getUploadPath() {
        return getFilePath() + "/upload";
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public static String getFilePath() {
        return SystemParamsConfig.filePath;
    }

    public void setFilePath(String filePath) {
        SystemParamsConfig.filePath = filePath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
