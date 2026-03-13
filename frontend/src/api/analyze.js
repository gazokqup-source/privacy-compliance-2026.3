import request from './request'

// 启动分析任务
export function startAnalyze(data) {
  return request.post('/analyze/start', data)
}

// 获取分析结果
export function getAnalyzeResult(taskId) {
  return request.get(`/analyze/${taskId}`)
}

// 获取历史分析记录
export function getAnalyzeHistory(params) {
  return request.get('/analyze/history', { params })
}

// 删除分析记录
export function deleteAnalyze(taskId) {
  return request.delete(`/analyze/${taskId}`)
}
