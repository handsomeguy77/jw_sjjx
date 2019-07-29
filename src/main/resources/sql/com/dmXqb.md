pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from DM_XQB where IS_DEL='false'
                                        @if(!isEmpty(Xqmc)){
                                            and XQMC like #'%'+Xqmc+'%'#
                                        @}
                                        
                                        
selectByXqmc
===
*通过学期名称查询，用于添加用户或者更细用户的时候校验该学期是否已经存在
select * from DM_XQB where XQMC=#Xqmc# and IS_DEL='false'   

deleteById
===
*根据id进行软删除
update DM_XQB set IS_DEL='true' where ID =#Id#

                                    