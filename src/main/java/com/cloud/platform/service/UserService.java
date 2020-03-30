package com.cloud.platform.service;

import com.cloud.platform.DTO.*;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.UserMapper;
import com.cloud.platform.model.User;
import com.cloud.platform.model.UserExample;
import com.cloud.platform.utils.JwtUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public ResultDTO<UserDTO> validate(String username, String password) {
        ResultDTO<UserDTO> resultDTO = new ResultDTO<>();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            resultDTO.setMeta(MetaDTO.errorOf(ResponseType.FAIL.getValue(),"用户不存在"));
        }else{
            User dbUser = users.get(0);
            if(!dbUser.getPassword().equals(password)){
                resultDTO.setMeta(MetaDTO.errorOf(ResponseType.FAIL.getValue(),"密码错误"));
            }else{
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(users.get(0),userDTO);
                userDTO.setToken(JwtUtil.sign(userDTO.getUsername(),userDTO.getRid()));
                resultDTO.setData(userDTO);
                resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"登陆成功"));
            }
        }
        return resultDTO;
    }

    public ResultDTO<PaginationDTO> getUserPagination(Integer userRid, Integer pagenum, Integer pagesize, String query) {
        ResultDTO<PaginationDTO> resultDTO = new ResultDTO<>();
        PaginationDTO<List<UserListDTO>> paginationDTO = new PaginationDTO<>();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameLike("%"+query+"%");
        long totalNum = userMapper.countByExample(userExample);
        paginationDTO.setTotal((int) totalNum);
        paginationDTO.setPagenum(pagenum);
        List<User> users = userMapper.selectByExampleWithRowbounds(userExample, new RowBounds(pagesize * (pagenum - 1), pagesize));
        List<UserListDTO> userListDTOS = new ArrayList<>();
        users.stream().forEach(user -> {
            UserListDTO userListDTO = new UserListDTO();
            BeanUtils.copyProperties(user, userListDTO);
            userListDTOS.add(userListDTO);
        });
        paginationDTO.setData(userListDTOS);
        resultDTO.setData(paginationDTO);
        resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"获取管理员用户名单成功"));
        return resultDTO;
    }

    public ResultDTO<Object> updateUserStateById(Integer id, boolean newState) {
        ResultDTO<Object> resultDTO = new ResultDTO<>();
        User user = userMapper.selectByPrimaryKey(id);
        user.setMgState(newState);
        int i = userMapper.updateByPrimaryKey(user);
        if(i == 0)
            resultDTO.setMeta(MetaDTO.errorOf(ResponseType.FAIL.getValue(),"修改状态失败"));
        else
            resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue(),"修改状态成功"));
        return resultDTO;
    }

    public ResultDTO<Object> addUser(String username, String password, String email, String mobile) {
        ResultDTO<Object> resultDTO = new ResultDTO<>();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()!=0){
            resultDTO.setMeta(MetaDTO.errorOf(ResponseType.ERROR.getValue(),"用户名已存在"));
        }else{
            User user = new User();
            user.setMgState(true);
            user.setUsername(username);
            user.setEmail(email);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(null);
            user.setMobile(mobile);
            user.setRid(1);
            user.setRoleName("测试用户");
            user.setPassword(password);
            int insert = userMapper.insert(user);
            if(insert == 0){
                resultDTO.setMeta(MetaDTO.errorOf(ResponseType.ERROR.getValue(),"服务器异常，用户注册失败"));
            }else{
                resultDTO.setMeta(MetaDTO.okOf(ResponseType.SUCCESS.getValue()+1,"注册成功"));
            }
        }
        return resultDTO;
    }
}
