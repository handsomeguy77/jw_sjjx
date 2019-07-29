pageQuery
===
* 翻页查询
select       @pageTag() {
                        A.ID as ID,A.JSH as JSH,A.JSM as JSM,A.XB as XB,A.ZC as ZC,B.XYM as XYM
                     @}
                        from DM_JSB as A left join DM_XY as B on A.XYH=B.XYH 
                            where A.IS_DEL='false' and B.IS_DEL='false'
                                        @if(!isEmpty(Jsh)){
                                            and A.JSH like #'%'+Ksh+'%'#
                                        @}
                                        @if(!isEmpty(Jsm)){
                                            and A.JSM like #'%'+Jsm+'%'#
                                        @}
                                        @if(!isEmpty(Xb)){
                                            and A.XB like #'%'+Xb+'%'#
                                        @}
                                        @if(!isEmpty(Zc)){
                                            and A.ZC like #'%'+Zc+'%'#
                                        @}
                                        @if(!isEmpty(Xym)){
                                            and B.XYM like #'%'+Xym+'%'#
                                        @}
                       
selectByXym
===
*通过学院名查找该学院下的所有教师名
select A.JSM from DM_JSB as A left join DM_XY as B on A.XYH=B.XYH 
                where B.XYM=#Xym# and A.IS_DEL='false' and B.IS_DEL='false'
                                        
selectByJsh
===
*通过教师号查询，用于添加用户或者更新用户的时候校验该用户是否已经存在
select * from DM_JSB where JSH=#Jsh# and IS_DEL='false'

selectById
===
*通过id号查询
select * from DM_JSB where ID=#Id# and IS_DEL='false'

selectSysSzIdByJsm
===
*用于编辑用户中，若编辑的是教师名，则通过原教师名查询实验室表获取该教师对应的id
select ID from SYS_SZ where SYSZR=#Jsm# and IS_DEL='false'

selectSysRyIdByJsm
===
*用于编辑用户中，若编辑的是教师名，则通过原教师名查询实验室人员表获取该教师对应的id
select ID from SYS_RY where JSM=#Jsm# and IS_DEL='false'

selectSysUserIdByJsm
===
*用于编辑用户中，若编辑的是教师名，则通过原教师名查询用户表（SYS_USER）获取该教师对应的id
select ID from SYS_USER where USER_NAME=#Jsm# and USER_ROLE='DIRECTOR'

updateSysRyById
===
*用于编辑用户中，若编辑的是教师名，则通过id更新实验室人员表中对应的教师名
update SYS_RY set JSM=#Jsm# where ID=#Id# and IS_DEL='false'

updateSysUserById
===
*用于编辑用户中，若编辑的是教师名，则通过id更新用户表（SYS_USER）中对应的教师名
update SYS_USER set USER_NAME=#Jsm# where ID=#Id#

selectSysSzByJsm
===
*通过教师名查找实验室表，检查该教师是否是实验室主任
select * from SYS_SZ where SYSZR=#Jsm# and IS_DEL='false'

updateSysSzByJsm
===
*删除（或者编辑）的时候，如果该教师是实验主任的话，更新实验室表中的对应实验室的实验室主任
update SYS_SZ set SYSZR=#Jsm# where ID=#Id# and IS_DEL ='false'

deleteSysUserByJsm
===
*删除的时候，如果该教师是某个实验室的实验室主任，把该教师从用户表中删除
delete from SYS_USER where USER_NAME=#Jsm#

deleteSysRyByJsm
===
*删除的时候，若该教师也是实验室的成员，则一并删除该实验室成员
update SYS_RY set IS_DEL='true' where JSM=#Jsm#

updateSysRyByJsm
===
*编辑的时候，若更改的是教师名，则实验室人员表格中对应教师的教师名一并更改



deleteById
===
*根据id进行软删除
update DM_JSB set IS_DEL='true' where ID =#Id#

