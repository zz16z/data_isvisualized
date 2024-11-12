<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="180px">
      <el-form-item label="项目id" prop="xjlsjqkProjectId" hidden="hidden">
        <el-input v-model="form.xjlsjqkProjectId" placeholder="请输入项目id"  />
      </el-form-item>
      <el-form-item label="预计现金流关联id" prop="xjlsjqkYjxjlId" hidden="hidden">
        <el-input v-model="form.xjlsjqkYjxjlId" placeholder="请输入项目id" />
      </el-form-item>
      <div  class="margin-div">
        <span class="fenggx">资金情况(万元):</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="应收账款总额" prop="xjlsjqkXmyszkze">
            <el-input v-model="form.xjlsjqkXmyszkze" placeholder="请输入应收账款总额" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="已收预收购款" prop="xjlsjqkSk">
            <el-input v-model="form.xjlsjqkSk" placeholder="请输入已收预收购款" :disabled=isFormShow />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="已收融资款" prop="xjlsjqkRzk">
            <el-input v-model="form.xjlsjqkRzk" placeholder="请输入已收融资款" :disabled=isFormShow />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="已收工程款（业主回款）" prop="xjlsjqkGck">
            <el-input v-model="form.xjlsjqkGck" placeholder="请输入已收工程款（业主回款）" :disabled=isFormShow />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="已收电费" prop="xjlsjqkDfsk">
            <el-input v-model="form.xjlsjqkDfsk" placeholder="请输入已收电费" :disabled=isFormShow />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="实际收款总额" prop="xjlsjqkSrHj">
            <el-input v-model="form.xjlsjqkSrHj" placeholder="请输入实际收款总额" :disabled=isFormShow />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="应收账款余额" prop="xjlsjqkYsye">
            <el-input v-model="form.xjlsjqkYsye" placeholder="请输入应收账款余额" :disabled=isFormShow />
          </el-form-item>
        </el-col>


         <el-col :span="8">
           <el-form-item label="应付账款总额" prop="xjlsjqkXmyfzkzje">
             <el-input v-model="form.xjlsjqkXmyfzkzje" placeholder="请输入应付账款总额" :disabled=isFormShow  />
           </el-form-item>
         </el-col>


         <el-col :span="8">
           <el-form-item label="已付设备款（万元）" prop="xjlsjqkSbk">
             <el-input v-model="form.xjlsjqkSbk" placeholder="请输入已付设备款（万元）" :disabled=isFormShow />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="已付建安款（万元）" prop="xjlsjqkJak">
             <el-input v-model="form.xjlsjqkJak" placeholder="请输入已付建安款（万元）" :disabled=isFormShow />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="已付其他款项（万元）" prop="xjlsjqkQt">
             <el-input v-model="form.xjlsjqkQt" placeholder="请输入已付其他款项（万元）" :disabled=isFormShow />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="实际付款总额（万元）" prop="xjlsjqkZcHj">
             <el-input v-model="form.xjlsjqkZcHj" placeholder="请输入实际付款总额（万元）" :disabled=isFormShow  />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="实际应付未付款（万元）" prop="xjlsjqkYfwsk">
             <el-input v-model="form.xjlsjqkYfwsk" placeholder="请输入实际应付未付款（万元）" :disabled=isFormShow />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="应付账款余额（万元）" prop="xjlsjqkYfzkye">
             <el-input v-model="form.xjlsjqkYfzkye" placeholder="请输入应付账款余额（万元）" :disabled=isFormShow />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="已付履约保证金（万元）" prop="xjlsjqkLybzj">
             <el-input v-model="form.xjlsjqkLybzj" placeholder="请输入已付履约保证金（万元）" :disabled=isFormShow  />
           </el-form-item>
         </el-col>

         <el-col :span="8">
           <el-form-item label="已退履约保证金（万元）" prop="xjlsjqkLybzjBack">
             <el-input v-model="form.xjlsjqkLybzjBack" placeholder="已退履约保证金（万元）" :disabled=isFormShow  />
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
    </el-form>


    <div  class="save-style"  v-if=isHoldShow >
      <el-button type="primary" @click="submitForm1" v-if=!isFormShow >保 存</el-button>
    </div>
  </div>
</template>

<script>
  import {addCash, updateCash,getCash,getCashGl} from "@/api/system/actualCash";

  export default {
    name: "addActual",
    props: {
      //项目id
      msg_xmid_itemv:String,
      //操作更新变量
      msg_isFormShowv:Boolean,
      //预计与实际表关联id
      msg_yjxjl_item:String,

    },
    data() {
      return {

        isHoldShow:true,
        yjxjl_id:'',
        isFormShow:false,
        // 表单参数

        form : {

        },
        // 表单校验
        rules: {
          xjlsjqkSk : [
            // { required: true, message: "收款额不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkRzk: [
            // { required: true, message: "融资款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkDfsk: [
            // { required: true, message: "电费收款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkSrHj: [
            // { required: true, message: "实际收入合计不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkSbk: [
            // { required: true, message: "设备款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkJak: [
            // { required: true, message: "建安款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkZcHj: [
            // { required: true, message: "实际支出合计不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkYfwsk: [

            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkLybzj: [
            //  { required: true, message: "履约保证金不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkQt: [
            //  { required: true, message: "其他款项不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkGck: [
            // { required: true, message: "工程款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkXmyszkze: [
            // { required: true, message: "工程款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],
          xjlsjqkXmyfzkzje: [
            // { required: true, message: "工程款不能为空", trigger: "blur" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ]
        }

      }
    },
    watch:{
      // msg_isFormShowv() {
      //   this.isFormShow= this.msg_isFormShowv
      //
      // }
      msg_yjxjl_item(valHal,val){
        this.yjxjl_id=this.msg_yjxjl_item
        this.getCashData(this.yjxjl_id)
      }
    },
    created() {
      this.isFormShow = this.msg_isFormShowv
    },
    methods: {
      getCashData(id) {
        getCashGl({"id":id}).then(response => {
          if( response.data||response.data!=null){
            this.form = response.data;
          }

        });
      },
      submitForm1() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.xjlsjqkId != null) {
              this.form.xjlsjqkProjectId=this.msg_xmid_itemv
              updateCash(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");

              });
            } else {

              if(this.msg_xmid_itemv===""||this.yjxjl_id===""){ //项目不存在时不能保存
                this.$modal.msgError("先添加预计现金流！！！");
                return
              }
              this.form.xjlsjqkProjectId=this.msg_xmid_itemv
              this.form.xjlsjqkYjxjlId=this.yjxjl_id
              addCash(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.isHoldShow=false
              });
            }
          }
        });
      }
    },

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
