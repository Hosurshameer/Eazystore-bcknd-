package com.eazybytes.eazystore.repository;

import com.eazybytes.eazystore.entity.StickersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StickerRepository extends JpaRepository<StickersEntity,Long> {
    List<StickersEntity> findByUsername(String username);
}
