//package com.bm.jw.service;
//
//import com.bm.jw.dao.SysUserDao;
//import com.bm.jw.entity.SysUser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private SysUserDao sysUserDao;
//    @Autowired
//    ObjectMapper objectMapper;
//    /**function:引用接口UserDetailService,重写方法loadUserByUsername
//     *    通过UserBuilder构造一个授权的用户，该用户至少包含用户名，密码以及角色
//     *  param: return:*/
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = sysUserDao.SelectByUserName(username);
//        try{
//            System.out.println(objectMapper.writeValueAsString(sysUser));
//        }catch (JsonProcessingException e){
//
//        }
//
//        if (sysUser == null){
//            throw new UsernameNotFoundException("user not found");
//        }else {
//            User.UserBuilder userBuilder = User.withUsername(username);
//            userBuilder.password(sysUser.getUserPass());      //加密时启用   数据库存密文
//////            userBuilder.password(new BCryptPasswordEncoder().encode(getUser.getPassword()));
////            //md5加密处理
////              userBuilder.password(DigestUtils.md5DigestAsHex(getUser.getPassword().getBytes()));
//            userBuilder.roles(sysUser.getUserRole());      //一个用户可能存在多种角色
//            return userBuilder.build();
//        }
//    }
//}