<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <el-form-item label="项目id" prop="xmqkProjectId" hidden="hidden">
        <el-input v-model="form.xmqkProjectId" placeholder="请输入项目id" />
      </el-form-item>

      <el-row>
        <el-col :span="8">
          <el-form-item label="项目名称" prop="xmqkXmmc">
            <el-input v-model="form.xmqkXmmc" placeholder="请输入项目名称"   />
          </el-form-item>
        </el-col>


        <el-col :span="8">
          <el-form-item label="项目类型" prop="xmqkXmlx">
            <el-select v-model="form.xmqkXmlx" placeholder="请输入项目类型"   @change="selectXmlx"  clearable style="width: 100%">
              <el-option
                v-for="dict in xmqkXmlxList"
                :key="dict.id"
                :label="dict.zdName"
                :value="dict.id"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="容量(MW)" prop="xmqkRl">
            <el-input v-model="form.xmqkRl" placeholder="请输入容量(MW)" />
          </el-form-item>
        </el-col>

        </el-row>

        <el-row>
        <el-col :span="8">
          <el-form-item label="项目现状" prop="xmqkXmxz">
            <el-select v-model="form.xmqkXmxz" placeholder="请输入项目现状" clearable style="width: 100%">
              <el-option
                v-for="dict in xmqkXmxzList"
                :key="dict.id"
                :label="dict.zdName"
                :value="dict.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <!--          <el-form-item label="建设状态" prop="xmqkJszt">-->
          <!--            <el-input v-model="form.xmqkJszt" placeholder="请输入建设状态" />-->
          <!--          </el-form-item>-->

          <el-form-item label="建设状态" prop="xmqkJszt">
            <el-select v-model="form.xmqkJszt" placeholder="请输入建设状态"  @change="selectJszt" clearable style="width: 100%">
              <el-option
                v-for="dict in xmqkJsztList"
                :key="dict.id"
                :label="dict.zdName"
                :value="dict.id"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="履约分公司" prop="xmqkLyfgs">
            <el-select v-model="form.xmqkLyfgs" placeholder="请输入履约分公司"  @change="selectJszt" clearable style="width: 100%">
              <el-option
                v-for="dict in xmqkLyfgsList"
                :key="dict.id"
                :label="dict.zdName"
                :value="dict.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
         </el-row>
        <el-row>
        <el-col :span="8">
          <el-form-item label="区域" prop="xmqkQy">
            <el-input v-model="form.xmqkQy" placeholder="请输入区域" />
<!--            <el-select v-model="form.xmqkQy" placeholder="请输入区域"    clearable style="width: 100%">-->
<!--              <el-option-->
<!--                v-for="dict in xmqkQyList"-->
<!--                :key="String(dict.id)"-->
<!--                :label="dict.zdName"-->
<!--                :value="String(dict.id)"-->
<!--              />-->
<!--            </el-select>-->
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="动工时间" prop="xmqkHtsj">
            <el-date-picker clearable   style="width: 100%"
                            v-model="form.xmqkHtsj"
                            type="month"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择动工时间">
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="并网时间" prop="xmqkBwsj">
            <el-date-picker clearable   style="width: 100%"
                            v-model="form.xmqkBwsj"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择并网时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="融资方" prop="xmqkRzf">
            <el-input v-model="form.xmqkRzf" placeholder="请输入融资方" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="收购方" prop="xmqkSgf">
            <el-input v-model="form.xmqkSgf" placeholder="请输入收购方" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="业主方" prop="xmqkHzf">
            <el-input v-model="form.xmqkHzf" placeholder="请输入业主方" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="融资经理" prop="xmqkRzjl">
            <el-input v-model="form.xmqkRzjl" placeholder="请输入融资经理" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="商务经理" prop="xmqkSwjl">
            <el-input v-model="form.xmqkSwjl" placeholder="请输入商务经理" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>

       <!-- <el-col :span="8">
          <el-form-item label="项目当前产值" prop="xmqkXmdqz">
            <el-input v-model="form.xmqkXmdqz" placeholder="请输入项目当前产值" />
          </el-form-item>
        </el-col> -->
      </el-row>

      <el-row   >
        <el-col :span="8">
          <el-form-item label="应出售日期" prop="xmqkYcsrq">
            <el-date-picker clearable   style="width: 100%"
                            v-model="form.xmqkYcsrq"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择应出售日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="预警原因 " prop="xmqkYjyy">
            <el-input v-model="form.xmqkYjyy" placeholder="请输入预警原因" />
          </el-form-item>
        </el-col>

       <!-- <el-col :span="8">
          <el-form-item label="设备到货率 " prop="xmqkSbdhl">
            <el-input v-model="form.xmqkSbdhl" placeholder="请输入设备到货率" />
          </el-form-item>
        </el-col> -->
      </el-row>


