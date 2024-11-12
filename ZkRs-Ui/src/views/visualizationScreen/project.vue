<template>
    <!--项目管理-->
  <div class="project" >
    <div class="table">
      <div class="table-container">
        <div class="query_conditions">
          <div class="query_form">
            <el-row>
              <el-form ref="params" :model="params" label-width="82px">
                <el-row>
                  <el-col :span="6">
                    <el-form-item label="项目名称">
                      <el-input style="width: 100%" v-model="params.xmqkXmmc" clearable placeholder="请输入项目名"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="合作方">
                      <el-input v-model="params.xmqkHzf" clearable placeholder="请输入合作方"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="收购方">
                      <el-input v-model="params.xmqkSgf" clearable placeholder="请输入收购方"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="融资方">
                      <el-input v-model="params.xmqkRzf" clearable placeholder="请输入融资方"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <div style="height: 2px"></div>
                <el-row>
                  <el-col :span="6">
                    <el-form-item label="区域">
                      <el-input v-model="params.xmqkQy" clearable placeholder="请输入选择区域"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="动工年">
                      <el-date-picker
                        style="width: 100%;"
                        v-model="params.xmqkHtsjYear"
                        type="year"
                        value-format="yyyy"
                        :picker-options="pickerOptions"
                        placeholder="选择年">
                      </el-date-picker>
