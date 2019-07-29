pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from SYFS_XX where IS_DEL='false'
                                        @if(!isEmpty(Xym)){
                                            and XYM like #'%'+Xym+'%'#
                                        @}
                                        @if(!isEmpty(Sysm)){
                                            and SYSM like #'%'+Sysm+'%'#
                                        @}
                                        @if(!isEmpty(Syfsm)){
                                            and SYFSM like #'%'+Syfsm+'%'#
                                        @}
                                        @if(!isEmpty(Fjh)){
                                            and FJH like #'%'+Fjh+'%'#
                                        @}
                                        @if(!isEmpty(Fzr)){
                                            and FZR like #'%'+Fzr+'%'#
                                        @}

selectAll
===
*获取所有的实验室分室
select SYFSM from SYFS_XX where IS_DEL='false'

selectBySysm
===
*通过实验室名，查找出该实验室管理的实验分室
select SYFSM from SYFS_XX where SYSM=#Sysm# and IS_DEL='false'

selectByFzr
===
*通过负责人，查找出该负责人管理的实验分室名
select * from SYFS_XX where FZR=#Fzr#

updateSysRyByGlfsmc
===
*通过负责人，查找出该实验室成员的信息，并设置其管理分室名称
update SYS_RY set GLFSMC=#Glfsmc# where JSM=#Fzr#

                                      
selectBySysmAndSyfsm
===
*通过实验室名和实验分室名查询，添加或更改的数据是否已经存在
select * from SYFS_XX where SYSM=#Sysm# and SYFSM=#Syfsm# and IS_DEL='false'

deleteById
===
*根据id进行软删除
update SYFS_XX set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update SYFS_XX set IS_DEL='true' where ID =#Id#

updateByFzr
===
*通过负责人进行更新，用户为该实验分室添加相对应的负责人
update SYFS_XX set FZR=#Fzr# where ID=#Id#
