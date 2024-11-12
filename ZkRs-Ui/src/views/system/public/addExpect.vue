<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="150px">
      <el-form-item label="项目id" prop="xjlyjqkProjectId" hidden="hidden">
        <el-input v-model="form.xjlyjqkProjectId" placeholder="请输入项目id" />
      </el-form-item>

      <el-form-item label="预计现金流关联id" prop="xjlsjqkYjxjlId" hidden="hidden">
        <el-input v-model="form.xjlsjqkYjxjlId" />
      </el-form-item>
      <div  class="margin-div">
        <span class="fenggx">日期(实际添加日期):</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="日期" prop="xjlyjqkRq">
            <el-date-picker clearable  style="width: 100%" :disabled=isFormShow
                            v-model="form.xjlyjqkRq"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <div  class="margin-div">
        <span class="fenggx">预计经营情况（万元）:</span>
      </div>
      <el-row>
       <!-- <el-col :span="8">
          <el-form-item label="日期" prop="xjlyjqkRq">
            <el-date-picker clearable  style="width: 100%" :disabled=isFormShow
                            v-model="form.xjlyjqkRq"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择日期">
            </el-date-picker>
          </el-form-item>
        </el-col> -->
        <el-col :span="8">
          <el-form-item label="预计出售总价" prop="xjlyjqkYjcszj" label-width="150px">
            <el-input v-model="form.xjlyjqkYjcszj" placeholder="请输入预计出售总价" :disabled=isFormShow />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="EPC结算价" prop="xjlyjqkYjepcjsj" label-width="150px">
            <el-input v-model="form.xjlyjqkYjepcjsj" placeholder="请输入EPC结算价" :disabled=isFormShow />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
         <el-col :span="8">
           <el-form-item label="预计收入合计" prop="xjlyjqkSrHjAll" label-width="150px">
             <el-input v-model="form.xjlyjqkSrHjAll" placeholder="请输入预计收入合计" :disabled=isFormShow />
           </el-form-item>
         </el-col>
         <el-col :span="8">
           <el-form-item label="预计项目公司成本" prop="xjlyjqkYjxmgscb" label-width="150px">
             <el-input v-model="form.xjlyjqkYjxmgscb" placeholder="请输入预计项目公司成本" :disabled=isFormShow />
           </el-form-item>
         </el-col>
         <el-col :span="8">
           <el-form-item label="项目EPC总成本" prop="xjlyjqkYjecpcb" label-width="170px">
             <el-input v-model="form.xjlyjqkYjecpcb" placeholder="项目EPC总成本" :disabled=isFormShow />
           </el-form-item>
         </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="项目总成本" prop="xjlyjqkYjxmcb" label-width="150px">
            <el-input v-model="form.xjlyjqkYjxmcb" placeholder="项目总成本" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="EPC利润" prop="xjlyjqkYjlr" label-width="150px">
          <el-input v-model="form.xjlyjqkYjlr" placeholder="请输入EPC利润" :disabled=isFormShow />
        </el-form-item>
        </el-col>
      </el-row>

   <!--   <el-row>
                <el-col :span="8">
                  <el-form-item label="资源费" prop="xjlyjqkDfsk">
                    <el-input v-model="form.xjlyjqkDfsk" placeholder="请输入资源费" :disabled=isFormShow />
                  </el-form-item>
                </el-col>
      </el-row> -->

      <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">预计收入(万元):</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="融资款" prop="xjlyjqkRzk">
            <el-input v-model="form.xjlyjqkRzk" placeholder="请输入融资款" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="工程款" prop="xjlyjqkGck">
            <el-input v-model="form.xjlyjqkGck" placeholder="请输入工程款" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="其他（收购款）" prop="xjlyjqkSk">
            <el-input v-model="form.xjlyjqkSk" placeholder="请输入预收购款" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="收入合计" prop="xjlyjqkSrHj">
            <el-input v-model="form.xjlyjqkSrHj" placeholder="请输入收入合计" :disabled=isFormShow />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
