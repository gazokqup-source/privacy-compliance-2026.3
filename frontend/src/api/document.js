import request from './request'

// 上传文件
export function uploadDocument(file) {
  const formData = new FormData()
  formData.append('file', file)
  return request.post('/document/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 提交文本
export function submitDocumentText(data) {
  return request.post('/document/text', data)
}

// 获取文档详情
export function getDocument(id) {
  return request.get(`/document/${id}`)
}

// 获取文档条款列表
export function getDocumentClauses(id) {
  return request.get(`/document/${id}/clauses`)
}

// 获取文档列表
export function getDocumentList(params) {
  return request.get('/document/list', { params })
}
