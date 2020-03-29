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
        children.add(generateMenuDTO(126,"用户列表","users",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(125,"用户管理","users", children));
        ArrayList<MenuDTO> children1 = new ArrayList<>();
        children1.add(generateMenuDTO(104,"角色列表","roles",new ArrayList<>()));
        children1.add(generateMenuDTO(105,"权限列表","rights",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(103,"权限管理","rights", children1));
        ArrayList<MenuDTO> children2 = new ArrayList<>();
        children2.add(generateMenuDTO(106,"商品列表","goods",new ArrayList<>()));
        children2.add(generateMenuDTO(107,"分类参数","params",new ArrayList<>()));
        children2.add(generateMenuDTO(108,"商品分类","classifications",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(101,"商品管理","goods", children2));
        ArrayList<MenuDTO> children3 = new ArrayList<>();
        children3.add(generateMenuDTO(109,"订单展示","orders",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(102,"订单管理","orders", children3));
        ArrayList<MenuDTO> children4 = new ArrayList<>();
        children4.add(generateMenuDTO(110,"数据统计","reports",new ArrayList<>()));
        menuDTOS.add(generateMenuDTO(145,"数据统计","reports", children4));
        return menuDTOS;
    }
}
