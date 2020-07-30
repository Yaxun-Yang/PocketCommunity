package org.yyx.pocket.persistence;


import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Material;

import java.util.List;

@Repository
public interface MaterialMapper {

    void insertMaterial(Material material);
    void deleteMaterial(String materialId);
    void updateMaterial(Material materialId);
    String getRecentMaterialId();
    Material getMaterial(String materialId);
    List<Material> getMaterialList();
}
