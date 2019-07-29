pageQuery
===
* 翻页查询
select       @pageTag() {
                          *
                     @}
                        from SYS_SYKB where IS_DEL='false'
                                        @if(!isEmpty(Xym)){
                                            and XYM like #'%'+Xym+'%'#
                                        @}
                                        @if(!isEmpty(Sysm)){
                                            and SYSM like #'%'+Sysm+'%'#
                                        @}
                                        @if(!isEmpty(Zc)){
                                            and ZC like #'%'+Zc+'%'#
                                        @}
                                        @if(!isEmpty(Xq)){
                                            and XQ like #'%'+Xq+'%'#
                                        @}
                                        @if(!isEmpty(Kch)){
                                            and KCH like #'%'+Kch+'%'#
                                        @}
                                        @if(!isEmpty(Kcm)){
                                            and KCM like #'%'+Kcm+'%'#
                                        @}
                                        @if(!isEmpty(Syxm)){
                                            and SYXM like #'%'+Syxm+'%'#
                                        @}
                                        @if(!isEmpty(Sylb)){
                                            and SYLB like #'%'+Sylb+'%'#
                                        @}
                                        @if(!isEmpty(Fjh)){
                                            and FJH like #'%'+Fjh+'%'#
                                        @}
                                        @if(!isEmpty(Zybj)){
                                            and ZYBJ like #'%'+Zybj+'%'#
                                        @}
                                        @if(!isEmpty(Rs)){
                                            and RS like #'%'+Rs+'%'#
                                        @}
                                        @if(!isEmpty(Syxs)){
                                            and SYXS like #'%'+Syxs+'%'#
                                        @}
                                        @if(!isEmpty(Zdjs)){
                                            and ZDJS like #'%'+Zdjs+'%'#
                                        @}
                                        @if(!isEmpty(Jsh)){
                                            and JSH like #'%'+Jsh+'%'#
                                        @}
                                        @if(!isEmpty(Kcxq)){
                                            and KCXQ like #'%'+Kcxq+'%'#
                                        @}
                                        @if(!isEmpty(Jc)){
                                            and JC like #'%'+Jc+'%'#
                                        @}
                                        
deleteById
===
*根据id进行软删除
update SYS_SYKB set IS_DEL='true' where ID =#Id#

deleteBatchById
===
*根据id进行批量删除
update SYS_SYKB set IS_DEL='true' where ID =#Id#

selectDmKcbByKcm
===
*通过课程名查询课程表，获取该课程对应的课程号
select  KCH from DM_KCB where KCM=#Kcm#

selectDmJsbByZdjs
===
*通过指导教师名查询教师表，获取该教师对应的教师号
select  JSH from DM_JSB where JSM=#Zdjs#



selectKcm
===
*用于调停课中，查找对课表中的所有课程
select       @pageTag() {
                         DISTINCT KCM, XYM, ZDJS, JSH
                     @}
                        from SYS_SYKB where IS_DEL='false'
                                @if(!isEmpty(Xym)){
                                    and SYSM like #'%'+Xym+'%'#
                                @}
                                @if(!isEmpty(Zdjs)){
                                    and ZDJS like #'%'+Zdjs+'%'#
                                @}
                                @if(!isEmpty(Jsh)){
                                    and JSH like #'%'+Jsh+'%'#
                                @}
                                @if(!isEmpty(Kcm)){
                                    and SYFSM like #'%'+Kcm+'%'#
                                @}
                                
selectByKcm
===
*调停课中，通过课程名查询该课程的所有周次
select       @pageTag() {
                          ZC,KCM,ZDJS,JSH,KSSJ,JSSJ,KSJC,JSJC,FJH
                     @}
                        from SYS_SYKB where IS_DEL='false'
                                @if(!isEmpty(Kcm)){
                                    and KCM like #'%'+Kcm+'%'#
                                @}