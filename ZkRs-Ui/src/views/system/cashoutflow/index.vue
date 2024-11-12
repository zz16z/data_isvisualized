<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="年份" prop="projectYear">
        <el-date-picker clearable
                        v-model="queryParams.projectYear"
                        type="year"
                        value-format="yyyy"
                        placeholder="请选择年份">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"  v-if=isOperationShow   >
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5"   v-if=isOperationShow>
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5"  v-if=isOperationShow>
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cashList" @selection-change="handleSelectionChange">
     <el-table-column type="selection" width="55" align="center" />
     <el-table-column label="主键ID" align="center" prop="id"  v-if="false"/>
     <el-table-column label="项目ID" align="center" prop="projectId"  v-if="false"/>
     <el-table-column label="epc签约日期" align="center" prop="projectEpc" width="90">
       <template slot-scope="scope">
         <span>{{ parseTime(scope.row.projectEpc, '{y}-{m}-{d}') }}</span>
       </template>
     </el-table-column>
     <el-table-column label="年份" align="center" prop="projectYear" width="90">
     </el-table-column>

     <el-table-column label="一月" align="center" prop="jan" />
     <el-table-column label="二月" align="center" prop="feb" />
     <el-table-column label="三月" align="center" prop="mar" />
     <el-table-column label="四月" align="center" prop="apr" />
     <el-table-column label="五月" align="center" prop="may" />
     <el-table-column label="六月" align="center" prop="jun" />
     <el-table-column label="七月" align="center" prop="jul" />
     <el-table-column label="八月" align="center" prop="aug" />
     <el-table-column label="九月" align="center" prop="sep" />
     <el-table-column label="十月" align="center" prop="oct" />
     <el-table-column label="十一月" align="center" prop="nov" />
     <el-table-column label="十二月" align="center" prop="decs" />
     <el-table-column label="合计" align="center" prop="all" />
      <el-table-column label="操作" width="180" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleLook(scope.row)"
            v-hasPermi="['system:statistics:edit']"
          >查看</el-button>

          <el-button   v-if=isOperationShow
                       size="mini"
                       type="text"
                       icon="el-icon-edit"
                       @click="handleUpdate(scope.row)"
                       v-hasPermi="['system:statistics:edit']"
          >修改</el-button>
          <el-button   v-if=isOperationShow
                       size="mini"
                       type="text"
                       icon="el-icon-delete"
                       @click="handleDelete(scope.row)"
                       v-hasPermi="['system:statistics:remove']"
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

    <!-- 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" append-to-body>
      <el-form ref="form" :model="form"   :rules="rules" label-width="220px">


        <el-form-item label="id" prop="id" hidden="hidden">
          <el-input v-model="form.id" placeholder="请输入项目id" />
        </el-form-item>

        <el-row>
          <el-col :span="8">
              <el-form-item label="Epc签约日期" prop="projectEpc"  >
                <el-date-picker clearable  style="width: 100%"  :disabled=isFormShow
                                v-model="form.projectEpc"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
            <el-form-item label="年份" prop="projectYear">
              <el-date-picker clearable   :disabled=isFormShow
                              v-model="form.projectYear"
                              type="year"
                              value-format="yyyy"
                              placeholder="请选择年份">
              </el-date-picker>
            </el-form-item>
             </el-col>
        </el-row>
         <el-row>
          <el-col :span="8">
            <el-form-item label="一月" prop="jan">
              <el-input v-model="form.jan" placeholder="一月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="二月" prop="feb">
              <el-input v-model="form.feb" placeholder="二月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="三月" prop="mar">
              <el-input v-model="form.mar" placeholder="三月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="四月" prop="apr">
              <el-input v-model="form.apr" placeholder="四月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="五月" prop="may">
              <el-input v-model="form.may" placeholder="五月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="六月" prop="jun">
              <el-input v-model="form.jun" placeholder="六月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="七月" prop="jul">
              <el-input v-model="form.jul" placeholder="七月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="八月" prop="aug">
              <el-input v-model="form.aug" placeholder="八月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="九月" prop="sep">
              <el-input v-model="form.sep" placeholder="九月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="十月" prop="oct">
              <el-input v-model="form.oct" placeholder="十月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="十一月" prop="nov">
              <el-input v-model="form.nov" placeholder="十一月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="十二月" prop="decs">
              <el-input v-model="form.decs" placeholder="十二月"  :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="8">
            <el-form-item label="合计" prop="all">
              <el-input v-model="form.all" placeholder="合计"  :disabled=isFormShow />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-if=!isFormShow>确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

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
</template>

