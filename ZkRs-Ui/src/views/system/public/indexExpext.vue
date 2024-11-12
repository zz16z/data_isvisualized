<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="日期" prop="xjlyjqkRq">
        <el-date-picker clearable
                        v-model="queryParams.xjlyjqkRq"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8" >
      <el-col :span="1.5" v-if=isOperationShow>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5" v-if=isOperationShow>
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5" v-if=isOperationShow>
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >预计现金流导出</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImport"
        >预计现金流导入</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportSj"
        >实际现金流导出</el-button>
      </el-col> -->

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleImportSj"-->
<!--        >实际现金流导入</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cashList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="现金流情况id" align="center" prop="xjlyjqkId"  v-if="false"/>
      <el-table-column label="项目id" align="center" prop="xjlyjqkProjectId" v-if="false" />
      <el-table-column label="日期" align="center" prop="xjlyjqkRq" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.xjlyjqkRq, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计月收入支出(万元)" align="center" prop="xjlyjqkSrHj" />
      <el-table-column label="预计月支出(万元)" align="center" prop="xjlyjqkZcHj" />
      <el-table-column label="实际月收入(万元)" align="center" prop="xjlsjqkSrHj" />
      <el-table-column label="实际月支出(万元)" align="center" prop="xjlsjqkZcHj" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleLook(scope.row)"
          >查看</el-button>

          <el-button v-if=isOperationShow
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button v-if=isOperationShow
                     size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改现金流预计情况对话框 -->
    <el-dialog :title="title" :visible.sync="open"   v-if="open"  width="85%" append-to-body>
      <indexXjglAdd  :msg_xmid_item = msg_xmid  :msg_operation_id=yjxjlId :msg_isFormShow=isFormShow  ></indexXjglAdd>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-if="false">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>

    <!-- 导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" v-if="upload.open" width="400px" append-to-body>
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
          <div style="margin-top: 5px"><el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">预计现金流下载模板</el-link></div>