<!--                      <el-date-picker-->
<!--                        v-model="params.xmqkHtsjYear"-->
<!--                        type="date"-->
<!--                        placeholder="选择日期"-->
<!--                        :picker-options="pickerOptions"-->
<!--                        popper-class="picker-popper"-->
<!--                      >-->
<!--                      </el-date-picker>-->
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="动工月">
                      <el-select style="width: 100%" v-model="params.xmqkHtsjMonth" clearable placeholder="选择月">
                        <el-option
                          v-for="item in monthOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="新老项目">
                      <el-select
                        style="width: 100%"
                        v-model="params.xmFag"
                        clearable
                        placeholder="请选择"
                      >
                        <el-option
                          v-for="item in xmFagOptions"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id"
                        >
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="4">
                    <el-form-item label="项目分类">
                      <el-select style="width: 100%" v-model="params.xmqkXmlx" clearable>
                        <el-option
                          v-for="item in xmflOptions"
                          :key="item.id"
                          :label="item.zdName"
                          :value="item.id">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="项目现状">
                      <el-select style="width: 100%" v-model="params.xmqkXmxz" clearable>
                        <el-option
                          v-for="item in xmxzOptions"
                          :key="item.id"
                          :label="item.zdName"
                          :value="item.id">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="建设现状">
                      <el-select style="width: 100%" v-model="params.xmqkJszt" clearable>
                        <el-option
                          v-for="item in jsqkOptions"
                          :key="item.id"
                          :label="item.zdName"
                          :value="item.id">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="履约分公司">
                      <el-select style="width: 100%" v-model="params.xmqkLyfgs" clearable >
                        <el-option
                          v-for="item in lvfgsOptions"
                          :key="item.id"
                          :label="item.zdName"
                          :value="item.id">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item label="经理">
                      <el-input style="width: 100%" v-model="params.manage" clearable placeholder="请输入经理名"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="3">
                    <el-form-item label-width="0px">
                     <div style="display: flex; justify-content: space-around">
                       <el-button :class="isActive === 'query' ? 'active' : 'focus'" @click="conditionsQuery('query')">搜索</el-button>
                       <el-button :class="isActive === 'reset' ? 'active' : 'focus'" @click="reset('reset', )">重置</el-button>
                     </div>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </el-row>
          </div>
        </div>

        <div  style="margin: 5px">
          <el-table
            header-align="ceneter"
            :data="tableData"
            style="width: 100%"
            :row-class-name="tableClassName"
            @cell-dblclick="rowDetails"
            v-loading="loading"
            element-loading-background="rgba(4,15,54, 1)">
            <el-table-column
              prop="xmqkXmmc"
              label="项目名称"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkRl"
              label="容量（MW）"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkXmlx"
              label="项目分类"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkXmxz"
              label="项目现状"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkJszt"
              label="建设状态"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkLyfgs"
              label="履约分公司"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkHtsj"
              label="动工时间"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkHzf"
              label="合作方"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkSgf"
              label="收购方"
              align="center">
            </el-table-column>
            <el-table-column
              prop="xmqkRzf"
              label="融资方"
              align="center">
            </el-table-column>
          </el-table>
        </div>

        <div class="paging">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-size="params.pageSize"
            layout="total, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import {getSubsidiaryData, xmglQuery} from "@/api/screen/zl";
  import { parseTime } from '@/utils/ruoyi'
    export default {
      name: "project",
      props:['visualizationHeight'],
      data(){
        const that = this
        return {
          badgeDate: ['2023-06-01', '2023-05-11', '2023-05-15'],
          pickerOptions: {
            cellClassName: (time) => {
              console.log(that.badgeDate.includes(parseTime(time, '{y}-{m}-{d}')), 666,parseTime(time, '{y}'))
              if (that.badgeDate.includes(parseTime(time, '{y}-{m}-{d}'))) {
                return 'badge'
              }
            }
          },



          form:{

          },

          tableData:[],

          isActive:false,

          xmflOptions:[],
          xmxzOptions:[],
          jsqkOptions:[],
          lvfgsOptions:[],

          xmFagOptions:[{name:'老项目',id:'0'},{name:'新项目',id:'1'}],

          params:{
            //aggregation:10,
            isFull:1,
            pageNum:1,
            pageSize:5,
            xmqkHzf:'',
            xmqkJszt:'',
            xmqkLyfgs:'',
            xmqkRl:'',
            xmqkRzf:'',
            xmqkSgf:'',
            xmqkXmlx:'',
            xmqkXmmc:'',
            xmqkXmxz:'',
            aggregation:'',
            //新增
            xmqkHtsjYear: '',
            xmqkHtsjMonth:'',
            xmqkQy:'',
            manage:'',

          },
          monthOptions:[
            {
              value:'1',
              label:'1月'
            },
            {
              value:'2',
              label:'2月'
            },
            {
              value:'3',
              label:'3月'
            },
            {
              value:'4',
              label:'4月'
            },
            {
              value:'5',
              label:'5月'
            },
            {
              value:'6',
              label:'6月'
            },
            {
              value:'7',
              label:'7月'
            },
            {
              value:'8',
              label:'8月'
            },{
              value:'9',
              label:'9月'
            },{
              value:'10',
              label:'10月'
            },{
              value:'11',
              label:'11月'
            },{
              value:'12',
              label:'12月'
            }
          ],

          total:0,
          currentPage4: 1,
          loading:false,
        }
      },
      watch:{
        visualizationHeight:{
          deep:true,
          handler(newHeight, oldHeight){
            /**
             * newHeight- padding-top(80) = contentHeight
             * contentHeight - paddingTop(10) - search(165) - marginTop(5) - tableTileHeight(44) - 10 - 52
             *
             * 80+10+165+5+44+10+52
             *
             * */

            console.log(newHeight, oldHeight)
            let height = (newHeight - 371) / 90
            console.log(parseInt(height), '可以放多少个')
            if(parseInt(height) >=1 ){
              this.params.pageSize = parseInt(height)
            }else{
              this.params.pageSize = 1
            }
            this.init()
          }
        }
      },
      methods:{

        initHeight(newHeight){
          console.log('被调了')
          let height = (newHeight - 371) / 90
          if(parseInt(height) >=1 ){
            this.params.pageSize = parseInt(height)
          }else{
            this.params.pageSize = 1
          }
          this.init()
        },

        tableClassName({row, rowIndex}){
          return 'row-active-style';
        },
        handleSizeChange(val) {
          console.log(`每页 ${val} 条`);
          this.params.pageSize = val
          this.conditionsQuery()
        },
        handleCurrentChange(val) {
          console.log(`当前页: ${val}`);
          this.params.pageNum = val
          this.init()

        },
        conditionsQuery(e){
          this.isActive = e
          this.init()
        },
        reset(e){
          this.isActive = e
          this.params.xmqkHzf = ''
          this.params.xmqkJszt = ''
          this.params.xmqkLyfgs = ''
          this.params.xmqkRl = ''
          this.params.xmqkRzf = ''
          this.params.xmqkSgf = ''
          this.params.xmqkXmlx = ''
          this.params.xmqkXmmc = ''
          this.params.xmqkXmxz = ''
          this.params.aggregation = ''
          this.params.xmqkQy = ''
          this.params.monthValue = ''
          this.params.yearValue = ''
          this.params.manage = ''
          this.init()

        },
        //逻辑方法
        rowDetails(row, column,event){
          this.$router.push({name:'projectDetails',query:{xmid:row.xmid, xmqkJsztId:row.xmqkJsztId, xmqkJszt:row.xmqkXmlxId}})
        },

        //初始化
        init(){
          this.loading = true
          Object.keys(this.params).map((key, item) => {
            if(!this.params[key]){
              delete this.params[key]
            }
          })
          // let domHeight = document.getElementsByClassName('visualizationHeight')[0].offsetHeight
          // let size = (domHeight - 351) / 67
          // console.log(domHeight, size,  '初始化')
          //this.params.pageSize = parseInt(size)
          xmglQuery(this.params).then(res => {
            console.log('项目管理', res)
            if(res.code === 200 && res.data){
              this.tableData = res.data.rsList
              this.total = res.data.total
            }else{
              this.tableData = []
              this.total = 0
            }
            this.loading = false
          })
        },

        //选择框
        getSelect(){
          //项目现状
          getSubsidiaryData(7).then(res => {
            console.log()
            if(res.code === 200){
              this.xmxzOptions = res.data
            }
          })
          //项目类型
          getSubsidiaryData(8).then(res => {
            if(res.code === 200){
              this.xmflOptions = res.data
            }
          })
          //建设状态
          getSubsidiaryData(9).then(res => {
            if(res.code === 200){
              this.jsqkOptions = res.data
            }
          })
          //履约分公司
          getSubsidiaryData(50).then(res => {
            if(res.code === 200){
              this.lvfgsOptions = res.data
            }
          })
        }


      },
      mounted() {
        this.tableData = []

        //this.init()

        this.getSelect()

        document.onkeydown = (e) => {
          let _key = window.event.keyCode;
          if (_key === 13) {
            this.conditionsQuery('query')
          }
        }

        console.log(document.getElementsByClassName('visualizationHeight'),'容器')

      },
    }