<script>
  import {listCash,updateCash,addCash,getCash,delCash} from "@/api/system/cash";

  import { getToken } from "@/utils/auth";
  import {financingImport} from "@/api/system/financing";
  export default {
    name: "Statistics",
    props: {
      //项目ID
      msg_xmid:String,
      msg_status:String
    },
    data() {
      return {
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
        isFormShow:false,
        isOperationShow:true,
        isOperationShowAdd:true,
        isOperationShowEdit:true,
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
        //
        cashList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectId: null,
          projectYear: null,
          outIn: 0
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          projectYear: [
            { required: true, trigger: "blur", message: "日期不能为空" }
          ],
          projectEpc: [
            { required: true, trigger: "blur", message: "日期不能为空" }
          ],
        }
      };
    },
    created() {
      // if(this.msg_status==='2'){
      //
      //   this.isOperationShow=false
      // }

    },
    mounted(){
      // this.getList();

    },
    watch:{
      msg_status(){
        this.getList();
        if(this.msg_status==='2'){ //查看
          this.isOperationShow=false //
          this.isOperationShowAdd=false
          this.isOperationShowEdit=false

        }
        if(this.msg_status==='1'){ //修改不能增加
          this.isOperationShowAdd=false
        }

        if(this.msg_status==='0'){ //新增不能增加
          this.isOperationShowEdit=false
        }

      },
      msg_xmid(){

      }
    },
    methods: {
      /** 查询支出统计列表 */
      getList() {
        this.loading = true;
        this.queryParams.projectId=this.msg_xmid
        listCash(this.queryParams).then(response => {
          console.log(response)
          this.cashList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          projectId: null,
          projectEpc: null,
          projectYear: null,
          outIn: null,
          cash_id:null,
          jan:null,
          feb:null,
          ma:null,
          apr:null,
          may:null,
          jun:null,
          jul:null,
          aug:null,
          sep:null,
          oct:null,
          nov:null,
          decs:null,
          all:null
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
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.isFormShow=false
        this.reset();
        this.open = true;
        this.title = "添加预计现金流出";
      },
      /** 查看按钮操作 */
      handleLook(row) {
        this.isFormShow=true
        this.reset();
        const id = row.id || this.ids
        getCash(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看预计现金流出";

        });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isFormShow=false
        this.reset();
        const id = row.id || this.ids
        getCash(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改预计现金流出";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.form.outIn = 0;
            if (this.form.id != null) {
              this.form.projectId=this.msg_xmid
              updateCash(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              if(this.msg_xmid===""){ //项目不存在时不能保存
                this.$modal.msgError("先添加项目再保存！！！");
                return
              }
              this.form.projectId=this.msg_xmid
              addCash(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
         const id = row.id || this.ids
        this.$modal.confirm('是否确认删除编号为"' + id + '"的数据项？').then(function() {
          return delCash(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/statistics/export', {
          ...this.queryParams
        }, `电量统计_${new Date().getTime()}.xlsx`)
      },
      /** 导出按钮操作 */
      handleExportAll() {
        let params = this.queryParams;
        params.dltjProjectId = null;
        this.download('system/statistics/export', {
          ...params
        }, `所有电量统计_${new Date().getTime()}.xlsx`)
      },
      /** 导入按钮操作 */
      handleEntrance() {
        this.download('system/statistics/export', {
          ...this.queryParams
        }, `电量统计_${new Date().getTime()}.xlsx`)
      },
      ///////////
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/statistics/downloadTemplate', {
        }, `电量统计${new Date().getTime()}.xlsx`)

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
      },

    }
  };
</script>
<style scoped>

  .fenggx{
    color: black;
    font-size: 22px;

  }
  .margin-div {
    margin-bottom: 19px;
  }
</style>
