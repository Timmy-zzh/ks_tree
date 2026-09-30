const todoForm = document.querySelector("#todoForm");
const todoInput = document.querySelector("#todoInput");
const addButton = document.querySelector("#addButton");
const todoList = document.querySelector("#todoList");
const storageKey = "todo-list-items";

let todos = loadTodos();

function loadTodos() {
  try {
    const savedTodos = JSON.parse(localStorage.getItem(storageKey));

    if (!Array.isArray(savedTodos)) {
      return [];
    }

    return savedTodos
      .filter(function (todo) {
        return todo && typeof todo.text === "string" && todo.text.trim() !== "";
      })
      .map(function (todo, index) {
        return {
          id: todo.id || `${Date.now()}-${index}`,
          text: todo.text.trim(),
          completed: todo.completed === true
        };
      });
  } catch (error) {
    return [];
  }
}

function saveTodos() {
  try {
    localStorage.setItem(storageKey, JSON.stringify(todos));
  } catch (error) {
    // 浏览器禁用存储时，页面仍然可以正常使用。
  }
}

function updateAddButton() {
  addButton.disabled = todoInput.value.trim() === "";
}

function createTodoItem(todo) {
  const listItem = document.createElement("li");
  listItem.classList.toggle("completed", todo.completed);

  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.className = "todo-checkbox";
  checkbox.checked = todo.completed;
  checkbox.setAttribute("aria-label", "标记为已完成");

  const text = document.createElement("span");
  text.className = "todo-text";
  text.textContent = todo.text;

  const deleteButton = document.createElement("button");
  deleteButton.type = "button";
  deleteButton.className = "delete-button";
  deleteButton.textContent = "删除";
  deleteButton.setAttribute("aria-label", `删除待办事项：${todo.text}`);

  checkbox.addEventListener("change", function () {
    todo.completed = checkbox.checked;
    listItem.classList.toggle("completed", todo.completed);
    saveTodos();
  });

  deleteButton.addEventListener("click", function () {
    todos = todos.filter(function (item) {
      return item.id !== todo.id;
    });
    saveTodos();

    listItem.classList.add("removing");
    listItem.addEventListener("animationend", function () {
      listItem.remove();
    }, { once: true });
  });

  listItem.append(checkbox, text, deleteButton);
  todoList.appendChild(listItem);
}

function renderTodos() {
  todoList.innerHTML = "";
  todos.forEach(createTodoItem);
}

function addTodo(event) {
  event.preventDefault();

  const todoText = todoInput.value.trim();

  if (todoText === "") {
    todoInput.focus();
    return;
  }

  const newTodo = {
    id: `${Date.now()}-${Math.random()}`,
    text: todoText,
    completed: false
  };

  todos.push(newTodo);
  saveTodos();
  createTodoItem(newTodo);

  todoInput.value = "";
  updateAddButton();
  todoInput.focus();
}

todoForm.addEventListener("submit", addTodo);
todoInput.addEventListener("input", updateAddButton);

renderTodos();
updateAddButton();