<!--      <el-row   v-if="fdxmShow">
        <el-col :span="8">
          <el-form-item label="风机厂商" prop="xmqkFjcs">
            <el-input v-model="form.xmqkFjcs" placeholder="请输入风机厂商" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="塔筒厂商 " prop="xmqkTtcs">
            <el-input v-model="form.xmqkTtcs" placeholder="请输入塔筒厂商" />
          </el-form-item>
        </el-col>
      </el-row>


      <el-row  v-if="gfxmShow">
        <el-col :span="8">
          <el-form-item label="组件厂商" prop="xmqkZjcs">
            <el-input v-model="form.xmqkZjcs" placeholder="请输入组件厂商" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="支架厂商" prop="xmqkZjiacs">
            <el-input v-model="form.xmqkZjiacs" placeholder="请输入支架厂商" />
          </el-form-item>
        </el-col>
      </el-row> -->


<!--
      <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">施工情况:</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="设备到货" prop="xmqkSbdh">
            <el-input v-model="form.xmqkSbdh" placeholder="请输入设备到货" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="设备安装" prop="xmqkSbaz">
            <el-input v-model="form.xmqkSbaz" placeholder="请输入设备安装" />
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="外线施工" prop="xmqkWxsg">
            <el-input v-model="form.xmqkWxsg" placeholder="请输入外线施工" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="升压站开挖" prop="xmqkSyzkw">
            <el-input v-model="form.xmqkSyzkw" placeholder="请输入升压站开挖" />
          </el-form-item>
        </el-col>

        <el-col :span="8"  v-if="isjckwShow">
          <el-form-item label="基础开挖">
            <el-input v-model="form.xmqkJckw" placeholder="请输入基础开挖" />
          </el-form-item>
        </el-col>

        <el-col :span="8"  v-if="iszjShow">
          <el-form-item label="在建设情况" prop="xmqkJsqk">
            <el-input v-model="form.xmqkJsqk" placeholder="请输入在建设情况" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="施工进度" prop="xmqkSgjd">
            <el-input v-model="form.xmqkSgjd" placeholder="请输入施工进度" />
          </el-form-item>
        </el-col>


      </el-row>
 -->

      <!--      <div  class="margin-div">-->
      <!--        <el-divider></el-divider>-->
      <!--        <span class="fenggx">预计收入情况:</span>-->
      <!--      </div>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="调整预计出售单价(元/千瓦)" prop="xmqkTzyjcsdj">-->
      <!--            <el-input v-model="form.xmqkTzyjcsdj" placeholder="请输入调整预计出售单价(元/千瓦)" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="预计出售总价" prop="xmqkYjcszj">-->
      <!--            <el-input v-model="form.xmqkYjcszj" placeholder="请输入预计出售总价" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="EPC结算价 " prop="xmqkEpcJsj">-->
      <!--            <el-input v-model="form.xmqkEpcJsj" placeholder="请输入EPC结算价 " />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->
      <!--      <el-row>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="增加EPC结算价" prop="xmqkEpcZjjsj">-->
      <!--            <el-input v-model="form.xmqkEpcZjjsj" placeholder="请输入增加EPC结算价" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="预计收入合计" prop="xmqkYjsyhj">-->
      <!--            <el-input v-model="form.xmqkYjsyhj" placeholder="请输入预计收入合计" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->

      <!--      <div  class="margin-div">-->
      <!--        <el-divider></el-divider>-->
      <!--        <span class="fenggx">成本情况:</span>-->
      <!--      </div>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="项目公司成本(万元)" prop="xmqkXmgscb">-->
      <!--            <el-input v-model="form.xmqkXmgscb" placeholder="请输入项目公司成本" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="项目EPC成本(万元)" prop="xmqkXmepccb">-->
      <!--            <el-input v-model="form.xmqkXmepccb" placeholder="请输入项目EPC成本" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->

      <!--          <el-form-item label="其中资源费(万元)" prop="xmqkQzzyf">-->
      <!--            <el-input v-model="form.xmqkQzzyf" placeholder="请输入其中资源费" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->

      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="总成本(万元)" prop="xmqkZcb">-->
      <!--            <el-input v-model="form.xmqkZcb" placeholder="请输入总成本" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->

      <!--      <div  class="margin-div">-->
      <!--        <el-divider></el-divider>-->
      <!--        <span class="fenggx">实际收款情况:</span>-->
      <!--      </div>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已收预收购款" prop="xmqkSjYsysgk">-->
      <!--            <el-input v-model="form.xmqkSjYsysgk" placeholder="请输入已收预收购款" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已收融资款" prop="xmqkYsrzk">-->
      <!--            <el-input v-model="form.xmqkYsrzk" placeholder="请输入已收融资款" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="工程回款(业主回款)" prop="xmqkGzk">-->
      <!--            <el-input v-model="form.xmqkGzk" placeholder="请输入工程回款(业主回款)" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已收电费" prop="xmqkYsdf">-->
      <!--            <el-input v-model="form.xmqkYsdf" placeholder="请输入已收电费" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="实际收入合计" prop="xmqkSjsrhj">-->
      <!--            <el-input v-model="form.xmqkSjsrhj" placeholder="请输入实际收入合计" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="应收余额" prop="xmqkYsye">-->
      <!--            <el-input v-model="form.xmqkYsye" placeholder="请输入应收余额" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->

      <!--      <div  class="margin-div">-->
      <!--        <el-divider></el-divider>-->
      <!--        <span class="fenggx">实际付款情况:</span>-->
      <!--      </div>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已付设备款(万元)" prop="xmqkYfsbk">-->
      <!--            <el-input v-model="form.xmqkYfsbk" placeholder="请输入已付设备款" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已付建安款(万元)" prop="xmqkYfjak">-->
      <!--            <el-input v-model="form.xmqkYfjak" placeholder="请输入已付建安款" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已付其他款项(万元)" prop="xmqkYfqtkx">-->
      <!--            <el-input v-model="form.xmqkYfqtkx" placeholder="请输入已付其他款项" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->

      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="已付总金额(万元)" prop="xmqkYfzje">-->
      <!--            <el-input v-model="form.xmqkYfzje" placeholder="请输入已付总金额" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="实际应付未付款项(万元)" prop="xmqkSjyfwfkx">-->
      <!--            <el-input v-model="form.xmqkSjyfwfkx" placeholder="请输入实际应付未付款项" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->

      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="应收余额(万元)" prop="xmqkYssjye">-->
      <!--            <el-input v-model="form.xmqkYssjye" placeholder="请输入应收余额" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->
      <!--      </el-row>-->
      <!--      <div  class="margin-div">-->
      <!--        <el-divider></el-divider>-->
      <!--        <span class="fenggx">合同支付余额:</span>-->
      <!--      </div>-->
      <!--      <el-row>-->
      <!--        <el-col :span="8">-->
      <!--          <el-form-item label="合同支付余额" prop="xmqkHtzfye">-->
      <!--            <el-input v-model="form.xmqkHtzfye" placeholder="请输入合同支付余额" />-->
      <!--          </el-form-item>-->
      <!--        </el-col>-->


      <!--      </el-row>-->
      <!--      <div  class="margin-div">
              <el-divider></el-divider>
              <span class="fenggx">履约保证金支付情况:</span>
            </div>
            <el-row>
              <el-col :span="8">
                <el-form-item label="已付履约保证金" prop="xmqkYflybzj">
                  <el-input v-model="form.xmqkYflybzj" placeholder="请输入已付履约保证金" />
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="已退履约保证金" prop="xmqkYtlybzj">
                  <el-input v-model="form.xmqkYtlybzj" placeholder="请输入已退履约保证金" />
                </el-form-item>
              </el-col>
            </el-row> -->


  <!--    <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">合同支付余额:</span>
      </div>
      <el-row>
        <el-col :span="8">
          <el-form-item label="合同支付余额" prop="xmqkQzzyf">
            <el-input v-model="form.xmqkQzzyf" placeholder="请输入合同支付余额" />
          </el-form-item>
        </el-col>
      </el-row> -->

     <!-- <div  class="margin-div">
        <el-divider></el-divider>
        <span class="fenggx">备注:</span>
      </div> -->
      <el-form-item label="备注" prop="xmqkRemark">
        <el-input v-model="form.xmqkRemark" type="textarea" placeholder="请输入内容"/>
      </el-form-item>
    </el-form>

    <div  class="save-style"  v-if=isHoldShow>
      <el-button type="primary" @click="submitForm"  v-if="isSaveShow" >保 存</el-button>
    </div>

  </div>
