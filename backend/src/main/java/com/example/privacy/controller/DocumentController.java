package com.example.privacy.controller;

import com.example.privacy.dto.DocumentUploadDTO;
import com.example.privacy.service.DocumentService;
import com.example.privacy.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/document")
@CrossOrigin
public class DocumentController {
    
    @Autowired
    private DocumentService documentService;
    
    @PostMapping("/upload")
    public Result<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        // 第一阶段：假文件上传
        Map<String, Object> data = new HashMap<>();
        data.put("id", System.currentTimeMillis());
        data.put("title", file.getOriginalFilename());
        data.put("fileName", file.getOriginalFilename());
        data.put("fileType", getFileType(file.getOriginalFilename()));
        data.put("rawText", "假文本内容...");
        return Result.success(data);
    }
    
    @PostMapping("/text")
    public Result<Map<String, Object>> submitText(@RequestBody DocumentUploadDTO dto) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", System.currentTimeMillis());
        data.put("title", dto.getTitle());
        data.put("rawText", dto.getText());
        return Result.success(data);
    }
    
    @GetMapping("/{id}")
    public Result<Map<String, Object>> getDocument(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("title", "某 APP 隐私政策");
        data.put("rawText", "我们可能收集您的个人信息用于相关服务...");
        return Result.success(data);
    }
    
    @GetMapping("/{id}/clauses")
    public Result<Map<String, Object>> getClauses(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("documentId", id);
        data.put("clauses", new String[]{
            "我们可能收集您的个人信息用于相关服务。",
            "我们可能会与第三方共享您的信息。",
            "您有权查询、更正您的个人信息。"
        });
        return Result.success(data);
    }
    
    @GetMapping("/list")
    public Result<Map<String, Object>> getDocumentList() {
        Map<String, Object> data = new HashMap<>();
        data.put("total", 3);
        data.put("list", new String[]{"文档 1", "文档 2", "文档 3"});
        return Result.success(data);
    }
    
    private String getFileType(String filename) {
        if (filename == null) return "unknown";
        if (filename.endsWith(".pdf")) return "PDF";
        if (filename.endsWith(".doc") || filename.endsWith(".docx")) return "WORD";
        if (filename.endsWith(".txt")) return "TXT";
        return "unknown";
    }
}
