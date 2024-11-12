import request from '@/utils/request'

// 查询融资放款列表
export function listLoan(query) {
  return request({
    url: '/system/loan/list',
    method: 'get',
    params: query
  })
}

// 查询融资放款列表
export function listLoanInfo(query) {
  return request({
    url: '/system/loan/listInfo',
    method: 'get',
    params: query
  })
}

// 新增融资时间
export function addDateString(data) {
  return request({
    url: '/system/loan/addList',
    method: 'post',
    data: data
  })
}

// 查询融资放款详细
export function getLoan(financingId) {
  return request({
    url: '/system/loan/' + financingId,
    method: 'get'
  })
}

// 新增融资放款
export function addLoan(data) {
  return request({
    url: '/system/loan',
    method: 'post',
    data: data
  })
}

// 修改融资放款
export function updateLoan(data) {
  return request({
    url: '/system/loan',
    method: 'put',
    data: data
  })
}

// 删除融资放款
export function delLoan(id) {
  return request({
    url: '/system/loan/' + id,
    method: 'delete'
  })
}
