pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from DM_KCB where IS_DEL='false'
                                        @if(!isEmpty(Kch)){
                                            and KCH like #'%'+Kch+'%'#
                                        @}
                                        @if(!isEmpty(Kcm)){
                                            and KCM like #'%'+Kcm+'%'#
                                        @}
                                        @if(!isEmpty(Kkxq)){
                                            and KKXQ like #'%'+Kkxq+'%'#
                                        @}
                                        @if(!isEmpty(Xs)){
                                            and XS like #'%'+Xs+'%'#
                                        @}
                                        @if(!isEmpty(Xf)){
                                            and XF like #'%'+Xf+'%'#
                                        @}
                                        @if(!isEmpty(Kslx)){
                                            and KSLX like #'%'+Kslx+'%'#
                                        @}
                                        
                                        
selectAll
===
*查询所有课程名
select KCM from DM_KCB where IS_DEL='false'
                                                      
selectByKch
===
*通过课程号查询，用于添加用户或者更细用户的时候校验该用户是否已经存在
select * from DM_KCB where KCH=#Kch# and IS_DEL='false'                       
                                        
deleteById
===
*根据id进行软删除
update DM_KCB set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update DM_KCB set IS_DEL='true' where ID =#Id#