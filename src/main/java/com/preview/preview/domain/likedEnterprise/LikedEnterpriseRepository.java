package com.preview.preview.domain.likedEnterprise;

import com.preview.preview.domain.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedEnterpriseRepository extends JpaRepository<Enterprise,String> {
}
