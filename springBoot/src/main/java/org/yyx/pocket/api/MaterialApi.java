package org.yyx.pocket.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.Material;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.service.MaterialService;

//物资模块相关api
@RestController
@RequestMapping("/material")
public class MaterialApi {

    @Autowired
    private MaterialService materialService;


    //新建一个物资
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


    //删除一个物资
    @DeleteMapping("/material")
    public ResponseTemplate deleteMaterial(@RequestParam String materialId)
    {
        materialService.deleteMaterial(materialId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    //修改一个物资相关信息
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

    //通过id获取物资相关信息
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

    //获取物资列表
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
