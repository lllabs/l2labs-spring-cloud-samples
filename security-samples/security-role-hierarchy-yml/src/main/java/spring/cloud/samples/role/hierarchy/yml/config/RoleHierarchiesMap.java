package spring.cloud.samples.role.hierarchy.yml.config;


import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.security.role")
public class RoleHierarchiesMap {

    @Setter
    Map<String, List<String>> hierarchies = new HashMap<>();

    @Override
    public String toString() {
        return RoleHierarchyUtils.roleHierarchyFromMap(hierarchies);
    }

}
