import request from '@/utils/request'

// 查询现金流入流出列表
export function listCash(query) {
  return request({
    url: '/system/cash/list',
    method: 'get',
    params: query
  })
}

// 查询现金流入流出详细
export function getCash(id) {
  return request({
    url: '/system/cash/' + id,
    method: 'get'
  })
}

// 新增现金流入流出
export function addCash(data) {
  return request({
    url: '/system/cash',
    method: 'post',
    data: data
  })
}

// 修改现金流入流出
export function updateCash(data) {
  return request({
    url: '/system/cash',
    method: 'put',
    data: data
  })
}

// 删除现金流入流出
export function delCash(id) {
  return request({
    url: '/system/cash/' + id,
    method: 'delete'
  })
}
