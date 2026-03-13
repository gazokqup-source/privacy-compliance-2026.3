import request from './request'

// 获取规则列表
export function getRuleList(params) {
  return request.get('/rules/list', { params })
}

// 新增规则
export function addRule(data) {
  return request.post('/rules/add', data)
}

// 修改规则
export function updateRule(data) {
  return request.put('/rules/update', data)
}

// 删除规则
export function deleteRule(id) {
  return request.delete(`/rules/${id}`)
}
