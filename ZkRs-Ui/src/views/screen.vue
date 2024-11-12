
<template>
  <div class="dashboard-editor-container">
    <div class='header'>
      <div class="screenBtn">
                <svg-icon :icon-class="isFullscreen?'exit-fullscreen':'fullscreen'"@click="creen" />
      </div>
      <div class="module_title">
<!--        <p class="header_title text-fradient">-->
<!--          <h5>商务金融中心项目管控平台</h5>-->
<!--        </p>-->
        <h5 class="text-fradient">商务金融中心项目管控平台</h5>
        <div class="moduleBtn">
          <div class="mun-btn">
            <router-link class="nav_btn" :class="this.$route.name === 'overview' ? 'active' : 'lose'" :to="{name: 'overview'}">总览</router-link>
            <router-link class="nav_btn" :class="this.$route.name === 'subsidiary' ? 'active' : 'lose'" :to="{name: 'subsidiary'}">分公司管理</router-link>
          </div>
          <div class="mun-btn">
            <router-link class="nav_btn" :class="this.$route.name === 'project' ? 'active' : 'lose'" :to="{name: 'project'}">项目管理</router-link>
            <router-link class="nav_btn" :class="this.$route.name === 'money' ? 'active' : 'lose'" :to="{name: 'money'}">资金管理</router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="visualization" :class="h" ref="visualization">
      <router-view :visualizationHeight="visualizationHeight"  ref="screenModel"></router-view>
    </div>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import screenfull from "screenfull";
import screem from './visualizationScreen/overview'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    screem
  },
  watch:{
    $route(to,from){
      console.log(to);
      if(to.name === 'project'){
        this.refresh()
      }
    }
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
      h:{
        height:`${window.innerHeight - 118}px`
      },
      fullscreen:false,
      isFullscreen: false,
      visualizationHeight:''
    }
  },
  mounted() {
    //this.init()
    let that = this
    window.onresize = () => {
      return (() => {
        that.visualizationHeight = that.$refs.visualization.offsetHeight
      })()
    }

    this.$refs.screenModel.initHeight(that.$refs.visualization.offsetHeight)
  },
  beforeDestroy() {
    this.destroy()
  },
  methods: {
    refresh(){
     this.$nextTick(()=>{
       this.$refs.screenModel.initHeight(this.$refs.visualization.offsetHeight)
     })
    },

    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    creen(){
      console.log(123)
      let fullscreen = false;
      let element = document.documentElement;

      // 判断是否已经是全屏
      // 如果是全屏，退出
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
        // console.log('已还原！');
      } else {    // 否则，进入全屏
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
        // console.log('已全屏！');
      }
      // 改变当前全屏状态
      this.fullscreen = !this.fullscreen;
      this.$store.dispatch('settings/changeSetting', {
        key: 'controlMenuHidden',
        value: !this.fullscreen
      })

    },
    change() {
      this.isFullscreen = screenfull.isFullscreen
    },
    init() {
      if (screenfull.isEnabled) {
        screenfull.on('change', this.change)
      }
    },
    destroy() {
      if (screenfull.isEnabled) {
        screenfull.off('change', this.change)
      }
    }
  }
}
</script>

<style lang="scss" scoped>

@import "../assets/styles/adapter";
.screenfull-svg {
  display: inline-block;
  cursor: pointer;
  fill: #5a5e66;;
  width: 20px;
  height: 20px;
  vertical-align: 10px;
}

.dashboard-editor-container {
  //padding: 32px;
  /*background-color: rgb(240, 242, 245);*/
  color: #c1cbcb;
  position: relative;
  background: url("../assets/bg.jpg") no-repeat;
  background-size: 100% 100%;
  .header{
    height: 118px;
    background: url("../assets/screen/tb.png") no-repeat;
    background-size: 100% 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-end;
    .screenBtn{
      height: 50px;
      width: 50px;
      padding: 10px;
      display: flex;
      justify-content: flex-end;
    }
    .module_title{
      height: vh(69);
      width: 100%;
      .moduleBtn{
        width: 100%;
        margin-top: -15px;
        display: flex;
        justify-content: space-around;
        .mun-btn{
          width: vw(300);
          display: flex;
          justify-content: space-around;
          .nav_btn{
            width: vw(126);
            height: vh(40);
            text-align: center;
            color: #ffffff;
            padding: fontSize(10) fontSize(20);
            font-size: fontSize(13);
            background-size: 100% 100%;
          }
        }
      }
    }
  }
  .visualization{
    height: calc(100vh - 118px);
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

.active{
  background: url("../assets/screen/dh2.png") no-repeat;
}
.lose{
  background: url("../assets/screen/dh1.png") no-repeat;
}

.text-fradient{
  margin: 0;padding: 0;
  font-size: 2rem;
  font-weight: bold;
  text-align: center;
  background-image: linear-gradient(to bottom,#d5dfff, #6abcff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  cursor: default;
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
