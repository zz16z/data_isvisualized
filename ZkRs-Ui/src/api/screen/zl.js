import request from '@/utils/request'

function tansParams(params) {
  let result = ''
  for (const propName of Object.keys(params)) {
    const value = params[propName];
    var part = encodeURIComponent(propName) + "=";
    if (value !== null && value !== "" && typeof (value) !== "undefined") {
      if (typeof value === 'object') {
        for (const key of Object.keys(value)) {
          if (value[key] !== null && value[key] !== "" && typeof (value[key]) !== 'undefined') {
            let params = propName + '[' + key + ']';
            var subPart = encodeURIComponent(params) + "=";
            result += subPart + encodeURIComponent(value[key]) + "&";
          }
        }
      } else {
        result += part + encodeURIComponent(value) + "&";
      }
    }
  }
  return result
}


//总览左边项目分类接口
export const getText = (params) => {
  return request({
    url: '/api/home/overview/projectType',
    method: 'get',
    params
  })
}

//总览左边项目现状接口
export const getProjectSituation = (params) => {
  return request({
    url: '/api/home/overview/projectXz',
    method: 'get',
    params
  })
}

//总览左侧项目建设情况接口
export const getConstructionSituation = (params) => {
  return request({
    url: '/api/home/overview/projectJsqk',
    method: 'get',
    params
  })
}

//项目现状和项目建设情况详情接口
export const getXzAndJsqkDetalis = (params) => {
  return request({
    url: '/api/home/overview/projectXmqklb',
    method: 'get',
    params
  })
}

//总览headedr第1行
export const getLatestData = (params) => {
  return request({
    url: '/api/home/overview/projectZl',
    method: 'get',
    params
  })
}

//总览中间项目公司情况
export const getProjectUnitSituation = (params) => {
  return request({
    url: '/api/home/overview/projectXmfdlxq',
    method: 'get',
    params
  })
}


//电量数值面板
export const getFdqkObject = (params) => {
  return request({
    url: '/api/home/overview/projectXmfdlxq',
    method: 'get',
    params
  })
}

//电量实际收入
export const getFdqkSjsr = (params) => {
  return request({
    url: '/api/home/overview/projectXmsjsr',
    method: 'get',
    params
  })
}
//电量实际支出
export const getFdqkSjzc = (params) => {
  return request({
    url: '/api/home/overview/projectXmfdlxqsjzc',
    method: 'get',
    params
  })
}


//项目公司
export const getXmgsList = (params) => {
  return request({
    url: '/api/home/projectCapitalManagement/projectElectricityList',
    method: 'get',
    params
  })
}
//项目经营数据情况弹框三个接口
//实际付款情况
export const getSjTotal = (params) => {
  return request({
    url: '/api/home/overview/projectInfoTotal',
    method: 'get',
    params
  })
}

//实际付款情况
export const getSjfkqk = (params) => {
  return request({
    url: '/api/home/overview/projectJysjSjfkqk',
    method: 'get',
    params
  })
}

//实际收款情况
export const getSjskqk = (params) => {
  return request({
    url: '/api/home/overview/projectJysjSjskqk',
    method: 'get',
    params
  })
}

//预计经营情况
export const getXmjyqk = (params) => {
  return request({
    url: '/api/home/overview/projectJysjYjjyqk',
    method: 'get',
    params
  })
}

//总览中间项目经营情况
export const getProjectBusinessSit = (year) => {
  console.log(year,'参数')
  return request({
    url: `/api/home/overview/projectJysj`,
    method: 'get',
    params:year
  })
}

//总览右侧 工期预警
export const getProjectGqyj = (params) => {
  return request({
    url: `/api/home/overview/projectGqyj`,
    method: 'get',
    params
  })
}

//总览右侧 资金预警
export const getProjectZjyj = (params) => {
  return request({
    url: `/api/home/overview/projectZjyj`,
    method: 'get',
    params
  })
}

//总览右侧 资金预警数据列表
export const getProjectZjyjList = (params) => {
  return request({
    url: `/api/home/overview/projectZjyjxq`,
    method: 'get',
    params
  })
}

//总览右侧 出售预警
export const getProjectCsyj = (params) => {
  return request({
    url: `/api/home/overview/projectCsyj`,
    method: 'get',
    params
  })
}

//总览右侧 融资预警
export const projectRzyj = (params) => {
  return request({
    url: `api/home/overview/projectRzyj`,
    method: 'get',
    params
  })
}

//总览右侧 项目履约分公司
export const getLyfgs = () => {
  return request({
    url: `/api/home/overview/projectlyfgs`,
    method: 'get'
  })
}

/**分公司管理*/

//分公司下拉框
export const getSubsidiaryData = (id) => {
  return request({
    url: `/api/Dictionaries/List?id=${id}`,
    method: 'get'
  })
}


//分公司现金流情况
export const fgsXjlqk = (params) => {
  return request({
    url: `api/home/overview/projectXjlqk`,
    method: 'get',
    params
  })
}

/**项目管理*/
//项目管理模块
export const xmglQuery = (params) => {
  let parameter
  if(params){
    parameter = tansParams(params).slice(0, -1)
  }
  return request({
    url: `api/home/projectManagement/projectXmgl?` + parameter,
    method: 'post'
  })
}

//项目清单
export const xmqdQuery = (params) => {
  return request({
    url: `api/home/overview/projectInfoList`,
    method: 'get',
    params
  })
}


/**资金管理*/
//项目建设收支情况
export const xmjsSzqk = () => {
  return request({
    url: 'api/home/projectCapitalManagement/projectJsqk',
    method: 'get'
  })
}

//资金统计
export const xmjsStatistical = () => {
  return request({
    url: 'api/home/projectCapitalManagement/projectZjTj',
    method: 'get'
  })
}

//分公司收款情况情况
export const fgsSkqk = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectFgsskqk',
    method: 'get',
    params
  })
}

//分公司收款情况情况
export const xmlxCharts = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectFgxm',
    method: 'get',
    params
  })
}


//融资情况
export const rzqkCharts = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectZjRzqk',
    method: 'get',
    params
  })
}

//现金流情况统计数据
export const xjlTitle = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectXjlqkHj',
    method: 'get',
    params
  })
}

//现金流情况
export const xjlCharts = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectZjXjlqk',
    method: 'get',
    params
  })
}


//建设项目收支and分公司收款情况
export const xmjsAnd = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectJsxmszqk',
    method: 'get',
    params
  })
}


//资金还款
export const getZjhkList = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectZjHk',
    method: 'get',
    params
  })
}

//融资情况
export const getRzqkList = (params) => {
  return request({
    url: 'api/home/projectCapitalManagement/projectZjXmrz',
    method: 'get',
    params
  })
}