<!--        <el-col :span="8">-->
<!--          <el-form-item label="电费收款" prop="xjlyjqkDfsk">-->
<!--            <el-input v-model="form.xjlyjqkDfsk" placeholder="请输入电费收款" :disabled=isFormShow />-->
<!--          </el-form-item>-->
<!--        </el-col>-->

      </el-row>

      <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">预计支出(万元):</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="设备费" prop="xjlyjqkSbk">
            <el-input v-model="form.xjlyjqkSbk" placeholder="请输入设备费" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="建安费" prop="xjlyjqkJak">
            <el-input v-model="form.xjlyjqkJak" placeholder="请输入建安费" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        </el-row>
         <el-row>
          <el-col :span="8">
            <el-form-item label="经营管理支出" prop="xjlyjqkQtkx">
              <el-input v-model="form.xjlyjqkQtkx" placeholder="经营管理支出" :disabled=isFormShow />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="履约保证金" prop="xjlyjqkBzj">
              <el-input v-model="form.xjlyjqkBzj" placeholder="请输入履约保证金" :disabled=isFormShow />
            </el-form-item>
          </el-col>
        </el-row>
      <el-row>
        <el-col :span="16">
          <el-form-item label="支出总计" prop="xjlyjqkZcHj">
            <el-input v-model="form.xjlyjqkZcHj" placeholder="请输入支出总计" :disabled=isFormShow />
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>

    <div  class="save-style"  v-if=isHoldShow >
      <el-button type="primary" @click="submitForm"  v-if=!isFormShow  >保 存</el-button>
    </div>
  </div>
</template>

<script>
  import {addCash, updateCash,getCash} from "@/api/system/expectCash";

  export default {
    name: "addExpect",
    props: {
      //项目id
      msg_xmid_itemv:String,
      msg_isFormShowv:Boolean,
      msg_operation_idv:String
    },
    data() {
      return {
        queryParamss:{
          xjlyjqkProjectId:null,
          xjlyjqkId:null
        },
        isHoldShow:true,
        isFormShow:false,
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          xjlyjqkRq: [
            {required: true, trigger: "blur", message: "日期不能为空"}
          ],
          xjlyjqkYjcszj: [
           // {required: true, trigger: "blur", message: "已经出售总价不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkYjecpcb: [
         //   {required: true, trigger: "blur", message: "预计EPC成本不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkYjxmgscb: [
          //  {required: true, trigger: "blur", message: "预计项目公司成本不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkYjlr: [
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkYjepcjsj: [
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],

          xjlyjqkSk: [
           // {required: true, trigger: "blur", message: "收款额不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkRzk: [
           // {required: true, trigger: "blur", message: "融资款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkGck: [
          //  {required: true, trigger: "blur", message: "工程款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkDfsk: [
           // {required: true, trigger: "blur", message: "电费收款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkSrHjAll: [

            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkSbk: [
           // {required: true, trigger: "blur", message: "设备款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkJak: [
          //  {required: true, trigger: "blur", message: "建安款不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkQtkx: [
          //  {required: true, trigger: "blur", message: "其他款项不能为空"},
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlyjqkZcHj: [

            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ]
        }

      }
    },
    created() {
      this.isFormShow = this.msg_isFormShowv
        if(this.msg_operation_idv!=''){
          this.getCashData()
        }


    },
    watch:{
      msg_isFormShowv() {
        this.isFormShow= this.msg_isFormShowv
        // this.queryParamss.xjlyjqkProjectId=this.msg_xmid_itemv
      },


      msg_operation_idv() {
        //加载数据
        // this.getCashData()

      }

    },
    methods: {
      getCashData() {
        getCash(this.msg_operation_idv).then(response => {
          console.log(response)
          this.form = response.data;
          this.$emit("child_ysxjl_parent",response.data.xjlsjqkYjxjlId+"")
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.xjlyjqkId != null) {
              this.form.xjlyjqkProjectId=this.msg_xmid_itemv
              updateCash(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");


              });
            } else {

              if(this.msg_xmid_itemv===""){ //项目不存在时不能保存
                this.$modal.msgError("先添加项目在保存！！！");
                return
              }

              this.form.xjlyjqkProjectId=this.msg_xmid_itemv
              addCash(this.form).then(response => {
                if(response.msg==="200"){
                  this.$emit("child_ysxjl_parent",response.data+"")
                  this.$modal.msgSuccess("新增成功");
                  this.isHoldShow=false

                }else{
                  this.$modal.msgError("新增失败")
                }

              });
            }
          }
        });
      }
    }

  }
</script>


<style scoped>
  .save-style{
    margin-bottom: 30px;
    /* width: 100%; */
    /* height: 100%; */
    margin-right: 80px;
    float: right;
  }
  .fenggx{
    color: black;
    font-size: 22px;

  }
  .margin-div {
    margin-bottom: 19px;
  }
</style>
