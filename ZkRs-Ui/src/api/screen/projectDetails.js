import request from '@/utils/request'

//项目管理二级页面

//右则预计情况and预计收入and预计支出
export const leftConditionsQuery = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmyjqkxq',
    method: 'get',
    params
  })
}

//右则实际收入and实际支出
export const leftActual = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmsjqkxq',
    method: 'get',
    params
  })
}

//项目现金流，统计数据
export const xmxjlTitle = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmXjl',
    method: 'get',
    params
  })
}

//项目现金流，EchartsData 月
export const xmxjMonthCharts = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmYf',
    method: 'get',
    params
  })
}

//项目现金流，EchartsData 年
export const xmxjYearCharts = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmNf',
    method: 'get',
    params
  })
}

//右则电量统计and电费收入
export const dltjAndDfsr = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmDltj',
    method: 'get',
    params
  })
}

//右则项目融资
export const xmrz = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmRztji',
    method: 'get',
    params
  })
}


//中间数据看板
export const dataSTAT = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmglbtsj',
    method: 'get',
    params
  })
}

//融资情况弹框数据统计
export const rzqkDataSTATTitle = (params) => {
  return request({

    url: '/api/home/projectManagement/projectXmRzxq',
    method: 'get',
    params
  })
}

//融资情况弹框放款情况
export const rzqkDataFkqkTitle = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmRzxqFkqk',
    method: 'get',
    params
  })
}

//融资情况弹框资金还款
export const rzqkDataZjhkTitle = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmRzxqHkqk',
    method: 'get',
    params
  })
}

//施工情况（风电）
export const sgqkFdData = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmGsqk',
    method: 'get',
    params
  })
}

//施工情况（光伏）
export const sgqkGfData = (params) => {
  return request({
    url: '/api/home/projectManagement/projectXmGsqkGf',
    method: 'get',
    params
  })
}
