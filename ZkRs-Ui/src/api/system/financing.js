import request from '@/utils/request'

// 查询项目融资列表
export function listFinancing(query) {
  return request({
    url: '/system/financing/list',
    method: 'get',
    params: query
  })
}

// 查询项目融资详细
export function getFinancing(xmrzId) {
  return request({
    url: '/system/financing/' + xmrzId,
    method: 'get'
  })
}

// 新增项目融资
export function addFinancing(data) {
  return request({
    url: '/system/financing',
    method: 'post',
    data: data
  })
}

// 修改项目融资
export function updateFinancing(data) {
  return request({
    url: '/system/financing',
    method: 'put',
    data: data
  })
}

// 删除项目融资
export function delFinancing(xmrzId) {
  return request({
    url: '/system/financing/' + xmrzId,
    method: 'delete'
  })
}







// 新增项目融资
export function financingImport(data) {
  return request({
    url: '/system/financing/import',
    method: 'post',
    data: data
  })
}



export function importBox(data) {
  return request({
    url: '/system/financing/importBox',
    method: 'post',
    data: data
  })
}


