# 隐私协议智能合规风险辅助审查系统

## 项目名称
Privacy Compliance Assistant System

## 技术栈
- **前端**: Vue 3 + Vite + Element Plus
- **后端**: Spring Boot 3.2 + H2 Database (开发用)
- **数据库**: H2 (开发) / MySQL (生产)

## 项目结构
```
privacy-compliance/
├── frontend/          # Vue 前端项目
├── backend/           # Spring Boot 后端项目
└── docs/             # 文档
```

## 快速启动

### 前端启动
```bash
cd frontend
npm install
npm run dev
```
访问：http://localhost:3000

### 后端启动
```bash
cd backend
mvn spring-boot:run
```
API 地址：http://localhost:8080/api
Swagger UI: http://localhost:8080/swagger-ui.html

## 默认账号
- 用户名：admin
- 密码：123456

## 第一阶段功能
- ✅ 用户登录/注册
- ✅ 文档上传（文件/文本）
- ✅ 假结果展示
- ✅ 历史记录查看
- ✅ 规则管理

## API 接口
详见 Swagger UI 或 API 文档
