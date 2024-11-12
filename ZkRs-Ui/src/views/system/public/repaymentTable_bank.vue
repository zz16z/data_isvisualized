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
          @click="handleDeleteDetails"
        >删除</el-button>

        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          @click="addDateItem"
        >提交</el-button>

        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleExport">导出</el-button>
        <el-button type="primary" icon="el-icon-plus" size="mini"  @click="handleImport">导入</el-button>
      </div>
      <!--表格-->
      <div  >
        <el-table
          v-loading="loading"
          :data="bcglXiangXiList"
          :row-class-name="rowClassName"
          @selection-change="handleDetailSelectionChange"
          ref="tb"
        >
          <el-table-column type="selection" width="30" align="center" />
          <el-table-column label="序号" align="center" prop="xh" width="50" v-if="false"></el-table-column>
          <el-table-column label="时间" align="center" prop="times" width="100">
            <template slot-scope="scope">   <!-- 金额保留2位下手-->
              <el-input  v-model="bcglXiangXiList[scope.row.xh-1].times"    maxlength="4"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
              />
            </template>
          </el-table-column>
          <el-table-column label="一月" align="center" >
            <el-table-column label="几号" align="center" prop="januaryDay" width="80">
              <template slot-scope="scope"  >
                <el-input   v-model="bcglXiangXiList[scope.row.xh-1].januaryDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
                />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="january" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].january" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="二月" align="center" >
            <el-table-column label="几号" align="center" prop="februaryDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].februaryDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="february" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].february" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
                />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="三月" align="center">
            <el-table-column label="几号" align="center" prop="marchDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].marchDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="march" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].march" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="四月" align="center" >
            <el-table-column label="几号" align="center" prop="aprilDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].aprilDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="april" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].april" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="五月" align="center" >
            <el-table-column label="几号" align="center" prop="mayDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].mayDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="may" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].may" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="六月" align="center" >
            <el-table-column label="几号" align="center" prop="juneDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].juneDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="june" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].june" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="七月" align="center" >
            <el-table-column label="几号" align="center" prop="julyDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].julyDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
                />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="july" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].july" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="八月" align="center" >
            <el-table-column label="几号" align="center" prop="augustDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].augustDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="august" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].august" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="九月" align="center" >
            <el-table-column label="几号" align="center" prop="septemberDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].septemberDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"  />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="september" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].september" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="十月" align="center" >
            <el-table-column label="几号" align="center" prop="octoberDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].octoberDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="october" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].october" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="十一月" align="center" >
            <el-table-column label="几号" align="center" prop="novemberDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].novemberDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="november" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].november" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="十二月" align="center" >
            <el-table-column label="几号" align="center" prop="decemberDay" width="80">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].decemberDay" maxlength="2"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
            <el-table-column label="金额" align="center" prop="december" width="150">
              <template slot-scope="scope">
                <el-input v-model="bcglXiangXiList[scope.row.xh-1].december" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
              </template>
            </el-table-column>
          </el-table-column>
          <el-table-column label="放款合计" align="center" prop="totalLoans" width="150">
            <template slot-scope="scope">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].totalLoans" axlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
            </template>
          </el-table-column>
          <el-table-column label="提款合计" align="center" prop="totalWithdrawal" width="150">
            <template slot-scope="scope" class="inputDeep">
              <el-input v-model="bcglXiangXiList[scope.row.xh-1].totalWithdrawal" maxlength="10"  onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" />
            </template>
          </el-table-column>
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
  import {addDateString,listDate,delDate,importBox} from "@/api/system/financingDate";
  import { getToken } from "@/utils/auth";
  export default {
    //放款
    name: "repaymentTable",
    props: {
      //操作数据id
      msg_id: String
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
      this.initTimes()
      this.getlistDate(  Number(this.msg_id))
    },
    watch:{
      msg_id(){
        //第一初始化无法监听到，后面可以的
        // this.getlistDate( this.msg_id)
      }
    },
    methods: {
      /** 导出按钮操作 */
      handleExport() {
        this.queryParams.financingId=null
        this.queryParams.paragraphType=null
        this.queryParams.paragraphType=1
        this.queryParams.financingId=this.msg_id
        this.download('system/date/export', {
          ...this.queryParams
        }, `融资时间还款_${new Date().getTime()}.xlsx`)
      },
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/statistics/downloadTemplate', {
        }, `融资时间${new Date().getTime()}.xlsx`)

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
      getlistDate(id){
        listDate({"financingId":id,paragraphType:1}).then(response => {
          if( response.code===200){
            this.handleDeleteAllDetails()

            this.bcglXiangXiList=response.rows

          }
        });
      },
      putDelDate(id) {
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

        //关联id
        obj.financingId= this.msg_id;
        //时间
        obj.times=''
        //月份///
        //一月Day
        obj.january=''
        //二月
        obj.february=''
        // 三月
        obj.march=''
        // 四月
        obj.april=''
        // 五月：
        obj.may=''
        // 六月：
        obj.june=''
        // 七月
        obj.july=''
        // 八月：
        obj.august=''
        // 九月：
        obj.september=''
        // 十月：
        obj.october=''
        // 十一月：
        obj.november=''
        // 十二月：
        obj.december=''
        //天数///
        //一月day
        obj.januaryDay=''
        //二月天数
        obj.februaryDay=''
        // 三月天数
        obj.marchDay=''
        // 四月天数
        obj.aprilDay=''
        // 五月天数
        obj.mayDay=''
        // 六月天数
        obj.juneDay=''
        // 七月天数
        obj.julyDay=''
        // 八月：天数
        obj.augustDay=''
        // 九月：天数
        obj.septemberDay=''
        // 十月：天数
        obj.octoberDay=''
        // 十一月：天数
        obj.novemberDay=''
        // 十二月：天数
        obj.decemberDay=''
        //还款合计
        obj.totalRepaymen=''
        //放款0 还款1
        obj.paragraphType='1'
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
      initTimes(){//初始化时间
        let i=0
        for(i==1;i<32;i++){
          let obj = {};
          obj.label=i+'号'
          obj.value=i
          this.times.push(obj)
        }

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
