<template>
  <div class="app-container">
    <div >
      <!--操作 -->
      <div>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDetails">添加</el-button>
        <el-button
          type="success"
          icon="el-icon-delete"
          size="mini"
          @click="addDateItem"
        >提交</el-button>


        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="handleDeleteDetails"
        >删除</el-button>



        <!-- <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleExport">导出</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"  @click="handleImport">导入</el-button> -->
      </div>
      <!--表格-->
      <div  style="width: 100%">
        <el-table
          v-loading="loading"
          :data="bcglXiangXiList"
          :row-class-name="rowClassName"
          @selection-change="handleDetailSelectionChange"
          ref="tb"
        >
          <el-table-column type="selection" width="30" align="center" />
          <el-table-column label="序号" align="center" prop="xh" width="50" v-if="false"></el-table-column>
          <el-table-column label="时间" align="center" prop="financingRq" width="250">
            <template slot-scope="scope"  >
              <el-date-picker  v-model="bcglXiangXiList[scope.row.xh-1].financingRq" type="date" placeholder="选择时间"    value-format="yyyy-MM-dd"></el-date-picker>
            </template>
          </el-table-column>


          <el-table-column label="成本" align="center" prop="financingCb" width="150">
            <template slot-scope="scope"  >
              <el-input   v-model="bcglXiangXiList[scope.row.xh-1].financingCb" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')"
              />
            </template>
          </el-table-column>

          <el-table-column label="利息" align="center" prop="financingLx" width="150">
            <template slot-scope="scope">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].financingLx" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')" />
            </template>
          </el-table-column>


          <el-table-column label="手续费" align="center" prop="financingSxf" width="150">
            <template slot-scope="scope">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].financingSxf" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')" />
            </template>
          </el-table-column>

         <!-- <el-table-column label="还款合计" align="center" prop="financingBysjhk" width="150">
            <template slot-scope="scope">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].financingBysjhk" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')" />
            </template>
          </el-table-column>

          <el-table-column label="还款保证金" align="center" prop="financingBzj" width="150">
            <template slot-scope="scope">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].financingBzj" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,4})?).*$/g, '$1')" />
            </template>
          </el-table-column> -->


        </el-table>
      </div>

      <!-- 导入对话框 -->
      <el-dialog :title="upload.title" :visible.sync="upload.open" v-if="upload.open"  width="400px" append-to-body>
        <el-upload
          ref="upload"
          :limit="1"
          accept=".xlsx, .xls"
          :headers="upload.headers"
          :action="upload.url "
          :disabled="upload.isUploading"
          :on-progress="handleFileUploadProgress"
          :on-success="handleFileSuccess"
          :auto-upload="false"
          drag
          :http-request="uploadFileok"
          action
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip text-center" slot="tip">
            <span>仅允许导入xls、xlsx格式文件。</span>
            <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
          </div>
        </el-upload>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitFileForm">确 定</el-button>
          <el-button @click="upload.open = false">取 消</el-button>
        </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
  import {addDateString,listDate,delDate,importBox} from "@/api/system/still";
  import { getToken } from "@/utils/auth";
  export default {
    //放款
    name: "repaymentTable",
    props: {
      //操作数据id
      msg_id: String,
      //数据加载
      mgs_get_data : String
    },
    data() {
      return {
        queryParams:{
          financingId:null,
          paragraphType:null
        },
        upload: {
          // 是否显示弹出层
          open: false,
          // 弹出层标题
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 设置上传的请求头部
          headers: { Authorization: "Bearer " + getToken() },
          // 上传的地址，请求地址
          // url: process.env.VUE_APP_BASE_API + "/system/statistics/importBox"
        },
        financingDate:'',
        //日期数组
        times:[],
        loading:false,
        //详细list
        bcglXiangXiList: [],
        //选中的从表数据
        checkedDetail: [],
        dkddOptions:[],
        zdtsOptions:[]
      }
    },
    created() {

      this.getlistDate(  Number(this.msg_id))

    },
    watch:{
      msg_id(){
        //第一初始化无法监听到，后面可以的
        // this.getlistDate( this.msg_id)
      },
      mgs_get_data(new_v,old_v){

        this.getlistDate(  Number(this.msg_id))
      }
    },
    methods: {
      // jobSearch() {
      //   this.getJobListByMonth();
      // },
      //  initData(data) {
      //   //获取当前时间
      //   var now   = new Date();
      //   var monthn = now.getMonth()+1;
      //   var yearn  = now.getFullYear();
      //   this.selectMonth = yearn+"-"+monthn;
      //
      //   // this.selectUser = parseInt(sessionStorage.getItem("userid"));
      //   // this.getWeekJobList({
      //   //   userid: sessionStorage.getItem("userid"),
      //   //   weekid: "1"
      //   // });
      //   this.getJobListByMonth();
      // },
      //  getJobListByMonth(data) {
      //
      // },


      /** 导出按钮操作 */
      handleExport() {
        this.queryParams.financingId=null
        this.queryParams.paragraphType=null
        this.queryParams.paragraphType=1
        this.queryParams.financingId=this.msg_id
        this.queryParams.isYjSj=this.mgs_get_data
        this.download('system/still/export', {
          ...this.queryParams
        }, `融资还款_${new Date().getTime()}.xlsx`)
      },
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/still/downloadTemplate', {
        }, `融资还款${new Date().getTime()}.xlsx`)

        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
      },
      /** 导入按钮操作 */
      handleImport() {
        this.upload.title = "用户导入";
        this.upload.open = true;
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
        this.$alert("" +
          "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
          " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
          "导入结果", {
            dangerouslyUseHTMLString: true
          });
        if(response.code=="200"){
          this.getlistDate(  Number(this.msg_id))
        }
      },
      // 提交上传文件
      submitFileForm() {
        //触发uploadFileok函数
        this.$refs.upload.submit();
      },
      uploadFileok(item){

        if(this.msg_xmid==""){
          this.$alert("" +
            "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
            " 0vh;padding: 10px 20px 0;'>" + "请在对应的序号下导入" + "</div>",
            "导入结果", {
              dangerouslyUseHTMLString: true
            });
          return
        }
        let FormDatas = new FormData()
        FormDatas.append('file',item.file);
        FormDatas.append("xmType",1)
        FormDatas.append("msg_id",this.msg_id)
        FormDatas.append("isYjSj",this.mgs_get_data)
        importBox(FormDatas).then(response => {
          this.upload.open = false;
          this.upload.isUploading = false;
          this.$refs.upload.clearFiles();
          this.$alert("" +
            "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
            " 0vh;padding: 10px 20px 0;'>" + response.msg + "</div>",
            "导入结果", {
              dangerouslyUseHTMLString: true
            });
          if(response.code=="200"){
            this.getlistDate(  Number(this.msg_id))
          }
        })
      },
      getlistDate(id){  debugger
        let isYjSj='';
        if(this.mgs_get_data===""){
          isYjSj='0'
        }else{
          isYjSj=this.mgs_get_data
        }

        listDate({"financingId":id}).then(response => {
          if( response.code===200){
            this.handleDeleteAllDetails()

            this.bcglXiangXiList=response.rows

          }
        });
      },
      putDelDate(id) {
        if(id===""){
          this.bcglXiangXiList.splice(this.checkedDetail[0].xh - 1, 1);
          return ;
        }
        this.$modal.confirm('是否确认删除数据项？').then(function () {
          return delDate(id);
        }).then(() => {
          this.$modal.msgSuccess("删除成功");
          this.bcglXiangXiList.splice(this.checkedDetail[0].xh - 1, 1);
        }).catch(() => {
        })
      },
      handleSetLineChartData(type) {
        this.lineChartData = lineChartData[type]
      },
      //行回调(数据变化)
      rowClassName({ row, rowIndex }) {
        row.xh = rowIndex + 1;
      },
      //单选框选中数据
      handleDetailSelectionChange(selection) {
        if (selection.length > 1) {
          this.$refs.tb.clearSelection();
          this.$refs.tb.toggleRowSelection(selection.pop());
        } else {
          this.checkedDetail = selection;
        }
      },
      //添加
      handleAddDetails() {
        if(Number(this.msg_id)<=0){
          this.$alert("请先添加项目情况融资情况在添加", "提示", {
            confirmButtonText: "确定",
          });
          return
        }
        if (this.bcglXiangXiList == undefined) {
          this.bcglXiangXiList = new Array();
        }
        let obj = {}

        //组件id
        obj.id=''

        /** 日期 */
        obj.financingRq=''

        //融资表关联id
        obj.financingId= this.msg_id;

        /** 成本 */
        obj.financingCb='';

        /** 利息 */
        obj.financingLx='';

        /** 手续费 */
        obj.financingSxf='';

        /** 还款保证金 */
        obj.financingBzj='';
        //默认为预计
        if(this.mgs_get_data===""){
          //预计标记
          obj.isYjSj=''
        }else{
          obj.isYjSj=''
        }


        this.bcglXiangXiList.push(obj);
      },
      //删除
      handleDeleteDetails() {

        if (this.checkedDetail.length == 0) {
          this.$alert("请先选择要删除的数据", "提示", {
            confirmButtonText: "确定",
          });
        } else {
          this.putDelDate(this.checkedDetail[0].id)
        }
      },
      //清空
      handleDeleteAllDetails() {
        this.bcglXiangXiList = undefined;
      },
      handleFors(){
        this.bcglXiangXiList.forEach( (v,i) => {
          // console.log(v,i.ts);
          console.log(v.totalWithdrawal);
        },this);
      },

      addDateItem(){

        if (this.checkedDetail.length == 0) {
          this.$alert("请先选择要添加的数据", "提示", {
            confirmButtonText: "确定",
          });
          return
        }
        //
        let myJsonString = JSON.stringify(this.bcglXiangXiList).toString();
        addDateString(myJsonString).then(response => {
          this.getlistDate(  Number(this.msg_id))
          this.$modal.msgSuccess("添加成功");
        });
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
  /*.el-input-null>>>.el-input__inner{*/
  /*  border: 0*/
  /*}*/



  .paperview-input-text>>>el-input__inner {
    border: 0px;
  }

  .inputDeep>>>.el-input__inner {
    border: 0;
  }
</style>
