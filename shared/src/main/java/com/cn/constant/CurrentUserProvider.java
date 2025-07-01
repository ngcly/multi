package com.cn.constant;

import java.util.Optional;

/**
 * @author LinChen
 */
public interface CurrentUserProvider {
    Optional<String> getCurrentUser();
}
