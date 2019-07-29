pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from DM_XY where IS_DEL='false'
                                        @if(!isEmpty(Xyh)){
                                            and XYH like #'%'+Xyh+'%'#
                                        @}
                                        @if(!isEmpty(Xym)){
                                            and XYM like #'%'+Xym+'%'#
                                        @}
                                        
                                        
selectAll
===
*用于查询出所有的学院名
select XYM from DM_XY where IS_DEL='false'
                                        
updateByID
===
*根据ID号更新，因为表中有多个主键，因此BaseMapper中自带的updateById方法不能用，要自己重新定义
update DM_XY set XYH=#Xyh#,XYM=#Xym# where ID=#Id# and IS_DEL='false'

selectByXyh
===
*通过学院号查询，用于添加用户或者更新用户的时候校验该学院是否已经存在
select XYM from DM_XY where XYH=#Xyh# and IS_DEL='false'   

selectByXym
===
*通过学院名查询，用于查找出对应的学院号
select XYH from DM_XY where XYM=#Xym# and IS_DEL='false'

deleteById
===
*根据id进行软删除
update DM_XY set IS_DEL='true' where ID =#Id#

