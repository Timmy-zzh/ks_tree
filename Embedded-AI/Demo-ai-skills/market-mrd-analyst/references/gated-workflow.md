# 闸门式 MRD 工作流

本文件把 MRD 从"一次性生成文档"改为"闸门式决策流程"。默认目标不是尽快写完整 MRD，而是判断是否值得写、能否写得可靠。

## 模式定义

### 1. `mrd-intake-gate`

目标：确认最小必要边界。没过关不写 full MRD。

必须确认：

- 产品边界：形态、包含物、不包含物。
- 目标地区。
- 目标场景。
- beachhead 起步人群。
- 决策用途：立项、融资、内部规划、课程/比赛等。
- 团队/资源约束：预算、供应链、渠道、已有资料。
- 是否授权联网查证。

输出：

- `<slug>-process-log.md`
- `<slug>-clarity-score.md`
- `<slug>-intake-gate.md`

通过条件：

- 产品边界明确。
- 起步人群唯一。
- 地区和场景明确。
- 决策用途明确。
- 对无法确认的项，用户明确选择"记录缺口并继续到 discovery"，而不是直接 full MRD。

未通过时：只输出问题清单和下一步选项，不进入 market brief / full MRD。

### 2. `mrd-discovery`

目标：识别和排序关键假设，而不是写大文档。

必须覆盖：

- Value：用户是否真痛。
- Usability：用户能否用。
- Feasibility：团队能否做。
- Viability：能否赚钱或形成可持续业务。
- GTM（市场进入）：能否触达、转化、成交。
- Timing：为什么现在。
- Regulation：合规/准入。
- Team：团队资源与能力。

输出：

- `<slug>-assumptions.md`
- `<slug>-validation.md`

通过条件：

- 列出 3-5 个 load-bearing assumptions。
- 每个假设都有最低成本验证动作。
- 每个验证动作都有成功/调整/停止阈值；无证据时写"需用户确认阈值"。

### 3. `mrd-market-brief`

目标：只做市场口径、竞品、替代方案和渠道现实。

必须覆盖：

- 上位参考市场 / 目标 TAM / SAM / SOM 分开。
- 直接竞品、间接竞品、替代方案、不做任何事。
- 硬件/消费电子必须覆盖电商现实，或记录缺口。
- 竞品缺口必须落到可测差异化命题。

输出：

- `<slug>-market-sizing.md`
- `<slug>-competitors.md`

通过条件：

- 没有把上位市场当目标 TAM。
- 没有只用官网功能替代真实竞品现实。
- 差异化至少有 1 个可测命题。

### 4. `mrd-evidence-freshness`

目标：确认 MRD 使用的数据是否足够新，尤其是会影响当下立项的易变信息。

必须覆盖：

- 竞品价格、促销、SKU 组合和电商实际售价。
- 电商评价、销量/榜单、差评主题、退货/售后线索。
- 渠道政策、平台准入、达人报价、投放成本。
- 法规、认证、标准、平台规则。
- 行业报告和宏观数据的资料日期、口径和是否过期。

输出：

- `<slug>-evidence-freshness.md`

通过条件：

- 每条易变证据都有来源 URL、查证日期、资料日期或"无日期"标记。
- 价格/电商/渠道数据超过 90 天或无日期时，不能支撑当前定价/GTM 判断。
- 行业报告超过 12 个月或口径不匹配时，只能作为历史/上位参考。
- 无法查证的关键证据已在 MRD 顶部列为决策缺口。

未通过时：停止 full MRD，询问用户选择联网补查、提供资料、记录证据不足并继续草案、或取消。

### 5. `mrd-opportunity-alignment`

目标：在 full MRD 前让用户确认核心产品机会点，避免 AI 替用户选择定位。

步骤：

1. 基于 intake、discovery 和 market brief 提出 3-5 个候选机会点。
2. 每个机会点必须写明目标痛点、竞品缺口、MVP 验证命题、最大风险和放弃代价。
3. 向用户确认首版押注 1 个机会点。
4. 记录用户明确放弃的机会点。
5. 确认 MVP 只验证什么、不验证什么。

输出：

- `<slug>-opportunity-alignment.md`

通过条件：

- 用户明确选择 1 个首版机会点。
- 用户确认至少 1 个不做/放弃项。
- MVP 验证命题由用户确认，而不是 AI 自动选定。

未通过时：不能进入 `mrd-full`。只输出候选机会点和待确认问题。

### 6. `mrd-red-team`

目标：攻击计划的承重假设。full MRD 前强烈建议执行；硬件/高风险产品必须执行。

步骤：

1. 抽取所有关键声明。
2. 只保留 load-bearing claims。
3. 每个声明先 steelman，再 attack。
4. 写成"Fails if ___"。
5. 按 impact × likelihood × cheapness-to-test 排序。
6. 给出 evidence to get this week、kill criterion、cheapest test。

输出：

- `<slug>-red-team.md`

通过条件：

- 前 3-5 个 kill assumptions 都有可执行测试。
- 如果出现 No-Go 倾向，必须让用户确认是否接受 No-Go、补证据或重审。

### 7. `mrd-full`

目标：只有前面闸门信息足够时才写完整 MRD。

前置条件：

- intake gate 已通过，或用户明确要求带缺口继续。
- discovery 的 load-bearing assumptions 已列出。
- market brief 至少完成上位市场/竞品/替代方案证据或记录缺口。
- evidence freshness 已完成，且关键易变证据没有被过期材料支撑。
- opportunity alignment 已完成，用户确认核心机会点和 MVP 验证命题。
- red-team 已完成，或用户明确选择跳过并记录。

输出：

- `<slug>-feasibility.md`
- `<slug>-mrd.md`

## 默认路由

- 用户只说"做 MRD / 输出 MRD / 分析想法"：先进入 `mrd-intake-gate`，不要直接 full MRD。
- 用户说"完整 MRD"：仍先走 `mrd-intake-gate`；通过后询问是否继续 `mrd-discovery → mrd-market-brief → mrd-evidence-freshness → mrd-opportunity-alignment → mrd-red-team → mrd-full`。
- 用户说"不要问，直接出草案"：可以输出草案，但必须把缺口写在顶部，并不得填无源数字或替用户选边界。
- 用户明确指定旧模式（idea-scan / discovery-plan / market-sizing / competitive-brief / full-mrd）：兼容执行，但 full-mrd 仍受 intake gate 约束。

## Gate 回执格式

每个 gate 结束后只回执：

- 当前 gate。
- 通过 / 未通过 / 带缺口继续。
- 已确认的用户决定。
- 最大缺口。
- 建议下一步。
- 文件路径。

不要把完整文档贴回对话。
