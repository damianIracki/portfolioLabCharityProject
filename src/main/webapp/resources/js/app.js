document.addEventListener("DOMContentLoaded", function() {

  function summaryForm() {
    var quantity = document.forms['form1']['quantity'].value;
    var institution = document.forms['form1']['institution'].value;
    var street = document.forms['form1']['street'].value;
    var city = document.forms['form1']['city'].value;
    var zipCode = document.forms['form1']['zipCode'].value;
    var phoneNumber = document.forms['form1']['phoneNumber'].value;
    var pickUpDate = document.forms['form1']['pickUpDate'].value;
    var pickUpTime = document.forms['form1']['pickUpTime'].value;
    var pickUpComment = document.forms['form1']['pickUpComment'].value;

    var categories = document.querySelectorAll('input[name="categories"]');
    var checkedCategories = [];

    for(var i = 0; i < categories.length; i++){
      if(categories[i].checked){
        checkedCategories += categories[i].nextElementSibling.nextElementSibling.innerText + "; ";

      }
    }

    var placeForQuantity = document.querySelector('#placeForQuantity');
    placeForQuantity.innerText = quantity + " worki w kategoriach: " + checkedCategories ;

    var placeForInstitution = document.querySelector('#placeForInstitution');
    placeForInstitution.innerText = "Dla instytucji: " + institution;

    var placeForStreet = document.querySelector('#placeForStreet');
    placeForStreet.innerText = street;

    var placeForCity = document.querySelector('#placeForCity');
    placeForCity.innerText = city;

    var placeForZipCode = document.querySelector('#placeForZipCode');
    placeForZipCode.innerText = zipCode;

    var placeForStreet = document.querySelector('#placeForStreet');
    placeForStreet.innerText = street;

    var placeForPhoneNumber = document.querySelector('#placeForPhoneNumber');
    placeForPhoneNumber.innerText = phoneNumber;

    var placeForPickUpDate = document.querySelector('#placeForPickUpDate');
    placeForPickUpDate.innerText = pickUpDate;

    var placeForPickUpTime = document.querySelector('#placeForPickUpTime');
    placeForPickUpTime.innerText = pickUpTime;

    var placeForPickUpComment = document.querySelector('#placeForPickUpComment');
    placeForPickUpComment.innerText = pickUpComment;

    console.log(checkedCategories);
  }

  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      summaryForm();
    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
