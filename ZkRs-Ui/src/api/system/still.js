import request from '@/utils/request'

// // 查询还款情况列表
// export function listStill(query) {
//   return request({
//     url: '/system/still/list',
//     method: 'get',
//     params: query
//   })
// }

// // 查询还款情况详细
// export function getStill(id) {
//   return request({
//     url: '/system/still/' + id,
//     method: 'get'
//   })
// }

// // 新增还款情况
// export function addStill(data) {
//   return request({
//     url: '/system/still',
//     method: 'post',
//     data: data
//   })
// }

// // 修改还款情况
// export function updateStill(data) {
//   return request({
//     url: '/system/still',
//     method: 'put',
//     data: data
//   })
// }

// // 删除还款情况
// export function delStill(id) {
//   return request({
//     url: '/system/still/' + id,
//     method: 'delete'
//   })
// }


///////////////////////////////////////



// 查询还款情况列表
export function listDate(query) {
  return request({
    url: '/system/still/list',
    method: 'get',
    params: query
  })
}

// 查询融资时间详细
export function getDate(id) {
  return request({
    url: '/system/still/' + id,
    method: 'get'
  })
}

// 新增融资时间
export function addDate(data) {
  return request({
    url: '/system/still',
    method: 'post',
    data: data
  })
}

// 修改融资时间
export function updateDate(data) {
  return request({
    url: '/system/still',
    method: 'put',
    data: data
  })
}

// 删除融资时间
export function delDate(id) {
  return request({
    url: '/system/still/' + id,
    method: 'delete'
  })
}


// 新增融资时间
export function addDateString(data) {
  return request({
    url: '/system/still/addList',
    method: 'post',
    data: data
  })
}
// 导入
export function importBox(data) {
  return request({
    url: '/system/still/importBox',
    method: 'post',
    data: data
  })
}

