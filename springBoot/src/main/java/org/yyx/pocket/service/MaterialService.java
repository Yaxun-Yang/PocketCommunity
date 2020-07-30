package org.yyx.pocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyx.pocket.domain.Material;
import org.yyx.pocket.persistence.MaterialMapper;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Transactional
    public String insertMaterial(Material material)
    {
        materialMapper.insertMaterial(material);
        return  materialMapper.getRecentMaterialId();
    }

    public void deleteMaterial(String materialId)
    {
        materialMapper.deleteMaterial(materialId);
    }

    public void updateMaterial(Material material)
    {
        materialMapper.updateMaterial(material);
    }

    public Material getMaterial(String materialId)
    {
        return materialMapper.getMaterial(materialId);
    }

    public List<Material> getMaterialList()
    {
        return materialMapper.getMaterialList();
    }
}
