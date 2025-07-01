package com.cn.config;

import com.cn.constant.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Optional;

/**
 * @author chenning
 */
@Configuration
@EnableJdbcAuditing
@RequiredArgsConstructor
public class DataAuditingConfig implements AuditorAware<String> {
    private final CurrentUserProvider currentUserProvider;

    @Override
    public Optional<String> getCurrentAuditor() {
        return currentUserProvider.getCurrentUser();
    }
}