</script>

<style lang="scss" scoped>

.project{
  padding-top: 80px;
  .table{
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    .table-container{
      padding: 10px 0;
      width: 95%;
      //border: 1px solid #345b7c;
      box-shadow: 0 0 15px #2183c4 inset;
      border-radius: 5px;
      background: rgba(33,131,196, 0.1);
      //.el-table{
      //  width: 95%;
      //  &::before{
      //    height: 0;
      //  }
      //}
      .query_conditions{
        padding-top: 10px;
        display: flex;
        justify-content: center;
        .query_form{
          width: 80%;
          ::v-deep .el-row{
            .el-form{
              .el-col{
                padding-right: 10px;
                .el-form-item{
                  margin-bottom: 15px;
                  .el-form-item__label{color:#a8a0a0}
                  .el-form-item__content{

                    .el-input{
                      .el-input__inner{
                        background: none !important;
                        border: 1px solid #778f9b;
                        color: #cedee2;
                      }
                    }
                    .el-button--default{
                      background: none;
                    }

                  }
                }
                &:nth-child(7){
                  .el-form-item{
                    .el-form-item__content{
                      margin-left: 0px !important;
                    }
                  }
                }
              }
            }
          }
        }
      }
      .paging{
        padding: 10px;
        display: flex;
        justify-content: flex-end;
        /*position: fixed;*/
        /*bottom:0;*/
        /*right:0;*/
        ::v-deep .el-pagination{
          .el-pagination__sizes{
            .el-select{
              .el-input{
                .el-input__inner{
                  background: none;
                  border: none;
                  box-shadow: 0 0 10px #2183c4 inset;
                }
              }
            }
          }
          .btn-prev{
            background: none;
            box-shadow: 0 0 10px #2183c4 inset;
          }
          .el-pager{
            .number{
              background: none;
              box-shadow: 0 0 10px #2183c4 inset;
            }
            li{
              background: none;
              box-shadow: 0 0 10px #2183c4 inset;
            }
          }
          .btn-next{
            background: none;
            box-shadow: 0 0 10px #2183c4 inset;
          }
          .el-pagination__jump{
            .el-input{
              .el-input__inner{
                background: none;
                border: none;
                box-shadow: 0 0 10px #2183c4 inset;
              }
            }
          }
        }
      }
    }
  }

  ::v-deep .el-table{
    width: 95%;
    color: #e6eff3;
    background: none;
    &::before{
      height: 0;
    }


    .rowBackground{
      background: #00afff;
      opacity: 0.1;
    }

    .el-table__body{
      tbody{
        .el-table__row{
          background: none;
          .el-table__cell{
            border-bottom: 0.0625rem solid #345b7c !important;
          }
        }
      }
    }

    .has-gutter{
      tr{
        background: rgba(43, 131, 172, 0.5);
        .el-table__cell{
          color: #a8a0a0;
          background: none;
          border-bottom: 0.0625rem solid #345b7c !important;
        }
      }
    }

  }

  ::v-deep .el-input__inner{
    &::placeholder{
      color: #a8a0a0;
    }
  }
  .active{
    border: none;
    color: #ffffff;
    box-shadow: 0 0 20px #2183c4 inset;
  }
  .focus{
    border: none;
    color: #ffffff;
    box-shadow: 0 0 20px #3a3d3f inset;
  }

  ::v-deep .el-table--enable-row-hover .el-table__body tr:hover > td.el-table__cell{
    background-color: rgba(3, 131, 172, 0.5);
  }
  /**弹框样式修改*/
  ::v-deep .el-dialog{
    top:80px;
    box-shadow: 0 0 0.3125rem #2183c4 inset;
    border-radius: 0.3125rem;
    background: rgba(6,92,189,0.5);
    .el-dialog__body{
      padding: 10px 0;
      .el-tabs{
        padding: 0 30px;
      }
    }
    .el-dialog__header{
      .el-dialog__title{
        color: #FFFFFF;
      }
    }
    .el-input{
      width: 150px;
      margin-right: 10px;
      &:first-child{
        margin-left: 10px;
      }
      .el-input__inner{
        background: none !important;
        border: 1px solid #2183c4;
      }
    }
    .el-button--default{
      background: none;
      box-shadow: 0 0 1.25rem #4097e7 inset;
      border: none;
    }

    .dialog_ul{
      .el-table{
        &::before{
          height: 0;
        }
      }
      .paging{
        padding: 10px;
        display: flex;
        justify-content: flex-end;
        .el-pagination{
          .el-pagination__sizes{
            .el-select{
              .el-input{
                .el-input__inner{
                  background: none;
                  border: none;
                  box-shadow: 0 0 10px #2183c4 inset;
                }
              }
            }
          }
          .btn-prev{
            background: none;
            box-shadow: 0 0 10px #2183c4 inset;
          }

          .btn-next{
            background: none;
            box-shadow: 0 0 10px #2183c4 inset;
            color: #FFFFFF;
          }
          .el-pagination__jump{
            .el-input{
              .el-input__inner{
                background: none;
                border: none;
                box-shadow: 0 0 10px #2183c4 inset;
              }
            }
          }
        }
      }
    }
  }

  ::v-deep el-input::-webkit-input-placeholder {
    -webkit-text-fill-color: #cedee2;
  }
}


::v-deep .el-table__body-wrapper::-webkit-scrollbar {
  width: 6px; // 横向滚动条
  height: 6px; // 纵向滚动条 必写
  background-color: rgba(240, 240, 240, 1);
}

::v-deep .el-table__body-wrapper::-webkit-scrollbar-track {
  box-shadow: inset 0 0 0px rgba(240, 240, 240, .5);
  border-radius: 10px;
  background-color: rgba(70, 130, 180,1);
}


::v-deep .el-table__body-wrapper::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 0px rgba(240, 240, 240, 0.5);
  background-color: #25e5df;
}



</style>
