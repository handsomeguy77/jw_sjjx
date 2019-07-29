pageQuery
===
* 翻页查询(校级管理员)
select       @pageTag() {
                          *
                     @}
                        from SYS_RY where IS_DEL='false'
                                        @if(!isEmpty(Xym)){
                                            and XYM like #'%'+Xym+'%'#
                                        @}
                                        @if(!isEmpty(Sysm)){
                                            and SYSM like #'%'+Sysm+'%'#
                                        @}
                                        @if(!isEmpty(Jsm)){
                                            and JSM like #'%'+Jsm+'%'#
                                        @}
                                        @if(!isEmpty(Zw)){
                                            and ZW like #'%'+Zw+'%'#
                                        @}
                                        @if(!isEmpty(Glfsmc)){
                                            and GLFSMC like #'%'+Glfsmc+'%'#
                                        @}
                                        
                                        
selectDmJsbByJsm
===
*通过教师名查询，查询该教师对应的教师号是多少
select * from DM_JSB where JSM=#Jsm# and IS_DEL='false'

insertIntoSysUser
===
*往用户表(SYS_USER)中添加实验室主任
insert into SYS_USER (USERNAME,JSH,XM,XYM,SYSM,PASSWORD,ROLE) value(#Username#,#Jsh#,#Xm#,#Xym#,#Sysm#,md5(123456),"MEMBER")

selectByJsm
===
*通过教师名查询，添加或者更改的时候，验证该该教师是否已经存在
select * from SYS_RY where JSM=#Jsm# and IS_DEL='false'

selectSysSzBySysm
===
*通过实验室名查询实验室设置表，添加或者更改的时候，验证该实验室是否已经存在实验室主任了
select * from SYS_SZ where SYSM=#Sysm# and IS_DEL='false'

updateSysSzByJsm
===
*通过教师名来对实验室设置表格中，对应的实验室加上实验室主任
update SYS_SZ set SYSZR=#Jsm# where SYSM=#Sysm# and IS_DEL='false'

deleteSysUserByJsm
===
*通过教师名删除用户表（SYS_USER）中的那条数据
delete from SYS_USER where XM=#Jsm#

deleteById
===
*根据id进行软删除
update SYS_RY set IS_DEL='true' where ID =#Id#

selectById
===
*通过id查询实验室人员表，该人员是否有实验室主任的职务
select * from SYS_RY where ID=#Id# and IS_DEL='false'

updateSysSzBySysmAndXym
===
*删除实验室人员的时候，检查该实验室人员是否也是实验室主任，让该实验室的实验室主任置位null
update SYS_SZ set SYSZR=null where XYM=#Xym# and SYSM=#Sysm# and IS_DEL='false'

selectSysUserByUsername
===
*通过登陆用户的USER_ID查询用户表（SYS_USER），获取该用户用户的教师名
select XM from SYS_USER where USERNAME=#Username#

selectSyfsXxBySysm
===
*通过实验室名，从实验分室表中获取该实验室有哪些实验分室
select SYFSM from SYFS_XX where SYSM=#Sysm# and IS_DEL='false'

