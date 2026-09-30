window.BLOG_CONFIG = {
  site: {
    title: "栈间笔记",
    eyebrow: "A PERSONAL ENGINEERING JOURNAL",
    headline: "在代码与现实之间，\n记录那些值得复用的思考。",
    description:
      "关于嵌入式系统、边缘 AI 与工程实践。这里没有速成答案，只有经过验证的方法、踩过的坑，以及仍在生长的问题。",
    footerText: "写给持续构建、持续好奇的人。"
  },
  author: {
    name: "Lin",
    initials: "L",
    role: "嵌入式软件工程师 / AI 探索者",
    bio: "关注软硬件交界处的问题，也关心技术如何变成真正好用的产品。",
    location: "Shenzhen, China",
    available: true,
    links: [
      { label: "GitHub", url: "https://github.com/" },
      { label: "Email", url: "mailto:hello@example.com" }
    ]
  },
  categories: ["全部", "嵌入式", "边缘 AI", "工程方法", "随笔"],
  posts: [
    {
      slug: "edge-ai-from-demo-to-product",
      title: "边缘 AI：从能跑的 Demo 到可靠的产品",
      date: "2026-09-18",
      category: "边缘 AI",
      tags: ["Edge AI", "部署", "性能优化"],
      excerpt:
        "模型跑起来只是起点。功耗、内存、时延和异常恢复，才是边缘 AI 真正进入产品时要面对的考题。",
      featured: true,
      published: true
    },
    {
      slug: "debugging-embedded-systems",
      title: "嵌入式调试的本质：缩小未知的边界",
      date: "2026-08-26",
      category: "工程方法",
      tags: ["调试", "方法论", "日志"],
      excerpt:
        "一个可重复的调试流程，远比灵光一现更可靠。本文分享如何从现象、证据到最小复现，一层层收紧问题范围。",
      featured: false,
      published: true
    },
    {
      slug: "rtos-task-design",
      title: "设计 RTOS 任务时，我会先问的 7 个问题",
      date: "2026-07-12",
      category: "嵌入式",
      tags: ["RTOS", "架构", "并发"],
      excerpt:
        "任务不是越多越好。职责、时序、通信和故障边界想清楚，系统才能在复杂度上升时依然可控。",
      featured: false,
      published: true
    },
    {
      slug: "build-a-second-brain",
      title: "把个人知识库当作一项长期工程",
      date: "2026-06-03",
      category: "随笔",
      tags: ["知识管理", "写作", "复盘"],
      excerpt:
        "收藏不是知识，写下来也不等于理解。一个有生命力的知识库，应该能在需要时帮助你做出更好的判断。",
      featured: false,
      published: true
    }
  ]
};
