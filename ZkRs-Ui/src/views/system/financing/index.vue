<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="项目id" prop="xmrzProjectId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.xmrzProjectId"-->
      <!--          placeholder="请输入项目id"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="融资机构" prop="xmrzRzjg">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.xmrzRzjg"-->
      <!--          placeholder="请输入融资机构"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="签约日期" prop="xmrzQyrq">
        <el-date-picker clearable
                        v-model="queryParams.xmrzQyrq"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择签约日期">
        </el-date-picker>
      </el-form-item>
      <div>
        <!--      <el-form-item label="融资负责人" prop="xmrzRzfzr">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzRzfzr"-->
        <!--          placeholder="请输入融资负责人"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="融资总额" prop="xmrzRzze">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzRzze"-->
        <!--          placeholder="请输入融资总额"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="已放款总额" prop="xmrzYfkze">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzYfkze"-->
        <!--          placeholder="请输入已放款总额"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="预计下笔放款" prop="xmrzYjxbfk">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzYjxbfk"-->
        <!--          placeholder="请输入预计下笔放款"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="保证金" prop="xmrzBzj">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzBzj"-->
        <!--          placeholder="请输入保证金"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="融资利率" prop="xmrzRzll">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzRzll"-->
        <!--          placeholder="请输入融资利率"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="手续费收取方式" prop="xmrzSxfsqfs">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzSxfsqfs"-->
        <!--          placeholder="请输入手续费收取方式"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="首年IRR" prop="xmrzSnirr">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzSnirr"-->
        <!--          placeholder="请输入首年IRR"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="全周期综合irr" prop="xmrzQzqzhirr">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzQzqzhirr"-->
        <!--          placeholder="请输入全周期综合irr"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="融资情况" prop="xmrzRzqk">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.xmrzRzqk"-->
        <!--          placeholder="请输入融资情况"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
        <!--      <el-form-item label="是否删除：未删除 0、  删除 1" prop="isDelete">-->
        <!--        <el-input-->
        <!--          v-model="queryParams.isDelete"-->
        <!--          placeholder="请输入是否删除：未删除 0、  删除 1"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->
        <!--      </el-form-item>-->
      </div>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"   v-if=isOperationShow>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:financing:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5"  v-if=isOperationShow>
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:financing:edit']"
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
          v-hasPermi="['system:financing:remove']"
        >删除</el-button>
      </el-col>
    <!--  <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:financing:export']"
        >导出</el-button>
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
      <el-col :span="1.5">
        <el-tooltip class="item" effect="dark" :content="tag.name" placement="top-start" v-for="(tag,index) in fileList" :key="index">
          <el-tag style="margin-right:10px;display:flex;" :disable-transitions="false" @close="handleClose(index)" closable  @click="downloadFile(tag)"><i class="el-icon-paperclip"></i><span class="tagtext">{{tag.name}}</span></el-tag>
        </el-tooltip>

      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="financingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column  align="center" prop="xmrzId" v-if="false" />
      <el-table-column label="项目id" align="center" prop="xmrzProjectId" v-if="false" />
      <el-table-column label="融资机构" align="center" prop="xmrzRzjg" />
      <el-table-column label="签约日期" align="center" prop="xmrzQyrq" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.xmrzQyrq, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="融资经理" align="center" prop="xmrzRzfzr" />
      <el-table-column label="融资金额" align="center" prop="xmrzRzze" />
      <el-table-column label="已放款" align="center" prop="xmrzYfkze" />
      <el-table-column label="未放款" align="center" prop="xmrzWfkze" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleLook(scope.row)"
            v-hasPermi="['system:financing:edit']"
          >查看</el-button>

          <el-button  v-if=isOperationShow
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      @click="handleUpdate(scope.row)"
                      v-hasPermi="['system:financing:edit']"
          >修改</el-button>
          <el-button  v-if=isOperationShow
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      @click="handleDelete(scope.row)"
                      v-hasPermi="['system:financing:remove']"
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

    <!-- 添加或修改项目融资对话框 -->
    <el-dialog :title="title" :visible.sync="open"  v-if="open"  width="85%"  append-to-body>
      <div >
        <el-form ref="form" :model="form" :rules="rules" label-width="200px">
          <el-row>
            <!--        <el-col :span="8">-->
            <!--          <el-form-item label="项目id" prop="xmrzProjectId">-->
            <!--            <el-input v-model="form.xmrzProjectId" placeholder="请输入项目id" />-->
            <!--          </el-form-item>-->
            <!--        </el-col>-->
            <el-col :span="8">
              <el-form-item label="融资机构" prop="xmrzRzjg">
                <el-input v-model="form.xmrzRzjg" placeholder="请输入融资机构" :disabled=isFormShow />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="签约日期" prop="xmrzQyrq" :disabled=isFormShow >
                <el-date-picker clearable      style="width: 100%"
                                v-model="form.xmrzQyrq"
                                type="date"
                                format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择签约日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="融资经理" prop="xmrzRzfzr">
                <el-input v-model="form.xmrzRzfzr" placeholder="请输入融资经理" :disabled=isFormShow />
              </el-form-item>
            </el-col>
              </el-row>
               <el-row>
             <el-col :span="8">
               <el-form-item label="融资金额" prop="xmrzRzze">
                 <el-input v-model="form.xmrzRzze" placeholder="请输入融资金额" :disabled=isFormShow />
               </el-form-item>
             </el-col>

             <el-col :span="8">
               <el-form-item label="首年IRR" prop="xmrzSnirr">
                 <el-input v-model="form.xmrzSnirr" placeholder="请输入首年IRR" :disabled=isFormShow />
               </el-form-item>
             </el-col>

            <el-col :span="8">
              <el-form-item label="全周期综合IRR" prop="xmrzQzqzhirr">
                <el-input v-model="form.xmrzQzqzhirr" placeholder="请输入全周期综合irr" :disabled=isFormShow />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="已放款" prop="xmrzYfkze">
                <el-input v-model="form.xmrzYfkze" placeholder="请输入已放款" :disabled=isFormShow />
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="未放款" prop="xmrzWfkze">
                <el-input v-model="form.xmrzWfkze" placeholder="请输入未放款" :disabled=isFormShow />
              </el-form-item>
            </el-col>

            <!-- <el-col :span="8">
                       <el-form-item label="融资情况" prop="xmrzRzqk">
                         <el-input v-model="form.xmrzRzqk" placeholder="请输入融资情况" :disabled=isFormShow />
                       </el-form-item>
            </el-col> -->
			<el-col :span="8">
			  <el-form-item label="融资情况" prop="xmrzRzqk">
			    <el-select v-model="form.xmrzRzqk" placeholder="请输入融资情况"   clearable style="width: 100%">
			      <el-option
			        v-for="dict in xmqkRzjgList"
			        :key="dict.id"
			        :label="dict.zdName"
			        :value="dict.id"
			      />
			    </el-select>
			  </el-form-item>
			</el-col>
            <el-col :span="8">
              <el-form-item label="预计下笔放款时间" prop="xmrzYjxbfksj" :disabled=isFormShow >
                <el-date-picker clearable      style="width: 100%"
                                v-model="form.xmrzYjxbfksj"
                                type="date"
                                format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择预计下笔放款时间">
                </el-date-picker>
              </el-form-item>
            </el-col>


             <el-col :span="8">
               <el-form-item label="下笔放款金额" prop="xmrzYjxbfk">
                 <el-input v-model="form.xmrzYjxbfk" placeholder="请输入下笔放款金额" :disabled=isFormShow />
               </el-form-item>
             </el-col>

             <el-col :span="8">
                        <el-form-item label="下笔放款要求，是否满足？" prop="xmrzIsxcfk">
                          <el-input v-model="form.xmrzIsxcfk" placeholder="请输入下笔放款要求，是否满足？(是/否)" :disabled=isFormShow />
                        </el-form-item>
             </el-col>

			 <el-col :span="8">
						<el-form-item label="融资风险提示" prop="xmrzFxts">
						  <el-input v-model="form.xmrzFxts" placeholder="请输入融资风险提示" :disabled=isFormShow />
						</el-form-item>
			 </el-col>

                 <el-col :span="8">
                   <el-form-item label="手续费收取方式" prop="xmrzSxfsqfs">
                     <el-input v-model="form.xmrzSxfsqfs" placeholder="请输入手续费收取方式" :disabled=isFormShow />
                   </el-form-item>
                 </el-col>
          </el-row>







          <!--        <div  class="margin-div">-->
          <!--          <el-divider></el-divider>-->
          <!--          <span class="fenggx">还款情况:</span>-->
          <!--        </div>-->
          <!--        <el-row>-->
          <!--          <el-col :span="8">-->
          <!--            <el-form-item label="成本" prop="xmrzCb">-->
          <!--              <el-input v-model="form.xmrzCb" placeholder="成本" :disabled=isFormShow />-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <!--          <el-col :span="8">-->
          <!--            <el-form-item label="利息" prop="xmrzLx">-->
          <!--              <el-input v-model="form.xmrzLx" placeholder="请输入利息" :disabled=isFormShow />-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <!--          <el-col :span="8">-->
          <!--            <el-form-item label="手续费" prop="xmrzSxf">-->
          <!--              <el-input v-model="form.xmrzSxf" placeholder="请输入手续费" :disabled=isFormShow />-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <!--        </el-row>-->

          <!--        <el-row>-->
          <!--          <el-col :span="8">-->
          <!--            <el-form-item label="还款保证金" prop="xmrzHkbzj">-->
          <!--              <el-input v-model="form.xmrzHkbzj" placeholder="还款保证金" :disabled=isFormShow />-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <!--        </el-row>-->

        </el-form>
        <div  class="save-style-div" v-if=isHoldShow>
          <div  class="save-style" >
            <el-button type="primary" @click="submitForm"  v-if=!isFormShow  >保 存</el-button>
          </div>
        </div>

      </div>
     <!-- <div>

        <el-tabs v-model="activeName" @tab-click="handleClickAdd"   class="xuanzq">

          <el-tab-pane  label="预计融资" name="yjrz">

          </el-tab-pane>

          <el-tab-pane label="实际融资" name="sjrz"    >

          </el-tab-pane>
        </el-tabs>

      </div> -->

      <div  class="margin-div">
        <!--        <el-divider></el-divider>-->
        <span class="fenggx">放款情况时间表(金额单位-万元):</span>
      </div>

      <div>
        <paymentTable  :msg_id = msg_idv   :mgs_get_data = mgs_get_data  :project_id = msg_xmid>   </paymentTable>
      </div>
      <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">还款情况时间表(金额单位-万元):</span>
      </div>
      <repaymentTable  :msg_id = msg_idv  :mgs_get_data = mgs_get_data  :project_id = msg_xmid></repaymentTable>

      <div slot="footer" class="dialog-footer">
        <!--        <el-button type="primary" @click="submitForm"  v-if=!isFormShow >确 定</el-button>-->
        <el-button @click="cancel">返 回</el-button>
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
  import {financingImport, listFinancing, getFinancing, delFinancing, addFinancing, updateFinancing,importBox } from "@/api/system/financing";
	import {listDictionary} from "@/api/system/dictionaryData";


  import { getToken } from "@/utils/auth";
  import paymentTable  from "@/views/system/public/paymentTable";
  import repaymentTable  from "@/views/system/public/repaymentTable.vue";

  export default {
    name: "Financing",
    components: {
      paymentTable,
      repaymentTable
    },
    props: {
      //项目ID
      msg_xmid:String,
      msg_status:String,
    },
    data() {
      return {
        activeName: 'yjrz',
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
        isHoldShow:true,
        msg_idv:'',
        mgs_get_data:'0',
        isFormShow:false,
        isOperationShow:true,
        isOperationShowAdd:true,
        isOperationShowEdit:true,
        fileList: [],
        fileType: [ "pdf", "doc", "docx", "xls", "xlsx","txt","png","jpg", "bmp", "jpeg"],
        fileSize: 50,
        fileLimit: 5,
        headers: { "Content-Type": "multipart/form-data" },
        //////////////////
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
        // 项目融资表格数据
        financingList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          xmrzProjectId: null,
          xmrzRzjg: null,
          xmrzQyrq: null,
          xmrzRzfzr: null,
          xmrzRzze: null,
          xmrzYfkze: null,
          xmrzYjxbfk: null,
          xmrzBzj: null,
          xmrzRzll: null,
          xmrzSxfsqfs: null,
          xmrzSnirr: null,
          xmrzQzqzhirr: null,
          xmrzRzqk: null,
          xmrzXbfktj: null,
          isDelete: null
        },
		xmqkRzjgList:[],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          // xmrzRzjg: [
          //   {required: true, trigger: "blur", message: "融资机构不能为空"}
          // ],
          //
          // xmrzQyrq: [
          //   {required: true, trigger: "blur", message: "签约日期不能为空"}
          // ],
          xmrzRzze: [
            // {required: true, trigger: "blur", message: "融资总额不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmrzYfkze: [
            // {required: true, trigger: "blur", message: "已放款总额不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmrzYjxbfk: [
            // {required: true, trigger: "blur", message: "预计下笔放款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmrzBzj: [
            // {required: true, trigger: "blur", message: "保证金不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmrzRzll: [
            // {required: true, trigger: "blur", message: "融资利率不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmrzSxfsqfs: [
            // {required: true, trigger: "blur", message: "手续费收取方式不能为空"},
            {
              // pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              // message: "请输入正确金额,最多两位小数",
              // trigger: "blur"
            }
          ],
          xmrzSnirr: [
            // {required: true, trigger: "blur", message: "首年IRR不能为空"}
          ],
          xmrzQzqzhirr: [
            // {required: true, trigger: "blur", message: "全周期综合IRR不能为空"}
          ],

          xmrzXbfktj: [
            // {required: true, trigger: "blur", message: "下笔放款条件不能为空"},
            // {
            //   pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
            //   message: "请输入正确金额,最多两位小数",
            //   trigger: "blur"
            // }
          ],
          xmrzRzfzr: [
            // {required: true, trigger: "blur", message: "融资负责人不能为空"}
          ]
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
		this.getListDir(108);
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
      }
    },
    methods: {
		getListDir(pid) {
		  listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
			  console.log(response)
		    this.xmqkRzjgList = response.rows;
		  });
		},
      handleClickAdd(tab, event) {
        this.mgs_get_data=tab.index
        console.log(tab.index);
        console.log(tab, event);
      },
      //下载模板
      importTemplate() {
        // this.download('/system/statistics/downloadTemplate', {
        // }, `终端箱导入表${new Date().getTime()}.xlsx`)

        this.download('/system/financing/downloadTemplate', {
        }, `项目融资${new Date().getTime()}.xlsx`)

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
      //////////////////////
      /** 查询项目融资列表 */
      getList() {
        this.loading = true;
        this.queryParams.xmrzProjectId=this.msg_xmid
        listFinancing(this.queryParams).then(response => {
          this.financingList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
        this.getList();
      },
      // 表单重置
      reset() {
        this.form = {
          xmrzId: null,
          xmrzProjectId: null,
          xmrzRzjg: null,
          xmrzQyrq: null,
          xmrzRzfzr: null,
          xmrzRzze: null,
          xmrzYfkze: null,
          xmrzYjxbfk: null,
          xmrzBzj: null,
          xmrzRzll: null,
          xmrzSxfsqfs: null,
          xmrzSnirr: null,
          xmrzQzqzhirr: null,
          xmrzRzqk: null,
          xmrzXbfktj: null,
		  xmrzFxts:null,
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
        this.ids = selection.map(item => item.xmrzId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.mgs_get_data='0'
        this.activeName='yjrz'
        this.isHoldShow=true
        this.isFormShow=false
        this.reset();
        this.open = true;
        this.msg_idv="-200"
        this.title = "添加项目融资";
      },
      //查看
      handleLook(row) {
        this.mgs_get_data='0'
        this.activeName='yjrz'
        this.isFormShow=true
        this.reset();
        const xmrzId = row.xmrzId || this.ids
        this.msg_idv=xmrzId+""
        getFinancing(xmrzId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "查看项目融资";
        });
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.mgs_get_data='0'
        this.activeName='yjrz'
        this.isFormShow=false
        this.reset();
        const xmrzId = row.xmrzId || this.ids
        this.msg_idv=xmrzId+""
        getFinancing(xmrzId).then(response => {
		  console.log(response)
          this.form = response.data;
          this.open = true;
          this.title = "修改项目融资";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.xmrzId != null) {
              this.form.xmrzProjectId=this.msg_xmid
              updateFinancing(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {

              if(this.msg_xmid===""){ //项目不存在时不能保存

                this.$modal.msgError("先添加项目在保存！！！");
                return
              }
              this.form.xmrzProjectId=this.msg_xmid
              addFinancing(this.form).then(response => {
                if(response.msg==='200'){
                  this.msg_idv=response.data+""
                  this.$modal.msgSuccess("新增成功");
                  this.isHoldShow=false
                }
                // this.open = false;
                // this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const xmrzIds = row.xmrzId || this.ids;
        this.$modal.confirm('是否确认删除项目融资编号为"' + xmrzIds + '"的数据项？').then(function() {
          return delFinancing(xmrzIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/financing/export', {
          ...this.queryParams
        }, `项目融资_${new Date().getTime()}.xlsx`)
      }
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
  .save-style{
    /*margin-bottom: 40px;*/
    /* width: 100%; */
    /* height: 100%; */
    margin-right: 80px;
    float: right;
  }
  .save-style-div {

    margin-top: 10px;
    margin-bottom: 70px;
  }


  .xuanzq {
    margin-left: 15px
  }


</style>
