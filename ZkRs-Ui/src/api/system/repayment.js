import request from '@/utils/request'

// 查询融资还款列表
export function listRepayment(query) {
  return request({
    url: '/system/repayment/list',
    method: 'get',
    params: query
  })
}
export function addDateString(data) {
  return request({
    url: '/system/repayment/addList',
    method: 'post',
    data: data
  })
}
export function listRepaymentInfo(query) {
  return request({
    url: '/system/repayment/listInfo',
    method: 'get',
    params: query
  })
}
// 查询融资还款详细
export function getRepayment(financingId) {
  return request({
    url: '/system/repayment/' + financingId,
    method: 'get'
  })
}

// 新增融资还款
export function addRepayment(data) {
  return request({
    url: '/system/repayment',
    method: 'post',
    data: data
  })
}

// 修改融资还款
export function updateRepayment(data) {
  return request({
    url: '/system/repayment',
    method: 'put',
    data: data
  })
}

// 删除项目融资
export function delRepayment(xmrzId) {
  return request({
    url: '/system/repayment/' + xmrzId,
    method: 'delete'
  })
}
