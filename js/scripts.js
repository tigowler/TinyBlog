/*!
 * Start Bootstrap - Clean Blog v6.0.7 (https://startbootstrap.com/theme/clean-blog)
 * Copyright 2013-2021 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
 */
// for main Navigation when scroll
window.addEventListener("DOMContentLoaded", () => {
  let scrollPos = 0;
  const mainNav = document.getElementById("mainNav");
  const headerHeight = mainNav.clientHeight;
  window.addEventListener("scroll", function () {
    const currentTop = document.body.getBoundingClientRect().top * -1;
    if (currentTop < scrollPos) {
      // Scrolling Up
      if (currentTop > 0 && mainNav.classList.contains("is-fixed")) {
        mainNav.classList.add("is-visible");
      } else {
        console.log(123);
        mainNav.classList.remove("is-visible", "is-fixed");
      }
    } else {
      // Scrolling Down
      mainNav.classList.remove(["is-visible"]);
      if (
        currentTop > headerHeight &&
        !mainNav.classList.contains("is-fixed")
      ) {
        mainNav.classList.add("is-fixed");
      }
    }
    scrollPos = currentTop;
  });
});

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
