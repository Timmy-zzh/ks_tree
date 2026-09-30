(function () {
  "use strict";

  var config = window.BLOG_CONFIG;
  var app = document.getElementById("app");
  var searchInput = document.getElementById("searchInput");
  var state = { query: "", category: "全部" };

  function escapeHtml(value) {
    var node = document.createElement("div");
    node.textContent = value == null ? "" : String(value);
    return node.innerHTML;
  }

  function formatDate(value) {
    return new Intl.DateTimeFormat("zh-CN", {
      year: "numeric",
      month: "short",
      day: "numeric"
    }).format(new Date(value + "T00:00:00"));
  }

  function getPublishedPosts() {
    return config.posts
      .filter(function (post) { return post.published !== false; })
      .sort(function (a, b) { return b.date.localeCompare(a.date); });
  }

  function getFilteredPosts() {
    var query = state.query.trim().toLowerCase();
    return getPublishedPosts().filter(function (post) {
      var matchesCategory = state.category === "全部" || post.category === state.category;
      var haystack = [post.title, post.excerpt, post.category].concat(post.tags || []).join(" ").toLowerCase();
      return matchesCategory && (!query || haystack.indexOf(query) !== -1);
    });
  }

  function renderHome() {
    var posts = getFilteredPosts();
    var featured = getPublishedPosts().filter(function (post) { return post.featured; })[0];
    var chips = config.categories.map(function (category) {
      return '<button class="filter-chip' + (state.category === category ? ' is-active' : '') +
        '" type="button" data-category="' + escapeHtml(category) + '">' + escapeHtml(category) + '</button>';
    }).join("");

    var cards = posts.map(function (post, index) {
      return '<article class="post-card reveal" style="--delay:' + Math.min(index * 60, 240) + 'ms">' +
        '<a class="post-card-link" href="#/article/' + encodeURIComponent(post.slug) + '" aria-label="阅读：' + escapeHtml(post.title) + '"></a>' +
        '<div class="post-index">' + String(index + 1).padStart(2, "0") + '</div>' +
        '<div class="post-body">' +
          '<div class="post-meta"><span>' + escapeHtml(post.category) + '</span><time datetime="' + post.date + '">' + formatDate(post.date) + '</time></div>' +
          '<h3>' + escapeHtml(post.title) + '</h3>' +
          '<p>' + escapeHtml(post.excerpt) + '</p>' +
          '<div class="post-tags">' + (post.tags || []).map(function (tag) { return '<span># ' + escapeHtml(tag) + '</span>'; }).join("") + '</div>' +
        '</div>' +
        '<div class="post-arrow" aria-hidden="true">↗</div>' +
      '</article>';
    }).join("");

    app.innerHTML =
      '<section class="hero">' +
        '<div class="hero-grid container">' +
          '<div class="hero-copy reveal">' +
            '<p class="eyebrow"><span></span>' + escapeHtml(config.site.eyebrow) + '</p>' +
            '<h1>' + escapeHtml(config.site.headline).replace(/\n/g, "<br>") + '</h1>' +
            '<p class="hero-description">' + escapeHtml(config.site.description) + '</p>' +
            '<div class="hero-actions">' +
              '<a class="primary-button" href="#/article/' + encodeURIComponent(featured.slug) + '">阅读最新文章 <span>↗</span></a>' +
              '<a class="text-link" href="#/about">认识作者 <span>→</span></a>' +
            '</div>' +
          '</div>' +
          '<div class="signal-card reveal" aria-hidden="true">' +
            '<div class="signal-top"><span>FIELD NOTES</span><span class="signal-status"><i></i> ONLINE</span></div>' +
            '<div class="signal-graphic">' +
              '<div class="orbit orbit-one"></div><div class="orbit orbit-two"></div>' +
              '<div class="signal-core"><span>01</span><small>BUILD</small></div>' +
              '<span class="coord coord-a">22.5431° N</span><span class="coord coord-b">114.0579° E</span>' +
            '</div>' +
            '<div class="signal-bottom"><span>LEARN</span><span>DOCUMENT</span><span>SHIP</span></div>' +
          '</div>' +
        '</div>' +
      '</section>' +
      '<section class="content-section container">' +
        '<div class="feed-column">' +
          '<div class="section-heading">' +
            '<div><p class="section-kicker">NOTES / ' + String(posts.length).padStart(2, "0") + '</p><h2>最近更新</h2></div>' +
            '<div class="category-filter" aria-label="文章分类">' + chips + '</div>' +
          '</div>' +
          '<div class="post-list">' + (cards || renderEmptyState()) + '</div>' +
        '</div>' +
        renderSidebar() +
      '</section>';

    document.title = config.site.title;
    bindCategoryButtons();
    updateActiveNav("home");
    requestAnimationFrame(revealElements);
  }

  function renderSidebar() {
    var links = config.author.links.map(function (link) {
      return '<a href="' + escapeHtml(link.url) + '" target="_blank" rel="noreferrer">' + escapeHtml(link.label) + '<span>↗</span></a>';
    }).join("");
    return '<aside class="sidebar">' +
      '<div class="profile-card reveal">' +
        '<div class="profile-head"><div class="avatar">' + escapeHtml(config.author.initials) + '<span></span></div>' +
          '<div><h3>' + escapeHtml(config.author.name) + '</h3><p>' + escapeHtml(config.author.role) + '</p></div></div>' +
        '<p class="profile-bio">' + escapeHtml(config.author.bio) + '</p>' +
        '<div class="profile-location"><span>⌖</span>' + escapeHtml(config.author.location) + '</div>' +
        '<div class="profile-links">' + links + '</div>' +
      '</div>' +
      '<div class="now-card reveal">' +
        '<p class="card-label">NOW</p><h3>正在关注</h3>' +
        '<ul><li><span>01</span>端侧模型的量化与部署</li><li><span>02</span>可观测的嵌入式系统</li><li><span>03</span>AI 时代的个人工作流</li></ul>' +
      '</div>' +
      '<div class="subscribe-card reveal"><p class="card-label">STAY CURIOUS</p><p>新文章通过 GitHub 更新，保持连接，偶尔回来看看。</p><a href="' + escapeHtml(config.author.links[0].url) + '" target="_blank" rel="noreferrer">在 GitHub 关注 <span>→</span></a></div>' +
    '</aside>';
  }

  function renderEmptyState() {
    return '<div class="empty-state"><div>∅</div><h3>没有找到相关笔记</h3><p>换一个关键词或分类试试。</p><button type="button" id="clearFilters">清除筛选</button></div>';
  }

  function renderArticle(slug) {
    var post = getPublishedPosts().filter(function (item) { return item.slug === slug; })[0];
    if (!post) return renderNotFound();
    showLoading();
    fetch('posts/' + encodeURIComponent(slug) + '.md')
      .then(function (response) {
        if (!response.ok) throw new Error("文章读取失败");
        return response.text();
      })
      .then(function (markdown) {
        var minutes = estimateReadingTime(markdown);
        app.innerHTML = '<div class="article-shell container">' +
          '<a class="back-link" href="#/"><span>←</span> 返回文章列表</a>' +
          '<article class="article-paper reveal">' +
            '<header class="article-header">' +
              '<p class="article-category">' + escapeHtml(post.category) + '</p>' +
              '<h1>' + escapeHtml(post.title) + '</h1>' +
              '<p class="article-lead">' + escapeHtml(post.excerpt) + '</p>' +
              '<div class="article-byline"><div class="byline-avatar">' + escapeHtml(config.author.initials) + '</div>' +
                '<div><strong>' + escapeHtml(config.author.name) + '</strong><span><time datetime="' + post.date + '">' + formatDate(post.date) + '</time> · ' + minutes + ' 分钟阅读</span></div>' +
                '<div class="article-tag-list">' + post.tags.map(function (tag) { return '<span>#' + escapeHtml(tag) + '</span>'; }).join("") + '</div>' +
              '</div>' +
            '</header>' +
            '<div class="markdown-body">' + parseMarkdown(stripLeadingTitle(markdown)) + '</div>' +
            '<footer class="article-end"><span>END OF NOTE</span><a href="#/">继续阅读 <b>→</b></a></footer>' +
          '</article>' +
        '</div>';
        document.title = post.title + ' — ' + config.site.title;
        updateActiveNav("");
        enhanceCodeBlocks();
        requestAnimationFrame(revealElements);
        window.scrollTo(0, 0);
      })
      .catch(function () { renderNotFound("文章文件未找到"); });
  }

  function renderAbout() {
    showLoading();
    fetch("about.md")
      .then(function (response) {
        if (!response.ok) throw new Error("页面读取失败");
        return response.text();
      })
      .then(function (markdown) {
        app.innerHTML = '<div class="about-shell container">' +
          '<div class="about-aside reveal"><p class="eyebrow"><span></span>ABOUT ME</p>' +
            '<div class="about-monogram">' + escapeHtml(config.author.initials) + '</div>' +
            '<p>Based in<br><strong>' + escapeHtml(config.author.location) + '</strong></p>' +
            '<div class="availability"><i></i>' + (config.author.available ? '可交流新想法' : '专注工作中') + '</div></div>' +
          '<article class="about-content reveal"><a class="back-link" href="#/">← 返回首页</a><div class="markdown-body">' + parseMarkdown(markdown) + '</div></article>' +
        '</div>';
        document.title = '关于 — ' + config.site.title;
        updateActiveNav("about");
        requestAnimationFrame(revealElements);
        window.scrollTo(0, 0);
      })
      .catch(function () { renderNotFound("关于页面未找到"); });
  }

  function parseMarkdown(markdown) {
    if (window.marked) {
      window.marked.use({ gfm: true, breaks: false });
      return window.marked.parse(markdown);
    }
    return basicMarkdown(markdown);
  }

  function stripLeadingTitle(markdown) {
    return markdown.replace(/^#\s+[^\n]+\n+/, "");
  }

  function basicMarkdown(markdown) {
    var blocks = escapeHtml(markdown).split(/\n{2,}/);
    return blocks.map(function (block) {
      if (/^### /.test(block)) return '<h3>' + inlineMarkdown(block.slice(4)) + '</h3>';
      if (/^## /.test(block)) return '<h2>' + inlineMarkdown(block.slice(3)) + '</h2>';
      if (/^# /.test(block)) return '<h1>' + inlineMarkdown(block.slice(2)) + '</h1>';
      if (/^&gt; /.test(block)) return '<blockquote><p>' + inlineMarkdown(block.replace(/^&gt; /gm, "")) + '</p></blockquote>';
      if (/^```/.test(block)) {
        var code = block.replace(/^```[^\n]*\n?/, "").replace(/```$/, "");
        return '<pre><code>' + code + '</code></pre>';
      }
      if (/^- /.test(block)) return '<ul>' + block.split("\n").map(function (line) { return '<li>' + inlineMarkdown(line.slice(2)) + '</li>'; }).join("") + '</ul>';
      return '<p>' + inlineMarkdown(block).replace(/\n/g, "<br>") + '</p>';
    }).join("");
  }

  function inlineMarkdown(text) {
    return text
      .replace(/\*\*(.+?)\*\*/g, "<strong>$1</strong>")
      .replace(/`([^`]+)`/g, "<code>$1</code>")
      .replace(/\[([^\]]+)\]\(([^)]+)\)/g, '<a href="$2">$1</a>');
  }

  function enhanceCodeBlocks() {
    if (window.hljs) window.hljs.highlightAll();
    document.querySelectorAll("pre").forEach(function (pre) {
      var button = document.createElement("button");
      button.type = "button";
      button.className = "copy-code";
      button.textContent = "COPY";
      button.addEventListener("click", function () {
        navigator.clipboard.writeText(pre.innerText).then(function () {
          button.textContent = "COPIED";
          setTimeout(function () { button.textContent = "COPY"; }, 1600);
        });
      });
      pre.appendChild(button);
    });
  }

  function estimateReadingTime(text) {
    var chinese = (text.match(/[\u4e00-\u9fff]/g) || []).length;
    var words = text.replace(/[\u4e00-\u9fff]/g, " ").trim().split(/\s+/).filter(Boolean).length;
    return Math.max(1, Math.ceil(chinese / 350 + words / 220));
  }

  function showLoading() {
    app.innerHTML = '<div class="loading-screen"><div class="loading-mark"><span></span><span></span><span></span></div><p>LOADING NOTE</p></div>';
  }

  function renderNotFound(message) {
    app.innerHTML = '<section class="not-found container"><p>ERROR / 404</p><h1>这页笔记不在这里。</h1><span>' + escapeHtml(message || "也许它被移动了，或者从未存在过。") + '</span><a class="primary-button" href="#/">回到首页 →</a></section>';
    document.title = '页面未找到 — ' + config.site.title;
    updateActiveNav("");
  }

  function bindCategoryButtons() {
    document.querySelectorAll("[data-category]").forEach(function (button) {
      button.addEventListener("click", function () {
        state.category = button.dataset.category;
        renderHome();
        document.querySelector(".content-section").scrollIntoView({ behavior: "smooth", block: "start" });
      });
    });
    var clear = document.getElementById("clearFilters");
    if (clear) clear.addEventListener("click", function () {
      state.query = "";
      state.category = "全部";
      searchInput.value = "";
      renderHome();
    });
  }

  function updateActiveNav(route) {
    document.querySelectorAll("[data-route]").forEach(function (link) {
      link.classList.toggle("is-active", link.dataset.route === route);
    });
  }

  function revealElements() {
    document.querySelectorAll(".reveal").forEach(function (element) { element.classList.add("is-visible"); });
  }

  function route() {
    closeMenu();
    var hash = window.location.hash || "#/";
    var articleMatch = hash.match(/^#\/article\/([^?]+)/);
    if (articleMatch) return renderArticle(decodeURIComponent(articleMatch[1]));
    if (hash === "#/about") return renderAbout();
    renderHome();
    window.scrollTo(0, 0);
  }

  function closeMenu() {
    document.getElementById("mobileNav").classList.remove("is-open");
    document.getElementById("menuToggle").setAttribute("aria-expanded", "false");
  }

  function initTheme() {
    var saved = localStorage.getItem("blog-theme");
    if (saved) document.documentElement.dataset.theme = saved;
    document.getElementById("themeToggle").addEventListener("click", function () {
      var isDark = document.documentElement.dataset.theme === "dark" ||
        (!document.documentElement.dataset.theme && window.matchMedia("(prefers-color-scheme: dark)").matches);
      document.documentElement.dataset.theme = isDark ? "light" : "dark";
      localStorage.setItem("blog-theme", document.documentElement.dataset.theme);
    });
  }

  function init() {
    document.getElementById("brandName").textContent = config.site.title;
    document.getElementById("footerBrand").textContent = config.site.title;
    document.getElementById("footerDescription").textContent = config.site.footerText;
    document.getElementById("footerAuthor").textContent = config.author.name;
    document.getElementById("currentYear").textContent = new Date().getFullYear();

    initTheme();
    searchInput.addEventListener("input", function () {
      state.query = searchInput.value;
      if (!/^#\/$/.test(window.location.hash || "#/")) window.location.hash = "#/";
      else renderHome();
    });
    document.addEventListener("keydown", function (event) {
      if (event.key === "/" && !/input|textarea/i.test(document.activeElement.tagName)) {
        event.preventDefault();
        searchInput.focus();
      }
      if (event.key === "Escape") searchInput.blur();
    });
    document.getElementById("menuToggle").addEventListener("click", function () {
      var nav = document.getElementById("mobileNav");
      var open = nav.classList.toggle("is-open");
      this.setAttribute("aria-expanded", String(open));
    });
    document.getElementById("backTop").addEventListener("click", function () { window.scrollTo({ top: 0, behavior: "smooth" }); });
    window.addEventListener("scroll", function () {
      var max = document.documentElement.scrollHeight - window.innerHeight;
      var percent = max > 0 ? (window.scrollY / max) * 100 : 0;
      document.getElementById("readingProgress").style.width = percent + "%";
      document.getElementById("backTop").classList.toggle("is-visible", window.scrollY > 500);
    }, { passive: true });
    window.addEventListener("hashchange", route);
    route();
  }

  init();
})();
