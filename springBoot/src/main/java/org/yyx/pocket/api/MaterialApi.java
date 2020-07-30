package org.yyx.pocket.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.Material;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.service.MaterialService;


@RestController
@RequestMapping("/material")
public class MaterialApi {

    @Autowired
    private MaterialService materialService;


    @PostMapping("/material")
    public ResponseTemplate insertMaterial(@RequestBody JSONObject req) {
        Material material = new Material();
        material.setOrigin(req.getString("origin"));
        material.setDestination(req.getString("destination"));
        material.setTransferStation(req.getString("transferStation"));
        material.setMaterialName(req.getString("materialName"));


        JSONObject data = new JSONObject();
        data.put("momentId", materialService.insertMaterial(material));

        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/material")
    public ResponseTemplate deleteMaterial(@RequestParam String materialId)
    {
        materialService.deleteMaterial(materialId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/material")
    public ResponseTemplate updateMaterial(@RequestBody JSONObject req)
    {
        Material material = new Material();
        material.setMaterialId(req.getString("materialId"));
        material.setOrigin(req.getString("origin"));
        material.setDestination(req.getString("destination"));
        material.setTransferStation(req.getString("transferStation"));
        material.setMaterialName(req.getString("materialName"));

        materialService.updateMaterial(material);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/material")
    public ResponseTemplate getMaterial(@RequestParam String materialId)
    {
        JSONObject data = new JSONObject();
        data.put("material", materialService.getMaterial(materialId));

        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @GetMapping("/materialList")
    public ResponseTemplate getMaterialList()
    {
        JSONObject data = new JSONObject();
        data.put("materialList", materialService.getMaterialList());

        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

}
