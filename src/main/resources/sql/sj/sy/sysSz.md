pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from SYS_SZ where IS_DEL='false'
                                        @if(!isEmpty(Xym)){
                                            and XYM like #'%'+Xym+'%'#
                                        @}
                                        @if(!isEmpty(Sysm)){
                                            and SYSM like #'%'+Sysm+'%'#
                                        @}
                                        @if(!isEmpty(Syszr)){
                                            and SYSZR like #'%'+Syszr+'%'#
                                        @}
 
                                       
selectAll
===
*获取所有的实验室
select SYSM from SYS_SZ where IS_DEL='false'
                                 
selectByXym
===
*通过学院名获取该学院下的所有实验室名
select SYSM from SYS_SZ where XYM=#Xym# and IS_DEL='false'

selectBySysm
===
*通过实验室名查询，添加或更改的数据是否已经存在
select * from SYS_SZ where SYSM=#Sysm# and IS_DEL='false'

selectById
===
*通过id进行查询，用于删除中，查找该实验室是否有实验室主任，有的话把用户表（SYS_USER）中的该用户也删除掉
select * from SYS_SZ where ID=#Id# and IS_DEL='false'

deleteById
===
*根据id进行软删除
update SYS_SZ set IS_DEL='true' where ID =#Id#

deleteSysUserBySyszr
===
*通过实验室主任名删除用户表（SYS_USER）中的用户
delete from SYS_USER where USER_NAME=#Syszr# and USER_ROLE='DIRECTOR'