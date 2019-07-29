pageQuery
===
* 翻页查询
select       @pageTag() {
                         A.ID as ID,A.XH as XH,A.XM as XM,A.XB as XB,B.XYM as XYM,A.BM as BM,A.SSNJ as SSNJ
                     @}
                        from XJ_XJB as A left join DM_XY as B on A.XYH=B.XYH
                         where A.IS_DEL='false' and B.IS_DEL='false'
                                        @if(!isEmpty(Xh)){
                                            and A.XH like #'%'+Xh+'%'#
                                        @}
                                        @if(!isEmpty(Xm)){
                                            and A.XM like #'%'+Xm+'%'#
                                        @}
                                        @if(!isEmpty(Xb)){
                                            and A.XB like #'%'+Xb+'%'#
                                        @}
                                        @if(!isEmpty(Xym)){
                                            and B.XYM like #'%'+Xym+'%'#
                                        @}
                                        @if(!isEmpty(Bm)){
                                            and A.BM like #'%'+Bm+'%'#
                                        @}
                                        @if(!isEmpty(Ssnj)){
                                            and A.SSNJ like #'%'+Ssnj+'%'#
                                        @}
                                        
selectByXh
===
*通过学号查询，用于添加用户或者更细用户的时候校验该学生是否已经存在
select * from XJ_XJB where XH=#Xh# and IS_DEL='false'   

deleteById
===
*根据id进行软删除
update XJ_XJB set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update XJ_XJB set IS_DEL='true' where ID =#Id#