<template>
  <div class="result-container">
    <el-card>
      <template #header>
        <div class="result-header">
          <h3>审查结果报告</h3>
          <el-button @click="goBack">返回</el-button>
        </div>
      </template>
      
      <!-- 总体风险评估 -->
      <el-alert
        :title="overallRiskText"
        :type="overallRiskType"
        :closable="false"
        show-icon
        style="margin-bottom: 20px"
      />
      
      <el-descriptions title="文档信息" :column="2" border>
        <el-descriptions-item label="文档标题">{{ mockData.documentTitle }}</el-descriptions-item>
        <el-descriptions-item label="分析时间">{{ mockData.analyzeTime }}</el-descriptions-item>
        <el-descriptions-item label="条款数量">{{ mockData.clauseCount }}</el-descriptions-item>
        <el-descriptions-item label="总体风险评分">
          <el-tag :type="riskScoreType">{{ mockData.totalRiskScore }}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
      
      <!-- 条款级审查结果 -->
      <h4 style="margin: 20px 0 10px">条款级审查结果</h4>
      
      <el-collapse accordion>
        <el-collapse-item
          v-for="(clause, index) in mockData.clauseResults"
          :key="index"
          :title="`条款 ${index + 1}: ${clause.riskLevel}`"
        >
          <div class="clause-content">
            <div class="clause-text">
              <strong>原文：</strong>
              <p>{{ clause.clauseText }}</p>
            </div>
            
            <el-divider />
            
            <div class="clause-result">
              <el-descriptions :column="1" size="small">
                <el-descriptions-item label="风险类别">{{ clause.modelLabel }}</el-descriptions-item>
                <el-descriptions-item label="置信度">{{ (clause.modelScore * 100).toFixed(1) }}%</el-descriptions-item>
                <el-descriptions-item label="命中规则">{{ clause.ruleHit }}</el-descriptions-item>
                <el-descriptions-item label="法律依据">{{ clause.lawReference }}</el-descriptions-item>
                <el-descriptions-item label="修改建议">
                  <p style="color: #e6a23c">{{ clause.suggestion }}</p>
                </el-descriptions-item>
              </el-descriptions>
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
      
      <div style="margin-top: 20px; text-align: center">
        <el-button type="primary" @click="exportReport">导出报告</el-button>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

// 模拟数据（第一阶段用假数据）
const mockData = ref({
  documentTitle: '某 APP 隐私政策',
  analyzeTime: '2026-03-13 13:40:00',
  clauseCount: 5,
  totalRiskScore: 78.5,
  finalRiskLevel: 'HIGH',
  clauseResults: [
    {
      clauseNo: 1,
      clauseText: '我们可能收集您的个人信息用于相关服务。',
      modelLabel: '信息收集目的不明确',
      modelScore: 0.86,
      ruleHit: '命中规则：收集目的描述模糊',
      riskLevel: 'MEDIUM',
      lawReference: '个人信息保护法 第 17 条',
      suggestion: '建议明确说明收集目的、范围和使用场景。'
    },
    {
      clauseNo: 2,
      clauseText: '我们可能会与第三方共享您的信息。',
      modelLabel: '共享规则不明确',
      modelScore: 0.92,
      ruleHit: '命中规则：共享规则缺失必要信息',
      riskLevel: 'HIGH',
      lawReference: '个人信息保护法 第 23 条',
      suggestion: '建议明确说明共享的第三方类型、共享目的和信息类型。'
    },
    {
      clauseNo: 3,
      clauseText: '您有权查询、更正您的个人信息。',
      modelLabel: '用户权利说明不足',
      modelScore: 0.78,
      ruleHit: '命中规则：用户权利描述不完整',
      riskLevel: 'MEDIUM',
      lawReference: '个人信息保护法 第 44 条',
      suggestion: '建议补充说明删除、撤回同意等权利及行使方式。'
    },
    {
      clauseNo: 4,
      clauseText: '我们将采取合理的安全措施保护您的信息。',
      modelLabel: '安全措施描述模糊',
      modelScore: 0.71,
      ruleHit: '命中规则：安全措施描述过于笼统',
      riskLevel: 'LOW',
      lawReference: '个人信息保护法 第 51 条',
      suggestion: '建议具体说明采取的技术和管理措施。'
    },
    {
      clauseNo: 5,
      clauseText: '本政策自发布之日起生效。',
      modelLabel: '生效日期明确',
      modelScore: 0.95,
      ruleHit: '无',
      riskLevel: 'LOW',
      lawReference: '-',
      suggestion: '该条款符合规范要求。'
    }
  ]
})

const overallRiskText = computed(() => {
  const level = mockData.value.finalRiskLevel
  if (level === 'HIGH') return '总体风险评估：高风险 - 存在多项严重合规问题，建议立即整改'
  if (level === 'MEDIUM') return '总体风险评估：中风险 - 存在部分合规问题，建议尽快整改'
  return '总体风险评估：低风险 - 基本符合规范要求'
})

const overallRiskType = computed(() => {
  const level = mockData.value.finalRiskLevel
  if (level === 'HIGH') return 'error'
  if (level === 'MEDIUM') return 'warning'
  return 'success'
})

const riskScoreType = computed(() => {
  const score = mockData.value.totalRiskScore
  if (score >= 70) return 'danger'
  if (score >= 40) return 'warning'
  return 'success'
})

const goBack = () => {
  router.push('/home/upload')
}

const exportReport = () => {
  ElMessage.success('报告导出功能开发中...')
}
</script>

<style scoped>
.result-container {
  max-width: 1000px;
  margin: 0 auto;
}

.result-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.clause-content {
  padding: 10px;
}

.clause-text p {
  background-color: #f5f7fa;
  padding: 10px;
  border-radius: 4px;
  margin: 5px 0;
}

.clause-result {
  margin-top: 10px;
}
</style>
