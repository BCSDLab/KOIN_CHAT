package in.koreatech.koin.common.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerGroupConfig {

    @Bean
    public GroupedOpenApi lostItemApi() {
        String[] packagesPath = new String[] {
            "in.koreatech.koin.domain.lostitem"
        };

        return createGroupedOpenApi("1. lostItem API", packagesPath);
    }

    private GroupedOpenApi createGroupedOpenApi(String groupName, String[] packagesPath) {
        return GroupedOpenApi.builder()
            .group(groupName)
            .packagesToScan(packagesPath)
            .build();
    }
}
