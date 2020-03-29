package com.cloud.platform.service;

import com.cloud.platform.DTO.MetaDTO;
import com.cloud.platform.DTO.ResultDTO;
import com.cloud.platform.DTO.UserDTO;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.mapper.UserMapper;
import com.cloud.platform.model.User;
import com.cloud.platform.model.UserExample;
import com.cloud.platform.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            MetaDTO meta = new MetaDTO();
            meta.setCode(ResponseType.FAIL.getValue());
            meta.setMessage("该用户不存在");
            resultDTO.setMeta(meta);
        }else{
            User dbUser = users.get(0);
            if(!dbUser.getPassword().equals(password)){
                MetaDTO meta = new MetaDTO();
                meta.setCode(ResponseType.FAIL.getValue());
                meta.setMessage("密码错误");
                resultDTO.setMeta(meta);
            }else{
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(users.get(0),userDTO);
                userDTO.setToken(JwtUtil.sign(userDTO.getUsername(),userDTO.getRid()));
                resultDTO.setData(userDTO);
                MetaDTO meta = new MetaDTO();
                meta.setCode(ResponseType.SUCCESS.getValue());
                meta.setMessage("登陆成功");
                resultDTO.setMeta(meta);
            }
        }
        return resultDTO;
    }
}
