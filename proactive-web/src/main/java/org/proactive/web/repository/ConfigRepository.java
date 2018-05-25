package org.proactive.web.repository;

import org.proactive.web.domain.ConfigParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<ConfigParam, Long>{

}
