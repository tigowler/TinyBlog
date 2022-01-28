// for inner nav dropdown
const categoryDropDown = document.querySelector("#navbarDropdown__category");
const sortDropDown = document.querySelector("#navbarDropdown__sort");
const categoryDropDownItems = document.querySelector(
  "#dropdown-menu__category"
);
const sortDropDownItems = document.querySelector("#dropdown-menu__sort");

categoryDropDownItems.addEventListener("click", (event) => {
  if (event.target.innerHTML == "none") {
    categoryDropDown.innerHTML = "Category";
  } else {
    categoryDropDown.innerHTML = `Category: ${event.target.innerHTML}`;
  }
});

sortDropDownItems.addEventListener("click", (event) => {
  if (event.target.innerHTML == "none") {
    sortDropDown.innerHTML = "Sort by";
  } else {
    sortDropDown.innerHTML = `Sort by: ${event.target.innerHTML}`;
  }
});
