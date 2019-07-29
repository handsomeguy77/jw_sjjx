pageQuery
===
* 翻页查询
select       @pageTag() {
                         A.ID as ID,A.NJ as NJ,A.BM as BM,B.XYM as XYM
                     @}
                        from DM_BJB as A left join DM_XY as B on A.XYH=B.XYH 
                            where A.IS_DEL='false' and B.IS_DEL='false'
                                        @if(!isEmpty(Nj)){
                                            and A.NJ like #'%'+Nj+'%'#
                                        @}
                                        @if(!isEmpty(Bm)){
                                            and A.BM like #'%'+Bm+'%'#
                                        @}
                                        @if(!isEmpty(Xym)){
                                            and B.XYM like #'%'+Xym+'%'#
                                        @}
                                       
updateByID
===
*根据ID号更新，因为表中有多个主键，因此BaseMapper中自带的updateById方法不能用，要自己重新定义
update DM_BJB set NJ=#Nj#,BM=#Bm#,XYH=#Xyh# where ID=#Id# and IS_DEL='false'

selectByNjAndBm
===
*通过年级和班名查询，添加或更改的数据是否已经存在
select * from DM_BJB where NJ=#Nj# and BM=#Bm# and IS_DEL='false'

deleteById
===
*根据id进行软删除
update DM_BJB set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update DM_BJB set IS_DEL='true' where ID =#Id#