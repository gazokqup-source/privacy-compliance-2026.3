package com.example.privacy.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyzeService {
    
    /**
     * 获取模拟的分析结果（第一阶段用假数据）
     */
    public Map<String, Object> getMockResult(Long taskId) {
        Map<String, Object> result = new HashMap<>();
        result.put("taskId", taskId);
        result.put("documentId", 1L);
        result.put("totalRiskScore", 78.5);
        result.put("finalRiskLevel", "HIGH");
        result.put("summary", "该隐私协议存在用户权利说明不足、共享规则不明确等问题，建议人工复核。");
        
        // 条款级结果
        List<Map<String, Object>> clauseResults = new ArrayList<>();
        
        // 条款 1
        Map<String, Object> clause1 = new HashMap<>();
        clause1.put("clauseNo", 1);
        clause1.put("clauseText", "我们可能收集您的个人信息用于相关服务。");
        clause1.put("modelLabel", "信息收集目的不明确");
        clause1.put("modelScore", 0.86);
        clause1.put("ruleHit", "命中规则：收集目的描述模糊");
        clause1.put("riskLevel", "MEDIUM");
        clause1.put("lawReference", "个人信息保护法 第 17 条");
        clause1.put("suggestion", "建议明确说明收集目的、范围和使用场景。");
        clauseResults.add(clause1);
        
        // 条款 2
        Map<String, Object> clause2 = new HashMap<>();
        clause2.put("clauseNo", 2);
        clause2.put("clauseText", "我们可能会与第三方共享您的信息。");
        clause2.put("modelLabel", "共享规则不明确");
        clause2.put("modelScore", 0.92);
        clause2.put("ruleHit", "命中规则：共享规则缺失必要信息");
        clause2.put("riskLevel", "HIGH");
        clause2.put("lawReference", "个人信息保护法 第 23 条");
        clause2.put("suggestion", "建议明确说明共享的第三方类型、共享目的和信息类型。");
        clauseResults.add(clause2);
        
        // 条款 3
        Map<String, Object> clause3 = new HashMap<>();
        clause3.put("clauseNo", 3);
        clause3.put("clauseText", "您有权查询、更正您的个人信息。");
        clause3.put("modelLabel", "用户权利说明不足");
        clause3.put("modelScore", 0.78);
        clause3.put("ruleHit", "命中规则：用户权利描述不完整");
        clause3.put("riskLevel", "MEDIUM");
        clause3.put("lawReference", "个人信息保护法 第 44 条");
        clause3.put("suggestion", "建议补充说明删除、撤回同意等权利及行使方式。");
        clauseResults.add(clause3);
        
        // 条款 4
        Map<String, Object> clause4 = new HashMap<>();
        clause4.put("clauseNo", 4);
        clause4.put("clauseText", "我们将采取合理的安全措施保护您的信息。");
        clause4.put("modelLabel", "安全措施描述模糊");
        clause4.put("modelScore", 0.71);
        clause4.put("ruleHit", "命中规则：安全措施描述过于笼统");
        clause4.put("riskLevel", "LOW");
        clause4.put("lawReference", "个人信息保护法 第 51 条");
        clause4.put("suggestion", "建议具体说明采取的技术和管理措施。");
        clauseResults.add(clause4);
        
        // 条款 5
        Map<String, Object> clause5 = new HashMap<>();
        clause5.put("clauseNo", 5);
        clause5.put("clauseText", "本政策自发布之日起生效。");
        clause5.put("modelLabel", "生效日期明确");
        clause5.put("modelScore", 0.95);
        clause5.put("ruleHit", "无");
        clause5.put("riskLevel", "LOW");
        clause5.put("lawReference", "-");
        clause5.put("suggestion", "该条款符合规范要求。");
        clauseResults.add(clause5);
        
        result.put("clauseResults", clauseResults);
        return result;
    }
}
