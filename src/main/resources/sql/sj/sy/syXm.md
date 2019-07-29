pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from SY_XM where IS_DEL='false'
                                        @if(!isEmpty(Sysm)){
                                            and SYSM like #'%'+Sysm+'%'#
                                        @}
                                        @if(!isEmpty(Kch)){
                                            and KCH like #'%'+Kch+'%'#
                                        @}
                                        @if(!isEmpty(Syxmmc)){
                                            and SYXMMC like #'%'+Syxmmc+'%'#
                                        @}
                                        @if(!isEmpty(Syxmbh)){
                                            and SYXMBH like #'%'+Syxmbh+'%'#
                                        @}
                                        @if(!isEmpty(Syxs)){
                                            and SYXS like #'%'+Syxs+'%'#
                                        @}
                                        @if(!isEmpty(Syfsm)){
                                            and SYFSM like #'%'+Syfsm+'%'#
                                        @}
                                       
                                        

selectDmKcbByKch
===
*通过课程号查询课程表，用来判断导入Excel的时候校验是否有这一门课程
select * from DM_KCB where KCH=#Kch# and IS_DEL='false'

deleteById
===
*根据id进行软删除
update SY_XM set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update SY_XM set IS_DEL='true' where ID =#Id#