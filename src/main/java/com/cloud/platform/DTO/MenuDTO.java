package com.cloud.platform.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDTO {
    private int id;
    private String authName;
    private String path;
    private ArrayList<MenuDTO> children;

    private static MenuDTO generateMenuDTO(int id,String authName,String path,ArrayList<MenuDTO> children){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(id);
        menuDTO.setAuthName(authName);
        menuDTO.setPath(path);
        menuDTO.setChildren(children);
        return menuDTO;
    }

    public static ArrayList<MenuDTO> getAdminMenu(){
        ArrayList<MenuDTO> menuDTOS = new ArrayList<>();
        ArrayList<MenuDTO> children = new ArrayList<>();
        children.add(generateMenuDTO(101,"服务商信息","servers", new ArrayList<>()));
        children.add(generateMenuDTO(102,"服务商服务范围","range/serve", new ArrayList<>()));
        children.add(generateMenuDTO(103,"服务商区域范围","range/territory", new ArrayList<>()));
        children.add(generateMenuDTO(104,"服务商资质","intelligence", new ArrayList<>()));
        children.add(generateMenuDTO(105,"服务商人员","crew", new ArrayList<>()));
        children.add(generateMenuDTO(106,"服务商人员资质","crew/intelligence", new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(1,"服务商管理","servers",children));
        ArrayList<MenuDTO> children1 = new ArrayList<>();
        children1.add(generateMenuDTO(201,"维修工单管理","repairs",new ArrayList<>()));
        children1.add(generateMenuDTO(202,"巡检任务管理","detects",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(2,"工单管理","workOrders", children1));
        ArrayList<MenuDTO> children2 = new ArrayList<>();
        children2.add(generateMenuDTO(301,"设备故障代码","codes",new ArrayList<>()));
        children2.add(generateMenuDTO(302,"设备分类","classification",new ArrayList<>()));
        children2.add(generateMenuDTO(303,"资产设备设施","asset",new ArrayList<>()));
        children2.add(generateMenuDTO(305,"配件类型","accessoryType",new ArrayList<>()));
        children2.add(generateMenuDTO(306,"配件设施","accessoryDevice",new ArrayList<>()));
        children2.add(generateMenuDTO(307,"非资产类型","unAssetType",new ArrayList<>()));
        children2.add(generateMenuDTO(308,"非资产类型设备设施","unAssetDevice",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(3,"设备管理","devices", children2));
        ArrayList<MenuDTO> children3 = new ArrayList<>();
        children3.add(generateMenuDTO(401,"角色管理","roles",new ArrayList<>()));
        children3.add(generateMenuDTO(402,"用户管理","users",new ArrayList<>()));
        children3.add(generateMenuDTO(403,"日志管理","logs",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(4,"系统管理","management", children3));
        return menuDTOS;
    }
}