</template>

<script>
  import { getInfo, addInfo, updateInfo ,check} from "@/api/system/info";
  import {listDictionary} from "@/api/system/dictionaryData";
  export default {
    //     filters:{
    //       filter_xmlx(oldVal){
    //         this.xmqkXmlxList.forEach((item) => {
    //           newArr.push(arr[item])
    //           console.log(newArr)
    //         })
    // //oldVal.toLowerCase().split('').reverse().join('')
    //         return oldVal.toLowerCase().split('').reverse().join('')
    //       }
    //     },
    name: "addProject",
    props: {
      //获取父组件给的项目ID值
      msg_xmid:String,
      msg_status:String
    },
    data() {
      const checkByname = (rule, value, callback) => {
        if(this.operationStatus==='1'){ //修改时不验证
          callback();
        }
        else {
          let FormDatas = new FormData()
          FormDatas.append("xmqkXmmc", value)
          check(FormDatas).then(response => {

            if (response.msg === '-1') {
              callback(new Error(response.data + ":项目名称重复请重新输入"));

            } else {
              callback();
            }
          });
        }

      };
      return {
        iszjShow:false,
        isjckwShow:false,
        fdxmShow:false,
        gfxmShow:false,

        isHoldShow:true,
        isSaveShow:true,
        //项目类型字典
        xmqkXmlxList:[],
        xmqkXmxzList:[],
        xmqkJsztList:[],
        xmqkLyfgsList:[],
        xmqkQyList:[],
        child_string_parent:'',
        operationStatus:'',
        // 表单参数
        form: {},
        // 表单校验
        rules: {

          xmqkXmmc: [
            { required: true, trigger: "blur", message: "项目名称不能为空" },

            { required: true, validator: checkByname, trigger: "blur" }

          ],
          xmqkRl: [
            //   { required: true, trigger: "blur", message: "容量不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkXmlx: [
            //      { required: true, trigger: "blur", message: "项目类型不能为空" }
          ],

          xmqkXmxz: [
            //   { required: true, trigger: "blur", message: "项目现状不能为空" }
          ],

          xmqkLyfgs: [
            //    { required: true, trigger: "blur", message: "履约分公司不能为空" }
          ],
          xmqkJszt: [
            //   { required: true, trigger: "blur", message: "建设状态不能为空" }
          ],
          xmqkQy: [
            //  { required: true, trigger: "blur", message: "区域不能为空" }
          ],
          xmqkHtsj: [
             // { required: true, trigger: "blur", message: "动工时间不能为空" }
          ],
          xmqkBwsj: [
            //   { required: true, trigger: "blur", message: "并网时间不能为空" }
          ],

          xmqkRzf: [
            //  { required: true, trigger: "blur", message: "融资方不能为空" }
          ],

          xmqkSgf: [
            //       { required: true, trigger: "blur", message: "收购方不能为空" }
          ],


          xmqkHzf: [
            //    { required: true, trigger: "blur", message: "收合作方不能为空" }
          ],

          xmqkRzjl: [
            //    { required: true, trigger: "blur", message: "融资经理不能为空" }
          ],

          xmqkSwjl: [
            //     { required: true, trigger: "blur", message: "商务经理不能为空" }
          ],

          xmqkXmdqz: [
            //     { required: true, trigger: "blur", message: "项目当前产值不能为空" }
          ],

          xmqkSyzkw: [
            //  { required: true, trigger: "blur", message: "升压站开挖不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))?$/,
              message: "请输入整数",
              trigger: "blur"
            }
          ],


          xmqkJckw: [
            //    { required: true, trigger: "blur", message: "基础开挖不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))?$/,
              message: "请输入整数",
              trigger: "blur"
            }
          ],

          // xmqkJckw: [
          //   //    { required: true, trigger: "blur", message: "基础开挖不能为空" }
          //   {
          //     pattern: /^[+-]?(0|([1-9]\d*))?$/,
          //     message: "请输入整数",
          //     trigger: "blur"
          //   }
          // ],
          //基础开挖
          // xmqkJckw: [
          //   //    { required: true, trigger: "blur", message: "基础开挖不能为空" }
          //   {
          //     pattern: /^[+-]?(0|([1-9]\d*))?$/,
          //     message: "请输入整数",
          //     trigger: "blur"
          //   }
          // ],
          xmqkSbdhl:[
            {
              pattern: /^[+-]?(0|([1-9]\d*))?$/,
              message: "请输入整数",
              trigger: "blur"
            }
          ],
          xmqkJsqk: [
            //      { required: true, trigger: "blur", message: "在建设情况不能为空" }
          ],


          xmqkSbdh: [
            //    { required: true, trigger: "blur", message: "设备到货不能为空" }
          ],

          xmqkSbaz: [
            //     { required: true, trigger: "blur", message: "设备安装不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))?$/,
              message: "请输入整数",
              trigger: "blur"
            }
          ],
          xmqkWxsg: [
            //       { required: true, trigger: "blur", message: "外线施工不能为空" }
            {
              pattern: /^[+-]?(0|([1-9]\d*))?$/,
              message: "请输入整数",
              trigger: "blur"
            }
          ],

          xmqkTzyjcsdj: [
            //    { required: true, trigger: "blur", message: "调整预计出售单价不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkYjcszj: [
            //    { required: true, trigger: "blur", message: "预计出售总价不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkEpcJsj: [
            //  { required: true, trigger: "blur", message: "EPC结算价不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkEpcZjjsj: [
            //  { required: true, trigger: "blur", message: "增加EPC结算价不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkYjsyhj: [
            //  { required: true, trigger: "blur", message: "预计收入合计不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkXmgscb: [
            //    { required: true, trigger: "blur", message: "项目公司成本不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkXmepccb: [
            //  { required: true, trigger: "blur", message: "项目EPC成本不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkQzzyf: [
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkZcb: [
            //   { required: true, trigger: "blur", message: "总成本不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkSjYsysgk: [
            //   { required: true, trigger: "blur", message: "已收预收购款不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYsrzk: [
            //  { required: true, trigger: "blur", message: "已收融资款不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkGzk: [
            //  { required: true, trigger: "blur", message: "工程回款(业主回款)不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYsdf: [
            //  { required: true, trigger: "blur", message: "已收电费不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkSjsrhj: [
            //  { required: true, trigger: "blur", message: "实际收入不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],


          xmqkYsye: [
            //   { required: true, trigger: "blur", message: "应收余额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYfsbk: [
            //    { required: true, trigger: "blur", message: "已付设备款不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYfjak: [
            //   { required: true, trigger: "blur", message: "已付建安款不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkYfqtkx: [
            //  { required: true, trigger: "blur", message: "已付其他款项不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkYfzje: [
            //   { required: true, trigger: "blur", message: "已付总金额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYsye1: [
            //   { required: true, trigger: "blur", message: "应收余额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkSjyfwfkx: [
            //  { required: true, trigger: "blur", message: "实际应付未付款项不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],

          xmqkHtzfye: [
            // { required: true, trigger: "blur", message: "合同支付余额不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ],
          xmqkYflybzj: [
            // { required: true, trigger: "blur", message: "已付履约保证金不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,4})?$/,
              message: "请输入正确金额,最多四位小数",
              trigger: "blur"
            }
          ],

          xmqkYtlybzj: [
            //  { required: true, trigger: "blur", message: " 已退履约保证金不能为空" },
            {
              pattern: /^[+-]?(0|([1-9]\d*))(\.\d{1,2})?$/,
              message: "请输入正确金额,最多两位小数",
              trigger: "blur"
            }
          ]

        }

      }
    },
    created() {
      this.isOperation()
      //项目现状
      this.getListXmxzDir( 7)
      //项目类型
      this.getListDir(8)
      //建设状态
      this.getListJsztDir(9)
      //履约分公司
      this.getListlyfgs(50)
     //区域
     //  this.getxmqkQyList(105)
    },
    // mounted(){
    //
    //
    // },

    methods: {

      lodShow1(){
        if(this.form.xmqkXmlx==30){//风电项目
          this.fdxmShow=true
          this.gfxmShow=false
          this.isjckwShow=true
          return
        }
        if(this.form.xmqkXmlx==31){//光伏项目
          this.gfxmShow=true
          this.fdxmShow=false
          this.isjckwShow=false
          return
        }
        this.gfxmShow=false
        this.fdxmShow=false
        this.isjckwShow=false

      } ,lodShow2(){
        if(this.form.xmqkJszt==32){//在建
          if( this.fdxmShow){
            this.isjckwShow=true
          }
          this.iszjShow=true
          return
        }
        this.isjckwShow=false
        this.iszjShow=false
      },
      selectJszt(v){ debugger
        if(v==32){//在建
          if( this.fdxmShow){
            this.isjckwShow=true
          }
          this.iszjShow=true
          return
        }
        this.isjckwShow=false
        this.iszjShow=false
      },

      selectXmlx(v){
        if(v==30){//风电项目
          this.fdxmShow=true
          this.gfxmShow=false
          this.isjckwShow=true
          return
        }
        if(v==31){//光伏项目
          this.gfxmShow=true
          this.fdxmShow=false
          this.isjckwShow=false
          return
        }
        this.gfxmShow=false
        this.fdxmShow=false
        this.isjckwShow=false


      },
      isOperation(){
        ////获取跳转参数：0新增、1修改、2查看  xmqkProjectId项目ID， operationStatus操作状态
        this.operationStatus= this.$route.query.operationStatus
        if(parseInt(this.operationStatus)>0){ //获取项目ID
          //获取项目id
          let  id=this.$route.query.xmqkProjectId
          this.handleUpdate(id)
          this.$emit("child_string_parent",id+"",this.operationStatus+"")
          if(this.operationStatus==='2'){
            this.isSaveShow=false
          }
        }else {
          this.$emit("child_string_parent","",this.operationStatus+"")
        }


      },
      //加载编辑数据
      handleUpdate(id) {
        getInfo(id).then(response => {
          this.form = response.data;
          this.lodShow1()
          this.lodShow2()
        });
      },
      getListDir( pid) {//
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkXmlxList = response.rows;
        });
      },

      getListXmxzDir( pid) { //7
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkXmxzList = response.rows;
        });
      },
      getListJsztDir( pid) { //9
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkJsztList = response.rows;
        });
      },
      getListlyfgs( pid) { //50
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkLyfgsList = response.rows;
        });
      },
      getxmqkQyList( pid) { //区域
        listDictionary({"isCatalogue":"3","pId":pid}).then(response => {
          this.xmqkQyList = response.rows;

        });
      },
      click_trigger(){
        //设置项目id，传给父组件
        this.child_string_parent='测试'
        this.$emit("child_string_parent",this.child_string_parent)
      },

      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {

          if (valid) {
            if (this.form.xmqkProjectId != null) {
              updateInfo(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");

              });
            } else {
              addInfo(this.form).then(response => {
                if(response.msg==='200'){
                  this.$modal.msgSuccess("新增成功");
                  //获取项目id
                  this.$emit("child_string_parent", response.data)
                  this.isHoldShow=false


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
    margin-bottom: 35px;
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
