package com.allan.controller;

import com.allan.model.vo.UserVo;
import com.allan.service.UserService;
import com.allan.utils.JWTUtils;
import com.allan.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.allan.constant.Constants;
import com.allan.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgy
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @description 登录
     * @param user 用户
     * @return {@link R }
     * @author LGY
     * @date 2023/03/26 15:29
     */
    @PostMapping("/login")
    public R login(@RequestBody User user){
        User userDB = userService.login(user);
        if(userDB==null){
            return R.error(Constants.CODE_500,"没有该用户");
        }

        UserVo userVo = new UserVo();
        userVo.setUsername(userDB.getUsername());
        String token = JWTUtils.getToken(userDB.getId().toString(), userDB.getPassword());
        userVo.setToken(token);
        return R.ok("登录成功").setData(userVo);

    }

    /**
     * @description 注册
     * @param user 用户
     * @return {@link R }
     * @author LGY
     * @date 2023/03/26 15:29
     */
    @PostMapping("/register")
    public R regiseter(@RequestBody User user){
        userService.register(user);
        return R.ok("注册成功").setData(user);
    }

    /**
     * @description 检查用户名
     * @param username 用户名
     * @return {@link R }
     * @author LGY
     * @date 2023/03/26 16.26
     */
    @GetMapping("/check")
    public R countByUsername(@RequestParam String username) {
        long count = userService.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if(count!=0){
            return R.error("用户名已存在");
        }
        return R.ok();
    }

    /**
     * @description 身份验证
     *
     * @return {@link R }
     * @author LGY
     * @date 2023/03/26 22:09
     */
    @PostMapping("/authentication")
    public R authentication(){
        return R.ok();
    }

    @GetMapping("/getUserInfo")
    public R getUserInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("nickname","无所谓^_^");
        map.put("qq","2673152463");
        map.put("address","浙江省");
        map.put("url","https://blog.csdn.net/weixin_51603038?type=blog");
        map.put("text","在笑大学牲");
        return R.ok().setData(map);
    }
}
