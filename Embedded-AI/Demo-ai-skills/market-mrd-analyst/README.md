# 市场 MRD 分析师

把产品想法、创业方向、功能概念或业务机会转换成 MRD 的分析型 skill。它适合创业/产品立项、市场机会判断、硬件新品方向评估、竞品与渠道现实梳理。

本目录就是可直接安装的 skill 包，根目录必须保留 `SKILL.md`。`README.md` 只给人类维护者使用；真正被 Codex 和 Claude Code 执行的是 `SKILL.md`、`references/` 和 `assets/`。

## 适用边界

- 产出 MRD、市场分析、机会点对齐、验证计划、red-team 结论。
- 不产出 PRD、硬件方案、代码、技术架构、API 或原型。
- 需要实时数据、价格、销量、认证、政策、平台规则时，必须联网查证并标注查证日期。
- 在 full MRD 前必须先完成 intake gate、证据实时性检查和机会点对齐；不能替用户决定核心机会点。

## 目录结构

```text
market-mrd-analyst/
  SKILL.md
  README.md
  agents/openai.yaml
  assets/
    *-template.md
  references/
    evidence-rules.md
    gated-workflow.md
    hardware-mrd-rules.md
    ...
```

## 输出文件

默认写入：

```text
docs/mrd/<slug>-*.md
```

常见文件包括 `<slug>-intake-gate.md`、`<slug>-evidence-freshness.md`、`<slug>-opportunity-alignment.md`、`<slug>-red-team.md` 和 `<slug>-mrd.md`。

## 安装到 Codex

个人 skill 路径：

```text
%USERPROFILE%\.codex\skills\market-mrd-analyst
```

PowerShell 安装或更新：

```powershell
$src = 'N:\AI\embedded_skill\market-mrd-analyst'
$dst = "$env:USERPROFILE\.codex\skills\market-mrd-analyst"
New-Item -ItemType Directory -Force -Path (Split-Path $dst) | Out-Null
Copy-Item -LiteralPath $src\* -Destination $dst -Recurse -Force
```

示例：

```text
$market-mrd-analyst 我想做一款低压 FOC 控制板，面向中国开发者教育实验室，创业立项，授权联网查证。先做 intake gate 和机会点讨论，不要直接输出 full MRD。
```

## 安装到 Claude Code

个人 skill 路径：

```text
%USERPROFILE%\.claude\skills\market-mrd-analyst
```

也可以放到项目内：

```text
<project-root>\.claude\skills\market-mrd-analyst
```

PowerShell 个人安装或更新：

```powershell
$src = 'N:\AI\embedded_skill\market-mrd-analyst'
$dst = "$env:USERPROFILE\.claude\skills\market-mrd-analyst"
New-Item -ItemType Directory -Force -Path (Split-Path $dst) | Out-Null
Copy-Item -LiteralPath $src\* -Destination $dst -Recurse -Force
```

示例：

```text
使用 $market-mrd-analyst 评估这个面向中国市场的产品想法。先执行 intake gate、证据实时性检查、机会点对齐和 red-team，再撰写最终 MRD。
```

## 维护检查

```powershell
$env:PYTHONUTF8='1'
python C:\Users\kemp\.codex\skills\.system\skill-creator\scripts\quick_validate.py `
  N:\AI\embedded_skill\market-mrd-analyst
```

检查要点：

- `SKILL.md` 保持精简，只放执行规则。
- `references/` 放详细方法、证据规则、硬件 MRD 规则。
- `assets/` 放输出模板。
- 修改后同步到 `.codex\skills` 和 `.claude\skills`。

## 故障排查

- skill 未触发：确认目标目录下有 `SKILL.md`，并重启 Codex 或 Claude Code 会话。
- MRD 直接输出、没有多轮确认：检查 `SKILL.md` 中 full MRD 前的 gate 规则是否仍在。
- 数据过旧：检查是否生成 `<slug>-evidence-freshness.md`，且易变证据是否写了查证日期。