<!--          <div style="margin-top: 6px"><el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplateSj">实际现金流下载模板</el-link></div>-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm" >确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
  import { listCash, getCash, delCash, addCash, updateCash,importBox,importSjBox } from "@/api/system/expectCash";
  import indexXjglAdd  from "@/views/system/public/indexXjglAdd";
  import { getToken } from "@/utils/auth";

  export default {
    props: {
      //项目ID
      msg_xmid:String,
      //操作数据ID
      msg_operation_id:String,
      msg_status:String

    },
    components: {
      indexXjglAdd

    },
    //现金流预计
    name: "indexExpext",
    data() {
      return {
        //true为预计，false为实际
        fileShow:true,
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
        isFormShow:Boolean,
        isOperationShow:true,
        isOperationShowAdd:true,
        isOperationShowEdit:true,
        yjxjlId:'',
        //选择id值，单选
        tabeId:"",
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 现金流预计情况表格数据
        cashList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          xjlyjqkProjectId: null,
          xjlyjqkRq: null,
          xjlyjqkYjcszj: null,
          xjlyjqkSrHj: null,
          xjlyjqkZcHj: null,
          xjlyjqkSk: null,
          xjlsjqkZcHj: null,
          xjlyjqkGck: null,
          xjlyjqkDfsk: null,
          xjlyjqkSbk: null,
          xjlyjqkJak: null,
          xjlyjqkQtkx: null,
          isDelete: null
        },
        queryParamsSj: {

        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      // this.getList();
    },
    watch:{
      msg_status(){
        // if(this.msg_status==='2'){
        //   this.isOperationShow=false
        // }
        this.getList();
        if(this.msg_status==='2'){ //查看
          this.isOperationShow=false //
          this.isOperationShowAdd=false
          this.isOperationShowEdit=false

        }
        if(this.msg_status==='1'){ //修改不能增加
          // this.isOperationShowAdd=false

        }

        if(this.msg_status==='0'){ //新增不能增加
          // this.isOperationShowEdit=false


        }
      }
    },
    methods: {
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/expect/cash/downloadTemplate', {
        }, `预计现金流${new Date().getTime()}.xlsx`)

        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
      },

      //下载模板
      importTemplateSj() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/actual/cash/downloadTemplate', {
        }, `实际现金流模板${new Date().getTime()}.xlsx`)

        this.upload.open = false;
        this.upload.isUploading = false;
        this.$refs.upload.clearFiles();
      },
      /** 预计导入按钮操作 */
      handleImport() {
        this.fileShow=true
        this.upload.title = "用户导入";
        this.upload.open = true;

      },
      /** 实际按钮操作 */
      handleImportSj() {
        if(  this.tabeId==""){
          this.$alert("" +
            "<div style='overflow: auto;overflow-x: hidden;max-height: 7  " +
            " 0vh;padding: 10px 20px 0;'>" + "请选择要导入的数据项" + "</div>",
            "导入提示", {
              dangerouslyUseHTMLString: true
            });

          return;
        }
        this.fileShow=false
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
          this.getList()
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
        FormDatas.append("xmid",this.msg_xmid)
        if(this.fileShow){//预计现金流
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
              this.getList()
            }
          })
        }else{//实际现金流
          FormDatas.append("xjlsjqkYjxjlId",this.tabeId)

          importSjBox(FormDatas).then(response => {
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
              this.getList()
            }
          })
        }

      },
      /** 查询现金流预计情况列表 */
      getList() {
        this.loading = true;
        this.queryParams.xjlyjqkProjectId=  this.msg_xmid
        listCash(this.queryParams).then(response => {
          this.cashList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
        this.getList()
      },
      // 表单重置
      reset() {
        this.form = {
          xjlyjqkId: null,
          xjlyjqkProjectId: null,
          xjlyjqkRq: null,
          xjlyjqkYjcszj: null,
          xjlyjqkSrHj: null,
          xjlyjqkZcHj: null,
          xjlsjqkSrHj: null,
          xjlsjqkZcHj: null,
          xjlyjqkGck: null,
          xjlyjqkDfsk: null,
          xjlyjqkHj: null,
          xjlyjqkSbk: null,
          xjlyjqkJak: null,
          xjlyjqkQtkx: null,
          xjlyjqkhHj: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          isDelete: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.xjlyjqkId)
        this.single = selection.length!==1
        this.multiple = !selection.length
        if(selection.length==1 &&this.ids[0]!=""){
          this.tabeId=selection[0].xjlsjqkYjxjlId
        }else{
          this.tabeId=""
        }

      },
      /** 新增按钮操作 */
      handleAdd() {
        this.yjxjlId=""
        this.isFormShow=false
        this.reset();
        this.open = true;
        this.title = "添加现金流预计情况";

      },
      /** 查看按钮操作 */
      handleLook(row) {

        this.isFormShow=true
        this.reset();
        const xjlyjqkId = row.xjlyjqkId || this.ids
        this.yjxjlId=xjlyjqkId+""
        getCash(xjlyjqkId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看现金流预计情况";
        });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isFormShow=false
        this.reset();
        const xjlyjqkId = row.xjlyjqkId || this.ids
        this.yjxjlId=xjlyjqkId+""
        this.open = true;
        this.title = "修改现金流预计情况";


        // getCash(xjlyjqkId).then(response => {
        //   this.form = response.data;
        //   this.open = true;
        //   this.title = "修改现金流预计情况";
        // });
      },
      /** 提交按钮 */
      submitForm() {
        // this.$refs["form"].validate(valid => {
        //   if (valid) {
        //     if (this.form.xjlyjqkId != null) {
        //       updateCash(this.form).then(response => {
        //         this.$modal.msgSuccess("修改成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     } else {
        //       addCash(this.form).then(response => {
        //         this.$modal.msgSuccess("新增成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     }
        //   }
        // });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const xjlyjqkIds = row.xjlyjqkId || this.ids;
        this.$modal.confirm('是否确认删除现金流预计情况编号为"' + xjlyjqkIds + '"的数据项？').then(function() {
          return delCash(xjlyjqkIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.queryParams.xjlyjqkProjectId=this.msg_xmid
        this.download('system/expect/cash/export', {
          ...this.queryParams
        }, `预计现金流_${new Date().getTime()}.xlsx`)
      },
      /** 实际导出按钮操作 */
      handleExportSj() {

        this.queryParamsSj.xjlsjqkProjectId=this.msg_xmid
        this.download('system/actual/cash/export', {
          ...this.queryParamsSj
        }, `实际现金流_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
