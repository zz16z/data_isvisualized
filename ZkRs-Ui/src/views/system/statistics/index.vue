<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="日期" prop="dltjRq">
        <el-date-picker clearable
                        v-model="queryParams.dltjRq"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"  v-if=isOperationShow   >
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:statistics:add']"
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
          v-hasPermi="['system:statistics:edit']"
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
          v-hasPermi="['system:statistics:remove']"
        >删除</el-button>
      </el-col>
     <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:statistics:export']"
        >导出当前项目电量统计</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportAll"
          v-hasPermi="['system:statistics:export']"
        >导出所有项目电量统计</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleImport"
        >导入</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="statisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="电量统计id" align="center" prop="dltjId" v-if="false"/>
      <el-table-column label="项目id" align="center" prop="dltjProjectId"  v-if="false" />
      <el-table-column label="项目名称" align="center" prop="projectName"/>
      <el-table-column label="日期" align="center" prop="dltjRq" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dltjRq, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="预计发电量" align="center" prop="dltjYjfdl" />-->
      <!--      <el-table-column label="发电量情况" align="center" prop="dltjQdlqk" />-->
      <!--      <el-table-column label="上月结算电费应收金额" align="center" prop="dltjSyjsdfyssj" />-->
      <!--      <el-table-column label="累计未到账电费" align="center" prop="dltjLjwdzdf" />-->

      <el-table-column label="电费收益" align="center" prop="dltjDfsy" />
      <el-table-column label="补贴" align="center" prop="dltjBt" />
      <el-table-column label="其他" align="center" prop="dltjQt" />
      <el-table-column label="本月收入合计" align="center" prop="dltjBysrhj" />
      <el-table-column label="本月支出总计" align="center" prop="dltjByzchj" />
      <!--      <el-table-column label="补贴" align="center" prop="dltjBt" />-->
      <!--      <el-table-column label="本月收入合计" align="center" prop="dltjBysrhj" />-->
      <!--      <el-table-column label="贵电工程回款" align="center" prop="dltjGdgchk" />-->
      <!--      <el-table-column label="下网电费" align="center" prop="dltjXwdf" />-->
      <!--      <el-table-column label="人工工资" align="center" prop="dltjRggz" />-->
      <!--      <el-table-column label="文件办理及其他" align="center" prop="dltjWjbljqt" />-->
      <!--      <el-table-column label="利息" align="center" prop="dltjLx" />-->
      <!--      <el-table-column label="是否删除：未删除 0、  删除 1" align="center" prop="isDelete" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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

    <!-- 添加或修改电量统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="85%" append-to-body>
      <el-form ref="form" :model="form"   :rules="rules" label-width="220px">

        <el-form-item label="项目id" prop="dltjProjectId" hidden="hidden">
          <el-input v-model="form.dltjProjectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="日期" prop="dltjRq"  >
              <el-date-picker clearable  style="width: 100%"  :disabled=isFormShow
                              v-model="form.dltjRq"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="预计发电量(万kWh)" prop="dltjYjfdl">
              <el-input v-model="form.dltjYjfdl" placeholder="请输入预计发电量"  :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本月上网电量(万kWh)" prop="dltjSjfdl">
              <el-input v-model="form.dltjSjfdl"  placeholder="请输入本月上网电量(万kWh)"   :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="上网电量偏差（万kWh）" prop="dltjPc">
              <el-input v-model="form.dltjPc"  placeholder="请输入上网电量偏差（万kWh）"   :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本月结算电量（万kWh）" prop="dltjSjjsdl">
              <el-input v-model="form.dltjSjjsdl"  placeholder="请输入本月结算电量（万kWh）"   :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="本月结算电费（万元）" prop="dltjSjdfsr">
              <el-input v-model="form.dltjSjdfsr" placeholder="请输入本月结算电费（万元）" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="累积上网电量（万kWh）" prop="dltjLjswdl">
              <el-input v-model="form.dltjLjswdl" placeholder="请输入累积上网电量（万kWh）" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="累计结算电量（万kWh）" prop="dltjLjjsdl">
              <el-input v-model="form.dltjLjjsdl" placeholder="请输入累计结算电量（万kWh）" :disabled=isFormShow />
            </el-form-item>
          </el-col>

           <el-col :span="8">
             <el-form-item label="结算累积应收（万元）" prop="dltjLjfdl">
               <el-input v-model="form.dltjLjfdl" placeholder="请输入结算累积应收（万元）" :disabled=isFormShow />
             </el-form-item>
           </el-col>

           <el-col :span="8">
             <el-form-item label="结算累积到账（万元）" prop="dltjDfjsljsjdz">
               <el-input v-model="form.dltjDfjsljsjdz" placeholder="请输入结算累积到账（万元）" :disabled=isFormShow />
             </el-form-item>
           </el-col>

           <el-col :span="8">
             <el-form-item label="结算累积未到账（万元）" prop="dltjLjwdzdf">
               <el-input v-model="form.dltjLjwdzdf" placeholder="请输入结算累积未到账（万元）" :disabled=isFormShow />
             </el-form-item>
           </el-col>
        </el-row>

        <el-row>

        </el-row>
        <el-row>


        </el-row>

        <el-row>

        </el-row>

        <el-row>





        </el-row>

        <div  class="margin-div">
          <el-divider></el-divider>
          <span class="fenggx">账户收入（万元）:</span>
        </div>
        <el-row>
          <el-col :span="8">
            <el-form-item label="电费收益" prop="dltjDfsy">
              <el-input v-model="form.dltjDfsy" placeholder="请输入电费收益" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="补贴" prop="dltjBt">
              <el-input v-model="form.dltjBt" placeholder="请输入补贴" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="其他" prop="dltjQt">
              <el-input v-model="form.dltjQt" placeholder="请输入其他" :disabled=isFormShow />
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="本月收入合计" prop="dltjBysrhj">
              <el-input v-model="form.dltjBysrhj" placeholder="请输入本月收入合计" :disabled=isFormShow />
            </el-form-item>
          </el-col>
        </el-row>
        <div  class="margin-div">
          <el-divider></el-divider>
          <span class="fenggx">实际支出（万元）:</span>
        </div>
        <!--      <el-row>-->
        <!--        <el-col :span="8">-->
        <!--          <el-form-item label="本月收入合计" prop="dltjBysrhj">-->
        <!--            <el-input v-model="form.dltjBysrhj" placeholder="请输入本月收入合计" />-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <!--      </el-row>-->
        <el-row>
          <el-col :span="8">
            <el-form-item label="下网电费" prop="dltjXwdf">
              <el-input v-model="form.dltjXwdf" placeholder="请输入下网电费" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="员工工资" prop="dltjRggz">
              <el-input v-model="form.dltjRggz" placeholder="请输入员工工资" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="文件办理及其他" prop="dltjWjbljqt">
              <el-input v-model="form.dltjWjbljqt" placeholder="请输入文件办理及其他" :disabled=isFormShow  />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="融资利息" prop="dltjLx">
              <el-input v-model="form.dltjLx" placeholder="请输入融资利息" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="本月支出合计" prop="dltjByzchj">
              <el-input v-model="form.dltjByzchj" placeholder="请输入本月支出合计" :disabled=isFormShow />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>

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
  import { importBox,listStatistics, getStatistics, delStatistics, addStatistics, updateStatistics } from "@/api/system/statistics";

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
        // 电量统计表格数据
        statisticsList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          dltjProjectId: null,
          dltjRq: null,
          dltjYjfdl: null,
          dltjQdlqk: null,
          dltjSyjsdfyssj: null,
          dltjLjwdzdf: null,
          dltjGdgchk: null,
          dltjDfsy: null,
          dltjBt: null,
          dltjBysrhj: null,
          dltjXwdf: null,
          dltjRggz: null,
          dltjWjbljqt: null,
          dltjLx: null,
          isDelete: null,
          dltjSyhj: null,
          dltZczj: null,
          dltjLjysgb: null,
          dltjSjjsdl: null,
          dltjDfjsljsjdz: null,
          dltjQt: null,
          dltjByzchj: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {

          dltjDljsljysje: [
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确格式,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjLjfdl: [
        {
          pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
          message: "请输入正确格式,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjDfjsljyjdz: [
            //   { required: true, trigger: "blur", message: "上月结算电费应收金额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjYjdfsr: [
            //   { required: true, trigger: "blur", message: "上月结算电费应收金额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjSjdfsr: [
            //   { required: true, trigger: "blur", message: "上月结算电费应收金额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjRq: [
            { required: true, trigger: "blur", message: "日期不能为空" }
          ],
          dltjYjfdl: [
          //  { required: true, trigger: "blur", message: "预发电量不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确格式,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjSjjsdl: [
           // { required: true, trigger: "blur", message: "实际结算电量不能为空" }
          ],
          dltjQdlqk: [
         //   { required: true, trigger: "blur", message: "发电量情况不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确格式,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjSyjsdfysje: [
         //   { required: true, trigger: "blur", message: "上月结算电费应收金额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjDfjsljsjdz: [
          //  { required: true, trigger: "blur", message: "电费结算累计实际到账不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          dltjLjwdzdf: [
          //  { required: true, trigger: "blur", message: "累计未到账电费不能为空" }
          ],
          dltjGdgchk: [
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjLjysgb: [
          //  { required: true, trigger: "blur", message: "累计应收国补不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjDfsy: [
          //  { required: true, trigger: "blur", message: "电费收益不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjBt: [
           // { required: true, trigger: "blur", message: "补贴不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjQt: [
          //  { required: true, trigger: "blur", message: "其他不能为空" }
          ],
          dltjBysrhj: [
          //  { required: true, trigger: "blur", message: "本月收入合计不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjXwdf: [
          //  { required: true, trigger: "blur", message: "下网电费不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjRggz: [
          //  { required: true, trigger: "blur", message: "人工工资不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjBxk: [
         //   { required: true, trigger: "blur", message: "报销款不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjWjbljqt: [
          //  { required: true, trigger: "blur", message: "文件办理及其他不能为空" }
          ],
          dltjLx: [
         //   { required: true, trigger: "blur", message: "利息不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          dltjByzchj: [
          //  { required: true, trigger: "blur", message: "本月支持合计不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
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
      /** 查询电量统计列表 */
      getList() {
        this.loading = true;
        this.queryParams.dltjProjectId=this.msg_xmid
        listStatistics(this.queryParams).then(response => {
          this.statisticsList = response.rows;
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
          dltjId: null,
          dltjProjectId: null,
          dltjRq: null,
          dltjYjfdl: null,
          dltjQdlqk: null,
          dltjSyjsdfyssj: null,
          dltjLjwdzdf: null,
          dltjGdgchk: null,
          dltjDfsy: null,
          dltjBt: null,
          dltjBysrhj: null,
          dltjXwdf: null,
          dltjRggz: null,
          dltjWjbljqt: null,
          dltjLx: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          isDelete: null,
          dltjSyhj: null,
          dltZczj: null,
          dltjLjysgb: null,
          dltjSjjsdl: null,
          dltjDfjsljsjdz: null,
          dltjQt: null,
          dltjByzchj: null
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
        this.ids = selection.map(item => item.dltjId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.isFormShow=false
        this.reset();
        this.open = true;
        this.title = "添加电量统计";

      },
      /** 查看按钮操作 */
      handleLook(row) {
        this.isFormShow=true
        this.reset();
        const dltjId = row.dltjId || this.ids
        getStatistics(dltjId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看电量统计";

        });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.isFormShow=false
        this.reset();
        const dltjId = row.dltjId || this.ids
        getStatistics(dltjId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改电量统计";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {  debugger
            if (this.form.dltjId != null) {
              this.form.dltjProjectId=this.msg_xmid
              updateStatistics(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {

              if(this.msg_xmid===""){ //项目不存在时不能保存

                this.$modal.msgError("先添加项目在保存！！！");
                return
              }
              this.form.dltjProjectId=this.msg_xmid
              addStatistics(this.form).then(response => {
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
        const dltjIds = row.dltjId || this.ids;
        this.$modal.confirm('是否确认删除电量统计编号为"' + dltjIds + '"的数据项？').then(function() {
          return delStatistics(dltjIds);
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
