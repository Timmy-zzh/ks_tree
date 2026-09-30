# 栈间笔记 · 个人博客

一个无需构建工具的静态个人博客，使用纯 HTML、CSS 和 JavaScript 开发，文章以 Markdown 文件维护，可直接部署到 GitHub Pages 或 EdgeOne Pages。

## 本地预览

Markdown 文章通过 `fetch` 读取，因此不要直接双击 `index.html`，请在本目录启动静态服务器：

```bash
python3 -m http.server 8000
```

然后访问 `http://localhost:8000`。

## 内容管理

站点信息、作者资料和文章索引都在 `js/config.js` 中维护。新增文章时：

1. 在 `posts/` 中新建一个 Markdown 文件，例如 `my-new-post.md`。
2. 在 `js/config.js` 的 `posts` 数组中添加同名 `slug` 的文章配置。
3. 设置 `published: true`，文章就会出现在首页。

主要目录：

```text
ai_blog/
├── index.html          # 单页入口
├── about.md            # 关于页面内容
├── css/style.css       # 全部视觉与响应式样式
├── js/config.js        # 网站与文章配置
├── js/app.js           # 路由、搜索与 Markdown 渲染
└── posts/*.md          # 文章正文
```

## 个性化

- 在 `js/config.js` 修改博客名称、首页文案、作者信息和社交链接。
- 在 `css/style.css` 顶部的 CSS 变量中修改主色、背景色和字体。
- 替换 `index.html` 中的 description、favicon 和页面标题以改善 SEO。

## 部署

### GitHub Pages

将目录内容推送至 GitHub 仓库，在仓库的 **Settings → Pages** 中选择从目标分支部署。所有路由使用 URL Hash，因此项目站点部署在子目录时也能正常访问。

### EdgeOne Pages

导入 Git 仓库后，将本目录设为根目录。此项目没有构建步骤，输出目录保持为当前目录即可。

## 主要功能

- 文章列表、Markdown 文章详情与关于页面
- 标题、摘要、分类和标签全文搜索
- 分类筛选、深浅色主题、阅读进度和代码复制
- 桌面端、平板与手机响应式布局
- CDN 不可用时的基础 Markdown 降级渲染
