package com.imshakthi.javatestserver.repository;

import com.imshakthi.javatestserver.dto.ProductBomView;
import com.imshakthi.javatestserver.entity.ProductBom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductBomRepository extends JpaRepository<ProductBom, UUID> {

  @Query(
      """
        SELECT i.name AS name, pb.requiredItemUnits AS requiredItemUnits
        FROM ProductBom pb
        JOIN pb.product p
        JOIN pb.item i
        WHERE p.name = :productName
    """)
  List<ProductBomView> findByProductName(@Param("productName") String productName);
}
