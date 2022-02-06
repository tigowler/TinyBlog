const Editor = toastui.Editor;
const editor = new Editor({
  el: document.querySelector("#editor"),
  height: "500px",
  initialEditType: "markdown",
  previewStyle: "vertical",
});

const form = document.querySelector("#form");
const btn = document.querySelector("#submitBtn");
const area = document.querySelector("#exampleFormControlTextarea1");
btn.addEventListener("click", () => {
  area.value = editor.getHTML();
  form.submit();
});

const category_dropdown = document.querySelector("#dropdown-category");
const year_dropdown = document.querySelector("#dropdown-year");
const categoryDropDown = document.querySelector("#navbarDropdown__category");
const sortDropDown = document.querySelector("#navbarDropdown__sort");
const categoryDropDownItems = document.querySelector(
  "#dropdown-menu__category"
);
const sortDropDownItems = document.querySelector("#dropdown-menu__sort");

document.addEventListener("DOMContentLoaded", (event) => {
  editor.setHTML(area.value, false);
  categoryDropDown.innerHTML = `Category: ${category_dropdown.value}`;
  sortDropDown.innerHTML = `Sort by: ${year_dropdown.value}`;
});

categoryDropDownItems.addEventListener("click", (event) => {
  category_dropdown.value = event.target.innerHTML;
  if (event.target.innerHTML == "none") {
    categoryDropDown.innerHTML = "Category";
  } else {
    categoryDropDown.innerHTML = `Category: ${event.target.innerHTML}`;
  }
});

sortDropDownItems.addEventListener("click", (event) => {
  year_dropdown.value = event.target.innerHTML;
  if (event.target.innerHTML == "none") {
    sortDropDown.innerHTML = "Sort by";
  } else {
    sortDropDown.innerHTML = `Sort by: ${event.target.innerHTML}`;
  }
});
